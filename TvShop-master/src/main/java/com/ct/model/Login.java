package com.ct.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="login")
public class Login {
	
	@Id
	@Column(unique=true)
	@Size(min=5, max = 30,  message= "username should be minimum of 5 characters")
	@NotEmpty
	private String username;
	@Size(min=6, max=30, message="password should be minimum of 6 characters")
	@NotEmpty
	private String password;
	private String role;
	public Login() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Login [username=" + username + ", role=" + role + "]";
	}
	
	

}
