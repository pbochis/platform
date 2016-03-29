package uno.cod.platform.server.core.dto.challenge;

import uno.cod.platform.server.core.dto.user.UserShowDto;

import java.time.ZonedDateTime;

public class LeaderboardEntryDto {
    private UserShowDto user;
    private long tasksCompleted;
    private ZonedDateTime lastLevelFinishDate;

    public UserShowDto getUser() {
        return user;
    }

    public void setUser(UserShowDto user) {
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
