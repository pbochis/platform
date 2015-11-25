package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.user.UserCreateDto;
import uno.cod.platform.server.core.dto.user.UserShowDto;
import uno.cod.platform.server.core.exception.ResourceConflictException;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createFromDto(UserCreateDto dto) {
        User found= repository.findByUsernameOrEmail(dto.getNick(), dto.getEmail());
        if(found != null)
            throw new ResourceConflictException("user.name.exists");
        User user = new User();
        user.setUsername(dto.getNick());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        repository.save(user);
    }

    public List<UserShowDto> listUsers() {
        return repository.findAll().stream().map(UserShowDto::new).collect(Collectors.toList());
    }
}

