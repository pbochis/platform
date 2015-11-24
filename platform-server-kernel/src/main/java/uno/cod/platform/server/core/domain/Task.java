package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A task is an atomic challenge that runs on the
 * coduno runtime
 */
@Entity
@Table(name = "task")
public class Task extends IdentifiableEntity {
    @Column(unique = true, nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String instructions;

    @ManyToMany(mappedBy = "tasks")
    private List<Challenge> challenges;

    @ManyToOne
    private Endpoint endpoint;

    @ManyToOne
    private Organization organization;

    @Column
    private boolean isPublic = false;

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

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    protected void addChallenge(Challenge challenge) {
        if (challenges == null) {
            challenges = new ArrayList<>();
        }
        challenges.add(challenge);
    }
}

