package uno.cod.platform.server.codingcontest.sync.dto;

import java.util.List;
import java.util.UUID;

public class ContestantDto {
    private UUID uuid;
    private String name;
    private String firstName;
    private String lastName;
    private String email;

    private String codingLanguage;
    private int rank;
    private int failedTests;
    private int levelsCompleted;
    private boolean finished;
    private List<ContestResultDto> results;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodingLanguage() {
        return codingLanguage;
    }

    public void setCodingLanguage(String codingLanguage) {
        this.codingLanguage = codingLanguage;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(int failedTests) {
        this.failedTests = failedTests;
    }

    public int getLevelsCompleted() {
        return levelsCompleted;
    }

    public void setLevelsCompleted(int levelsCompleted) {
        this.levelsCompleted = levelsCompleted;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<ContestResultDto> getResults() {
        return results;
    }

    public void setResults(List<ContestResultDto> results) {
        this.results = results;
    }
}
