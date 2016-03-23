package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uno.cod.platform.server.core.domain.Team;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
}

