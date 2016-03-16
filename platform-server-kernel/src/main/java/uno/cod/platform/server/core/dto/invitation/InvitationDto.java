package uno.cod.platform.server.core.dto.invitation;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class InvitationDto {
    @NotNull
    private String email;

    @NotNull
    private Long challengeId;

    private ZonedDateTime startDate;

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

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }
}
