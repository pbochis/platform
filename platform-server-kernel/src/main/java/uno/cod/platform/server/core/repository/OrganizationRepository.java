package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uno.cod.platform.server.core.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}

