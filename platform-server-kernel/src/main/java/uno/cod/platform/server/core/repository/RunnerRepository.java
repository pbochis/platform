package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Runner;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
