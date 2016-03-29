package uno.cod.platform.server.core.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import uno.cod.platform.server.core.domain.Runner;
import uno.cod.platform.server.core.dto.runner.RunnerShowDto;
import uno.cod.platform.server.core.repository.RunnerRepository;
import uno.cod.platform.server.core.service.util.RunnerTestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RunnerServiceTest {
    private RunnerService service;
    private RunnerRepository repository;

    @Before
    public void setup() {
        this.repository = Mockito.mock(RunnerRepository.class);
        this.service = new RunnerService(repository);
    }

    @Test
    public void findAll() throws Exception {
        List<Runner> runners = Collections.singletonList(RunnerTestUtil.getRunner());
        Mockito.when(repository.findAll()).thenReturn(runners);

        List<RunnerShowDto> dtos = service.findAll();

        assertEquals(runners.size(), dtos.size());

        RunnerShowDto dto = dtos.get(0);
        Runner runner = runners.get(0);

        assertEquals(runner.getId(), dto.getId());
        assertEquals(runner.getName(), dto.getName());
    }
}