package uno.cod.platform.server.core.dto.invitation;

import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.user.UserShowDto;

/**
 * Created by vbalan on 2/24/2016.
 */
public class InvitationAuthDto {
    private UserShowDto user;
    private Long challengeId;

    public InvitationAuthDto(User user, Long challengeId) {
        this.user = new UserShowDto(user);
        this.challengeId = challengeId;
    }

    public UserShowDto getUser() {
        return user;
    }

    public void setUser(UserShowDto user) {
        this.user = user;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }
}
