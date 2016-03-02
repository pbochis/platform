package uno.cod.platform.server.core.dto.task;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.CodingSkill;
import uno.cod.platform.server.core.domain.Language;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.assignment.AssignmentShowDto;
import uno.cod.platform.server.core.dto.template.TemplateShowDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskShowDto extends AssignmentShowDto{
    public TaskShowDto(Task task){
        BeanUtils.copyProperties(task, this);
    }

    private List<TemplateShowDto> templates;

    private Map<CodingSkill, Double> skillMap;

    public Map<CodingSkill, Double> getSkillMap() {
        return skillMap;
    }

    public void setSkillMap(Map<CodingSkill, Double> skillMap) {
        this.skillMap = skillMap;
    }

    public List<TemplateShowDto> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateShowDto> templates) {
        this.templates = templates;
    }

    public void addTemplate(TemplateShowDto dto){
        if (templates == null){
            templates = new ArrayList<>();
        }
        templates.add(dto);
    }
}
