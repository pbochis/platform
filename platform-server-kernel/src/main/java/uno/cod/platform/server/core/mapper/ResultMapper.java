package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Result;
import uno.cod.platform.server.core.dto.result.ResultShowDto;

public class ResultMapper {
    public static ResultShowDto map(Result result){
        return new ResultShowDto(result.getId());
    }
}
