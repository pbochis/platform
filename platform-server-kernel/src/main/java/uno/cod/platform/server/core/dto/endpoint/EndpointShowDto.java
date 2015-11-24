package uno.cod.platform.server.core.dto.endpoint;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Endpoint;

public class EndpointShowDto {
    private Long id;
    private String name;
    private String component;

    public EndpointShowDto(Endpoint endpoint) {
        BeanUtils.copyProperties(endpoint, this);
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
