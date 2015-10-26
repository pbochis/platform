package uno.cod.platform.server.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class SetupService {
    static final Logger logger = LoggerFactory.getLogger(SetupService.class);
    private static final String TAG = SetupService.class.getSimpleName();
    private Environment environment;
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public SetupService(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void init(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setEnabled(true);
        this.userRepository.save(user);
        if(Arrays.asList(this.environment.getActiveProfiles()).contains("development")) {
            this.initDevelopmentDatabase();
        }

    }

    private void initDevelopmentDatabase() {
    }
}
