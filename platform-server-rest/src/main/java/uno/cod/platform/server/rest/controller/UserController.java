package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.user.UserCreateDto;
import uno.cod.platform.server.core.dto.user.UserShowDto;
import uno.cod.platform.server.core.service.UserService;
import uno.cod.platform.server.rest.RestUrls;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = RestUrls.USERS, method = RequestMethod.POST)
    public ResponseEntity<String> create(@Valid @RequestBody UserCreateDto dto) {
        userService.createFromDto(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = RestUrls.USERS, method = RequestMethod.GET)
    public List<UserShowDto> list() {
        return userService.listUsers();
    }

    @RequestMapping(value = RestUrls.USER, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserShowDto> get(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(new UserShowDto(user), HttpStatus.OK);
    }
}
