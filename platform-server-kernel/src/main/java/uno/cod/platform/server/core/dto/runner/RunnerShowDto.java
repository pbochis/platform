package uno.cod.platform.server.core.dto.runner;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Runner;

public class RunnerShowDto {
    private Long id;
    private String name;

    public RunnerShowDto(Runner runner){
        BeanUtils.copyProperties(runner, this);
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
}
