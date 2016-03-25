package uno.cod.platform.server.core.dto.runner;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Runner;

import java.util.UUID;

public class RunnerShowDto {
    private UUID id;
    private String name;

    public RunnerShowDto(Runner runner){
        BeanUtils.copyProperties(runner, this);
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
}
