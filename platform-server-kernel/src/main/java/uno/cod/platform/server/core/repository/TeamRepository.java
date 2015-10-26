package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

