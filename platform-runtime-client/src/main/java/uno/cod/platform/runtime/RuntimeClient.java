package uno.cod.platform.runtime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
        JsonNode obj = null;
        try {
            LOGGER.trace("calling {} with parameters {}", endpoint, form);
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, form, String.class);
            obj = objectMapper.readValue(response.getBody(), JsonNode.class);
        } catch (HttpServerErrorException e) {
            LOGGER.debug("got error response status {} from runtime, body: {}", e.getStatusCode(), e.getResponseBodyAsString(), e);
            obj = objectMapper.createObjectNode();
            ((ObjectNode) obj).put("error", e.getResponseBodyAsString());
        } catch (HttpClientErrorException e) {
            obj = objectMapper.createObjectNode();
            ((ObjectNode) obj).put("error", e.getResponseBodyAsString());
            LOGGER.error("got generic rest client exception", e);
        }
        return obj;
    }
}

