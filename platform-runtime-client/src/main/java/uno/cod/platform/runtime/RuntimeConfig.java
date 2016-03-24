package uno.cod.platform.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableConfigurationProperties(RuntimeProperties.class)
public class RuntimeConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeConfig.class);

    @Autowired
    private RuntimeProperties runtimeProperties;

    @Bean(name = "runtimeRestTemplate")
    public RestTemplate runtimeRestTemplate() {
        ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        if(runtimeProperties.getUsername() != null &&
                !runtimeProperties.getUsername().isEmpty()) {
            LOGGER.info("enabled basic authentication for runtime client");
            List<ClientHttpRequestInterceptor> interceptors = Collections
                    .singletonList(new BasicAuthorizationInterceptor(runtimeProperties.getUsername(),
                                    runtimeProperties.getPassword()));
            clientHttpRequestFactory = new InterceptingClientHttpRequestFactory(clientHttpRequestFactory,
                    interceptors);
        }

        return new RestTemplate(clientHttpRequestFactory);
    }

    private static class BasicAuthorizationInterceptor
            implements ClientHttpRequestInterceptor {

        private final String username;

        private final String password;

        BasicAuthorizationInterceptor(String username, String password) {
            this.username = username;
            this.password = password == null ? "" : password;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            String token = Base64Utils.encodeToString(
                    (this.username + ":" + this.password).getBytes(UTF_8));
            request.getHeaders().add("Authorization", "Basic " + token);
            return execution.execute(request, body);
        }

    }
}
