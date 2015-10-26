package uno.cod.platform.server.core.domain;

import javax.persistence.*;

/**
 * A submission for a (task, challenge, user)
 */
@Entity
@Table(name = "submission")
public class Submission {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id", nullable=false, updatable=false)
    private User user;

    @ManyToOne(optional=false)
    @JoinColumn(name="challenge_id", nullable=false, updatable=false)
    private Challenge challenge;

    @ManyToOne(optional=false)
    @JoinColumn(name="task_id", nullable=false, updatable=false)
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
