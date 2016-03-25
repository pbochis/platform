package uno.cod.platform.server.core.dto.result;


import java.util.List;
import java.util.UUID;

public class ResultShowDto {
    private final UUID id;
    private final List<Long> startTimes;

    public ResultShowDto(UUID id, List<Long> startTimes) {
        this.id = id;
        this.startTimes = startTimes;
    }

    public UUID getId() {
        return id;
    }

    public List<Long> getStartTimes() {
        return startTimes;
    }
}
