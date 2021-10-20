package task.tracker.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import task.tracker.BootMavenApplication;
import task.tracker.exception.ObjectMissingProperties;
import task.tracker.model.Task;
import task.tracker.repository.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootMavenApplication.class)
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;
	@MockBean
	private TaskRepository taskRepositoryMock;
	private Task task = null;

	@Test
	public void testCreateTaskSuccess() throws ObjectMissingProperties {
		task = new Task("1st task", "Hello World");
		task.setId(1);
		when(taskRepositoryMock.save(task)).thenReturn(task);
		int id = taskService.addTask(task);
		assertEquals(1, id);
	}

	@Test
	public void testFindTaskSuccess() {
		task = new Task("1st task", "Hello World");
		task.setId(1);
		when(taskRepositoryMock.findOne(1)).thenReturn(task);
		assertEquals("1st task", taskService.getTask(task.getId()).getTitle());
	}

	@Test
	public void testFindTasksSuccess() {
		List<Task> tasks = Arrays
				.asList(new Task[] { new Task("1st task", "1st tasks"), new Task("2nd task", "2nd tasks") });
		when(taskRepositoryMock.findAll()).thenReturn(tasks);
		assertEquals(tasks, taskService.getTasks());
	}

	@Test
	public void testUpdateTaskSuccess() throws ObjectMissingProperties {
		task = new Task("2st task", "Hello World V2");
		task.setId(1);
		when(taskRepositoryMock.save(task)).thenReturn(task);
		int id = taskService.updateTask(task);
		assertEquals(1, id);
	}

	@Test
	public void testDeleteTaskSuccess() {
		task = new Task("1st task", "Hello World");
		task.setId(1);
		doNothing().when(taskRepositoryMock).delete(task.getId());
		int id = taskService.deleteTask(task.getId());
		assertEquals(1, id);
	}

	@Test(expected = ObjectMissingProperties.class)
	public void testCreateTaskFailedWithObjectMissingPropertiesWithEmptyTaskProperties()
			throws ObjectMissingProperties {
		task = new Task("", "");
		task.setId(1);
		when(taskRepositoryMock.save(task)).thenReturn(task);
		taskService.addTask(task);
	}

	@Test(expected = ObjectMissingProperties.class)
	public void testCreateTaskFailedWithObjectMissingPropertiesWithEmptyNullProperties()
			throws ObjectMissingProperties {
		task = new Task(null, null);
		task.setId(1);
		when(taskRepositoryMock.save(task)).thenReturn(task);
		taskService.addTask(task);
	}

	@Test(expected = ObjectMissingProperties.class)
	public void testUpdateTaskFailedWithObjectMissingPropertiesWithEmptyTaskProperties()
			throws ObjectMissingProperties {
		task = new Task("", "");
		task.setId(1);
		when(taskRepositoryMock.save(task)).thenReturn(task);
		taskService.updateTask(task);
	}

	@Test(expected = ObjectMissingProperties.class)
	public void testUpdateTaskFailedWithObjectMissingPropertiesWithEmptyNullProperties()
			throws ObjectMissingProperties {
		task = new Task(null, null);
		task.setId(1);
		when(taskRepositoryMock.save(task)).thenReturn(task);
		taskService.updateTask(task);
	}
}
