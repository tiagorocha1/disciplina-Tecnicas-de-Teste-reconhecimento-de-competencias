package br.edu.ifpb.dac.trainee.controller.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationControllerTest {

	private static final String URL_BASE="http://localhost:8081/?#/";
	private WebDriver browser;

	@BeforeEach
	private void beforeEach() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterEach
	private void afterEach() {
		browser.quit();
	}

	@Test
	void loginWithValidData() {

		browser.navigate().to(URL_BASE+"login");

		browser.findElement(By.id("inputEmail")).sendKeys("admin@task");
		browser.findElement(By.id("inputPassword")).sendKeys("123");
		browser.findElement(By.id("submit")).click();

		assertNotNull(browser.findElement(By.id("restrito")));
		assertTrue(browser.getPageSource().contains("logout") );
	
	}
	
	@Test
	void loginWithInValidData() {

		browser.navigate().to(URL_BASE+"login");

		browser.findElement(By.id("inputEmail")).sendKeys("admin@task");
		browser.findElement(By.id("inputPassword")).sendKeys("123456");
		browser.findElement(By.id("submit")).click();

	    
		assertNotNull(browser.findElement(By.id("submit")));
		assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("restrito")));
	

	}
	
	@Test
	void restrictedUrl() {
		browser.navigate().to(URL_BASE+"tasks");
		assertNotNull(browser.findElement(By.id("submit")));
		assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("restrito")));
		
	}

}
