package tasktracker.tasktrackerapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasks-app/api/")
public class TaskTrackerController {

    @GetMapping("/tasks")
    @ResponseBody
    public String getAllTasks() {
        return "Hello World";
    }
}
