package task.tracker.service;

import java.util.List;

import task.tracker.exception.ObjectMissingProperties;
import task.tracker.model.Task;

public interface TaskService {
	public List<Task> getTasks();

	public Task getTask(int id);

	public int addTask(Task task) throws ObjectMissingProperties;

	public int updateTask(Task task) throws ObjectMissingProperties;

	public int deleteTask(int id);
}
