package com.juniorsilvacc.fastservice.domain.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.juniorsilvacc.fastservice.domain.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(max = 60)
	private String name;
	
	@NotEmpty(message = "O campo mesa é obrigatório")
	@Column(name = "position")
	private Integer table;
	
	private Boolean draft = true;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime moment;
	
	private Integer status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<ItemOrder> items = new ArrayList<>();
	
	public Order() {
	}
	
	public Order(Integer id, String name, Integer table, Boolean draft, OffsetDateTime moment, Status status) {
		this.id = id;
		this.name = name;
		this.table = table;
		this.draft = draft;
		this.moment = moment;
		setStatus(status);
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(ItemOrder x : items) {
			sum = sum + x.getSubTotal();
		}
		
		return sum;
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

	public Integer getTable() {
		return table;
	}

	public void setTable(Integer table) {
		this.table = table;
	}

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public OffsetDateTime getMoment() {
		return moment;
	}

	public void setMoment(OffsetDateTime moment) {
		this.moment = moment;
	}

	public Status getStatus() {
		return Status.valueOf(status);
	}
	
	public void setStatus(Status status) {
		if(status != null) {
			this.status = status.getCode();
		}
	}
	
	public List<ItemOrder> getItems() {
		return items;
	}

	public void setItems(List<ItemOrder> items) {
		this.items = items;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
}
