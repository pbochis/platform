package uno.cod.platform.server.core.dto.team.invitation;

import uno.cod.platform.server.core.domain.TeamInvitation;
import uno.cod.platform.server.core.dto.team.TeamShowDto;
import uno.cod.platform.server.core.dto.user.UserShowDto;

public class TeamInvitationShowDto {
    private TeamShowDto team;
    private UserShowDto invitedBy;

    public TeamInvitationShowDto(TeamInvitation invitation) {
        this.team = new TeamShowDto(invitation.getKey().getTeam());
        this.invitedBy = new UserShowDto(invitation.getInvitedBy());
    }

    public TeamShowDto getTeam() {
        return team;
    }

    public void setTeam(TeamShowDto team) {
        this.team = team;
    }

    public UserShowDto getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(UserShowDto invitedBy) {
        this.invitedBy = invitedBy;
    }
}
