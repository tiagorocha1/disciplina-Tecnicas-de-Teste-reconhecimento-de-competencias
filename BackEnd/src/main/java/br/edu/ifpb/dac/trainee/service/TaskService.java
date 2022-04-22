package br.edu.ifpb.dac.trainee.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.trainee.model.Category;
import br.edu.ifpb.dac.trainee.model.Task;
import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.model.dto.CategoryDto;
import br.edu.ifpb.dac.trainee.model.repository.CategoryRepository;
import br.edu.ifpb.dac.trainee.model.repository.TaskRepository;
import br.edu.ifpb.dac.trainee.model.repository.UserRepository;
import br.edu.ifpb.dac.trainee.service.exception.TaskException;

@Service
public class TaskService {

	@Autowired
	private CategoryRepository categoryRepsitory;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	public Category getCategory(long categoryID) {

		Optional<Category> optional = categoryRepsitory.findById(categoryID);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			throw new TaskException(now() + "TaskService: Category is null");
		}

	}

	public Task getTask(Long id) {

		Optional<Task> optional = taskRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new TaskException(now() + "TaskService: Task is null");
		}

	}

	private String now() {
		return "[ " + LocalDateTime.now().toLocalTime() + " ]";
	}

	public Task update(Task task) {
		return taskRepository.save(task);
		
	}

	public List<Task> listAll(long idUsuario) {

		
		Optional<User> optional = userRepository.findById(idUsuario);
		
		if(optional.isPresent()) {
			return taskRepository.findByUser(optional.get());
		}
		
		return null;
	}

	public List<Task> searchDescription(String search,long idUsuario) {
		
		Optional<User> optional = userRepository.findById(idUsuario);
		
		if(optional.isPresent()) {
			return taskRepository.findByDescriptionContainsAndUser(search,optional.get().getId());	
		}
		
		return null;
	}

	public Task save(Task task, long idUsuario) {
		
		Optional<User> user = userRepository.findById(idUsuario);
		
		if(user.isPresent()) {			
			task.setUser(user.get());			
			return taskRepository.save(task);
		}
		return null;
	}

	public Optional<Task> getOptionalTask(Long id) {
	
		return taskRepository.findById(id);
	}

	public void delete(Long id) {
		taskRepository.deleteById(id);		
	}

	public List<Category> listCategory() {
 
		return categoryRepsitory.findAll();
	}

	public List<CategoryDto> listCategoryDto() {

		List<Category> list = categoryRepsitory.findAll();
		
		ArrayList<CategoryDto> listDto = new ArrayList<CategoryDto>();
		
		list.forEach( c -> listDto.add(new CategoryDto(c)));
		
		return listDto;
	}

	

}
