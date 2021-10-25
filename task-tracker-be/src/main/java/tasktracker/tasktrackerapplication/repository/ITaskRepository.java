package tasktracker.tasktrackerapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tasktracker.tasktrackerapplication.model.Task;

public interface ITaskRepository extends MongoRepository<Task, Integer> {
}
