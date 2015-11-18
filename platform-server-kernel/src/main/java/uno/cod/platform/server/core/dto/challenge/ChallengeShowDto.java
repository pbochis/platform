package uno.cod.platform.server.core.dto.challenge;

import uno.cod.platform.server.core.dto.task.TaskShowDto;

import java.util.List;

public class ChallengeShowDto {
    private String name;
    private List<TaskShowDto> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskShowDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskShowDto> tasks) {
        this.tasks = tasks;
    }
}
