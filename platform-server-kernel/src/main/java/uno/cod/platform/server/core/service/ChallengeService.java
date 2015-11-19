package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.challenge.ChallengeCreateDto;
import uno.cod.platform.server.core.dto.challenge.ChallengeShowDto;
import uno.cod.platform.server.core.mapper.ChallengeMapper;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChallengeService {
    private final ChallengeRepository repository;
    private final TaskRepository taskRepository;

    @Autowired
    public ChallengeService(ChallengeRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public Long save(ChallengeCreateDto dto){
        Challenge challenge = new Challenge();
        challenge.setName(dto.getName());
        List<Task> tasks = new ArrayList<>();
        for(Long taskId: dto.getTasks()){
            Task task = taskRepository.getOne(taskId);
            if(task == null){
                throw new IllegalArgumentException("task id " + taskId + " is not valid");
            }
            tasks.add(task);
        }
        challenge.setTasks(tasks);

        return repository.save(challenge).getId();
    }

    public ChallengeShowDto findById(Long id){
        return ChallengeMapper.map(repository.findOne(id));
    }

    public List<ChallengeShowDto> findAll(){
        return ChallengeMapper.map(repository.findAll());
    }
}
