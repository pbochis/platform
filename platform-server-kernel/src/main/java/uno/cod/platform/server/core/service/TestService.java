package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uno.cod.platform.server.core.domain.Runner;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.domain.Test;
import uno.cod.platform.server.core.dto.test.TestCreateDto;
import uno.cod.platform.server.core.dto.test.TestShowDto;
import uno.cod.platform.server.core.repository.RunnerRepository;
import uno.cod.platform.server.core.repository.TaskRepository;
import uno.cod.platform.server.core.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {
    private final TestRepository testRepository;
    private final TaskRepository taskRepository;
    private final RunnerRepository runnerRepository;

    @Autowired
    public TestService(TestRepository testRepository, TaskRepository taskRepository, RunnerRepository runnerRepository) {
        this.testRepository = testRepository;
        this.taskRepository = taskRepository;
        this.runnerRepository = runnerRepository;
    }

    public void save(TestCreateDto dto){
        Task task = taskRepository.findOne(dto.getTaskId());
        if(task == null){
            throw new IllegalArgumentException("task.invalid");
        }
        Runner runner = runnerRepository.findOne(dto.getRunnerId());
        if(runner == null){
            throw new IllegalArgumentException("runner.invalid");
        }
        Test test = new Test();
        test.setRunner(runner);
        test.setTask(task);
        test.setParams(dto.getParams());
        testRepository.save(test);
    }

    public List<TestShowDto> findByTaskId(Long taskId){
        return testRepository.findByTask(taskId).stream().map(TestShowDto::new).collect(Collectors.toList());
    }
}
