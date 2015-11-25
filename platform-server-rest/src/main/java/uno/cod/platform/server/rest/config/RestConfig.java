package uno.cod.platform.server.rest.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"uno.cod.platform.server.rest"})
public class RestConfig {

    @Bean(name = "exceptionMessageSource")
    public ResourceBundleMessageSource exceptionMessageSource() {
        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setFallbackToSystemLocale(false);
        bean.setBasename("i18n/exceptions");
        return bean;
    }
}
