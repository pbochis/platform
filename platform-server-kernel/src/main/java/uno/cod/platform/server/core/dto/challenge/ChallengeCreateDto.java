package uno.cod.platform.server.core.dto.challenge;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ChallengeCreateDto {
    @NotNull
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    private List<Long> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getTasks() {
        return tasks;
    }

    public void setTasks(List<Long> tasks) {
        this.tasks = tasks;
    }
}