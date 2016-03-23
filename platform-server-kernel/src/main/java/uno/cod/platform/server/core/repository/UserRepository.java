package uno.cod.platform.server.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uno.cod.platform.server.core.domain.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @EntityGraph("User.detail")
    User findByUsername(String username);

    @EntityGraph("User.detail")
    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);
}

