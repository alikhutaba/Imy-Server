package com.example.demo.rest.boundaries;

import java.util.Date;



public class SessionBoundary {
	
	
	
	private Long sessionId;
	private PatientBoundary patient;
	private UserBoundary createdBy;
	private Date signUpTimestamp;
	
	private QuestionBoundary howYouFeelToday;						// Q1		
	private QuestionBoundary howThePreviousInjectionWent;			// Q2
	private QuestionBoundary antihistamineBeforeVaccination;		// Q3
	
	
	
	public SessionBoundary() {}



	public SessionBoundary(Long sessionId, PatientBoundary patient, UserBoundary createdBy, Date signUpTimestamp,
			QuestionBoundary howYouFeelToday, QuestionBoundary howThePreviousInjectionWent,
			QuestionBoundary antihistamineBeforeVaccination) {
		super();
		this.sessionId = sessionId;
		this.patient = patient;
		this.createdBy = createdBy;
		this.signUpTimestamp = signUpTimestamp;
		this.howYouFeelToday = howYouFeelToday;
		this.howThePreviousInjectionWent = howThePreviousInjectionWent;
		this.antihistamineBeforeVaccination = antihistamineBeforeVaccination;
	}



	public Long getSessionId() {
		return sessionId;
	}



	public PatientBoundary getPatient() {
		return patient;
	}



	public UserBoundary getCreatedBy() {
		return createdBy;
	}



	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}



	public QuestionBoundary getHowYouFeelToday() {
		return howYouFeelToday;
	}



	public QuestionBoundary getHowThePreviousInjectionWent() {
		return howThePreviousInjectionWent;
	}



	public QuestionBoundary getAntihistamineBeforeVaccination() {
		return antihistamineBeforeVaccination;
	}



	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}



	public void setPatient(PatientBoundary patient) {
		this.patient = patient;
	}



	public void setCreatedBy(UserBoundary createdBy) {
		this.createdBy = createdBy;
	}



	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}



	public void setHowYouFeelToday(QuestionBoundary howYouFeelToday) {
		this.howYouFeelToday = howYouFeelToday;
	}



	public void setHowThePreviousInjectionWent(QuestionBoundary howThePreviousInjectionWent) {
		this.howThePreviousInjectionWent = howThePreviousInjectionWent;
	}



	public void setAntihistamineBeforeVaccination(QuestionBoundary antihistamineBeforeVaccination) {
		this.antihistamineBeforeVaccination = antihistamineBeforeVaccination;
	}



	@Override
	public String toString() {
		return "VisitBoundary [SessionId=" + sessionId + ", patient=" + patient + ", createdBy=" + createdBy
				+ ", signUpTimestamp=" + signUpTimestamp + ", howYouFeelToday=" + howYouFeelToday
				+ ", howThePreviousInjectionWent=" + howThePreviousInjectionWent + ", antihistamineBeforeVaccination="
				+ antihistamineBeforeVaccination + "]";
	}
	

	
	
	
	

}
