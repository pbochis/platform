package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "organization_member")
@AssociationOverrides({
        @AssociationOverride(name = "key.user", joinColumns = {@JoinColumn(name = "user_id")}),
        @AssociationOverride(name = "key.organization", joinColumns = {@JoinColumn(name = "organization_id")})
})
public class OrganizationMember {
    @EmbeddedId
    private OrganizationMemberKey key = new OrganizationMemberKey();

    @Column(nullable = false, updatable = false)
    private Date created = new Date();

    private boolean admin;

    public OrganizationMember() {
    }

    public OrganizationMemberKey getKey() {
        return this.key;
    }

    public void setKey(OrganizationMemberKey key) {
        this.key = key;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
