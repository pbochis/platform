package uno.cod.platform.runtime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class RuntimeClient {

    public static JsonNode postToRuntime(String runtimeUrl, String runnerName, MultiValueMap<String, Object> form) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode obj;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(runtimeUrl + "/" + runnerName, form, String.class);
            obj = objectMapper.readValue(response.getBody(), JsonNode.class);
        } catch (HttpClientErrorException e) {
            obj = objectMapper.createObjectNode();
            ((ObjectNode) obj).put("error", e.getResponseBodyAsString());
        }
        return obj;
    }
}

