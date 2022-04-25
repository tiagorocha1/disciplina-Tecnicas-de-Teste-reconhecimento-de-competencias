package br.edu.ifpb.dac.trainee.controller.security;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.edu.ifpb.dac.trainee.controller.TaskPage;

public class LoginPage {

	private static final String URL_BASE = "http://localhost:8081/?#/";
	private WebDriver browser;

	public LoginPage() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.to("login");
	}

	public void quit() {
		browser.quit();
	}

	public void to(String page) {
		browser.navigate().to(URL_BASE + page);
	}

	public void fillField(String field, String value) {
		browser.findElement(By.id(field)).sendKeys(value);
	}

	public void clickField(String field) {
		browser.findElement(By.id(field)).click();
	}

	public String getElement(String field) {
		try {
			return browser.findElement(By.id(field)).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public boolean contains(String field) {
		return browser.getPageSource().contains(field);
	}

	public void goToPageTasks() {
		this.to("tasks");
	}

	public TaskPage run(String login, String password) {

		this.fillField("inputEmail", login);
		this.fillField("inputPassword", password);
		this.clickField("submit");
		
		return new TaskPage(browser);
	}

}
