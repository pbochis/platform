package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uno.cod.platform.server.core.domain.Endpoint;
import uno.cod.platform.server.core.dto.endpoint.EndpointCreateDto;
import uno.cod.platform.server.core.dto.endpoint.EndpointShowDto;
import uno.cod.platform.server.core.mapper.EndpointMapper;
import uno.cod.platform.server.core.repository.EndpointRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EndpointService {
    private final EndpointRepository repository;

    @Autowired
    public EndpointService(EndpointRepository repository) {
        this.repository = repository;
    }

    public List<EndpointShowDto> findAll() {
        return EndpointMapper.map(repository.findAll());
    }

    public UUID createFromDto(EndpointCreateDto dto){
        Endpoint endpoint = repository.findOneByComponent(dto.getComponent());
        if (endpoint != null){
            return endpoint.getId();
        }
        endpoint = new Endpoint();
        endpoint.setComponent(dto.getComponent());
        endpoint.setName(dto.getName());
        return repository.save(endpoint).getId();
    }
}
