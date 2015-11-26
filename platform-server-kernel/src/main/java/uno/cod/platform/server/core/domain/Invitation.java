package uno.cod.platform.server.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    private String email;

    @ManyToOne(optional = false)
    private Challenge challenge;

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

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public ZonedDateTime getExpire() {
        return expire;
    }

    public void setExpire(ZonedDateTime expire) {
        this.expire = expire;
    }
}
