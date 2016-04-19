package uno.cod.platform.server.core.dto.user;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.User;

import java.util.UUID;

public class UserShortShowDto {
    private UUID id;
    private String username;

    public UserShortShowDto(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
