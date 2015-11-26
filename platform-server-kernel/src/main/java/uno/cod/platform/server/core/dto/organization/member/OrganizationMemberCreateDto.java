package uno.cod.platform.server.core.dto.organization.member;

import javax.validation.constraints.NotNull;

public class OrganizationMemberCreateDto {
    @NotNull
    private Long userId;
    private boolean isAdmin = false;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
