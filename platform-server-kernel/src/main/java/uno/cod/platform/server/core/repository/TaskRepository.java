package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uno.cod.platform.server.core.domain.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT task FROM Task task " +
            "LEFT JOIN FETCH task.endpoint " +
            "LEFT JOIN FETCH task.organization organization " +
            "WHERE organization = null OR organization.id = :organizationId")
    List<Task> findAllWithEndpoints(@Param("organizationId") Long organizationId);

    @Query("SELECT task FROM Task task " +
            "LEFT JOIN FETCH task.tests tests " +
            "LEFT JOIN FETCH tests.runner " +
            "WHERE task.id = :id")
    Task findOneWithTests(@Param("id") Long id);

    @Query("SELECT task FROM Task task " +
            "LEFT JOIN FETCH task.templates templates " +
            "WHERE task.id = :id")
    Task findOneWithTemplates(@Param("id") Long id);
}
