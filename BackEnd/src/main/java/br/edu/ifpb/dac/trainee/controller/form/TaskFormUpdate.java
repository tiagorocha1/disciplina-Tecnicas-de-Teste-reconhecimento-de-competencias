package br.edu.ifpb.dac.trainee.controller.form;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import br.edu.ifpb.dac.trainee.controller.service.TaskService;
import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;

public class TaskFormUpdate {

	@NotNull
	@NotEmpty
	private String description;
	private boolean done;
	private long categoryId;
	
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task update(Long id, TaskService taskService) {

		Task task = taskService.getTask(id);
		
		task.setDescription(this.description);
		task.setDone(this.done);
		
		if (this.done) {
			task.setFinishDate(LocalDateTime.now());
		} else {
			task.setFinishDate(null);
		}
		if(this.categoryId != 0) {
			Category category = taskService.getCategory(categoryId);
			task.setCategory(category);
		}
		
		
		
		
		return taskService.update(task);
				
	}

}
