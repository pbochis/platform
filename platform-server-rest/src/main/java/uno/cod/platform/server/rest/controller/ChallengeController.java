package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.cod.platform.server.core.dto.challenge.template.ChallengeTemplateShowDto;
import uno.cod.platform.server.core.service.ChallengeService;
import uno.cod.platform.server.rest.RestUrls;

@RestController
public class ChallengeController {

    private ChallengeService service;

    @Autowired
    public ChallengeController(ChallengeService challengeService){
        this.service = challengeService;
    }

    @RequestMapping(value = RestUrls.CHALLENGES_SCHEDULED_ID, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and @securityService.canAccessScheduledChallengeChallenge(principal, #id)")
    public ResponseEntity<ChallengeTemplateShowDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.findChallenge(id), HttpStatus.OK);
    }
}
