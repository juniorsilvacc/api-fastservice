package com.juniorsilvacc.fastservice.domain.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.domain.enums.Status;

public class OrderDTO extends RepresentationModel<OrderDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private int table;
	private boolean draft = false;
	private Status status;
	private Instant moment;
	
	public OrderDTO() {
	}
	
	public OrderDTO(Integer id, String name, int table, boolean draft, Instant moment, Status status) {
		this.id = id;
		this.name = name;
		this.table = table;
		this.draft = draft;
		this.moment = moment;
		this.status = status;
	}
	
	public OrderDTO(Order obj) {
		id = obj.getId();
		name = obj.getName();
		table = obj.getTable();
		draft = obj.getDraft();
		moment = obj.getMoment();
		status = obj.getStatus();
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

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
