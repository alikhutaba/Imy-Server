package com.example.demo.rest.boundaries;

import java.util.Date;

public class PatientBoundary {
	
	

	private String patientId;
	private String fisrtName;
	private String lastName;
	private String middleName;
	private BirthDate birthdate;
	private Gender gender;
	private String phone1;
	private String phone2;
	private AddressBoundary address;
	private Boolean active;
	private Hmo hmo;
	private Date signUpTimestamp;
	private Date updatedTimestamp;

	private UserBoundary added_by;
	private UserBoundary validated_by;

	private VaccineStatus vaccineStatus;
	private QuestionBoundary medicationSensitivity;
	private String asthmaRhinitis;
	private String antihistamine;
	private String notes;

	
	
	
	
	
	public PatientBoundary() {}






	public PatientBoundary(String patientId, String fisrtName, String lastName, String middleName, BirthDate birthdate,
			Gender gender, String phone1, String phone2, AddressBoundary address, Boolean active, Hmo hmo,
			Date signUpTimestamp, Date updatedTimestamp, UserBoundary added_by, UserBoundary validated_by,
			VaccineStatus vaccineStatus, QuestionBoundary medicationSensitivity, String asthmaRhinitis, String antihistamine, String notes) {
		super();
		this.patientId = patientId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address = address;
		this.active = active;
		this.hmo = hmo;
		this.signUpTimestamp = signUpTimestamp;
		this.updatedTimestamp = updatedTimestamp;
		this.added_by = added_by;
		this.validated_by = validated_by;
		this.vaccineStatus = vaccineStatus;
		this.medicationSensitivity = medicationSensitivity;
		this.asthmaRhinitis = asthmaRhinitis;
		this.antihistamine = antihistamine;
		this.notes = notes;

	}






	public String getPatientId() {
		return patientId;
	}






	public String getFisrtName() {
		return fisrtName;
	}






	public String getLastName() {
		return lastName;
	}






	public String getMiddleName() {
		return middleName;
	}






	public BirthDate getBirthdate() {
		return birthdate;
	}






	public Gender getGender() {
		return gender;
	}






	public String getPhone1() {
		return phone1;
	}






	public String getPhone2() {
		return phone2;
	}






	public AddressBoundary getAddress() {
		return address;
	}






	public Boolean getActive() {
		return active;
	}






	public Hmo getHmo() {
		return hmo;
	}






	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}






	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}






	public UserBoundary getAdded_by() {
		return added_by;
	}






	public UserBoundary getValidated_by() {
		return validated_by;
	}






	public VaccineStatus getVaccineStatus() {
		return vaccineStatus;
	}






	public QuestionBoundary getMedicationSensitivity() {
		return medicationSensitivity;
	}






	public String getAsthmaRhinitis() {
		return asthmaRhinitis;
	}






	public String getAntihistamine() {
		return antihistamine;
	}






	public String getNotes() {
		return notes;
	}






	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}






	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}






	public void setLastName(String lastName) {
		this.lastName = lastName;
	}






	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}






	public void setBirthdate(BirthDate birthdate) {
		this.birthdate = birthdate;
	}






	public void setGender(Gender gender) {
		this.gender = gender;
	}






	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}






	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}






	public void setAddress(AddressBoundary address) {
		this.address = address;
	}






	public void setActive(Boolean active) {
		this.active = active;
	}






	public void setHmo(Hmo hmo) {
		this.hmo = hmo;
	}






	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}






	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}






	public void setAdded_by(UserBoundary added_by) {
		this.added_by = added_by;
	}






	public void setValidated_by(UserBoundary validated_by) {
		this.validated_by = validated_by;
	}






	public void setVaccineStatus(VaccineStatus vaccineStatus) {
		this.vaccineStatus = vaccineStatus;
	}






	public void setMedicationSensitivity(QuestionBoundary medicationSensitivity) {
		this.medicationSensitivity = medicationSensitivity;
	}






	public void setAsthmaRhinitis(String asthmaRhinitis) {
		this.asthmaRhinitis = asthmaRhinitis;
	}






	public void setAntihistamine(String antihistamine) {
		this.antihistamine = antihistamine;
	}






	public void setNotes(String notes) {
		this.notes = notes;
	}






	@Override
	public String toString() {
		return "PatientBoundary [patientId=" + patientId + ", fisrtName=" + fisrtName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", birthdate=" + birthdate + ", gender=" + gender + ", phone1="
				+ phone1 + ", phone2=" + phone2 + ", address=" + address + ", active=" + active + ", hmo=" + hmo
				+ ", signUpTimestamp=" + signUpTimestamp + ", updatedTimestamp=" + updatedTimestamp + ", added_by="
				+ added_by + ", validated_by=" + validated_by + ", vaccineStatus=" + vaccineStatus
				+ ", medicationSensitivity=" + medicationSensitivity + ", asthmaRhinitis=" + asthmaRhinitis
				+ ", antihistamine=" + antihistamine + ", notes=" + notes + "]";
	}


	
	
	
	
	
	
	

}
