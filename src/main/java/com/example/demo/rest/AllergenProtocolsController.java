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

import com.example.demo.logic.AllergenProtocolsService;
import com.example.demo.rest.boundaries.AllergenProtocolsBoundary;


@RestController
@CrossOrigin
public class AllergenProtocolsController {

	
	
	private AllergenProtocolsService allergenProtocolsService;
	
	
	@Autowired
	public AllergenProtocolsController(AllergenProtocolsService allergenProtocolsService) {
		this.allergenProtocolsService = allergenProtocolsService;
	}
	
	
	


	//********************************* ADD ALLERGEN PROTOCO *********************************

	//	URL - http://localhost:8081/allergenProtocols/{userId}

	@RequestMapping(
			path = "/allergenProtocols/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public AllergenProtocolsBoundary addAllergenProtocols(
			@PathVariable("userId") String userId,
			@RequestBody AllergenProtocolsBoundary newAllergenProtocols) {

		return this.allergenProtocolsService.addAllergenProtocols(newAllergenProtocols, userId);

	}




	//********************************* UPDATE ALLERGEN PROTOCO *********************************

	// URL - http://localhost:8081/allergenProtocols/{userId}

	@RequestMapping(
			path = "/allergenProtocols/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAllergenProtocols(
			@PathVariable("userId") String userId,
			@RequestBody AllergenProtocolsBoundary updateAllergenProtocols) {

		this.allergenProtocolsService.updateAllergenProtocols(userId, updateAllergenProtocols);

	}





	//********************************* DELETE ALLERGEN PROTOCO *********************************

	// URL - http://localhost:8081/allergenProtocols/{userId}

//	@RequestMapping(
//			path = "/allergenProtocols/{userId}", 
//			method = RequestMethod.DELETE, 
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void deleteAllergenProtocols(
//			@PathVariable("userId") String userId,
//			@RequestBody AllergenProtocolsBoundary deleteAllergenProtocols) {
//
//		this.allergenProtocolsService.deleteAllergenProtocols(userId, deleteAllergenProtocols);
//
//	}


	
	
	
	//********************************* GET ALLERGEN PROTOCO BY DIAGNOSIS *********************************

	// URL - http://localhost:8081/allergenProtocols/{userId}/byDiagnosis{DiagnosisId}

	@RequestMapping(
			path = "/allergenProtocols/{userId}/byDiagnosis/{DiagnosisId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public AllergenProtocolsBoundary[] getAllergenProtocolsByDiagnosis(
			@PathVariable("userId") String userId,
			@PathVariable("DiagnosisId") String DiagnosisId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.allergenProtocolsService.getAllergenProtocolsByDiagnosis(userId, DiagnosisId, page ,size).toArray(new AllergenProtocolsBoundary[0]);



	}


	

	//********************************* GET ALL ALLERGEN PROTOCO *********************************

	// URL - http://localhost:8081/allergenProtocols/{userId}/all

	@RequestMapping(
			path = "/allergenProtocols/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public AllergenProtocolsBoundary[] getAllAllergenProtocols(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.allergenProtocolsService.getAllAllergenProtocols(userId, page ,size).toArray(new AllergenProtocolsBoundary[0]);



	}
	
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleUserUnvalidException(RuntimeException e) {
		

		String message = e.getMessage();
		if (message == null) {
			message = "Unvalid allergen protocol";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message); 

		return errorMessage;
	}
	
	
	
	
	
	
}
