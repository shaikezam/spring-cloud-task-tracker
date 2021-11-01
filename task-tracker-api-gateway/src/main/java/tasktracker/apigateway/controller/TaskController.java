package tasktracker.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TaskController {

    @GetMapping("/pmet")
    public String sendTask() {
        return "Hello";
    }
}
