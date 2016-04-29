package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Language;

import java.util.UUID;

public interface LanguageRepository extends JpaRepository<Language, UUID> {

    Language findByTag(String tag);

}
