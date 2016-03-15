package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.dto.challenge.template.ChallengeTemplateShowDto;
import uno.cod.platform.server.core.mapper.ChallengeMapper;
import uno.cod.platform.server.core.repository.ChallengeTemplateRepository;
import uno.cod.platform.server.core.repository.ChallengeRepository;

import java.time.ZonedDateTime;

@Service
public class ChallengeService {
    private ChallengeTemplateRepository challengeTemplateRepository;
    private ChallengeRepository repository;

    @Autowired
    public ChallengeService(ChallengeRepository repository, ChallengeTemplateRepository challengeTemplateRepository){
        this.repository = repository;
        this.challengeTemplateRepository = challengeTemplateRepository;
    }

    public Challenge findOrCreate(Long templateId, ZonedDateTime startDate){
        if (templateId == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        Challenge challenge = repository.findOneByTemplateAndStartDateWithOrganization(templateId, startDate);
        if (challenge == null){
            ChallengeTemplate challengeTemplate = challengeTemplateRepository.findOne(templateId);
            challenge = new Challenge();
            challenge.setChallengeTemplate(challengeTemplate);
            challenge.setStartDate(startDate);
            challenge.setEndDate(startDate.plus(challengeTemplate.getDuration()));
            //TODO: maybe set a default name, or in the future force organizations to input a name
            //TODO: even for "default" challenges
            challenge = repository.save(challenge);
        }
        return challenge;
    }

    public void save(Challenge challenge){
        repository.save(challenge);
    }

    public ChallengeTemplateShowDto findChallenge(Long scheduledChallengeId){
        return ChallengeMapper.map(repository.findOneByIdWithTemplate(scheduledChallengeId).getChallengeTemplate());
    }
}
