package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.Result;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.result.ResultShowDto;
import uno.cod.platform.server.core.mapper.ResultMapper;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ResultRepository;
import uno.cod.platform.server.core.repository.UserRepository;

import java.time.ZonedDateTime;

@Service
public class ResultService {
    private final ResultRepository repository;
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ResultService(ResultRepository repository, ChallengeRepository challengeRepository) {
        this.repository = repository;
        this.challengeRepository = challengeRepository;
    }

    public ResultShowDto save(Long challengeId, User user){
        Challenge challenge = challengeRepository.findOne(challengeId);
        if (challenge == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        Result result = new Result();
        challenge.addResult(result);
        result.setStarted(ZonedDateTime.now());
        result.setUser(user);

        return ResultMapper.map(repository.save(result));
    }

    public ResultShowDto findOne(Long id){
        return ResultMapper.map(repository.findOne(id));
    }
}
