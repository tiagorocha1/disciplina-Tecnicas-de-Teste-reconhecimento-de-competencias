package br.edu.ifpb.dac.trainee.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.dac.trainee.model.Category;

public class CategoryDto {

	private String categoryID;
	private String name;
	public CategoryDto(Category category) {		
		this.categoryID = category.getId().toString();
		this.name = category.getName();
	}
	public String getCategoryID() {
		return categoryID;
	}
	public String getName() {
		return name;
	}
	
	public static List<CategoryDto> toConvert(List<Category> categories) {

		ArrayList<CategoryDto> categoryDto = new ArrayList<CategoryDto>();
		
		categories.forEach(c -> categoryDto.add(new CategoryDto(c)));

		return categoryDto;
	}

	
}
