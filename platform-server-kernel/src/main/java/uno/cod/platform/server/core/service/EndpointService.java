package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.dto.endpoint.EndpointShowDto;
import uno.cod.platform.server.core.mapper.EndpointMapper;
import uno.cod.platform.server.core.repository.EndpointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EndpointService {
    private final EndpointRepository repository;

    @Autowired
    public EndpointService(EndpointRepository repository) {
        this.repository = repository;
    }

    public List<EndpointShowDto> findAll() {
        return EndpointMapper.map(repository.findAll());
    }
}
