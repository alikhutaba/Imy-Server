package com.example.demo.rest.boundaries;

import java.util.Date;

public class UserBoundary {

	
	private String userId;
	private String workerNumber;
	private String fisrtName;
	private String lastName;
	private BirthDate birthdate;
	private Gender gender;
	private String phone;
	private AddressBoundary address;
	private Boolean active;
	private Date signUpTimestamp;
	private String email;
	private String password;
	private UserRole role;
	
	
	
	public UserBoundary() {}
	
	
	public UserBoundary(String userId, String workerNumber, String fisrtName, String lastName, BirthDate birthdate,
			Gender gender, String phone, AddressBoundary address, Boolean active, Date signUpTimestamp, String email,
			String password, UserRole role) {
		super();
		this.userId = userId;
		this.workerNumber = workerNumber;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.active = active;
		this.signUpTimestamp = signUpTimestamp;
		this.email = email;
		this.password = password;
		this.role = role;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getWorkerNumber() {
		return workerNumber;
	}


	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}


	public String getFisrtName() {
		return fisrtName;
	}


	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public BirthDate getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(BirthDate birthdate) {
		this.birthdate = birthdate;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public AddressBoundary getAddress() {
		return address;
	}


	public void setAddress(AddressBoundary address) {
		this.address = address;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}


	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserBoundary [userId=" + userId + ", WorkerNumber=" + workerNumber + ", fisrtName=" + fisrtName
				+ ", lastName=" + lastName + ", birthdate=" + birthdate + ", gender=" + gender + ", phone=" + phone
				+ ", address=" + address + ", active=" + active + ", signUpTimestamp=" + signUpTimestamp + ", email="
				+ email + ", password=" + password + ", role=" + role + "]";
	}
	
	
}
