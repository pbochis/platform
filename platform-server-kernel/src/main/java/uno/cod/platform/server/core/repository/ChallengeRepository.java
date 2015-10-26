package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}

