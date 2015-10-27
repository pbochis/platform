package uno.cod.platform.server.core.dto.user;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.User;

public class UserShowDto {
    private Long id;
    private String username;

    public UserShowDto(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
