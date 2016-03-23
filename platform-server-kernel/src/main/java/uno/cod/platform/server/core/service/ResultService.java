package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.Result;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.result.ResultShowDto;
import uno.cod.platform.server.core.mapper.ResultMapper;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ResultRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ResultService {
    private final ResultRepository repository;
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ResultService(ResultRepository repository, ChallengeRepository challengeRepository) {
        this.repository = repository;
        this.challengeRepository = challengeRepository;
    }

    public ResultShowDto save(UUID challengeId, User user){
        Challenge challenge = challengeRepository.findOne(challengeId);
        if (challenge == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        Result result = repository.findOneByUserAndChallenge(user.getId(), challengeId);
        if(result!=null){
            throw new IllegalArgumentException("challenge.completed");
        }
        result = new Result();
        challenge.addResult(result);
        result.setStarted(ZonedDateTime.now());
        result.setUser(user);

        return ResultMapper.map(repository.save(result));
    }

    public void startTask(UUID resultId, UUID taskId) {
        Result result = repository.findOneWithChallenge(resultId);
        List<Task> tasks =result.getChallenge().getChallengeTemplate().getTasks();
        for(int i=0; i<tasks.size(); i++) {
            if(tasks.get(i).getId().equals(taskId)) {
                if(result.start(i)) {
                    repository.save(result);
                }
                break;
            }
        }
    }

    public ResultShowDto findOne(UUID id){
        return ResultMapper.map(repository.findOne(id));
    }

    public ResultShowDto findOneByUserAndChallenge(UUID userId, UUID challengeId){
        return ResultMapper.map(repository.findOneByUserAndChallenge(userId, challengeId));
    }
}
