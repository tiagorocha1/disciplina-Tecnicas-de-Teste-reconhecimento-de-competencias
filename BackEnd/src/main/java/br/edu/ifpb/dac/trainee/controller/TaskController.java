package br.edu.ifpb.dac.trainee.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifpb.dac.trainee.controller.dto.CategoryDto;
import br.edu.ifpb.dac.trainee.controller.dto.TaskDetailDto;
import br.edu.ifpb.dac.trainee.controller.dto.TaskDto;
import br.edu.ifpb.dac.trainee.controller.form.TaskFormAdd;
import br.edu.ifpb.dac.trainee.controller.form.TaskFormUpdate;
import br.edu.ifpb.dac.trainee.controller.service.TaskService;
import br.edu.ifpb.dac.trainee.model.Task;

@RestController()
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@GetMapping	
	public List<TaskDto> list(@RequestParam(required = false) String search, HttpServletRequest request) {
		
		String token = taskService.getToken(request);
		
		if (search==null || search.isEmpty()) {
			List<Task>  tasks = taskService.listAll(token);
			
			if(tasks!=null) {
				return TaskDto.toConvert(tasks);	
			}else {
				return null;
			}
			
		}else {
			List<Task>  tasks = null;
			try {
				  tasks = taskService.searchDescription(search, token);
			}catch (Exception e) {
				e.printStackTrace();
			}

			if(tasks!=null) {
				List<TaskDto> result = TaskDto.toConvert(tasks);
				return result;
			}else {
				return new ArrayList<TaskDto>();
			}
			 
		}

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "taskList", allEntries = true)
	@CrossOrigin
	public ResponseEntity<TaskDto> adicionar(@RequestBody @Valid TaskFormAdd form, UriComponentsBuilder uriBuilder,  HttpServletRequest request) {

		String token = taskService.getToken(request);
		
		Task task = form.converter(taskService);
		

		task = taskService.save(task, token);
		
		if(task!=null) {
			URI uri = uriBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri();

			return ResponseEntity.created(uri).body(new TaskDto(task));			
		}else {
			return ResponseEntity.badRequest().body(new TaskDto(task));
		}


	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDetailDto> detalhar(@PathVariable Long id) {
		Optional<Task> optional = taskService.getOptionalTask(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new TaskDetailDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "taskList", allEntries = true)
	public ResponseEntity<TaskDto> atualizar(@PathVariable Long id, @RequestBody @Valid TaskFormUpdate form) {

		Optional<Task> optional = taskService.getOptionalTask(id);
		if (optional.isPresent()) {
			Task task = form.update(id,taskService);
			return ResponseEntity.ok(new TaskDto(task));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "taskList", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Task> optional = taskService.getOptionalTask(id);
		if (optional.isPresent()) {
			taskService.delete(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
	
	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> category() {
		 
		
	   return ResponseEntity.ok(taskService.listCategoryDto());
		

		
	}
}
