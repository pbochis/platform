package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uno.cod.platform.server.core.domain.Result;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT result FROM Result result " +
            "LEFT JOIN FETCH result.challenge scheduledChallenge " +
            "LEFT JOIN FETCH scheduledChallenge.challenge challenge " +
            "LEFT JOIN FETCH challenge.tasks " +
            "WHERE result.id = :id")
    Result findOneWithChallenge(@Param("id") Long id);

    @Query("SELECT result FROM Result result " +
            "LEFT JOIN FETCH result.challenge scheduledChallenge " +
            "LEFT JOIN FETCH scheduledChallenge.challenge challenge " +
            "LEFT JOIN FETCH result.user user " +
            "WHERE user.id = :user AND challenge.id = :challenge")
    Result findOneByUserAndChallenge(@Param("user") Long userId, @Param("challenge") Long challengeId);

    @Query("SELECT result FROM Result result " +
            "JOIN result.challenge scheduledChallenge " +
            "JOIN scheduledChallenge.challenge challenge " +
            "WHERE challenge.id = :challenge ")
    List<Result> findAllByChallenge(@Param("challenge") Long challenge);
}
