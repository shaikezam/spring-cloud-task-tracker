package tasktracker.tasktrackerapplication.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tasktracker.tasktrackerapplication.model.Task;
import tasktracker.tasktrackerapplication.repository.ITaskRepository;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks-app/api/")
public class TaskTrackerController {

    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping("/tasks")
    @ResponseBody
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody Task task) {
        taskRepository.insert(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskByID(@PathVariable int id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                ));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> updateTaskByID(@PathVariable String id, @RequestBody Task task) {
        task.setId(id);
        taskRepository.save(task);
        return new ResponseEntity<>(id, null, HttpStatus.OK);
    }
}
