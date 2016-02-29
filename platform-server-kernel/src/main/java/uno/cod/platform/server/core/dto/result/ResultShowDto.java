package uno.cod.platform.server.core.dto.result;


import java.util.List;

public class ResultShowDto {
    private final Long id;
    private final List<Long> startTimes;

    public ResultShowDto(Long id, List<Long> startTimes){
        this.id = id;
        this.startTimes = startTimes;
    }

    public Long getId() {
        return id;
    }

    public List<Long> getStartTimes() {
        return startTimes;
    }
}
