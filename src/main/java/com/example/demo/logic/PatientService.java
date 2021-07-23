package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.PatientBoundary;

public interface PatientService {

	public PatientBoundary addPatient(PatientBoundary newPatient, String userId);

	public void updatePatient(PatientBoundary updatePatient, String userId);

	public void deletePatient(PatientBoundary deletedPatient, String userId);
	
	public PatientBoundary validatePatient(PatientBoundary updatePatient, String userId);

	public PatientBoundary getActivePatientById(String userId, String patientId);
	
	public PatientBoundary getPatientById(String userId, String patientId);

	public List<PatientBoundary> getAllActivePatients(String userId, int page, int size);

	public List<PatientBoundary> getAllActiveAndUnvalidPatients(String userId, int page, int size);

	public List<PatientBoundary> getAllPatients(String userId, int page, int size);
	

//	public List<PatientBoundary> getPatientByFirstName(String userId, String patientFisrtName, int size, int page);
//
//	public PatientBoundary getPatientPhone(String userId, String patientPhone);

}
