package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
