package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.*;

/**
 * A task is an atomic challenge that runs on the
 * coduno runtime
 */
@Entity
@Table(name = "task")
public class Task extends Assignment {
    @ManyToMany(mappedBy = "tasks")
    private List<Challenge> challenges;

    @ManyToOne
    private Endpoint endpoint;

    @ManyToOne
    private Organization organization;

    @Column
    private boolean isPublic;

    @OneToMany(mappedBy = "result")
    private Set<Submission> submissions;

    @ElementCollection
    @CollectionTable(name = "task_skillmap")
    private Map<CodingSkill, Double> skillMap;

    @OneToMany(mappedBy = "task")
    private List<Test> tests;

    @OneToMany(mappedBy = "task")
    private List<Template> templates;

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

    public Set<Submission> getSubmissions() {
        return Collections.unmodifiableSet(submissions);
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<Test> getTests() {
        return Collections.unmodifiableList(tests);
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Map<CodingSkill, Double> getSkillMap() {
        return skillMap;
    }

    public void setSkillMap(Map<CodingSkill, Double> skillMap) {
        this.skillMap = skillMap;
    }

    protected void addChallenge(Challenge challenge) {
        if (challenges == null) {
            challenges = new ArrayList<>();
        }
        challenges.add(challenge);
    }

    public void addSubmission(Submission submission) {
        if (submissions == null) {
            submissions = new HashSet<>();
        }
        submissions.add(submission);
        submission.setTask(this);
    }

    public void addTest(Test test) {
        if (tests == null) {
            tests = new ArrayList<>();
        }
        tests.add(test);
        test.setTask(this);
    }

    public void addSkill(CodingSkill skill, Double value) {
        if (skillMap == null) {
            skillMap = new HashMap<>();
        }
        skillMap.put(skill, value);
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public void addTemplate(Template template){
        if (templates == null){
            templates = new ArrayList<>();
        }
        templates.add(template);
    }
}

