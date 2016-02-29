package uno.cod.platform.server.core.dto.task;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.CodingSkill;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.assignment.AssignmentShowDto;

import java.util.Map;

public class TaskShowDto extends AssignmentShowDto{
    public TaskShowDto(Task task){
        BeanUtils.copyProperties(task, this);
    }

    private Map<CodingSkill, Double> skillMap;

    public Map<CodingSkill, Double> getSkillMap() {
        return skillMap;
    }

    public void setSkillMap(Map<CodingSkill, Double> skillMap) {
        this.skillMap = skillMap;
    }
}
