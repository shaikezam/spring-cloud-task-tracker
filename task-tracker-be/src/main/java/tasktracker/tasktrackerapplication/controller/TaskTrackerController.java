package tasktracker.tasktrackerapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tasktracker.tasktrackerapplication.model.Task;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks-app/api/")
public class TaskTrackerController {

    @GetMapping("/tasks")
    @ResponseBody
    public List<Task> getAllTasks() {
        return List.of(new Task("AAA", "aaa"),
                new Task("BBB", "bbb"));
    }
}
