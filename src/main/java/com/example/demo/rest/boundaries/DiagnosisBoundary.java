package com.example.demo.rest.boundaries;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class DiagnosisBoundary {


	private Long diagnosId;
	private int diagnosisNumber;
	private List<InjectionLocation> injectionLocation;
												// TODO add time
	private PatientBoundary patient;
	private Set<AllergensBoundary> allergens;






	public DiagnosisBoundary() {
		this.setAllergens(null);
	}





	public DiagnosisBoundary(Long diagnosId, PatientBoundary patient, Set<AllergensBoundary> allergens,
			int diagnosisNumber, List<InjectionLocation> injectionLocation) {
		super();
		this.diagnosId = diagnosId;
		this.patient = patient;
		this.diagnosisNumber = diagnosisNumber;
		this.injectionLocation = injectionLocation;
		this.setAllergens(allergens);

	}





	public Long getDiagnosId() {
		return diagnosId;
	}





	public PatientBoundary getPatient() {
		return patient;
	}





	public Set<AllergensBoundary> getAllergens() {
		return allergens;
	}





	public int getDiagnosisNumber() {
		return diagnosisNumber;
	}





	public List<InjectionLocation> getInjectionLocation() {
		return injectionLocation;
	}





	public void setDiagnosId(Long diagnosId) {
		this.diagnosId = diagnosId;
	}





	public void setPatient(PatientBoundary patient) {
		this.patient = patient;
	}





	public void setAllergens(Set<AllergensBoundary> allergens) {
		if(allergens == null)
			this.allergens = new HashSet<AllergensBoundary>();
		else
			this.allergens = allergens;
	}





	public void setDiagnosisNumber(int diagnosisNumber) {
		this.diagnosisNumber = diagnosisNumber;
	}





	public void setInjectionLocation(List<InjectionLocation> injectionLocation) {
		this.injectionLocation = injectionLocation;
	}





	@Override
	public String toString() {
		return "DiagnosisBoundary [diagnosId=" + diagnosId + ", patient=" + patient + ", allergens=" + allergens
				+ ", diagnosisNumber=" + diagnosisNumber + ", injectionLocation=" + injectionLocation + "]";
	}








}
