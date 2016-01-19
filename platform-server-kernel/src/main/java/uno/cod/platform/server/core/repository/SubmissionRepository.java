package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uno.cod.platform.server.core.domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
