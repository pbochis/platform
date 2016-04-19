package uno.cod.platform.server.core.dto.challenge;

import uno.cod.platform.server.core.dto.user.UserShortShowDto;

import java.time.ZonedDateTime;

public class LeaderboardEntryDto {
    private UserShortShowDto user;
    private long tasksCompleted;
    private ZonedDateTime lastLevelFinishDate;

    public UserShortShowDto getUser() {
        return user;
    }

    public void setUser(UserShortShowDto user) {
        this.user = user;
    }

    public long getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(long tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public ZonedDateTime getLastLevelFinishDate() {
        return lastLevelFinishDate;
    }

    public void setLastLevelFinishDate(ZonedDateTime lastLevelFinishDate) {
        this.lastLevelFinishDate = lastLevelFinishDate;
    }
}
