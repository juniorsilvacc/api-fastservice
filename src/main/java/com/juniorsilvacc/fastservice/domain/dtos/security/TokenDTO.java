package com.juniorsilvacc.fastservice.domain.dtos.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String accessToken;
	
	public TokenDTO() {
	}
	
	public TokenDTO(String email, Boolean authenticated, Date created, Date expiration, String accessToken) {
		this.email = email;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, authenticated, created, email, expiration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenDTO other = (TokenDTO) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(authenticated, other.authenticated)
				&& Objects.equals(created, other.created) && Objects.equals(email, other.email)
				&& Objects.equals(expiration, other.expiration);
	}

}
