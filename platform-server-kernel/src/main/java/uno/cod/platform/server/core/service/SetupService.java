package uno.cod.platform.server.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.*;
import uno.cod.platform.server.core.repository.RoleRepository;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class SetupService {
    final static Logger logger = LoggerFactory.getLogger(SetupService.class);

    private static final String TAG = SetupService.class.getSimpleName();

    private Environment environment;
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public SetupService(
        JdbcTemplate jdbcTemplate,
        PasswordEncoder passwordEncoder,
        UserRepository userRepository,
        RoleRepository roleRepository
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void init(String username, String password) {
        List<Role> roles = Stream.of("ROLE_SYSADMIN", "ROLE_MARKETING_ADMIN", "ROLE_CONTENT_ADMIN", "ROLE_BEACON_ADMIN")
            .map(Role::new)
            .collect(Collectors.toList());

        roles.forEach(roleRepository::save);

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);

        jdbcTemplate.execute(JdbcTokenRepositoryImpl.CREATE_TABLE_SQL);

        if (Arrays.asList(environment.getActiveProfiles()).contains("development")) {
            initDevelopmentDatabase();
        }
    }

    private void initDevelopmentDatabase() {

    }
}