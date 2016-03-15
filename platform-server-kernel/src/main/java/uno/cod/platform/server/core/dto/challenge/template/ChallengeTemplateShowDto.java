package uno.cod.platform.server.core.dto.challenge.template;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.dto.assignment.AssignmentShowDto;
import uno.cod.platform.server.core.dto.task.TaskShowDto;

import java.util.List;

public class ChallengeTemplateShowDto extends AssignmentShowDto{
    private List<TaskShowDto> tasks;

    public ChallengeTemplateShowDto(ChallengeTemplate challengeTemplate){
        BeanUtils.copyProperties(challengeTemplate, this);
    }

    public List<TaskShowDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskShowDto> tasks) {
        this.tasks = tasks;
    }
}
