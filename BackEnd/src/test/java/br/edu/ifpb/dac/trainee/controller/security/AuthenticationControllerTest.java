package br.edu.ifpb.dac.trainee.controller.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationControllerTest {

	private WebDriver browser;

	@BeforeEach
	private void initialize() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	void loginWithValidData() {

		browser.navigate().to("http://localhost:8081/?#/tasks");

		browser.findElement(By.id("inputEmail")).sendKeys("admin@task");
		browser.findElement(By.id("inputPassword")).sendKeys("123");
		browser.findElement(By.id("submit")).click();

		assertNotNull(browser.findElement(By.id("restrito")));
		assertTrue(browser.getPageSource().contains("logout") );
		browser.quit();
	}
	
	@Test
	void loginWithInValidData() {

		browser.navigate().to("http://localhost:8081/?#/tasks");

		browser.findElement(By.id("inputEmail")).sendKeys("admin@task");
		browser.findElement(By.id("inputPassword")).sendKeys("123456");
		browser.findElement(By.id("submit")).click();

	    
		assertNotNull(browser.findElement(By.id("submit")));
		assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("restrito")));
		browser.quit();

	}
	
	@Test
	void restrictedUrl() {
		browser.navigate().to("http://localhost:8081/?#/tasks");
		assertNotNull(browser.findElement(By.id("submit")));
		assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("restrito")));
	}

}
