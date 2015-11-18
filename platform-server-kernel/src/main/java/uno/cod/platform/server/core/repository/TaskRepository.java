package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
}
