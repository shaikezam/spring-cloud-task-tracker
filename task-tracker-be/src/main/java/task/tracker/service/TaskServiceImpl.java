package task.tracker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.tracker.exception.ObjectMissingProperties;
import task.tracker.model.Task;
import task.tracker.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	private final String MISSING_PROPERTIES = "Need to update all properties";

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task getTask(int id) {
		return taskRepository.findOne(id);
	}

	@Override
	public int addTask(Task task) throws ObjectMissingProperties {
		validTask(task);
		taskRepository.saveAndFlush(task);
		return task.getId();
	}

	@Override
	public int updateTask(Task task) throws ObjectMissingProperties {
		validTask(task);
		taskRepository.save(task);
		return task.getId();
	}

	@Override
	public int deleteTask(int id) {
		taskRepository.delete(id);
		return id;
	}

	private void validTask(Task task) throws ObjectMissingProperties {
		if ((task.getDescription() == null || task.getDescription().isEmpty())
				&& (task.getTitle() == null || task.getTitle().isEmpty())) {
			logger.error(MISSING_PROPERTIES);
			throw new ObjectMissingProperties(MISSING_PROPERTIES);
		}
	}

}
