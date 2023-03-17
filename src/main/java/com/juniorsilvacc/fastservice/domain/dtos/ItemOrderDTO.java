package com.juniorsilvacc.fastservice.domain.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;

public class ItemOrderDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer amount;

	private Integer order;
	private Integer product;
	
	public ItemOrderDTO() { 
	}
	
	public ItemOrderDTO(Integer id, Integer amount, Integer order, Integer product) {
		this.id = id;
		this.amount = amount;
		this.order = order;
		this.product = product;
	}
	
	public ItemOrderDTO(ItemOrder obj) {
		this.id = obj.getId();
		this.amount = obj.getAmount();
		this.order = obj.getOrder().getId();
		this.product = obj.getProduct().getId();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
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
		ItemOrderDTO other = (ItemOrderDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
