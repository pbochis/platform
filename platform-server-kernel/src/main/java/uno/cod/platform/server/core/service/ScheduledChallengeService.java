package uno.cod.platform.server.core.service;

import com.sun.org.apache.xml.internal.security.keys.ContentHandlerAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.ScheduledChallenge;
import uno.cod.platform.server.core.dto.challenge.ChallengeShowDto;
import uno.cod.platform.server.core.mapper.ChallengeMapper;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ScheduledChallengeRepository;

import java.time.ZonedDateTime;

@Service
public class ScheduledChallengeService {
    private ChallengeRepository challengeRepository;
    private ScheduledChallengeRepository repository;

    @Autowired
    public ScheduledChallengeService(ScheduledChallengeRepository repository, ChallengeRepository challengeRepository){
        this.repository = repository;
        this.challengeRepository = challengeRepository;
    }

    public ScheduledChallenge findOrCreate(Long challengeId, ZonedDateTime startDate){
        if (challengeId == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        ScheduledChallenge scheduledChallenge = repository.findOneByChallengheAndStartDateWithOrganization(challengeId, startDate);
        if (scheduledChallenge == null){
            Challenge challenge = challengeRepository.findOne(challengeId);
            scheduledChallenge = new ScheduledChallenge();
            scheduledChallenge.setChallenge(challenge);
            scheduledChallenge.setStartDate(startDate);
            scheduledChallenge.setEndDate(startDate.plus(challenge.getDuration()));
            scheduledChallenge = repository.save(scheduledChallenge);
        }
        return scheduledChallenge;
    }

    public void save(ScheduledChallenge scheduledChallenge){
        repository.save(scheduledChallenge);
    }

    public ChallengeShowDto findChallenge(Long scheduledChallengeId){
        return ChallengeMapper.map(repository.findOneByIdWithChallenge(scheduledChallengeId).getChallenge());
    }
}
