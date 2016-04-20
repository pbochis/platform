package uno.cod.platform.server.core.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GithubService {
    private static final Logger LOG = LoggerFactory.getLogger(GithubService.class);
    private static final String BASE = "https://api.github.com";

    private final RestTemplate restTemplate;

    @Autowired
    private GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Attempts to use the GitHub API to translate an email address
     * into a username by searching GitHub.
     *
     * @param email The email address to look up.
     * @return A list of usernames as returned by the GitHub API ordered by
     * best match, or an empty list if there were no matches or
     * something went wrong.
     * @see <a href="https://developer.github.com/v3/search/#search-users">"Search users" in the GitHub API docs</a>
     */
    public List<String> guessUsername(String email) {
        JsonNode body;

        try {
            body = restTemplate.getForObject(
                    UriComponentsBuilder
                            .fromUriString(BASE)
                            .path("/search/users")
                            .queryParam("q", email + " in:email")
                            .build()
                            .toUriString(),
                    JsonNode.class
            );
        } catch (Exception e) {
            LOG.warn("Swallowing exception.", e);
            return Collections.emptyList();
        }

        final int n = body.path("total_count").asInt(0);
        if (n < 1) {
            return Collections.emptyList();
        }

        return StreamSupport
                .stream(body.path("items").spliterator(), false)
                .map(x -> x.get("login").asText())
                .collect(Collectors.toList());
    }
}
