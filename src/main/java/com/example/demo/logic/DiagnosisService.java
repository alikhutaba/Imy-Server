package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.DiagnosisBoundary;

public interface DiagnosisService {

	public DiagnosisBoundary addDiagnosis(DiagnosisBoundary newDiagnosis, String userId);

	public void updateDiagnosis(String userId, DiagnosisBoundary updateDiagnosis);

//	public void deleteDiagnosis(String userId, DiagnosisBoundary deleteDiagnosis);

	public List<DiagnosisBoundary> getDiagnosisByPatient(String userId, String patientId, int page, int size);

	public List<DiagnosisBoundary> getAllDiagnosis(String userId, int page, int size);

}
