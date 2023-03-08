package com.juniorsilvacc.fastservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.juniorsilvacc.fastservice.domain.Category;
import com.juniorsilvacc.fastservice.domain.Product;

@JsonPropertyOrder({"id", "name", "description", "price", "image", "categories"})
public class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String image;
	
	private List<Category> categories = new ArrayList<>();

	public ProductDTO() { 
	}
	
	public ProductDTO(Integer id, String name, String description, Double price, String image, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.categories = categories;
	}

	public ProductDTO(Product obj) {
		id = obj.getId();
		name = obj.getName();
		description = obj.getDescription();
		price = obj.getPrice();
		image = obj.getImage();
		categories = obj.getCategories();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
