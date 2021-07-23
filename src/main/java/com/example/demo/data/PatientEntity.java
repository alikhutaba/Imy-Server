package com.example.demo.data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "PATIENTS")
public class PatientEntity {


	private String patientId;	//id
	private String fisrtName;
	private String lastName;
	private String middleName;
	private LocalDate birthdate;
	private Gender gender;	// original
	private String phone1;
	private String phone2;
	private AddressEntity address; // one to one
	private Boolean active;
	private Hmo hmo;
	private Date signUpTimestamp;	// TIMESTAMP
	private Date updatedTimestamp;	// TIMESTAMP

	private UserEntity added_by;
	private UserEntity validated_by;

	private VaccineStatus vaccineStatus;	// original
	private QuestionEntity medicationSensitivity;
	private String asthmaRhinitis;
	private String antihistamine;
	private String notes;

	private Set<SessionEntity> session;
	private Set<DiagnosisEntity> diagnosis;

	







	public PatientEntity() {
		this.setSession(null);
		this.setDiagnosis(null);

	}





	public PatientEntity(String patientId, String fisrtName, String lastName, String middleName, LocalDate birthdate,
			Gender gender, String phone1, String phone2, AddressEntity address, Boolean active, Hmo hmo,
			Date signUpTimestamp, Date updatedTimestamp, UserEntity added_by, UserEntity validated_by,
			VaccineStatus vaccineStatus, QuestionEntity medicationSensitivity, String asthmaRhinitis, String antihistamine, String notes,
			Set<SessionEntity> session, Set<DiagnosisEntity> diagnosis) {
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
		this.setSession(session);
		this.setDiagnosis(diagnosis);

	}






	@Id
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






//	@Temporal(TemporalType.TIMESTAMP)
	public LocalDate getBirthdate() {
		return birthdate;
	}






	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}







	public String getPhone1() {
		return phone1;
	}







	public String getPhone2() {
		return phone2;
	}






	@OneToOne
	public AddressEntity getAddress() {
		return address;
	}







	public Boolean getActive() {
		return active;
	}






	@Enumerated(EnumType.STRING)
	public Hmo getHmo() {
		return hmo;
	}






	@Temporal(TemporalType.TIMESTAMP)
	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}






	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}






	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "added_user_id", nullable = false)
	public UserEntity getAdded_by() {
		return added_by;
	}






	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "validated_user_id", nullable = true)
	public UserEntity getValidated_by() {
		return validated_by;
	}






	@Enumerated(EnumType.STRING)
	public VaccineStatus getVaccineStatus() {
		return vaccineStatus;
	}






	@Convert(converter = QuestionsToJsonStringConveter.class)
	@Lob
	@Column(name="MEDICATION_SENSITIVITY")
	public QuestionEntity getMedicationSensitivity() {
		return medicationSensitivity;
	}







	public String getAsthmaRhinitis() {
		return asthmaRhinitis;
	}







	public String getNotes() {
		return notes;
	}






	public String getAntihistamine() {
		return antihistamine;
	}






	@OneToMany(mappedBy = "sessionPatient", fetch = FetchType.LAZY)
	public Set<SessionEntity> getSession() {
		return session;
	}






	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	public Set<DiagnosisEntity> getDiagnosis() {
		return diagnosis;
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







	public void setBirthdate(LocalDate birthdate) {
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







	public void setAddress(AddressEntity address) {
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







	public void setAdded_by(UserEntity added_by) {
		this.added_by = added_by;
	}







	public void setValidated_by(UserEntity validated_by) {
		this.validated_by = validated_by;
	}







	public void setVaccineStatus(VaccineStatus vaccineStatus) {
		this.vaccineStatus = vaccineStatus;
	}







	public void setMedicationSensitivity(QuestionEntity medicationSensitivity) {
		this.medicationSensitivity = medicationSensitivity;
	}







	public void setAsthmaRhinitis(String asthmaRhinitis) {
		this.asthmaRhinitis = asthmaRhinitis;
	}







	public void setNotes(String notes) {
		this.notes = notes;
	}




	public void setAntihistamine(String antihistamine) {
		this.antihistamine = antihistamine;
	}





	public void setSession(Set<SessionEntity> session) {
		if(session == null)
			this.session = new HashSet<SessionEntity>();
		else
			this.session = session;
	}







	public void setDiagnosis(Set<DiagnosisEntity> diagnosis) {
		if(diagnosis == null)
			this.diagnosis = new HashSet<DiagnosisEntity>();
		else
			this.diagnosis = diagnosis;
	}





//	@Override
//	public String toString() {
//		return "PatientEntity [patientId=" + patientId + ", fisrtName=" + fisrtName + ", lastName=" + lastName
//				+ ", middleName=" + middleName + ", birthdate=" + birthdate + ", gender=" + gender + ", phone1="
//				+ phone1 + ", phone2=" + phone2 + ", address=" + address + ", active=" + active + ", hmo=" + hmo
//				+ ", signUpTimestamp=" + signUpTimestamp + ", updatedTimestamp=" + updatedTimestamp + ", added_by="
//				+ added_by + ", validated_by=" + validated_by + ", vaccineStatus=" + vaccineStatus
//				+ ", medicationSensitivity=" + medicationSensitivity + ", nursingHistory=" + nursingHistory + ", notes="
//				+ notes + ", session=" + session + ", diagnosis=" + diagnosis + "]";
//	}


	
	
	
	



}
