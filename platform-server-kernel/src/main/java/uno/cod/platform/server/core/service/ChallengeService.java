package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.dto.challenge.ChallengeCreateDto;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ChallengeTemplateRepository;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class ChallengeService {
    private final ChallengeTemplateRepository challengeTemplateRepository;
    private final ChallengeRepository repository;

    @Autowired
    public ChallengeService(ChallengeRepository repository, ChallengeTemplateRepository challengeTemplateRepository){
        this.repository = repository;
        this.challengeTemplateRepository = challengeTemplateRepository;
    }

    public void createFromDto(UUID templateId, ChallengeCreateDto dto){
        if (templateId == null){
            throw new IllegalArgumentException("challenge.invalid");
        }

        ChallengeTemplate template = challengeTemplateRepository.findOne(templateId);
        if (template == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        Challenge challenge = new Challenge();
        challenge.setChallengeTemplate(template);
        challenge.setName(dto.getName());
        challenge.setCanonicalName(dto.getCanonicalName());
        if (dto.getStartDate() != null){
            challenge.setStartDate(dto.getStartDate());
            challenge.setEndDate(dto.getStartDate().plus(template.getDuration()));
        }
        challenge.setInviteOnly(dto.isInviteOnly());
        repository.save(challenge);
    }

    public Challenge findOrCreateByTemplateAndStartDateAndOrganization(UUID templateId, ZonedDateTime startDate, UUID organizationId){
        if (templateId == null){
            throw new IllegalArgumentException("challenge.invalid");
        }
        Challenge challenge = repository.findOneByTemplateAndStartDateAndOrganization(templateId, startDate, organizationId);
        if (challenge == null){
            ChallengeTemplate challengeTemplate = challengeTemplateRepository.findOne(templateId);
            if (challengeTemplate == null){
                throw new IllegalArgumentException("challenge.invalid");
            }
            challenge = new Challenge();
            challenge.setChallengeTemplate(challengeTemplate);
            challenge.setStartDate(startDate);
            if(startDate!=null) {
                challenge.setEndDate(startDate.plus(challengeTemplate.getDuration()));
            }
            //TODO: maybe set a default name, or in the future force organizations to input a name
            //TODO: even for "default" challenges
            challenge = repository.save(challenge);
        }
        return challenge;
    }

    public void save(Challenge challenge){
        repository.save(challenge);
    }
}
