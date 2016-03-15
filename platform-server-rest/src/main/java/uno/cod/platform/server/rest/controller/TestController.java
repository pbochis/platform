package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.cod.platform.server.core.dto.test.TestShowDto;
import uno.cod.platform.server.core.service.TestService;
import uno.cod.platform.server.rest.RestUrls;

import java.util.List;

/**
 * Created by vbalan on 2/25/2016.
 */
@RestController
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = RestUrls.TASKS_ID_TESTS, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<TestShowDto>> findByTaskId(@PathVariable Long id) {
        return new ResponseEntity<List<TestShowDto>>(testService.findByTaskId(id), HttpStatus.OK);
    }
}
