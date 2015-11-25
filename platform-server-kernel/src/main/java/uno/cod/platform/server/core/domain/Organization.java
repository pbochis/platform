package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An organization, can be a school, company or something else
 * The concept is similar to github organizations
 */
@Entity
@Table(name = "organization",
        uniqueConstraints = {@UniqueConstraint(name = "nick", columnNames = "nick")}
)
public class Organization extends IdentifiableEntity {
    @Column(unique = true, nullable = false, length = 40)
    @Size(min = 5, max = 40)
    @Pattern(regexp = "^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$")
    private String nick;

    @Column(nullable = false, length = 255)
    private String name;

    /**
     * all organization members
     */
    @OneToMany(mappedBy = "key.organization")
    private Set<OrganizationMember> members;

    /**
     * challenges owned by the organization
     */
    @OneToMany(mappedBy = "organization")
    private Set<Challenge> challenges;

    @OneToMany(mappedBy = "organization")
    private Set<Task> tasks;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OrganizationMember> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    protected void setMembers(Set<OrganizationMember> members) {
        this.members = members;
    }

    public Set<Challenge> getChallenges() {
        return Collections.unmodifiableSet(challenges);
    }

    protected void setChallenges(Set<Challenge> challenges) {
        this.challenges = challenges;
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    protected void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addOrganizationMember(OrganizationMember member) {
        if (member == null) {
            throw new IllegalArgumentException("organization.member.invalid");
        }
        if (members == null) {
            members = new HashSet<>();
        }
        members.add(member);
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task.invalid");
        }
        if (tasks == null) {
            tasks = new HashSet<>();
        }
        task.setOrganization(this);
        tasks.add(task);
    }
}
