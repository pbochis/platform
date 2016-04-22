package uno.cod.platform.server.core.dto.team.invitation;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TeamInvitationCreateDto {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID teamId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }
}
