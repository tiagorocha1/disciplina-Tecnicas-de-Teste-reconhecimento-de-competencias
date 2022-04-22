package br.edu.ifpb.dac.trainee.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;

@SpringBootTest
class TaskDtoTest {

	@Test
	void dtoAttribute() {

		String description = "test description";
		Category category = new Category(1l, "teste");
		boolean done = false;

		Task task = new Task(description, done, category);
		task.setId(1l);

		TaskDto taskDto = new TaskDto(task);

		assertAll(
				() -> assertEquals(task.getDescription(), taskDto.getDescription()),
				() -> assertEquals(task.isDone(), taskDto.isDone()), 
				() -> assertEquals(task.getId(), taskDto.getId()),
				() -> assertEquals(task.getCategory().getId().toString(), taskDto.getCategoryID()),
				() -> assertEquals(task.getCategory().getName(), taskDto.getCategoryName()));

	}

}
