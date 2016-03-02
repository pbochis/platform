package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.domain.Template;
import uno.cod.platform.server.core.dto.task.TaskShowDto;
import uno.cod.platform.server.core.dto.template.TemplateShowDto;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskShowDto map(Task task) {
        TaskShowDto dto = new TaskShowDto(task);
        if (task.getEndpoint() != null) {
            dto.setEndpoint(EndpointMapper.map(task.getEndpoint()));
        }
        for (Template template: task.getTemplates()){
            dto.addTemplate(new TemplateShowDto(template));
        }
        return dto;
    }

    public static List<TaskShowDto> map(List<Task> tasks) {
        return tasks.stream().map(e -> map(e)).collect(Collectors.toList());
    }
}
