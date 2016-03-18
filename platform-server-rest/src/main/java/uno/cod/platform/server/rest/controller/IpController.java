package uno.cod.platform.server.rest.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IpController {
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public ResponseEntity<String> ip() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Metadata-Flavor", "Google");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/ip", HttpMethod.GET, entity, String.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
