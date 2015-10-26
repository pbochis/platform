package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * A task is an atomic challenge that runs on the
 * coduno runtime
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    private String name;

    @ManyToMany(mappedBy = "tasks")
    private List<Challenge> challenges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Challenge> getChallenges() {
        return Collections.unmodifiableList(challenges);
    }

    protected void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
}
