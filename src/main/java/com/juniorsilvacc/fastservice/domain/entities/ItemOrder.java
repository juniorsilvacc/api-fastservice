package com.juniorsilvacc.fastservice.domain.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_item_order")
public class ItemOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo quantidade é obrigatório")
	private Integer amount;
	
	@NotEmpty(message = "O campo Id Comanda é obrigatório")
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@NotEmpty(message = "O campo Id Produto é obrigatório")
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public ItemOrder() {
	}

	public ItemOrder(Integer id, Integer amount, Order order, Product product) {
		this.id = id;
		this.amount = amount;
		this.order = order;
		this.product = product;
	}
	
	public Double getSubTotal() {
		return product.getPrice() * amount;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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
		ItemOrder other = (ItemOrder) obj;
		return Objects.equals(id, other.id);
	}

}
