package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.dto.challenge.ChallengeDto;
import uno.cod.platform.server.core.dto.challenge.LeaderboardEntryDto;
import uno.cod.platform.server.core.dto.user.UserShowDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengeMapper {

    public static ChallengeDto map(Challenge challenge, List<LeaderboardEntryDto> leaderboardEntries) {
        if (challenge == null){
            return null;
        }
        ChallengeDto dto = new ChallengeDto(challenge);
        if (challenge.getInvitedUsers() != null){
            dto.setInvitedUsers(challenge.getInvitedUsers().stream().map(UserShowDto::new).collect(Collectors.toList()));
        }
        dto.setLeaderboard(leaderboardEntries);
        return dto;
    }

}
