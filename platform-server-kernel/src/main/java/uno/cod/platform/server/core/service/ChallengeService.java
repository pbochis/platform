package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.dto.challenge.ChallengeCreateDto;
import uno.cod.platform.server.core.dto.challenge.ChallengeDto;
import uno.cod.platform.server.core.mapper.ChallengeMapper;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ChallengeTemplateRepository;

import java.util.UUID;

@Service
public class ChallengeService {
    private final ChallengeTemplateRepository challengeTemplateRepository;
    private final ChallengeRepository repository;
    private final ResultService resultService;

    @Autowired
    public ChallengeService(ChallengeRepository repository,
                            ChallengeTemplateRepository challengeTemplateRepository,
                            ResultService resultService){
        this.repository = repository;
        this.challengeTemplateRepository = challengeTemplateRepository;
        this.resultService = resultService;
    }

    public UUID createFromDto(ChallengeCreateDto dto) {
        ChallengeTemplate template = challengeTemplateRepository.findOne(dto.getTemplateId());
        if (template == null) {
            throw new IllegalArgumentException("challenge.invalid");
        }
        Challenge challenge = new Challenge();
        challenge.setChallengeTemplate(template);
        challenge.setName(dto.getName());
        challenge.setCanonicalName(dto.getCanonicalName());
        if (dto.getStartDate() != null) {
            challenge.setStartDate(dto.getStartDate());
            challenge.setEndDate(dto.getStartDate().plus(template.getDuration()));
        }
        challenge.setInviteOnly(dto.isInviteOnly());
        return repository.save(challenge).getId();
    }

    public ChallengeDto findOneById(UUID challengeId) {
        return ChallengeMapper.map(repository.findOne(challengeId), resultService.getLeaderboard(challengeId));
    }

}
