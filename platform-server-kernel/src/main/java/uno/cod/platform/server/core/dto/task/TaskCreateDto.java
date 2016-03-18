package uno.cod.platform.server.core.dto.task;

import org.hibernate.validator.constraints.NotEmpty;
import uno.cod.platform.server.core.domain.CodingSkill;
import uno.cod.platform.server.core.dto.assignment.AssignmentCreateDto;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class TaskCreateDto extends AssignmentCreateDto{
    private boolean isPublic;

    @NotNull
    @NotEmpty
    private Map<CodingSkill, Double> skillMap;

    private Long runnerId;

    public Long getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Long runnerId) {
        this.runnerId = runnerId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Map<CodingSkill, Double> getSkillMap() {
        return skillMap;
    }

    public void setSkillMap(Map<CodingSkill, Double> skillMap) {
        this.skillMap = skillMap;
    }
}
