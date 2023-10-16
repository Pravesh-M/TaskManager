package com.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.model.Task;
import com.practice.repository.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskManagerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TaskRepo repo;

	@BeforeEach()
	void setUp() {
		repo.save(new Task("Watch Tv","New Season of Loki",true));
		repo.save(new Task("Do Work Out","Work out for at least 15 Min",false));
	}

	@Test
	void testCreateTask() throws Exception {
		Task task = new Task("Watch Anime","New Season of JJK",true);

		mockMvc.perform(MockMvcRequestBuilders
				.post("/task")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(task))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().string("New Task Created"));
	}

	@Test
	void testGetTask() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/task/{taskId}",1))
				.andExpect(status().isFound())
				.andExpect(jsonPath("$.title").value("Watch Tv"));
	}

	@Test
	void testGetTasks() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
						.get("/task/all"))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value("Watch Tv"));
	}

	@Test
	void testUpdateTask() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
						.put("/task/{taskId}/{isCompleted}",1,false)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Task Updated"));

	}

	@Test
	void testDeleteTask() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/task/{taskId}",1)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Task Deleted"));

	}

	private String asJsonString(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}
}
