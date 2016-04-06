package uno.cod.platform.server.core.dto.runner;

import uno.cod.platform.server.core.domain.Runner;

import java.util.UUID;

public class RunnerShowDto {
    private UUID id;
    private String path;

    public RunnerShowDto(Runner runner) {
        this.id = runner.getId();
        this.path = runner.getPath();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
