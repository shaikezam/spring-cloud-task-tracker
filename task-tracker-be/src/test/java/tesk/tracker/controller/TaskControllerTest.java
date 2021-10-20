package tesk.tracker.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import task.tracker.BootMavenApplication;
import task.tracker.exception.ObjectMissingProperties;
import task.tracker.model.Task;
import task.tracker.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootMavenApplication.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private TaskService taskServiceMock;
	private Task task1, task2;

	@Before
	public void init() throws ObjectMissingProperties {
		task1 = new Task("1st title", "1st description");
		task1.setId(1);
		task2 = new Task("2nd title", "2nd description");
		task2.setId(2);
		List<Task> tasks = Arrays.asList(new Task[] { task1, task2 });
		when(taskServiceMock.getTasks()).thenReturn(tasks);
		when(taskServiceMock.getTask(eq(1))).thenReturn(task1);
		when(taskServiceMock.getTask(eq(2))).thenReturn(task2);
		when(taskServiceMock.updateTask(eq(task2))).thenReturn(task2.getId());
		when(taskServiceMock.deleteTask(eq(2))).thenReturn(2);

	}

	@Test
	public void testGetTasks() throws Exception {
		mvc.perform(get("/webapi/tasks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(
						"[{\"id\":1,\"title\":\"1st title\",\"description\":\"1st description\"},{\"id\":2,\"title\":\"2nd title\",\"description\":\"2nd description\"}]"));
	}

	@Test
	public void testGetFirstTask() throws Exception {
		mvc.perform(get("/webapi/tasks/1/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"title\":\"1st title\",\"description\":\"1st description\"}"));
	}

	@Test
	public void testGetSecondTask() throws Exception {
		mvc.perform(get("/webapi/tasks/2/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":2,\"title\":\"2nd title\",\"description\":\"2nd description\"}"));
	}

	@Test
	public void testUpdateTask() throws Exception {
		mvc.perform(put("/webapi/tasks/2/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":2,\"title\":\"2nd title v2\",\"description\":\"2nd description v2\"}"))
				.andExpect(status().isOk()).andExpect(content().string("2"));
	}

	@Test
	public void testDeleteTask() throws Exception {
		mvc.perform(delete("/webapi/tasks/2/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("2"));
	}

	@Test
	public void testUpdateTaskWithEmptyFielsThrowsObjectMissingProperties() throws Exception {
		when(taskServiceMock.updateTask(any(Task.class)))
				.thenThrow(new ObjectMissingProperties("Need to update all properties"));
		mvc.perform(put("/webapi/tasks/2/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":2,\"title\":\"\",\"description\":\"\"}")).andExpect(status().isBadRequest())
				.andExpect(content().string("Need to update all properties"));
	}

}
