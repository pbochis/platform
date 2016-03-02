package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Template;

/**
 * Created by Paul on 3/2/2016.
 */
public interface TemplateRepository extends JpaRepository<Template, Long> {
}
