package uno.cod.platform.server.codingcontest.sync.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class CodingcontestDto {
    private String uuid;
    private String name;
    private String gameName;
    private String location;
    private boolean onSiteEvent;
    @JsonProperty("private")
    private boolean isPrivate;

    private Date startTime;
    private Date checkinTime;

    private String organizer;
    private String organizerEmail;

    private int maxRegistrations;
    private int maxTeamSize;

    private int failedTestPenalty;
    private int uploadedCodePerLevelBonus;
    private int durationHours;
    private int durationMinutes;

    private List<ParticipationDto> participations;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOnSiteEvent() {
        return onSiteEvent;
    }

    public void setOnSiteEvent(boolean onSiteEvent) {
        this.onSiteEvent = onSiteEvent;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
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

    public int getMaxRegistrations() {
        return maxRegistrations;
    }

    public void setMaxRegistrations(int maxRegistrations) {
        this.maxRegistrations = maxRegistrations;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
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

    public List<ParticipationDto> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationDto> participations) {
        this.participations = participations;
    }
}
