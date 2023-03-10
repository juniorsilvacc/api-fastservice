package com.juniorsilvacc.fastservice.domain.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juniorsilvacc.fastservice.domain.entities.pk.ItemOrderPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_item_order")
public class ItemOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemOrderPK id = new ItemOrderPK();
	
	private int amount;
	
	public ItemOrder() {
		
	}
	
	public ItemOrder(Order order, Product product, int amount) {
		id.setOrder(order);
		id.setProduct(product);
		this.amount = amount;
	}
	
	@JsonIgnore
	public Order getOrder () {
		return id.getOrder();
	}
	
	public void setOrder (Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
		ItemOrder other = (ItemOrder) obj;
		return Objects.equals(id, other.id);
	}
	
}
