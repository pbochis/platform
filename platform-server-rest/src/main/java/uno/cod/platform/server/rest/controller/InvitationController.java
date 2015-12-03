package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.OrganizationMember;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.invitation.InvitationDto;
import uno.cod.platform.server.core.service.InvitationService;
import uno.cod.platform.server.rest.RestUrls;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class InvitationController {
    private final InvitationService service;

    @Autowired
    public InvitationController(InvitationService service) {
        this.service = service;
    }

    @RequestMapping(value = RestUrls.INVITE, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> invite(@Valid @RequestBody InvitationDto dto, Principal principal) throws MessagingException {
        service.invite(dto, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = RestUrls.INVITE_AUTH_TOKEN, method = RequestMethod.GET)
    public ResponseEntity<Long> authByToken(@PathVariable String token) {
        return new ResponseEntity<>(service.authByToken(token), HttpStatus.OK);
    }

}
