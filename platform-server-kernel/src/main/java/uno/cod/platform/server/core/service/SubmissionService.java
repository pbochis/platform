package uno.cod.platform.server.core.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import uno.cod.platform.server.core.domain.*;
import uno.cod.platform.server.core.repository.ResultRepository;
import uno.cod.platform.server.core.repository.SubmissionRepository;
import uno.cod.platform.server.core.repository.TaskRepository;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class SubmissionService {
    private final SubmissionRepository repository;
    private final ResultRepository resultRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final WebSocketService webSocketService;

    @Value("${coduno.runtime_url}")
    private String runtimeUrl;

    @Autowired
    public SubmissionService(SubmissionRepository repository, ResultRepository resultRepository, TaskRepository taskRepository, UserRepository userRepository, WebSocketService webSocketService) {
        this.repository = repository;
        this.resultRepository = resultRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.webSocketService = webSocketService;
    }

    public void create(String username, Long resultId, Long taskId, MultipartFile file, String language) throws IOException {
        Result result = resultRepository.findOne(resultId);
        if (result == null) {
            throw new IllegalArgumentException("result.invalid");
        }
        Task task = taskRepository.findOneWithTests(taskId);
        if (task == null) {
            throw new IllegalArgumentException("task.invalid");
        }
        // TODO save files with the code and put a reference in the submission

        Submission submission = new Submission();
        result.addSubmission(submission);
        task.addSubmission(submission);
        repository.save(submission);

        User user = userRepository.findByUsernameOrEmail(username, username);
        // TODO update submission with results
        for(Test test: task.getTests()) {
            run(user.getId(), file, language, test);
        }
    }

    private void run(Long userId, MultipartFile file, String language, Test test) throws IOException {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("language", language);
        form.add("files", new FileMessageResource(file.getBytes(), file.getOriginalFilename()));
        Map<String, String> params = test.getParams();
        if(params != null){
            for(Map.Entry<String, String> param: params.entrySet()){
                form.add(param.getKey(), param.getValue());
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(runtimeUrl + "/" + test.getRunner().getName(), form, String.class);
            JSONObject obj = new JSONObject(response.getBody());
            obj.put("Test", test.getId());
            webSocketService.send(userId, obj.toString());
        } catch (Exception e) {
            webSocketService.send(userId, "error");
        }
    }

}
