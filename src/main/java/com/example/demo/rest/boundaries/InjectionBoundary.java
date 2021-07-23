package com.example.demo.rest.boundaries;

import java.util.Date;

public class InjectionBoundary {


	private Long injectionId;
	private Date signUpTimestamp;
	private InjectionLocation injectionLocation;
	private String notes;
	
	private AllergenProtocolsBoundary allergenProtocols;
	private SessionBoundary session;
	private UserBoundary createdBy;
	
	private int doseNumber;
	private String concentration;
	private String dosage;

	private Boolean DoctorInstruction;
	
	public InjectionBoundary() {}




	public InjectionBoundary(Long injectionId, Date signUpTimestamp, int doseNumber,
			InjectionLocation injectionLocation, String notes, AllergenProtocolsBoundary allergenProtocols,
			SessionBoundary session, UserBoundary createdBy, String concentration, String dosage, Boolean DoctorInstruction) {
		super();
		this.injectionId = injectionId;
		this.signUpTimestamp = signUpTimestamp;
		this.injectionLocation = injectionLocation;
		this.notes = notes;
		this.allergenProtocols = allergenProtocols;
		this.session = session;
		this.createdBy = createdBy;
		this.doseNumber = doseNumber;
		this.concentration = concentration;
		this.dosage = dosage;
		this.DoctorInstruction = DoctorInstruction;
	}




	public Long getInjectionId() {
		return injectionId;
	}




	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}




	public int getDoseNumber() {
		return doseNumber;
	}




	public InjectionLocation getInjectionLocation() {
		return injectionLocation;
	}




	public String getNotes() {
		return notes;
	}




	public AllergenProtocolsBoundary getAllergenProtocols() {
		return allergenProtocols;
	}




	public SessionBoundary getSession() {
		return session;
	}




	public UserBoundary getCreatedBy() {
		return createdBy;
	}




	public void setInjectionId(Long injectionId) {
		this.injectionId = injectionId;
	}




	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}




	public void setDoseNumber(int doseNumber) {
		this.doseNumber = doseNumber;
	}




	public void setInjectionLocation(InjectionLocation injectionLocation) {
		this.injectionLocation = injectionLocation;
	}




	public void setNotes(String notes) {
		this.notes = notes;
	}




	public void setAllergenProtocols(AllergenProtocolsBoundary allergenProtocols) {
		this.allergenProtocols = allergenProtocols;
	}




	public void setSession(SessionBoundary session) {
		this.session = session;
	}




	public void setCreatedBy(UserBoundary createdBy) {
		this.createdBy = createdBy;
	}


	

	public String getConcentration() {
		return concentration;
	}




	public String getDosage() {
		return dosage;
	}




	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}




	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	
	
	




	public Boolean getDoctorInstruction() {
		return DoctorInstruction;
	}




	public void setDoctorInstruction(Boolean doctorInstruction) {
		DoctorInstruction = doctorInstruction;
	}




	@Override
	public String toString() {
		return "InjectionBoundary [InjectionId=" + injectionId + ", signUpTimestamp=" + signUpTimestamp
				+ ", doseNumber=" + doseNumber + ", injectionLocation=" + injectionLocation + ", notes=" + notes
				+ ", allergenProtocols=" + allergenProtocols + ", Session=" + session + ", createdBy=" + createdBy + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
