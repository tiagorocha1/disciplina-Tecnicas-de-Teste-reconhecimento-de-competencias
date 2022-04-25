package br.edu.ifpb.dac.trainee.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TaskPage {

	private static final String URL_BASE = "http://localhost:8081/?#/";
	private WebDriver browser;

	public TaskPage(WebDriver browser) {
		this.browser = browser;
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

	public WebElement getElement(String field) {
		try {
			return browser.findElement(By.id(field));
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

	public void quit() {
		browser.quit();
	}

	public void deleteTask(String value) {
		getTask(value).findElement(By.className("btn-danger")).click();
	}

	public void doneTask(String value) {
		WebElement task = getTask(value);

		task.findElement(By.className("btn-success")).click();

	}

	public void searchTask(String descriptionTaskSearch) {
		

		this.fillField("description", descriptionTaskSearch);

		browser.findElement(By.id("searchTask")).click();
	}

	public WebElement getTask(String value) {

		try {

			List<WebElement> findElements = browser.findElements(By.className("focus-in-expand-fwd"));
			for (WebElement webElement : findElements) {
				if (webElement.findElement(By.className("taskLine")).getText().equalsIgnoreCase(value)) {
					return webElement;
				}
			}

		} catch (StaleElementReferenceException e) {
			return null;
		}
		return null;

	}

}
