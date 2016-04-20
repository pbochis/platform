package uno.cod.platform.server.core.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import uno.cod.platform.runtime.RuntimeClient;
import uno.cod.platform.server.core.domain.*;
import uno.cod.platform.server.core.repository.*;
import uno.cod.storage.PlatformStorage;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class SubmissionService {
    private final SubmissionRepository repository;
    private final ResultRepository resultRepository;
    private final TaskRepository taskRepository;
    private final TestRepository testRepository;
    private final RuntimeClient runtimeClient;
    private final LanguageRepository languageRepository;
    private final IClientPushConnection appClientConnection;
    private final PlatformStorage platformStorage;
    private final TaskResultService taskResultService;
    private final TestResultRepository testResultRepository;

    @Value("${coduno.storage.gcs.buckets.submissions}")
    private String bucket;

    @Autowired
    public SubmissionService(SubmissionRepository repository,
                             ResultRepository resultRepository,
                             TaskRepository taskRepository,
                             TestRepository testRepository,
                             PlatformStorage platformStorage,
                             LanguageRepository languageRepository,
                             RuntimeClient runtimeClient,
                             TaskResultService taskResultService,
                             TestResultRepository testResultRepository,
                             IClientPushConnection appClientConnection) {
        this.repository = repository;
        this.resultRepository = resultRepository;
        this.taskRepository = taskRepository;
        this.testRepository = testRepository;
        this.platformStorage = platformStorage;
        this.taskResultService = taskResultService;
        this.languageRepository = languageRepository;
        this.testResultRepository = testResultRepository;
        this.runtimeClient = runtimeClient;
        this.appClientConnection = appClientConnection;
    }

    public void submitToRuntime(SubmissionType type, UUID resultId, UUID taskId, MultipartFile[] files, String language) throws IOException {
        Result result = resultRepository.findOne(resultId);
        if (result == null) {
            throw new IllegalArgumentException("result.invalid");
        }

        Challenge challenge = result.getChallenge();
        if (challenge.getEndDate() != null && challenge.getEndDate().isBefore(ZonedDateTime.now())) {
            throw new AccessDeniedException("challenge.ended");
        }

        Task task = taskRepository.findOneWithTests(taskId);
        if (task == null) {
            throw new IllegalArgumentException("task.invalid");
        }

        TaskResult taskResult = taskResultService.findByTaskAndResult(taskId, resultId);

        Submission submission = new Submission();
        submission.setTaskResult(taskResult);
        submission.setSubmissionTime(ZonedDateTime.now());
        submission = repository.save(submission);
        if (SubmissionType.COMPILE_AND_RUN.equals(type)) {
            submission.setLanguage(languageRepository.findByTag(language));
            submission.setFileName(files[0].getOriginalFilename());
            platformStorage.upload(bucket, submission.filePath(), files[0].getInputStream(), files[0].getContentType());
        }

        boolean successful = true;
        switch (type) {
            case COMPILE_AND_RUN:
                List<Test> tests = testRepository.findByTaskIdOrderByIndex(taskId);
                for (Test test : tests) {
                    MultiValueMap<String, Object> form = createForm(test.getParams());
                    form.add("language", language);
                    form.add("files_gcs", submission.filePath());
                    successful = runAndSendResults(form, result.getUser().getId(), submission, test) && successful;
                }
                break;
            case VALIDATE_SOLUTION_FILE:
                for (MultipartFile file : files) {
                    UUID testId = UUID.fromString(file.getOriginalFilename());
                    Test test = testRepository.findOneWithRunner(testId);

                    MultiValueMap<String, Object> form = createForm(test.getParams());
                    form.add("files", new FileMessageResource(file.getBytes(), file.getOriginalFilename()));
                    form.add("validate", "true");

                    successful = runAndSendResults(form, result.getUser().getId(), submission, test) && successful;
                }
                successful = files.length == testRepository.findByTaskIdOrderByIndex(task.getId()).size() && successful;
                break;
        }

        if (!successful) {
            return;
        }

        submission.setSuccessful(true);
        repository.save(submission);
        taskResultService.finishTaskResult(taskResult, submission.getSubmissionTime(), true);
        this.appClientConnection.sendLevelCompleted(result.getUser().getId(), task.getId());
    }

    private MultiValueMap<String, Object> createForm(Map<String, String> params) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        addParams(form, params);

        return form;
    }

    private void addParams(MultiValueMap<String, Object> form, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return;
        }

        for (Map.Entry<String, String> param : params.entrySet()) {
            form.add(param.getKey(), param.getValue());
        }
    }

    private boolean runAndSendResults(MultiValueMap<String, Object> form, UUID userId, Submission submission, Test test) throws IOException {
        JsonNode obj = runtimeClient.postToRuntime(test.getRunner().getPath(), form);
        ((ObjectNode) obj).put("test", test.getId().toString());

        if (obj.get("failure") != null) {
            appClientConnection.send(userId, obj.toString());
            return false;
        }

        boolean successful = obj.get("successful") != null && obj.get("successful").booleanValue();

        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setSubmission(submission);
        testResult.setSuccessful(successful);
        testResultRepository.save(testResult);
        // TODO: Think whether we should save the test
        // results in the database or as a JSON file in GCS.
        appClientConnection.send(userId, obj.toString());
        return successful;
    }

    public void run(User user, UUID taskId, MultipartFile file, String language) throws IOException {
        Task task = taskRepository.findOneWithRunner(taskId);
        if (task == null) {
            throw new IllegalArgumentException("task.invalid");
        }

        MultiValueMap<String, Object> form = createForm(task.getParams());
        form.add("language", language);
        form.add("files", new FileMessageResource(file.getBytes(), file.getOriginalFilename()));

        appClientConnection.send(user.getId(), runtimeClient.postToRuntime(task.getRunner().getPath(), form).toString());
    }

    public enum SubmissionType {
        COMPILE_AND_RUN,
        VALIDATE_SOLUTION_FILE
    }
}
