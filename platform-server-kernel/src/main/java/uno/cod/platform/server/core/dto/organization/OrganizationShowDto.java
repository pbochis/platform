package uno.cod.platform.server.core.dto.organization;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Organization;

public class OrganizationShowDto {
    private Long id;
    private String name;
    private String nick;

    public OrganizationShowDto(Organization organization){
        BeanUtils.copyProperties(organization, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
