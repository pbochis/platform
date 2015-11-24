package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.Endpoint;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {

}
