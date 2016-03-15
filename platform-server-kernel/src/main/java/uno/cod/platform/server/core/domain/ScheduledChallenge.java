package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

@Entity
@Table(name = "scheduled_challenge")
public class ScheduledChallenge extends IdentifiableEntity{
    @ManyToOne
    private Challenge challenge;

    @ManyToMany
    private Set<User> invitedUsers;

    @OneToMany(mappedBy = "challenge")
    private Set<Result> results;

    /**
     * Start of the challenge, users can already be invited before
     */
    private ZonedDateTime startDate;

    /**
     * End of the challenge, the challenge is read only afterwards
     */
    private ZonedDateTime endDate;

    public Set<User> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(Set<User> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Set<Result> getResults() {
        return Collections.unmodifiableSet(results);
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    protected void addInvitedUser(User user) {
        if (invitedUsers == null) {
            invitedUsers = new HashSet<>();
        }
        invitedUsers.add(user);
    }

    public void addResult(Result result) {
        if (results == null) {
            results = new HashSet<>();
        }
        results.add(result);
        result.setChallenge(this);
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
