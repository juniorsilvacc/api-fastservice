package com.juniorsilvacc.fastservice.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.juniorsilvacc.fastservice.domain.Category;

@JsonPropertyOrder({"id", "name", "description"})
public class CategoryDTO extends RepresentationModel<CategoryDTO> implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@JsonProperty("id")
	private Integer key;
	private String name;
	private String description;
	
	public CategoryDTO() {
	}

	public CategoryDTO(Integer key, String name, String description) {
		this.key = key;
		this.name = name;
		this.description = description;
	}

	public CategoryDTO(Category obj) {
		key = obj.getId();
		name = obj.getName();
		description = obj.getDescription();
	}

	public Integer getId() {
		return key;
	}

	public void setId(Integer key) {
		this.key = key;
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
