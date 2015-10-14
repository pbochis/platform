package uno.cod.platform.server.core.service;

import uno.cod.platform.server.core.domain.Role;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.repository.RoleRepository;
import uno.cod.platform.server.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService extends AbstractBaseService<UserRepository, User> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user, Set<String> roles) {
        Set<Role> rolesEntities = roles.stream().map(roleRepository::findByAuthority).collect(Collectors.toSet());
        user.setRoles(rolesEntities);
        return createUser(user);
    }

    public User createUser(User user) {
        if(user.getId() != null)
            throw new IllegalArgumentException("user already exists");
        if(user.getPassword() == null || user.getPassword().isEmpty())
            throw new IllegalArgumentException("user creation with password is not allowed");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public User updateUser(User user, Set<String> roles) {
        Set<Role> rolesEntities = roles.stream().map(roleRepository::findByAuthority).collect(Collectors.toSet());
        user.setRoles(rolesEntities);
        return updateUser(user);
    }


    public User updateUser(User user) {
        if(user.getId() == null)
            throw new IllegalArgumentException("user does not exist");

        User persisted = repository.findOne(user.getId());
        if(user.getUsername() != null && !user.getUsername().isEmpty())
            persisted.setUsername(user.getUsername());

        if(user.getPassword() != null && !user.getPassword().isEmpty())
            persisted.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRoles() != null && !user.getRoles().isEmpty())
            persisted.setRoles(user.getRoles());

        persisted.setEnabled(user.isEnabled());

        return repository.save(persisted);
    }
}
