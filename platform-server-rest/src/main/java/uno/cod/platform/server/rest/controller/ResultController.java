package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.result.ResultShowDto;
import uno.cod.platform.server.core.service.ResultService;
import uno.cod.platform.server.rest.RestUrls;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public class ResultController {
    private final ResultService service;

    @Autowired
    public ResultController(ResultService service) {
        this.service = service;
    }

    @RequestMapping(value = RestUrls.RESULTS, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResultShowDto> create(@RequestBody @NotNull UUID challengeId, @AuthenticationPrincipal User principal) {
        return new ResponseEntity<>(service.save(challengeId, principal), HttpStatus.OK);
    }

    @RequestMapping(value = RestUrls.RESULTS_ID, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResultShowDto> findOne(@PathVariable UUID id) {
        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = RestUrls.RESULTS_CHALLENGE_ID_MY, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResultShowDto> findMyResultForChallenge(@PathVariable UUID id, @AuthenticationPrincipal User principal) {
        return new ResponseEntity<>(service.findOneByUserAndChallenge(principal.getId(), id), HttpStatus.OK);
    }
}
