package com.juniorsilvacc.fastservice.dto;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.juniorsilvacc.fastservice.domain.Product;

@JsonPropertyOrder({"id", "name", "description", "price", "image"})
public class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer key;
	private String name;
	private String description;
	private double price;
	private String image;
	
	public ProductDTO() {
	}
	
	public ProductDTO(Integer key, String name, String description, double price, String image) {
		this.key = key;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	
	public ProductDTO(Product obj) {
		this.key = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
		this.price = obj.getPrice();
		this.image = obj.getImage();
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key);
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
		return Objects.equals(key, other.key);
	}
	
}
