package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.dto.task.TaskCreateDto;
import uno.cod.platform.server.core.dto.task.TaskShowDto;
import uno.cod.platform.server.core.service.TaskService;
import uno.cod.platform.server.rest.RestUrls;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = RestUrls.TASKS, method = RequestMethod.POST)
    public ResponseEntity<String> create(@Valid @RequestBody TaskCreateDto dto) {
        taskService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = RestUrls.TASKS_ID, method = RequestMethod.GET)
    public ResponseEntity<TaskShowDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = RestUrls.TASKS, method = RequestMethod.GET)
    public ResponseEntity<List<TaskShowDto>> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }
}