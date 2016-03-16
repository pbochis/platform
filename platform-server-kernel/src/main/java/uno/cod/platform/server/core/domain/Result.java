package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Result holds the performance of an user for a challenge.
 * It is fixed once the user has completed the Challenge or timed out.
 */
@Entity
@Table(name = "result")
public class Result extends IdentifiableEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Challenge challenge;

    @OrderColumn
    @ElementCollection
    @CollectionTable(name = "result_starttimes")
    private List<ZonedDateTime> startTimes;

    private ZonedDateTime started;

    private ZonedDateTime finished;

    @OneToMany(mappedBy = "result")
    private Set<Submission> submissions;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public List<ZonedDateTime> getStartTimes() {
        if (startTimes == null) {
            return null;
        }
        return Collections.unmodifiableList(startTimes);
    }

    public void setStartTimes(List<ZonedDateTime> startTimes) {
        this.startTimes = startTimes;
    }

    public ZonedDateTime getStarted() {
        return started;
    }

    public void setStarted(ZonedDateTime started) {
        this.started = started;
    }

    public ZonedDateTime getFinished() {
        return finished;
    }

    public void setFinished(ZonedDateTime finished) {
        this.finished = finished;
    }

    public Set<Submission> getSubmissions() {
        return Collections.unmodifiableSet(submissions);
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public void addSubmission(Submission submission) {
        if (submissions == null) {
            submissions = new HashSet<>();
        }
        submissions.add(submission);
        submission.setResult(this);
    }

    public boolean start(int index) {
        if (startTimes == null) {
            startTimes = new ArrayList<>();
        }
        if (index >= startTimes.size()) {
            for (int i = startTimes.size() - 1; i <= index; i++) {
                startTimes.add(null);
            }
        }
        if (startTimes.get(index) == null) {
            startTimes.set(index, ZonedDateTime.now());
            return true;
        }
        return false;
    }
}
