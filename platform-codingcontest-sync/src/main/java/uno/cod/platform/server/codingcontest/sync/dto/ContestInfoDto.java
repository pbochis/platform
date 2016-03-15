package uno.cod.platform.server.codingcontest.sync.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ContestInfoDto {
    private UUID uuid;
    private String accessKey;
    private int durationHours;
    private int durationMinutes;
    private int failedTestPenalty;
    private int uploadedCodePerLevelBonus;
    private String gameName;
    private String name;
    private String organizer;
    private String organizerEmail;
    private Date startTime;

    private List<ContestantDto> participations;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getFailedTestPenalty() {
        return failedTestPenalty;
    }

    public void setFailedTestPenalty(int failedTestPenalty) {
        this.failedTestPenalty = failedTestPenalty;
    }

    public int getUploadedCodePerLevelBonus() {
        return uploadedCodePerLevelBonus;
    }

    public void setUploadedCodePerLevelBonus(int uploadedCodePerLevelBonus) {
        this.uploadedCodePerLevelBonus = uploadedCodePerLevelBonus;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<ContestantDto> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ContestantDto> participations) {
        this.participations = participations;
    }
}
