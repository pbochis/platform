package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.service.SubmissionService;
import uno.cod.platform.server.core.service.WebSocketService;
import uno.cod.platform.server.rest.RestUrls;

import java.io.IOException;
import java.security.Principal;

@RestController
public class SubmissionController {
    private final SubmissionService service;

    @Autowired
    public SubmissionController(SubmissionService service) {
        this.service = service;
    }

    @RequestMapping(value = RestUrls.RESULTS_TASKS_SUBMISSIONS, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> create(@PathVariable Long resultId,
                                         @PathVariable Long taskId,
                                         @RequestParam("language") String language,
                                         @RequestParam("file") MultipartFile file,
                                         @AuthenticationPrincipal User principal) throws IOException {
        service.create(principal, resultId, taskId, file, language);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
