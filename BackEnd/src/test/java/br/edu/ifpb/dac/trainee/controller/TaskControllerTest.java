package br.edu.ifpb.dac.trainee.controller;

import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.ifpb.dac.trainee.controller.config.security.SecurityConfigurations;
import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;
import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.model.repository.UserRepository;
import br.edu.ifpb.dac.trainee.service.TaskService;
import br.edu.ifpb.dac.trainee.service.auth.AutenticationService;
import br.edu.ifpb.dac.trainee.service.auth.TokenService;
import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import org.hamcrest.*;

@WebMvcTest
public class TaskControllerTest {

	
	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	@MockBean
	private TokenService tokenService;
	
	@MockBean
	private AutenticationService autenticationService;
	
	@MockBean
	private UserRepository userRepository;
	

	
	@BeforeEach
	public void setup(){
		standaloneSetup(this.taskController);
	
	}
	
	@Test
	public void returnSuccessWhenFetchingTask() {
		
		User user = new User();
		user.setName("name test");
		Task task = new Task("task test",true,new Category());
		task.setUser(user);
		
				
			when(this.taskService.getOptionalTask(1L))
				.thenReturn(Optional.of(task));
		
			given()
				.accept(ContentType.JSON)
			.when()
				.get("/api/tasks/{id}",1L)
			.then()
				.statusCode(org.springframework.http.HttpStatus.OK.value());
					
					
		
	}
}
