package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uno.cod.platform.server.core.domain.Test;

import java.util.List;

/**
 * Created by vbalan on 2/25/2016.
 */
public interface TestRepository extends JpaRepository<Test, Long> {

    @Query("SELECT test FROM Test test " +
            "LEFT JOIN FETCH test.task task " +
            "WHERE task.id = :taskId")
    List<Test> findByTask(@Param("taskId") Long taskId);
}
