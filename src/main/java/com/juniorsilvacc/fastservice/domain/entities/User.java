package com.juniorsilvacc.fastservice.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

@Entity
@Table(name = "tb_user")
public class User implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String cpf;
	
	private String avatar;
	
	private String password;
	
//	@Column(name = "account_non_expired")
//	private Boolean accountNonExpired = true;
//
//	@Column(name = "account_non_locked")
//	private Boolean accountNonLocked = true;
//
//	@Column(name = "credentials_non_expired")
//	private Boolean credentialsNonExpired = true;
//
//	private Boolean enabled = true;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_permission", joinColumns = {@JoinColumn (name = "id_user")},
		inverseJoinColumns =  {@JoinColumn (name = "id_permission")}
	)
	private List<Permission> permissions;
	
	public User() {
	}

	public User(UserDTO obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.avatar = obj.getAvatar();
		this.password = obj.getPassword();
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
		return this.password;
	}
	
	@Override
	public String getUsername() {
		return this.name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

//	public Boolean getAccountNonExpired() {
//		return accountNonExpired;
//	}
//
//	public void setAccountNonExpired(Boolean accountNonExpired) {
//		this.accountNonExpired = accountNonExpired;
//	}
//
//	public Boolean getAccountNonLocked() {
//		return accountNonLocked;
//	}
//
//	public void setAccountNonLocked(Boolean accountNonLocked) {
//		this.accountNonLocked = accountNonLocked;
//	}
//
//	public Boolean getCredentialsNonExpired() {
//		return credentialsNonExpired;
//	}
//
//	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
//		this.credentialsNonExpired = credentialsNonExpired;
//	}
//
//	public Boolean getEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(Boolean enabled) {
//		this.enabled = enabled;
//	}

	public List<Permission> getPermissions() {
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
