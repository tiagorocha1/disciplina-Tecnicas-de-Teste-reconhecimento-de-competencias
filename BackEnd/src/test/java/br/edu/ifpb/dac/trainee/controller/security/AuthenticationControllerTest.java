package br.edu.ifpb.dac.trainee.controller.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationControllerTest {

	private LoginPage loginPage;

	@BeforeEach
	private void beforeEach() {
		this.loginPage = new LoginPage();
	}

	@AfterEach
	private void afterEach() {
		this.loginPage.quit();
	}

	@Test
	void loginWithValidData() {			
		loginPage.run("admin@task","123"); 
		assertNotNull(loginPage.getElement("restrito"));
		assertTrue(loginPage.contains("logout"));
	}

	@Test
	void loginWithInValidData() {
		loginPage.fillField("inputEmail", "admin@task");
		loginPage.fillField("inputPassword", "123456");
		loginPage.clickField("submit");

		assertNotNull(loginPage.getElement("submit"));
		assertNull(loginPage.getElement("restrito"));
	}

	@Test
	void restrictedUrl() {
		loginPage.goToPageTasks();		
		assertNotNull(loginPage.getElement("submit"));
		assertNull(loginPage.getElement("restrito"));
	}

}


