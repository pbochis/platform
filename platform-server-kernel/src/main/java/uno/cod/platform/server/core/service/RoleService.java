package uno.cod.platform.server.core.service;

import uno.cod.platform.server.core.domain.Role;
import uno.cod.platform.server.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService extends AbstractBaseService<RoleRepository, Role>  {
    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
