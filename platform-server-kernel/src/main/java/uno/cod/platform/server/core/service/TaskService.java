package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.task.TaskCreateDto;
import uno.cod.platform.server.core.dto.task.TaskShowDto;
import uno.cod.platform.server.core.mapper.TaskMapper;
import uno.cod.platform.server.core.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void save(TaskCreateDto dto) {
        Task task = new Task();
        task.setName(dto.getName());

        repository.save(task);
    }

    public TaskShowDto findById(Long id) {
        return TaskMapper.map(repository.findOne(id));
    }

    public List<TaskShowDto> findAll() {
        return TaskMapper.map(repository.findAll());
    }
}
