package uno.cod.platform.server.core.dto.endpoint;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Endpoint;

import java.util.UUID;

public class EndpointShowDto {
    private UUID id;
    private String name;
    private String component;

    public EndpointShowDto(Endpoint endpoint) {
        BeanUtils.copyProperties(endpoint, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
