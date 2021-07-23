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

import com.example.demo.logic.DiagnosisService;
import com.example.demo.rest.boundaries.DiagnosisBoundary;


@RestController
@CrossOrigin
public class DiagnosisController {

	
	
	private DiagnosisService diagnosisService;
	
	
	
	@Autowired
	public DiagnosisController(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	
	
	

	//********************************* ADD DIAGNOSIS *********************************

	//	URL - http://localhost:8081/diagnosis/{userId}

	@RequestMapping(
			path = "/diagnosis/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public DiagnosisBoundary addDiagnosis(
			@PathVariable("userId") String userId,
			@RequestBody DiagnosisBoundary newDiagnosis) {

		return this.diagnosisService.addDiagnosis(newDiagnosis, userId);

	}




	//********************************* UPDATE DIAGNOSIS *********************************

	// URL - http://localhost:8081/diagnosis/{userId}

	@RequestMapping(
			path = "/diagnosis/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateDiagnosis(
			@PathVariable("userId") String userId,
			@RequestBody DiagnosisBoundary updateDiagnosis) {

		this.diagnosisService.updateDiagnosis(userId, updateDiagnosis);

	}





	//********************************* DELETE DIAGNOSIS *********************************

	// URL - http://localhost:8081/diagnosis/{userId}

//	@RequestMapping(
//			path = "/diagnosis/{userId}", 
//			method = RequestMethod.DELETE, 
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void deleteDiagnosis(
//			@PathVariable("userId") String userId,
//			@RequestBody DiagnosisBoundary deleteDiagnosis) {
//
//		this.diagnosisService.deleteDiagnosis(userId, deleteDiagnosis);
//
//	}


	
	
	
	//********************************* GET DIAGNOSIS BY PATIENT *********************************

	// URL - http://localhost:8081/diagnosis/{userId}/byPatient{patientId}

	@RequestMapping(
			path = "/diagnosis/{userId}/byPatient/{patientId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DiagnosisBoundary[] getDiagnosisByPatient(
			@PathVariable("userId") String userId,
			@PathVariable("patientId") String patientId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.diagnosisService.getDiagnosisByPatient(userId, patientId, page ,size).toArray(new DiagnosisBoundary[0]);



	}


	

	//********************************* GET ALL DIAGNOSIS *********************************

	// URL - http://localhost:8081/diagnosis/{userId}/all

	@RequestMapping(
			path = "/diagnosis/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DiagnosisBoundary[] getAllDiagnosis(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.diagnosisService.getAllDiagnosis(userId, page ,size).toArray(new DiagnosisBoundary[0]);



	}
	
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleUserUnvalidException(RuntimeException e) {
		

		String message = e.getMessage();
		if (message == null) {
			message = "Unvalid diagnosis";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message); 

		return errorMessage;
	}
	
	
	
	
	
	
	
	
}
