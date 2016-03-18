package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.dto.challenge.ChallengeCreateDto;
import uno.cod.platform.server.core.dto.challenge.template.ChallengeTemplateShowDto;
import uno.cod.platform.server.core.service.ChallengeService;
import uno.cod.platform.server.core.service.ChallengeTemplateService;
import uno.cod.platform.server.rest.RestUrls;

import javax.validation.Valid;

@RestController
public class ChallengeController {

    private final ChallengeService service;
    private final ChallengeTemplateService challengeTemplateService;

    @Autowired
    public ChallengeController(ChallengeService challengeService, ChallengeTemplateService challengeTemplateService){
        this.service = challengeService;
        this.challengeTemplateService = challengeTemplateService;
    }

    @RequestMapping(value = RestUrls.CHALLENGES_ID, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and @securityService.canAccessScheduledChallengeChallenge(principal, #id)")
    public ResponseEntity<ChallengeTemplateShowDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(challengeTemplateService.findByChallengeId(id), HttpStatus.OK);
    }

    @RequestMapping(value = RestUrls.CHALLENGE_TEMPLATES_CHALLENGE, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() and @securityService.canAccessChallenge(principal, #id)")
    public ResponseEntity createChallenge(@PathVariable Long id, @Valid @RequestBody ChallengeCreateDto dto){
        service.createFromDto(id, dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
