package uno.cod.platform.server.core.dto.task;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.endpoint.EndpointShowDto;

public class TaskShowDto {
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private EndpointShowDto endpoint;

    public TaskShowDto(Task task) {
        BeanUtils.copyProperties(task, this);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public EndpointShowDto getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(EndpointShowDto endpoint) {
        this.endpoint = endpoint;
    }
}
