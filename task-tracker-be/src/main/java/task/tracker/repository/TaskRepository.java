package task.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import task.tracker.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
