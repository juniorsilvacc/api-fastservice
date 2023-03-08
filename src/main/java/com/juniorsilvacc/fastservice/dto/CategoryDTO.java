package com.juniorsilvacc.fastservice.dto;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.juniorsilvacc.fastservice.domain.Category;

@JsonPropertyOrder({"id", "name", "description"})
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
