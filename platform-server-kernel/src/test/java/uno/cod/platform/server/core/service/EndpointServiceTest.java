package uno.cod.platform.server.core.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import uno.cod.platform.server.core.domain.Endpoint;
import uno.cod.platform.server.core.dto.endpoint.EndpointShowDto;
import uno.cod.platform.server.core.repository.EndpointRepository;
import uno.cod.platform.server.core.service.util.EndpointTestUtil;

import java.util.Collections;
import java.util.List;

public class EndpointServiceTest {
    private EndpointService service;
    private EndpointRepository repository;

    @Before
    public void setup() {
        this.repository = Mockito.mock(EndpointRepository.class);
        this.service = new EndpointService(repository);
    }

    @Test
    public void findAll() throws Exception {
        List<Endpoint> endpoints = Collections.singletonList(EndpointTestUtil.getEndpoint());

        Mockito.when(repository.findAll()).thenReturn(endpoints);

        List<EndpointShowDto> dtos = service.findAll();

        Assert.assertEquals(dtos.size(), endpoints.size());
        Endpoint endpoint = endpoints.get(0);
        EndpointShowDto dto = dtos.get(0);
        Assert.assertEquals(endpoint.getId(), dto.getId());
        Assert.assertEquals(endpoint.getName(), dto.getName());
        Assert.assertEquals(endpoint.getComponent(), dto.getComponent());
    }
}