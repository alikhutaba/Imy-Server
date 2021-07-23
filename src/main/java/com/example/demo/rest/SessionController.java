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

import com.example.demo.logic.SessionService;
import com.example.demo.rest.boundaries.SessionBoundary;

@RestController
@CrossOrigin
public class SessionController {

	
	
	private SessionService sessionService;
	
	
	
	@Autowired
	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	
	
	//********************************* ADD SESSION *********************************

		//	URL - http://localhost:8081/session

		@RequestMapping(
				path = "/session",
				method = RequestMethod.POST, 
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public SessionBoundary addSession(
				@RequestBody SessionBoundary newSession) {

			return this.sessionService.addSession(newSession);

		}




		//********************************* UPDATE SESSION *********************************

		// URL - http://localhost:8081/session

		@RequestMapping(
				path = "/session", 
				method = RequestMethod.PUT, 
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public void updateSession(
				@RequestBody SessionBoundary updateSession) {

			this.sessionService.updateSession(updateSession);

		}





		//********************************* DELETE SESSION *********************************

		// URL - http://localhost:8081/session

		@RequestMapping(
				path = "/session", 
				method = RequestMethod.DELETE, 
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public void deleteSession(
				@RequestBody SessionBoundary deleteSession) {

			this.sessionService.deleteSession(deleteSession);
		}


		
		
		
		//********************************* GET SESSION BY PATIENT ID *********************************

		// URL - http://localhost:8081/session/{userId}/byPatient{patientId}

		@RequestMapping(
				path = "/session/{userId}/byPatient/{patientId}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public SessionBoundary[] getSessionById(
				@PathVariable("userId") String userId,
				@PathVariable("patientId") String patientId,
				@RequestParam(name="size", required = false, defaultValue = "20") int size,
				@RequestParam(name="page", required = false, defaultValue = "0") int page) {

			return this.sessionService.getSessionsByPatient(userId, patientId, page, size).toArray(new SessionBoundary[0]);

		}
		
		
		
		@ExceptionHandler
		@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
		public Map<String, String> handleUserUnvalidException(RuntimeException e) {
			

			String message = e.getMessage();
			if (message == null) {
				message = "Unvalid session";
			}


			Map<String, String> errorMessage = new HashMap<>();
			errorMessage.put("error", message); 

			return errorMessage;
		}
	
	
	
}
