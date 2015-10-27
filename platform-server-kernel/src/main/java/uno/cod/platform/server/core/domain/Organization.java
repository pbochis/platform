package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * An organization, can be a school, company or something else
 * The concept is similar to github organizations
 */
@Entity
@Table(name = "organization",
       uniqueConstraints = {@UniqueConstraint(name = "nick",columnNames = "nick")}
)
public class Organization implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    @Size(min = 5, max = 40)
    @Pattern(regexp = "^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$")
    private String nick;

    @Column(nullable = false,length = 255)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
