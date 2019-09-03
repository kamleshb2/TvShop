package com.ct.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Login {
	
	@Id
	@Size(min=5, message= "username should be minimum of 5 characters")
	@NotNull
	private String username;
	@Size(min=6, message="password should be minimum of 6 characters")
	@NotNull
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
	
	

}
