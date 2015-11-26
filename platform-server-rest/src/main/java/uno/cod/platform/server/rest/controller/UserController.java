package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.cod.platform.server.core.dto.user.UserCreateDto;
import uno.cod.platform.server.core.dto.user.UserShowDto;
import uno.cod.platform.server.core.service.UserService;
import uno.cod.platform.server.rest.RestUrls;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;

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
    public ResponseEntity<UserShowDto> get(Principal principal) {
        return new ResponseEntity<UserShowDto>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    }
}
