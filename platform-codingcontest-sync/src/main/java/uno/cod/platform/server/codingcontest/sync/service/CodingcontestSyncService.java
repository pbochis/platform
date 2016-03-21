package uno.cod.platform.server.codingcontest.sync.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.codingcontest.sync.dto.CodingcontestDto;
import uno.cod.platform.server.codingcontest.sync.dto.ContestInfoDto;
import uno.cod.platform.server.codingcontest.sync.dto.ContestantDto;
import uno.cod.platform.server.codingcontest.sync.dto.ParticipationDto;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.domain.Result;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.repository.ChallengeRepository;
import uno.cod.platform.server.core.repository.ChallengeTemplateRepository;
import uno.cod.platform.server.core.repository.ResultRepository;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CodingcontestSyncService {
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeTemplateRepository challengeTemplateRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public CodingcontestSyncService(UserRepository userRepository, ChallengeRepository challengeRepository, ChallengeTemplateRepository challengeTemplateRepository, ResultRepository resultRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.challengeTemplateRepository = challengeTemplateRepository;
        this.resultRepository = resultRepository;
    }

    public User updateUserFromCodingcontest(ParticipationDto dto) {
        User user = userRepository.findOne(UUID.fromString(dto.getUuid()));
        if (user == null) {
            user = createUserFromDto(dto);
        } else {
            user.setPassword(dto.getPassword());
        }
        return userRepository.save(user);
    }

    public void createOrUpdateContest(CodingcontestDto dto) {
        UUID id = UUID.fromString(dto.getUuid());
        Challenge challenge = challengeRepository.findOne(id);
        ChallengeTemplate template = challengeTemplateRepository.findOneByName(dto.getGameName());
        if (template == null) {
            throw new IllegalArgumentException("sync.game.invalid");
        }
        if (challenge == null) {
            challenge = new Challenge();
            challenge.setId(id);
            challenge.setName(dto.getName());
            challenge.setCanonicalName("ccc-" + dto.getLocation() + "-" + Year.now().toString());
            challenge.setChallengeTemplate(template);
            challenge.setStartDate(ZonedDateTime.ofInstant(dto.getStartTime().toInstant(), ZoneId.of("UTC")));
            challenge = challengeRepository.save(challenge);
        }

        for (ParticipationDto participation : dto.getParticipations()) {
            User user = userRepository.findOne(UUID.fromString(dto.getUuid()));
            if (user == null) {
                user = createUserFromDto(participation);
                user.addInvitedChallenge(challenge);
            } else {
                user.setPassword(participation.getPassword());
                if(!user.getInvitedChallenges().contains(challenge)) {
                    user.addInvitedChallenge(challenge);
                }
            }
            userRepository.save(user);
        }
    }

    public ContestInfoDto getResults(UUID id) {
        Challenge challenge = challengeRepository.findOneWithTemplate(id);
        if (challenge == null) {
            return null;
        }
        ContestInfoDto contestInfoDto = new ContestInfoDto();
        contestInfoDto.setUuid(id);
        contestInfoDto.setDurationHours(4);
        contestInfoDto.setDurationMinutes(4 * 60);
        contestInfoDto.setFailedTestPenalty(0);
        contestInfoDto.setUploadedCodePerLevelBonus(0);
        contestInfoDto.setGameName(challenge.getChallengeTemplate().getName());
        contestInfoDto.setName(challenge.getName());
        // TODO replace with getLeaderboard
        List<Result> results = resultRepository.findAllByChallenge(challenge.getId());
        List<ContestantDto> contestants = new ArrayList<>();
        for (Result result : results) {
            ContestantDto contestant = new ContestantDto();
            contestant.setEmail(result.getUser().getEmail());
            contestant.setUuid(result.getUser().getId());
            // TODO fill with other data when TaskResults are added
        }

        return contestInfoDto;
    }

    private User createUserFromDto(ParticipationDto dto) {
        User user = new User();
        user.setId(UUID.fromString(dto.getUuid()));
        user.setUsername(dto.getName());
        user.setPassword(dto.getPassword());
        if(dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(dto.getUuid() + "@team.cod.uno");
        }
        return user;
    }
}
