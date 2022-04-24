package br.edu.ifpb.dac.trainee.controller.dto.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;
import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.model.repository.UserRepository;
import br.edu.ifpb.dac.trainee.service.TaskService;

@SpringBootTest
class TaskAddFormTest {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	void taskAddFormSave() {
		
		User userLogado = userRepository.findByEmail("admin@task").get();
		List<Category> listCategory = taskService.listCategory();
		
		String description = "test description";
		long category = listCategory.get(0).getId();
		boolean done = false;
		
		TaskAddForm taskAddForm = new TaskAddForm(description, done, category);
		
		Task task = taskAddForm.converter(taskService);
		
		taskService.save(task, userLogado.getId());


		assertAll(
					() -> assertEquals(task.getDescription(), description),
					() -> assertEquals(task.isDone(), done), 				
					() -> assertEquals(task.getCategory().getId(), category),
					() -> assertNotNull(task.getId())
				);
		
	}
	
	

}
