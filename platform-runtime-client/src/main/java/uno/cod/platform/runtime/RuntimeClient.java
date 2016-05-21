package uno.cod.platform.runtime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.*;

import java.io.IOException;

@Service
public class RuntimeClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeClient.class);

    private final RestTemplate restTemplate;
    private final RuntimeProperties runtimeProperties;
    private final ObjectMapper objectMapper;

    @Autowired
    private RuntimeClient(@Qualifier("runtimeRestTemplate") RestTemplate restTemplate, RuntimeProperties runtimeProperties) {
        this.restTemplate = restTemplate;
        this.runtimeProperties = runtimeProperties;
        this.objectMapper = new ObjectMapper();
    }

    public JsonNode postToRuntime(String runnerPath, MultiValueMap<String, Object> form) throws IOException {
        String endpoint = runtimeProperties.getUrl() + runnerPath;
        JsonNode obj;
        try {
            LOGGER.trace("calling {} with parameters {}", endpoint, form);
            obj = restTemplate.postForObject(endpoint, form, JsonNode.class);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            LOGGER.debug("Got HTTP {} and body {}", e.getStatusCode(), e.getResponseBodyAsString(), e);
            obj = objectMapper.createObjectNode();
            ((ObjectNode) obj).put("failure", e.getResponseBodyAsString());
        }
        return obj;
    }
}

