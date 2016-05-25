package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Runner;

import java.util.UUID;

public interface RunnerRepository extends JpaRepository<Runner, UUID> {
    Runner findOneByPath(String path);
}