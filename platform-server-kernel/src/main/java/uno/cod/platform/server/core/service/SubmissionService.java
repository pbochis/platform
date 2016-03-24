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
import uno.cod.platform.server.core.repository.ResultRepository;
import uno.cod.platform.server.core.repository.SubmissionRepository;
import uno.cod.platform.server.core.repository.TaskRepository;
import uno.cod.platform.server.core.repository.TestRepository;
import uno.cod.storage.PlatformStorage;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.ZonedDateTime;
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
    private final IClientPushConnection appClientConnection;
    private final PlatformStorage platformStorage;

    @Value("${coduno.storage.gcs.buckets.submissions}")
    private String bucket;

    @Autowired
    public SubmissionService(SubmissionRepository repository,
                             ResultRepository resultRepository,
                             TaskRepository taskRepository,
                             TestRepository testRepository,
                             PlatformStorage platformStorage,
                             RuntimeClient runtimeClient,
                             IClientPushConnection appClientConnection) {
        this.repository = repository;
        this.resultRepository = resultRepository;
        this.taskRepository = taskRepository;
        this.testRepository = testRepository;
        this.platformStorage = platformStorage;
        this.runtimeClient = runtimeClient;
        this.appClientConnection = appClientConnection;
    }

    public void create(User user, UUID resultId, UUID taskId, MultipartFile file, String language) throws IOException {
        Result result = resultRepository.findOne(resultId);
        if (result == null) {
            throw new IllegalArgumentException("result.invalid");
        }

        Challenge challenge = result.getChallenge();
        if (challenge.getEndDate() != null && challenge.getEndDate().isBefore(ZonedDateTime.now())){
            throw new AccessDeniedException("challenge.ended");
        }

        Task task = taskRepository.findOneWithTests(taskId);
        if (task == null) {
            throw new IllegalArgumentException("task.invalid");
        }

        Submission submission = new Submission();
        result.addSubmission(submission);
        task.addSubmission(submission);
        submission.setFileName(file.getOriginalFilename());
        submission = repository.save(submission);

        platformStorage.upload(bucket, submission.filePath(), file.getInputStream(), file.getContentType());

        repository.save(submission);

        // TODO update submission with results
        for (Test test : task.getTests()) {
            runTest(user.getId(), submission.filePath(), language, test);
        }
    }

    public void run(User user, UUID taskId, MultipartFile file, String language) throws IOException {
        Task task = taskRepository.findOneWithRunner(taskId);
        if (task == null) {
            throw new IllegalArgumentException("task.invalid");
        }
        // TODO update submission with results
        run(user.getId(), file, language, task.getRunner());
    }

    public boolean testOutput(UUID testId, MultipartFile file) throws IOException {
        Test test = testRepository.findOneWithRunner(testId);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("files", new FileMessageResource(file.getBytes(), file.getOriginalFilename()));
        form.add("output_test", "true");
        // TODO quickfix for runtime fault
        form.add("language", "py");
        Map<String, String> params = test.getParams();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                form.add(param.getKey(), param.getValue());
            }
        }
        JsonNode obj = runtimeClient.postToRuntime(test.getRunner().getName(), form);
        if(obj.get("Failed")==null){
            return false;
        }
        return !obj.get("Failed").booleanValue();
    }

    private void run(UUID userId, MultipartFile file, String language, Runner runner) throws IOException {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("language", language);
        form.add("files", new FileMessageResource(file.getBytes(), file.getOriginalFilename()));

        appClientConnection.send(userId, runtimeClient.postToRuntime(runner.getName(), form).toString());
    }

    private void runTest(UUID userId, String filePath, String language, Test test) throws IOException {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("language", language);
        form.add("files_gcs", filePath);
        Map<String, String> params = test.getParams();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                form.add(param.getKey(), param.getValue());
            }
        }
        JsonNode obj = runtimeClient.postToRuntime(test.getRunner().getName(), form);
        ((ObjectNode) obj).put("Test", test.getId().toString());
        appClientConnection.send(userId, obj.toString());
    }
}
