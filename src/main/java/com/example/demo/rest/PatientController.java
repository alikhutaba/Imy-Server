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

import com.example.demo.logic.PatientService;
import com.example.demo.rest.boundaries.PatientBoundary;



@RestController
@CrossOrigin
public class PatientController {
	
	
	private PatientService patientService;
	
	
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	
	
	
	
	//********************************* ADD PATIENT *********************************

	//	URL - http://localhost:8081/patient/{userId}
	@RequestMapping(
			path = "/patient/{userId}", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientBoundary addPatient(
			@PathVariable("userId") String userId,
			@RequestBody PatientBoundary newPatient) {

		return this.patientService.addPatient(newPatient, userId);
	}




	//********************************* UPDATE PATIENT *********************************

	// URL - http://localhost:8081/patient/{userId}
	@RequestMapping(
			path = "/patient/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePatient(
			@PathVariable("userId") String userId,
			@RequestBody PatientBoundary updatePatient) {

		this.patientService.updatePatient(updatePatient, userId);
	}




	//********************************* DELETE PATIENT *********************************

	// URL - http://localhost:8081/patient/{userId}
	@RequestMapping(
			path = "/patient/{userId}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePatient(
			@PathVariable("userId") String userId,
			@RequestBody PatientBoundary deletedPatient) {

		this.patientService.deletePatient(deletedPatient, userId);

	}

	
	//********************************* VALDIATE PATIENT *********************************

	// URL - http://localhost:8081/patient/{userId}
	@RequestMapping(
			path = "/patient/validate/{userId}", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientBoundary validatePatient(
			@PathVariable("userId") String userId,
			@RequestBody PatientBoundary updatePatient) {

		return this.patientService.validatePatient(updatePatient, userId);
	}

	
	

	//********************************* GET ACTIVE PATIENT BY ID *********************************

		//	URL - http://localhost:8081/patient/{userId}/byActivePatientId/{patientId}

		@RequestMapping(
				path = "/patient/{userId}/byActivePatientId/{patientId}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public PatientBoundary getActivePatientById(
				@PathVariable("userId") String userId,
				@PathVariable("patientId") String patientId) {

			return this.patientService.getActivePatientById(userId, patientId);
		}



	//********************************* GET PATIENT BY ID *********************************

	//	URL - http://localhost:8081/patient/{userId}/byPatientId/{patientId}

	@RequestMapping(
			path = "/patient/{userId}/byPatientId/{patientId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientBoundary getPatientById(
			@PathVariable("userId") String userId,
			@PathVariable("patientId") String patientId) {

		return this.patientService.getPatientById(userId, patientId);
	}




	//********************************* GET ALL ACTIVE PATIENTS *********************************

	//	URL - http://localhost:8081/patient/{userId}/activeAll

	@RequestMapping(
			path = "/patient/{userId}/activeAll",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientBoundary[] getAllActivePatients(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.patientService.getAllActivePatients(userId, page, size).toArray(new PatientBoundary[0]);
	}
	
	
	
	//********************************* GET ALL ACTIVE AND UNVALID PATIENTS *********************************

	//	URL - http://localhost:8081/patient/{userId}/activeAll

	@RequestMapping(
			path = "/patient/{userId}/activeAndUnvalidAll",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientBoundary[] getAllActiveAndUnvalidPatients(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.patientService.getAllActiveAndUnvalidPatients(userId, page, size).toArray(new PatientBoundary[0]);
	}

	
	

	//********************************* GET ALL PATIENTS *********************************

	//	URL - http://localhost:8081/patient/{userId}/all

	@RequestMapping(
			path = "/patient/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientBoundary[] getAllPatients(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.patientService.getAllPatients(userId, page, size).toArray(new PatientBoundary[0]);
	}



	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleUserUnvalidException(RuntimeException e) {
		

		String message = e.getMessage();
		if (message == null) {
			message = "Unvalid patient";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message); 

		return errorMessage;
	}

	
	
	



//	//********************************* GET PATIENT BY FIRST NAME *********************************
//
//	//	URL - http://localhost:8081/patient/{userId}/byFirstName/{patientFisrtName}
//	@RequestMapping(
//			path = "/patient/{userId}/byFirstName/{patientFisrtName}",
//			method = RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public PatientBoundary[] getPatientByFirstName(
//			@PathVariable("userId") String userId,
//			@PathVariable("patientFisrtName") String patientFisrtName,
//			@RequestParam(name="size", required = false, defaultValue = "20") int size,
//			@RequestParam(name="page", required = false, defaultValue = "0") int page) {
//
//		return this.patientService.getPatientByFirstName(userId, patientFisrtName, size, page).toArray(new PatientBoundary[0]);
//	}
//
//
//
//
//	//********************************* GET PATIENT BY PHONE *********************************
//
//	//	URL - http://localhost:8081/patient/{userId}/byPhone/{patientPhone}
//
//	@RequestMapping(
//			path = "/patient/{userId}/byPhone/{patientPhone}",
//			method = RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public PatientBoundary getPatientPhone(
//			@PathVariable("userId") String userId,
//			@PathVariable("patientPhone") String patientPhone){
//
//		return this.patientService.getPatientPhone(userId, patientPhone);
//	}




}
