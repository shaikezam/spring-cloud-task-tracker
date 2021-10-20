package task.tracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task.tracker.exception.ObjectMissingProperties;
import task.tracker.exception.ObjectNotFoundException;
import task.tracker.model.Task;
import task.tracker.service.TaskService;

@RestController
@RequestMapping("/webapi")
public class TaskController {

	private TaskService taskService;
	private Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		logger.debug("Fetching tasks");
		return taskService.getTasks();
	}

	@PostMapping("/tasks")
	public int createTask(@RequestBody Task task) throws ObjectMissingProperties {
		logger.debug("Create new task: {}", task);
		return taskService.addTask(task);
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Object> getTaskByID(@PathVariable int id) throws ObjectNotFoundException {
		logger.debug("Fetch task: {}", id);
		Task task = taskService.getTask(id);
		if (task != null) {
			return new ResponseEntity<>(task, HttpStatus.OK);
		}
		logger.warn("Task {} not found", id);
		throw new ObjectNotFoundException(String.format("Task %s not found", id));
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Object> updateTaskByID(@PathVariable int id, @RequestBody Task task)
			throws ObjectMissingProperties {
		task.setId(id);
		logger.debug("Update task: {}", task);
		taskService.updateTask(task);
		return new ResponseEntity<>(id, null, HttpStatus.OK);
	}

	@DeleteMapping("/tasks/{id}")
	public int deleteTaskByID(@PathVariable int id) {
		logger.debug("Delete task: {}", id);
		return taskService.deleteTask(id);
	}

}
