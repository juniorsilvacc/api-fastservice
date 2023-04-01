package com.juniorsilvacc.fastservice.domain.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo nome é obrigatório")
	@Size(min = 3, max = 60)
	private String user_name;
	
	@NotEmpty(message = "O campo e-mail é obrigatório")
	@Size(max = 50)
	@Email
	@Column(unique = true)
	private String email;
	
	@NotEmpty(message = "O campo CPF é obrigatório")
	@CPF
	@Column(unique = true)
	private String cpf;
	
	private String avatar;
	
	@NotEmpty(message = "O campo senha é obrigatório")
	@Size(min = 6, max = 60)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_permission", joinColumns = {@JoinColumn (name = "id_user")},
		inverseJoinColumns =  {@JoinColumn (name = "id_permission")}
	)
	private List<Permission> permissions;
	
	public User() {
	}

	public User(Integer id, String user_name, String email, String cpf, String avatar, String password,
			List<Permission> permissions) {
		this.id = id;
		this.user_name = user_name;
		this.email = email;
		this.cpf = cpf;
		this.avatar = avatar;
		this.password = password;
		this.permissions = permissions;
	}

	public User(UserDTO obj) {
		this.id = obj.getId();
		this.user_name = obj.getUser_name();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.permissions = obj.getPermissions();
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permission permission : permissions) {
			roles.add(permission.getDescription());
		}
		
		return roles ;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return user_name;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
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

	public List<Permission> getPermissions() {
		if (permissions == null) {
			permissions = new ArrayList<>();
		}
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}