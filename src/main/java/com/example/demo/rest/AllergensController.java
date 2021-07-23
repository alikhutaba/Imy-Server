package com.example.demo.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logic.AllergensService;
import com.example.demo.rest.boundaries.AllergensBoundary;

@RestController
@CrossOrigin
public class AllergensController {

	
	private AllergensService allergensService;
	
	@Autowired
	public AllergensController(AllergensService allergensService) {
		this.allergensService = allergensService;
	}
	
	
	
	

	//********************************* ADD ALLERGEN *********************************

	//	URL - http://localhost:8081/allergen/{userId}

	@RequestMapping(
			path = "/allergen/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public AllergensBoundary addAllergen(
			@PathVariable("userId") String userId,
			@RequestBody AllergensBoundary newAllergen) {

		return this.allergensService.addAllergen(newAllergen, userId);

	}




	//********************************* UPDATE ALLERGEN *********************************

	// URL - http://localhost:8081/allergen/{userId}

//	@RequestMapping(
//			path = "/allergen/{userId}", 
//			method = RequestMethod.PUT, 
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void updateAllergen(
//			@PathVariable("userId") String userId,
//			@RequestBody AllergensBoundary updateAllergen) {
//
//		this.allergensService.updateAllergen(userId, updateAllergen);
//
//	}





	//********************************* DELETE ALLERGEN *********************************

	// URL - http://localhost:8081/allergen/{userId}

//	@RequestMapping(
//			path = "/allergen/{userId}", 
//			method = RequestMethod.DELETE, 
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void deleteAllergen(
//			@PathVariable("userId") String userId,
//			@RequestBody AllergensBoundary deleteAllergen) {
//
//		this.allergensService.deleteAllergen(userId, deleteAllergen);
//
//	}


	
	
	
	//********************************* GET ALLERGEN BY ID *********************************

	// URL - http://localhost:8081/allergen/{userId}/byId/{allergenId}

	@RequestMapping(
			path = "/allergen/{userId}/byId/{allergenId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public AllergensBoundary getAllergenById(
			@PathVariable("userId") String userId,
			@PathVariable("allergenId") String allergenId) {

		return this.allergensService.getAllergenById(userId, allergenId);



	}


	

	//********************************* GET ALL ALLERGEN *********************************

	// URL - http://localhost:8081/allergen/{userId}/all

	@RequestMapping(
			path = "/allergen/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public AllergensBoundary[] getAllAllergen(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "500") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.allergensService.getAllAllergen(userId, page ,size).toArray(new AllergensBoundary[0]);



	}
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleUserUnvalidException(RuntimeException e) {
		

		String message = e.getMessage();
		if (message == null) {
			message = "Unvalid allergen";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message); 


		return errorMessage;
	}
	
	
	
	
}
