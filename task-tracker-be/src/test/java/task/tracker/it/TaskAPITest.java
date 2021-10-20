package task.tracker.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import task.tracker.BootMavenApplication;
import task.tracker.model.Task;
import task.tracker.repository.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootMavenApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class TaskAPITest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private TaskRepository repository;

	@Before
	public void init() {
		List<Task> tasks = Arrays.asList(
				new Task[] { new Task("1st title", "1st description"), new Task("2nd title", "2nd description") });
		repository.save(tasks);
	}

	@Test
	public void testGetTasks() throws Exception {
		mvc.perform(get("/webapi/tasks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(
						"[{\"id\":1,\"title\":\"1st title\",\"description\":\"1st description\"},{\"id\":2,\"title\":\"2nd title\",\"description\":\"2nd description\"}]"));
	}

	@Test
	public void testGetTask() throws Exception {
		mvc.perform(get("/webapi/tasks/1/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"title\":\"1st title\",\"description\":\"1st description\"}"));
	}

	@Test
	public void testCreateTask() throws Exception {
		mvc.perform(post("/webapi/tasks/").content(asJsonString(new Task("3rd title", "3rd description")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("3"));

		mvc.perform(get("/webapi/tasks/3/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":3,\"title\":\"3rd title\",\"description\":\"3rd description\"}"));
	}

	@Test
	public void testUpdateTask() throws Exception {
		mvc.perform(put("/webapi/tasks/1").content(asJsonString(new Task("1st title v2", "1st description v2")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("1"));

		mvc.perform(get("/webapi/tasks/1/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content()
						.string("{\"id\":1,\"title\":\"1st title v2\",\"description\":\"1st description v2\"}"));
	}

	@Test
	public void testDeleteTask() throws Exception {
		mvc.perform(delete("/webapi/tasks/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("1"));

		mvc.perform(get("/webapi/tasks/1/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(status().isNotFound()).andExpect(content().string("Task 1 not found"));
	}

	@Test
	public void testGetTaskNotFoundTaskThrowsObjectNotFoundException() throws Exception {
		mvc.perform(get("/webapi/tasks/999/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(status().isNotFound()).andExpect(content().string("Task 999 not found"));
	}

	@Test
	public void testCreateEmptyTaskThrowsBadRequest() throws Exception {
		mvc.perform(
				post("/webapi/tasks").content(asJsonString(new Task("", ""))).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Need to update all properties"));
	}

	@Test
	public void testUpdateEmptyTaskThrowsBadRequest() throws Exception {
		mvc.perform(
				put("/webapi/tasks/1").content(asJsonString(new Task("", ""))).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Need to update all properties"));

		mvc.perform(get("/webapi/tasks/1/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"title\":\"1st title\",\"description\":\"1st description\"}"));
	}

	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
