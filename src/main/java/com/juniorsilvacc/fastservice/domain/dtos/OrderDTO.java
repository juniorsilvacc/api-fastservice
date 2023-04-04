package com.juniorsilvacc.fastservice.domain.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.domain.enums.Status;

public class OrderDTO extends RepresentationModel<OrderDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer table;
	private Boolean draft = true;
	private Status status;
	private OffsetDateTime moment;
	
	private List<ItemOrder> items = new ArrayList<>();
	
	public OrderDTO() {
	} 
	
	public OrderDTO(Integer id, String name, Integer table, Boolean draft, OffsetDateTime moment, Status status, List<ItemOrder> items) {
		this.id = id;
		this.name = name;
		this.table = table;
		this.draft = draft;
		this.moment = moment;
		this.status = status;
		this.items = items;
	}
	
	public OrderDTO(Order obj) {
		id = obj.getId();
		name = obj.getName();
		table = obj.getTable();
		draft = obj.getDraft();
		moment = obj.getMoment();
		status = obj.getStatus();
		items = obj.getItems();
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
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
