package br.edu.ifpb.dac.trainee.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;
import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.model.repository.CategoryRepository;
import br.edu.ifpb.dac.trainee.model.repository.UserRepository;
import br.edu.ifpb.dac.trainee.service.exception.TaskException;


@SpringBootTest
class TaskServiceTest {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private List<Category> categories;
	
	private User user;
	
	@BeforeEach
	public void initialize() {
		this.categories = categoryRepository.findAll();
		this.user = userRepository.findById(1l).get();		
	}

	@Test
	void addTask() {		
		Category category = categories.get(0);
		boolean done = false;
		String description = "test task service";
		
		Task task = new Task(description, done, category);
		
		Task taskSave = taskService.save(task, this.user.getId());
		
		assertAll(() -> assertNotNull(taskSave),
				() -> assertEquals(this.user.getId(), taskSave.getUser().getId()),
				() -> assertEquals(description, taskSave.getDescription()),
				() -> assertEquals(category.getId(), taskSave.getCategory().getId()));
	}
	
	@Test
	void updateTask() {
		
		Task task = taskService.listAll(user.getId()).get(0);
				
		String taskNewDescription = "update task description";
		
		task.setDescription(taskNewDescription);
		
		Task taskUpdate = taskService.update(task);
		
		assertAll(() -> assertNotNull(taskUpdate),
				() -> assertEquals(this.user.getId(), taskUpdate.getUser().getId()),
				() -> assertEquals(taskNewDescription, taskUpdate.getDescription()),
				() -> assertEquals(task.getCategory().getId(), taskUpdate.getCategory().getId()));
	}
	
	@Test
	void deleteTask() {
		
		Task task = taskService.listAll(user.getId()).get(0);
				
		long idTask = task.getId();
		
		taskService.delete(idTask);
		
		assertThrows(TaskException.class, () -> taskService.getTask(idTask));
		
	}
	
	@Test
	void listTask() {
				
		assertInstanceOf(List.class, taskService.listAll(user.getId()));		
				
	}

}
