package br.edu.ifpb.dac.trainee.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.dac.trainee.controller.security.LoginPage;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskControllerTest {

	private TaskPage taskPage; 
	
	private String descriptionTaskDelete ="deletar esta task";
	private String descriptionTaskDone ="finalizar esta task";
	private String descriptionTaskSearch ="pesquisar esta task";
	

	@AfterEach
	private void afterEach() {
		this.taskPage.quit();
	}
	
	@Test // create tree tasks
	void taskTest01() {
		LoginPage loginPage = new LoginPage();
		this.taskPage = loginPage.run("admin@task", "123");
				
		taskPage.fillField("description",this.descriptionTaskDelete);				
		taskPage.clickField("addTask");
		
		
		taskPage.fillField("description",this.descriptionTaskDone);
		taskPage.clickField("addTask");
		
		taskPage.fillField("description",this.descriptionTaskSearch);
		taskPage.clickField("addTask");
		
		assertNotNull(taskPage.getTask(descriptionTaskDelete));
		assertNotNull(taskPage.getTask(descriptionTaskDone));
		assertNotNull(taskPage.getTask(descriptionTaskSearch));
	}
	
	@Test //delete one task
	void taskTest02() {		
		LoginPage loginPage = new LoginPage();
		this.taskPage = loginPage.run("admin@task", "123");
		taskPage.deleteTask(this.descriptionTaskDelete);	
		assertNull(taskPage.getTask(descriptionTaskDelete));
	}
	
	@Test //done one task
	void taskTest03() {	
		LoginPage loginPage = new LoginPage();
		this.taskPage = loginPage.run("admin@task", "123");
		taskPage.doneTask(this.descriptionTaskDone);
		
		assertNull(taskPage.getTask("notMarkedAsDone"));
	}
	
	@Test //search one task
	void taskTest04() {	
		LoginPage loginPage = new LoginPage();
		this.taskPage = loginPage.run("admin@task", "123");
		taskPage.searchTask(this.descriptionTaskSearch);
		
		assertNull(taskPage.getTask(descriptionTaskDone));
	}
}
