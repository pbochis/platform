package uno.cod.platform.server.core.dto.invitation;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class InvitationDto {
    @NotNull
    private String email;

    @NotNull
    private UUID challengeId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }
}
