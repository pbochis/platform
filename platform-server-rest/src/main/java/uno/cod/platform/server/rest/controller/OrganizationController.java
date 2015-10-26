package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.dto.organization.OrganizationCreateDto;
import uno.cod.platform.server.core.service.OrganizationService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class OrganizationController {
    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> create(@Valid @RequestBody OrganizationCreateDto createDto, Principal principal) {
        organizationService.createFromDto(createDto, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String list() {
        return "";
    }

    @RequestMapping(value = "/organizations/{id}", method = RequestMethod.GET)
    public String get(@PathVariable Long id) {
        return "";
    }
}
