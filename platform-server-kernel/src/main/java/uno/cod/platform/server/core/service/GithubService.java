package uno.cod.platform.server.core.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GithubService {
    private static final Logger LOG = LoggerFactory.getLogger(GithubService.class);

    private static final URI BASE;

    static {
        try {
            BASE = new URL("https", "api.github.com", "").toURI();
        } catch (MalformedURLException|URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    private GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Attempts to use the GitHub API to translate an email address
     * into a username by searching GitHub.
     *
     * @see <a href="https://developer.github.com/v3/search/#search-users">"Search users" in the GitHub API docs</a>
     *
     * @param email The email address to look up.
     * @return A list of usernames as returned by the GitHub API ordered by
     *         best match, or an empty list if there were no matches or
     *         something went wrong.
     */
    public List<String> guessUsername(String email) {
        JsonNode obj;

        ResponseEntity<String> response = restTemplate.getForEntity(
                UriComponentsBuilder
                    .fromUri(BASE)
                    .path("/search/users")
                    .queryParam("q", email + " in:email")
                    .toUriString(),
                null,
                String.class
        );

        try {
            obj = objectMapper.readValue(response.getBody(), JsonNode.class);
        } catch(IOException ioe) {
            LOG.warn("Swallowing exception.", ioe);
            return Collections.emptyList();
        }

        final int n = obj.path("item_count").asInt(0);
        if (n < 1) {
            return Collections.emptyList();
        }

        final List<String> results = new ArrayList<>(n);
        for (JsonNode node : obj.path("items")) {
            results.add(node.get("login").asText());
        }

        return results;
    }
}
