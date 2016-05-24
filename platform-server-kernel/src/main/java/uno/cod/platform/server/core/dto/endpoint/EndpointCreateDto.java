package uno.cod.platform.server.core.dto.endpoint;

public class EndpointCreateDto {
    private String name;
    private String component;

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
