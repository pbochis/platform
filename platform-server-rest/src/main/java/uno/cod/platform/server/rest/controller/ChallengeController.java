package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.dto.challenge.ChallengeCreateDto;
import uno.cod.platform.server.core.dto.challenge.ChallengeShowDto;
import uno.cod.platform.server.core.service.ChallengeService;
import uno.cod.platform.server.rest.RestUrls;

import java.util.List;

@RestController
public class ChallengeController {
    private final ChallengeService service;

    @Autowired
    public ChallengeController(ChallengeService service) {
        this.service = service;
    }

    @RequestMapping(value = RestUrls.CHALLENGES, method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody ChallengeCreateDto dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @RequestMapping(value = RestUrls.CHALLENGES, method = RequestMethod.GET)
    public ResponseEntity<List<ChallengeShowDto>> list() {
        return new ResponseEntity<List<ChallengeShowDto>>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = RestUrls.CHALLENGES_ID, method = RequestMethod.GET)
    public ResponseEntity<ChallengeShowDto> get(@PathVariable Long id) {
        return new ResponseEntity<ChallengeShowDto>(service.findById(id), HttpStatus.OK);
    }
}
