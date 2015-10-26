package uno.cod.platform.server.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeController {
    @RequestMapping(value = "/challenges", method = RequestMethod.POST)
    public ResponseEntity<String> create() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/challenges", method = RequestMethod.GET)
    public String list() {
        return "";
    }

    @RequestMapping(value = "/challenges/{id}", method = RequestMethod.GET)
    public String get(@PathVariable Long id) {
        return "";
    }
}
