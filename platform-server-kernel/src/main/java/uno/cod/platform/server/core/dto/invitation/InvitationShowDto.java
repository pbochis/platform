package uno.cod.platform.server.core.dto.invitation;

import java.time.ZonedDateTime;

public class InvitationShowDto {
    private String username;
    private String email;
    private String token;
    private ZonedDateTime expire;
    private ZonedDateTime started;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getExpire() {
        return expire;
    }

    public void setExpire(ZonedDateTime expire) {
        this.expire = expire;
    }

    public ZonedDateTime getStarted() {
        return started;
    }

    public void setStarted(ZonedDateTime started) {
        this.started = started;
    }
}
