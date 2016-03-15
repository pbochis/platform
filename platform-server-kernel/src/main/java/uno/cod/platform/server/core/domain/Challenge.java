package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

@Entity
@Table(name = "challenge")
public class Challenge extends IdentifiableEntity{

    @Column(name = "challenge_name")
    private String name;
    @Column(name = "canonical_name")
    private String canonicalName;

    @ManyToOne
    private ChallengeTemplate challengeTemplate;

    @ManyToMany
    private Set<User> invitedUsers;

    @OneToMany(mappedBy = "challenge")
    private Set<Result> results;

    private boolean inviteOnly = true;

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

    public boolean isInviteOnly() {
        return inviteOnly;
    }

    public void setInviteOnly(boolean inviteOnly) {
        this.inviteOnly = inviteOnly;
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

    public ChallengeTemplate getChallengeTemplate() {
        return challengeTemplate;
    }

    public void setChallengeTemplate(ChallengeTemplate challengeTemplate) {
        this.challengeTemplate = challengeTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }
}
