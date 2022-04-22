package br.edu.ifpb.dac.trainee.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.edu.ifpb.dac.trainee.model.Task;

public class TaskDto {

	private Long id;
	private String description;	
	private boolean done;
	private String categoryID;
	private String categoryName;

	public TaskDto(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();		
		this.done = task.isDone();
		this.categoryID= task.getCategory().getId().toString();
		this.categoryName = task.getCategory().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	

	public static Page<TaskDto> toConvert(Page<Task> tasks) {

		return tasks.map(TaskDto::new);
	}
	

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static List<TaskDto> toConvert(List<Task> tasks) {

		ArrayList<TaskDto> tasksDto = new ArrayList<TaskDto>();
		
		tasks.forEach(t -> tasksDto.add(new TaskDto(t)));

		return tasksDto;
	}

}