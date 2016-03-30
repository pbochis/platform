package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uno.cod.platform.server.core.domain.Language;

import java.util.UUID;

public interface LanguageRepository extends JpaRepository<Language, UUID> {

    @Query("Select language From Language language where language.tag=:tag")
    Language findByTag(@Param("tag") String tag);

}
