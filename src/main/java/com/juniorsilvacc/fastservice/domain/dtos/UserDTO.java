package com.juniorsilvacc.fastservice.domain.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.juniorsilvacc.fastservice.domain.entities.Permission;
import com.juniorsilvacc.fastservice.domain.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String user_name;
	private String email;
	private String cpf;
	private String avatar;
	private String password;

	private List<Permission> permissions;

	public UserDTO() {
	}

	public UserDTO(Integer id, String user_name, String email, String cpf, String avatar, String password, List<Permission> permissions) {
		this.id = id;
		this.user_name = user_name;
		this.email = email;
		this.cpf = cpf;
		this.cpf =  password;
		this.permissions = permissions;
	}

	public UserDTO(User obj) {
		this.id = obj.getId();
		this.user_name = obj.getUser_name();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.avatar = obj.getAvatar();
		this.password = obj.getPassword();
		this.permissions = obj.getPermissions();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}

}