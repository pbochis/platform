package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.dto.runner.RunnerShowDto;
import uno.cod.platform.server.core.repository.RunnerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RunnerService {
    private final RunnerRepository repository;

    @Autowired
    public RunnerService(RunnerRepository repository) {
        this.repository = repository;
    }

    public List<RunnerShowDto> findAll() {
        return repository.findAll().stream().map(RunnerShowDto::new).collect(Collectors.toList());
    }
}
