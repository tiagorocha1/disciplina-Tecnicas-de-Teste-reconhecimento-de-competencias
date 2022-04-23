package br.edu.ifpb.dac.trainee.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;
import br.edu.ifpb.dac.trainee.service.exception.TaskException;

@SpringBootTest
class TaskTest {
	
	

	@Test
	void descriptionNullException() {

		assertThrows(TaskException.class, () -> new Task(null, false, new Category()));
	}
	
	
	@Test
	void categoryNullException() {

		assertThrows(TaskException.class, () -> new Task("teste", false, null));
	}

	
	@Test
	void categoryAndDescriptionNullException() {

		assertThrows(TaskException.class, () -> new Task(null, false, null));
	}
	
	@Test
	void constructor() {
		String description = "Task Test";
		Task task = new Task(description, false, new Category());
		
		assertEquals(description, task.getDescription());
	}
}
