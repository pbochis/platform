package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.TestResult;

import java.util.UUID;

public interface TestResultRepository extends JpaRepository<TestResult, UUID> {
}
