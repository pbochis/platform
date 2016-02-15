package uno.cod.platform.server.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import uno.cod.platform.server.core.Profiles;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JdbcConfig {

    @Autowired
    private DataSourceProperties properties;

    @Bean
    @Profile(Profiles.APPENGINE)
    public DataSource dataSource() {
        Properties addProperties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        addProperties.setProperty("serverSslCert", "classpath:gae-mysql-ca.pem");
        dataSource.setConnectionProperties(addProperties);
        return dataSource;
    }


}
