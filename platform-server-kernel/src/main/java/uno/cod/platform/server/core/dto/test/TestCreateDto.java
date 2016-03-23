package uno.cod.platform.server.core.dto.test;

import java.util.Map;
import java.util.UUID;

public class TestCreateDto {
    private UUID runnerId;
    private UUID taskId;
    private Map<String, String> params;

    public UUID getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(UUID runnerId) {
        this.runnerId = runnerId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
