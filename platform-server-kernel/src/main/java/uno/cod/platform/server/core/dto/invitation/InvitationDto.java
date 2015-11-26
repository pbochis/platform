package uno.cod.platform.server.core.dto.invitation;

import javax.validation.constraints.NotNull;

public class InvitationDto {
    @NotNull
    private String email;
    @NotNull
    private Long challengeId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }
}
