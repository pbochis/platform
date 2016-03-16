package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uno.cod.platform.server.core.domain.Challenge;

import java.time.ZonedDateTime;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>{

    @Query("SELECT challenge FROM Challenge challenge " +
            "JOIN FETCH challenge.challengeTemplate challengeTemplate " +
            "LEFT JOIN FETCH challengeTemplate.organization organization " +
            "WHERE challengeTemplate.id=:templateId AND challenge.startDate=:startDate")
    Challenge findOneByTemplateAndStartDateWithOrganization(@Param("templateId") Long challengeTemplate, @Param("startDate")ZonedDateTime startDate);

    @Query("SELECT challenge FROM Challenge challenge " +
            "JOIN FETCH challenge.challengeTemplate template " +
            "WHERE challenge.id=:id")
    Challenge findOneByIdWithTemplate(@Param("id") Long id);
}
