package com.example.demo.data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "USERS")
public class UserEntity {

	private String userId;
	private String workerNumber;
	private String fisrtName;
	private String lastName;
	private LocalDate birthdate;
	private Gender gender;
	private String phone;
	private AddressEntity address;
	private Boolean active;
	private Date signUpTimestamp;
	private String email;
	private String password;
	private UserRole role;

	private Set<PatientEntity> addedPatients;
	private Set<PatientEntity> validatedPatients;

	private Set<SessionEntity> session;
	private Set<InjectionEntity> injections;






	public UserEntity() {
		this.setAddedPatients(null);
		this.setValidatedPatients(null);
		this.setSession(null);
		this.setInjections(null);

	}






	public UserEntity(String userId, String workerNumber, String fisrtName, String lastName, LocalDate birthdate,
			Gender gender, String phone, AddressEntity address, Boolean active, Date signUpTimestamp, String email,
			String password, UserRole role, Set<PatientEntity> addedPatients, Set<PatientEntity> validatedPatients,
			Set<SessionEntity> session, Set<InjectionEntity> injections) {
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
		this.setAddedPatients(addedPatients);
		this.setValidatedPatients(validatedPatients);
		this.setSession(session);
		this.setInjections(injections);
	}





	@Id
	public String getUserId() {
		return userId;
	}






	public String getWorkerNumber() {
		return workerNumber;
	}






	public String getFisrtName() {
		return fisrtName;
	}






	public String getLastName() {
		return lastName;
	}





//	@Temporal(TemporalType.TIMESTAMP)
	public LocalDate getBirthdate() {
		return birthdate;
	}





	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}






	public String getPhone() {
		return phone;
	}





	@OneToOne
	public AddressEntity getAddress() {
		return address;
	}






	public Boolean getActive() {
		return active;
	}





	@Temporal(TemporalType.TIMESTAMP)
	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}






	public String getEmail() {
		return email;
	}






	public String getPassword() {
		return password;
	}





	@Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}





	@OneToMany(mappedBy = "added_by", fetch = FetchType.LAZY)
	public Set<PatientEntity> getAddedPatients() {
		return addedPatients;
	}





	@OneToMany(mappedBy = "validated_by", fetch = FetchType.LAZY)
	public Set<PatientEntity> getValidatedPatients() {
		return validatedPatients;
	}





	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
	public Set<SessionEntity> getSession() {
		return session;
	}





	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
	public Set<InjectionEntity> getInjections() {
		return injections;
	}






	public void setUserId(String userId) {
		this.userId = userId;
	}






	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}






	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}






	public void setLastName(String lastName) {
		this.lastName = lastName;
	}






	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}






	public void setGender(Gender gender) {
		this.gender = gender;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}






	public void setAddress(AddressEntity address) {
		this.address = address;
	}






	public void setActive(Boolean active) {
		this.active = active;
	}






	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public void setRole(UserRole role) {
		this.role = role;
	}






	public void setAddedPatients(Set<PatientEntity> addedPatients) {
		if(addedPatients == null)
			this.addedPatients = new HashSet<PatientEntity>();
		else
			this.addedPatients = addedPatients;
	}






	public void setValidatedPatients(Set<PatientEntity> validatedPatients) {
		if(validatedPatients == null)
			this.validatedPatients = new HashSet<PatientEntity>();
		else
			this.validatedPatients = validatedPatients;
	}






	public void setSession(Set<SessionEntity> session) {
		if(session == null)
			this.session = new HashSet<SessionEntity>();
		else
			this.session = session;
	}






	public void setInjections(Set<InjectionEntity> injections) {
		if(injections == null)
			this.injections = new HashSet<InjectionEntity>();
		else
			this.injections = injections;
	}






	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", workerNumber=" + workerNumber + ", fisrtName=" + fisrtName
				+ ", lastName=" + lastName + ", birthdate=" + birthdate + ", gender=" + gender + ", phone=" + phone
				+ ", address=" + address + ", active=" + active + ", signUpTimestamp=" + signUpTimestamp + ", email="
				+ email + ", password=" + password + ", role=" + role + ", addedPatients=" + addedPatients
				+ ", validatedPatients=" + validatedPatients + ", session=" + session + ", injections=" + injections
				+ "]";
	}


	
	
	
	
	



}


