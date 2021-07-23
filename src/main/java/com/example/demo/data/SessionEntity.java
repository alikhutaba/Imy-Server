package com.example.demo.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "SESSION")
public class SessionEntity {


	private Long sessionId;		// id PK
	private PatientEntity sessionPatient;	// foreign key
	private UserEntity createdBy;	// foreign key
	private Date signUpTimestamp;	// TIMESTAMP


	private QuestionEntity howYouFeelToday;						// Q1		
	private QuestionEntity howThePreviousInjectionWent;			// Q2
	private QuestionEntity antihistamineBeforeVaccination;		// Q3

	private Set<InjectionEntity> injections;	// many to one









	public SessionEntity() {
		this.setInjections(null);
	}









	public SessionEntity(Long sessionId, PatientEntity sessionPatient, UserEntity createdBy, Date signUpTimestamp,
			QuestionEntity howYouFeelToday, QuestionEntity howThePreviousInjectionWent,
			QuestionEntity antihistamineBeforeVaccination, Set<InjectionEntity> injections) {
		super();
		this.sessionId = sessionId;
		this.sessionPatient = sessionPatient;
		this.createdBy = createdBy;
		this.signUpTimestamp = signUpTimestamp;
		this.howYouFeelToday = howYouFeelToday;
		this.howThePreviousInjectionWent = howThePreviousInjectionWent;
		this.antihistamineBeforeVaccination = antihistamineBeforeVaccination;
		this.setInjections(injections);

	}








	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getSessionId() {
		return sessionId;
	}








	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "patient_id", nullable = false)
	public PatientEntity getSessionPatient() {
		return sessionPatient;
	}








	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	public UserEntity getCreatedBy() {
		return createdBy;
	}








	@Temporal(TemporalType.TIMESTAMP)
	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}








	@Convert(converter = QuestionsToJsonStringConveter.class)
	@Lob
	@Column(name="FEEL_TODAY")
	public QuestionEntity getHowYouFeelToday() {
		return howYouFeelToday;
	}








	@Convert(converter = QuestionsToJsonStringConveter.class)
	@Lob
	@Column(name="PREVIOUS_INJECTION_WENT")
	public QuestionEntity getHowThePreviousInjectionWent() {
		return howThePreviousInjectionWent;
	}








	@Convert(converter = QuestionsToJsonStringConveter.class)
	@Lob
	@Column(name="ANTIHISTAMINE_BFORE_VACCINE")
	public QuestionEntity getAntihistamineBeforeVaccination() {
		return antihistamineBeforeVaccination;
	}








	@OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
	public Set<InjectionEntity> getInjections() {
		return injections;
	}









	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}









	public void setSessionPatient(PatientEntity sessionPatient) {
		this.sessionPatient = sessionPatient;
	}









	public void setCreatedBy(UserEntity createdBy) {
		this.createdBy = createdBy;
	}









	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}









	public void setHowYouFeelToday(QuestionEntity howYouFeelToday) {
		this.howYouFeelToday = howYouFeelToday;
	}









	public void setHowThePreviousInjectionWent(QuestionEntity howThePreviousInjectionWent) {
		this.howThePreviousInjectionWent = howThePreviousInjectionWent;
	}









	public void setAntihistamineBeforeVaccination(QuestionEntity antihistamineBeforeVaccination) {
		this.antihistamineBeforeVaccination = antihistamineBeforeVaccination;
	}









	public void setInjections(Set<InjectionEntity> injections) {
		if(injections == null)
			this.injections = new HashSet<InjectionEntity>();
		else
			this.injections = injections;
	}









	@Override
	public String toString() {
		return "SessionEntity [sessionId=" + sessionId + ", sessionPatient=" + sessionPatient + ", createdBy="
				+ createdBy + ", signUpTimestamp=" + signUpTimestamp + ", howYouFeelToday=" + howYouFeelToday
				+ ", howThePreviousInjectionWent=" + howThePreviousInjectionWent + ", antihistamineBeforeVaccination="
				+ antihistamineBeforeVaccination + ", injections=" + injections + "]";
	}

	
	
	




}


