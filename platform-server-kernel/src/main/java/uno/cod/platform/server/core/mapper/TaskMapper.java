package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.task.TaskCreateDto;
import uno.cod.platform.server.core.dto.task.TaskShowDto;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskShowDto map(Task task){
        return new TaskShowDto(task);
    }

    public static List<TaskShowDto> map(List<Task> tasks){
        return tasks.stream().map(e -> map(e)).collect(Collectors.toList());
    }
}