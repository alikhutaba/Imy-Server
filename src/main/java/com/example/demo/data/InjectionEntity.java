package com.example.demo.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "INJECTIONS")
public class InjectionEntity {




	private Long injectionId;		// id PK
	private Date createdTimestamp;	// TIMESTAMP
	private InjectionLocation injectionLocation; // STRING
	private String notes;

	private AllergenProtocolsEntity allergenProtocol;
	private SessionEntity session;
	private UserEntity createdBy;
	
	private int doseNumber;
	private String concentration;
	private String dosage;



	public InjectionEntity() {}




	public InjectionEntity(Long injectionId, Date createdTimestamp, int doseNumber, InjectionLocation injectionLocation,
			String notes, AllergenProtocolsEntity allergenProtocol, SessionEntity session, UserEntity createdBy, String concentration, String  dosage) {
		super();
		this.injectionId = injectionId;
		this.createdTimestamp = createdTimestamp;
		this.injectionLocation = injectionLocation;
		this.notes = notes;
		this.allergenProtocol = allergenProtocol;
		this.session = session;
		this.createdBy = createdBy;
		this.doseNumber = doseNumber;
		this.concentration = concentration;
		this.dosage = dosage;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getInjectionId() {
		return injectionId;
	}




	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}




	public int getDoseNumber() {
		return doseNumber;
	}




	@Enumerated(EnumType.STRING)
	public InjectionLocation getInjectionLocation() {
		return injectionLocation;
	}




	public String getNotes() {
		return notes;
	}



	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "allergenProtocol_id", nullable = false)
	public AllergenProtocolsEntity getAllergenProtocol() {
		return allergenProtocol;
	}



	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "session_id", nullable = false)
	public SessionEntity getSession() {
		return session;
	}




	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	public UserEntity getCreatedBy() {
		return createdBy;
	}




	public void setInjectionId(Long injectionId) {
		this.injectionId = injectionId;
	}




	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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




	public void setAllergenProtocol(AllergenProtocolsEntity allergenProtocol) {
		this.allergenProtocol = allergenProtocol;
	}




	public void setSession(SessionEntity session) {
		this.session = session;
	}




	public void setCreatedBy(UserEntity createdBy) {
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


	
	



}

