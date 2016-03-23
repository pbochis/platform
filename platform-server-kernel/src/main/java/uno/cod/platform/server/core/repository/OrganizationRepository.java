package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uno.cod.platform.server.core.domain.Organization;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    @Query("SELECT organization FROM Organization organization WHERE organization.nick = :nick")
    Organization findByNick(@Param("nick") String nick);
}

