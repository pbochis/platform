package uno.cod.platform.server.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * an invited user who has no account yet
 * when using the token for authentication
 * a user gets created and he gets redirected
 * the the challenge
 */
@Entity
@Table(name = "invitation")
public class Invitation {
    @Id
    private String token;

    @NotNull
    private String email;

    @ManyToOne(optional = false)
    private ScheduledChallenge challenge;

    @NotNull
    private ZonedDateTime expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ScheduledChallenge getChallenge() {
        return challenge;
    }

    public void setChallenge(ScheduledChallenge challenge) {
        this.challenge = challenge;
    }

    public ZonedDateTime getExpire() {
        return expire;
    }

    public void setExpire(ZonedDateTime expire) {
        this.expire = expire;
    }
}
