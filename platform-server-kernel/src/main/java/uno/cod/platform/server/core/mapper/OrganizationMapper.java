package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Organization;
import uno.cod.platform.server.core.dto.organization.OrganizationShowDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizationMapper {
    public static OrganizationShowDto map(Organization organization){
        if(organization == null){
            return null;
        }
        return new OrganizationShowDto(organization);
    }

    public static List<OrganizationShowDto> map(List<Organization> organizations){
        return organizations.stream().map(OrganizationMapper::map).collect(Collectors.toList());
    }
}
