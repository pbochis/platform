package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uno.cod.platform.server.core.domain.ScheduledChallenge;

import java.time.ZonedDateTime;

public interface ScheduledChallengeRepository extends JpaRepository<ScheduledChallenge, Long>{

    @Query("SELECT scheduledChallenge FROM ScheduledChallenge scheduledChallenge " +
            "JOIN FETCH scheduledChallenge.challenge challenge " +
            "LEFT JOIN FETCH challenge.organization organization " +
            "WHERE challenge.id=:challenge AND scheduledChallenge.startDate=:startDate")
    ScheduledChallenge findOneByChallengheAndStartDateWithOrganization(@Param("challenge") Long challenge, @Param("startDate")ZonedDateTime startDate);

    @Query("SELECT scheduledChallenge FROM ScheduledChallenge scheduledChallenge " +
            "JOIN FETCH scheduledChallenge.challenge challenge " +
            "WHERE scheduledChallenge.id=:id")
    ScheduledChallenge findOneByIdWithChallenge(@Param("id") Long id);
}
