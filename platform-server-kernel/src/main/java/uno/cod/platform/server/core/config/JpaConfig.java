package uno.cod.platform.server.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"uno.cod.platform.server.core.domain", "uno.cod.platform.server.core.repository"})
@EnableJpaRepositories(basePackages = {"uno.cod.platform.server.core.repository"})
@EnableTransactionManagement
public class JpaConfig {
}
