package uno.cod.platform.runtime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class RuntimeClient {
    private RestTemplate restTemplate;
    private RuntimeProperties runtimeProperties;

    @Autowired
    private RuntimeClient(@Qualifier("runtimeRestTemplate") RestTemplate restTemplate, RuntimeProperties runtimeProperties) {
        this.restTemplate = restTemplate;
        this.runtimeProperties = runtimeProperties;
    }

    public JsonNode postToRuntime(String runnerName, MultiValueMap<String, Object> form) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode obj;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(runtimeProperties.getUrl() + "/" + runnerName, form, String.class);
            obj = objectMapper.readValue(response.getBody(), JsonNode.class);
        } catch (HttpClientErrorException e) {
            obj = objectMapper.createObjectNode();
            ((ObjectNode) obj).put("error", e.getResponseBodyAsString());
        }
        return obj;
    }
}

