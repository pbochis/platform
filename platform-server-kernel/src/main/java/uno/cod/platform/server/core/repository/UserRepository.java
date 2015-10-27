package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import uno.cod.platform.server.core.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph("User.detail")
    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);
}

