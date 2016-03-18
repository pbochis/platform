package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uno.cod.platform.server.core.dto.test.TestShowDto;
import uno.cod.platform.server.core.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestShowDto> findByTaskId(Long taskId){
        return testRepository.findByTask(taskId).stream().map(TestShowDto::new).collect(Collectors.toList());
    }
}
