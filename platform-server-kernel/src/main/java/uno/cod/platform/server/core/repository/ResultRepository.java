package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uno.cod.platform.server.core.domain.Result;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT result FROM Result result " +
            "LEFT JOIN FETCH result.challenge challenge " +
            "LEFT JOIN FETCH challenge.challengeTemplate template " +
            "LEFT JOIN FETCH template.tasks " +
            "WHERE result.id = :id")
    Result findOneWithChallenge(@Param("id") Long id);

    @Query("SELECT result FROM Result result " +
            "LEFT JOIN FETCH result.challenge challenge " +
            "LEFT JOIN FETCH challenge.challengeTemplate template " +
            "LEFT JOIN FETCH result.user user " +
            "WHERE user.id = :user AND template.id = :template")
    Result findOneByUserAndChallenge(@Param("user") Long userId, @Param("template") Long templateId);

    @Query("SELECT result FROM Result result " +
            "JOIN result.challenge challenge " +
            "JOIN challenge.challengeTemplate template " +
            "WHERE template.id = :template ")
    List<Result> findAllByTemplate(@Param("template") Long template);
}
