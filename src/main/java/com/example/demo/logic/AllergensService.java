package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.AllergensBoundary;

public interface AllergensService {

	public AllergensBoundary addAllergen(AllergensBoundary newAllergen, String userId);

//	public void updateAllergen(String userId, AllergensBoundary updateAllergen);
//
//	public void deleteAllergen(String userId, AllergensBoundary deleteAllergen);

	public AllergensBoundary getAllergenById(String userId, String allergenId);

	public List<AllergensBoundary> getAllAllergen(String userId, int page, int size);

}
