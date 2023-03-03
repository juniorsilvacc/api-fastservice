package com.juniorsilvacc.fastservice.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.juniorsilvacc.fastservice.domain.Category;

public class CategoryDTO extends RepresentationModel<CategoryDTO> implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private Integer id;
	private String name;
	private String description;
	
	public CategoryDTO() {
	}

	public CategoryDTO(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getName();
		description = obj.getDescription();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
