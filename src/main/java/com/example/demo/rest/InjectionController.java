package com.example.demo.rest;

import java.util.HashMap;
import java.util.List;
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

import com.example.demo.logic.InjectionService;
import com.example.demo.rest.boundaries.InjectionBoundary;

@RestController
@CrossOrigin
public class InjectionController {

	
	private InjectionService injectionService;
	
	@Autowired
	public InjectionController(InjectionService injectionService) {
		this.injectionService = injectionService;
	}
	
	
	
	
	//********************************* ADD INJECTION *********************************

			//	URL - http://localhost:8081/injection/{userId}

			@RequestMapping(
					path = "/injection/{userId}",
					method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
			public InjectionBoundary addInjection(
					@PathVariable("userId") String userId,
					@RequestBody InjectionBoundary newInjection) {

				return this.injectionService.addInjection(newInjection, userId);

			}


			
			//********************************* ADD INJECTION *********************************

			//	URL - http://localhost:8081/injection/calculate/{userId}

			@RequestMapping(
					path = "/injection/calculate/{userId}",
					method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
			public InjectionBoundary calculateInjection(
					@PathVariable("userId") String userId,
					@RequestBody InjectionBoundary newInjection) {

				return this.injectionService.calculateInjection(newInjection, userId);

			}


			//********************************* UPDATE INJECTION *********************************

			// URL - http://localhost:8081/injection/{userId}

			@RequestMapping(
					path = "/injection/{userId}", 
					method = RequestMethod.PUT, 
					consumes = MediaType.APPLICATION_JSON_VALUE)
			public void updateInjection(
					@PathVariable("userId") String userId,
					@RequestBody InjectionBoundary updateInjection) {

				this.injectionService.updateInjection(userId, updateInjection);

			}





			//********************************* DELETE INJECTION *********************************

			// URL - http://localhost:8081/injection/{userId}

			@RequestMapping(
					path = "/injection/{userId}", 
					method = RequestMethod.DELETE, 
					consumes = MediaType.APPLICATION_JSON_VALUE)
			public void deleteInjection(
					@PathVariable("userId") String userId,
					@RequestBody InjectionBoundary deleteInjection) {

				this.injectionService.deleteInjection(userId, deleteInjection);

			}


			
			
			
			//********************************* GET INJECTION BY ID *********************************

			// URL - http://localhost:8081/injection/{userId}/byInjectionId{injectionId}

			@RequestMapping(
					path = "/injection/{userId}/byInjectionId/{injectionId}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
			public InjectionBoundary getInjectionById(
					@PathVariable("userId") String userId,
					@PathVariable("injectionId") String injectionId) {

				return this.injectionService.getInjectionById(userId, injectionId);



			}
			
			
			//********************************* GET INJECTION BY SESSION ID *********************************

			// URL - http://localhost:8081/injection/{userId}/bySession/{sessionId}

			@RequestMapping(
					path = "/injection/{userId}/bySession/{sessionId}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
			public List<InjectionBoundary> getInjectionBySession(
					@PathVariable("userId") String userId,
					@PathVariable("sessionId") String sessionId,
					@RequestParam(name="size", required = false, defaultValue = "50") int size,
					@RequestParam(name="page", required = false, defaultValue = "0") int page) {

				return this.injectionService.getInjectionBySession(userId, sessionId, page, size);



			}
			
			
			//********************************* GET INJECTION BY ALLERGEN PROTOCOL ID *********************************

			// URL - http://localhost:8081/injection/{userId}/byAllergenProtocolId/{allergenProtocolId}

			@RequestMapping(
					path = "/injection/{userId}/byAllergenProtocolId/{allergenProtocolId}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
			public List<InjectionBoundary> getInjectionByAllergenProtocol(
					@PathVariable("userId") String userId,
					@PathVariable("allergenProtocolId") String allergenProtocolId,
					@RequestParam(name="size", required = false, defaultValue = "50") int size,
					@RequestParam(name="page", required = false, defaultValue = "0") int page) {

				return this.injectionService.getInjectionByAllergenProtocol(userId, allergenProtocolId, page, size);



			}
	
			
			
			@ExceptionHandler
			@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
			public Map<String, String> handleUserUnvalidException(RuntimeException e) {
				
				String message = e.getMessage();
				if (message == null) {
					message = "Unvalid injection";
				}

				Map<String, String> errorMessage = new HashMap<>();
				errorMessage.put("error", message); 

				return errorMessage;
			}
	
	
}
