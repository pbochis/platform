package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Endpoint;
import uno.cod.platform.server.core.dto.endpoint.EndpointShowDto;

import java.util.List;
import java.util.stream.Collectors;

public class EndpointMapper {
    public static EndpointShowDto map(Endpoint endpoint) {
        return new EndpointShowDto(endpoint);
    }

    public static List<EndpointShowDto> map(List<Endpoint> endpoints) {
        return endpoints.stream().map(EndpointMapper::map).collect(Collectors.toList());
    }
}
