package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.AllergenProtocolsBoundary;

public interface AllergenProtocolsService {

	public AllergenProtocolsBoundary addAllergenProtocols(AllergenProtocolsBoundary newAllergenProtocols, String userId);

	public void updateAllergenProtocols(String userId, AllergenProtocolsBoundary updateAllergenProtocols);

//	public void deleteAllergenProtocols(String userId, AllergenProtocolsBoundary deleteAllergenProtocols);

	public List<AllergenProtocolsBoundary> getAllergenProtocolsByDiagnosis(String userId, String diagnosisId, int page, int size);

	public List<AllergenProtocolsBoundary> getAllAllergenProtocols(String userId, int page, int size);

}
