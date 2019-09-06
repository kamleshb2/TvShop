package com.ct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {
	@Id
	@Column(name="UserID")
	private int userId;
	@Size(min=3, max=17,message="first name cannot be less than 3 or greater than 17 characters")
	@NotNull(message="field is mandatory") 
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_Name")
	@NotNull(message="field is mandatory")
	private String lastName;
	@Column(name="ADDRESS")
	@NotNull(message="field is mandatory")
	private String address;
	@Column(name="MOBILE_NO")
	@NotEmpty
	//@Size(min=10, max=10,message="Mobile number should be valid")
	private String mobileNo;
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	@Column(name="USERNAME")
	@Size(min=5, message= "username should be minimum of 5 characters")
	@NotEmpty
	private String username;
	@Size(min=6, message="password should be minimum of 6 characters")
	@NotNull
	private String password;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", mobileNo=" + mobileNo + ", username=" + username + ", password=" + password + "]";
	}
	
	
	

}
