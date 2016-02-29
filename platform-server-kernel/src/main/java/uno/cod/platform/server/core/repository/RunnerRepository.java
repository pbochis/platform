package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Runner;

/**
 * Created by vbalan on 2/25/2016.
 */
public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
