package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
