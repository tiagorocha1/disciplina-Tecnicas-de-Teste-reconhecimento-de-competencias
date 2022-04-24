package br.edu.ifpb.dac.trainee.controller.dto;

import java.time.LocalDateTime;

import br.edu.ifpb.dac.trainee.model.Task;

public class TaskDetailDto {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime creationDate;
	private String userName;

	public TaskDetailDto(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();
		this.creationDate = task.getCreationDate();
		this.userName = task.getUser().getName();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
