package uno.cod.platform.server.core.dto.user;

import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.organization.member.OrganizationMembershipShowDto;

import java.util.List;
import java.util.stream.Collectors;

public class CurrentUserDto extends UserShowDto {
    private List<OrganizationMembershipShowDto> organizations;

    public CurrentUserDto(User user) {
        super(user);
        if (user.getOrganizationMemberships() == null) {
            return;
        }

        this.organizations = user.getOrganizationMemberships().stream().map(OrganizationMembershipShowDto::new).collect(Collectors.toList());
    }

    public List<OrganizationMembershipShowDto> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationMembershipShowDto> organizations) {
        this.organizations = organizations;
    }
}
