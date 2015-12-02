package uno.cod.platform.server.core.dto.result;


import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Result;

public class ResultShowDto {
    private final Long id;

    public ResultShowDto(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
