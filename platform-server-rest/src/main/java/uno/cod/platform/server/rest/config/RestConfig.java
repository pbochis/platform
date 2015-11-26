package uno.cod.platform.server.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import uno.cod.platform.server.core.Profiles;

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

    @Bean
    @Profile(Profiles.DEVELOPMENT)
    public WebMvcConfigurer devCorsConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    @Profile(Profiles.PRODUCTION)
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://app.cod.uno");
            }
        };
    }
}
