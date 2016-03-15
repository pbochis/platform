package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.*;
import uno.cod.platform.server.core.dto.result.ResultShowDto;
import uno.cod.platform.server.core.mapper.ResultMapper;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ResultRepository;
import uno.cod.platform.server.core.repository.ScheduledChallengeRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class ResultService {
    private final ResultRepository repository;
    private final ScheduledChallengeRepository scheduledChallengeRepository;

    @Autowired
    public ResultService(ResultRepository repository, ScheduledChallengeRepository scheduledChallengeRepository) {
        this.repository = repository;
        this.scheduledChallengeRepository = scheduledChallengeRepository;
    }

    public ResultShowDto save(Long challengeId, User user){
        ScheduledChallenge scheduledChallenge = scheduledChallengeRepository.findOne(challengeId);
        if (scheduledChallenge == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        Result result = repository.findOneByUserAndChallenge(user.getId(), challengeId);
        if(result!=null){
            return ResultMapper.map(result);
        }
        result = new Result();
        scheduledChallenge.addResult(result);
        result.setStarted(ZonedDateTime.now());
        result.setUser(user);

        return ResultMapper.map(repository.save(result));
    }

    public void startTask(Long resultId, Long taskId){
        Result result = repository.findOneWithChallenge(resultId);
        List<Task> tasks =result.getChallenge().getChallenge().getTasks();
        for(int i=0; i<tasks.size();i++){
            if(tasks.get(i).getId().equals(taskId)){
                if(result.start(i)) {
                    repository.save(result);
                }
                break;
            }
        }
    }

    public ResultShowDto findOne(Long id){
        return ResultMapper.map(repository.findOne(id));
    }
}
