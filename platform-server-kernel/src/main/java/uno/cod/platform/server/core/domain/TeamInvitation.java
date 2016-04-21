package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "team_invitation")
@AssociationOverrides({
        @AssociationOverride(name = "key.user", joinColumns = {@JoinColumn(name = "user_id")}),
        @AssociationOverride(name = "key.team", joinColumns = {@JoinColumn(name = "team_id")})
})
public class TeamInvitation {
    @EmbeddedId
    private TeamUserKey key = new TeamUserKey();

    @ManyToOne
    @JoinColumn(name = "invited_by_id", nullable = false, updatable = false)
    private User invitedBy;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime created = ZonedDateTime.now();

    public TeamUserKey getKey() {
        return key;
    }

    public void setKey(TeamUserKey key) {
        this.key = key;
    }

    public User getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(User invitedBy) {
        this.invitedBy = invitedBy;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }
}