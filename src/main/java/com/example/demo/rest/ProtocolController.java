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

import com.example.demo.logic.ProtocolService;
import com.example.demo.rest.boundaries.ProtocolBoundary;

@RestController
@CrossOrigin
public class ProtocolController {

	
	private ProtocolService protocolService;
	
	
	@Autowired
	public ProtocolController(ProtocolService protocolService) {
		this.protocolService = protocolService;
	}
	
	
	

	//********************************* ADD PROTOCOL *********************************

	//	URL - http://localhost:8081/protocol/{userId}

	@RequestMapping(
			path = "/protocol/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProtocolBoundary addProtocols(
			@PathVariable("userId") String userId,
			@RequestBody ProtocolBoundary newProtocols) {

		return this.protocolService.addProtocols(newProtocols, userId);

	}




	//********************************* UPDATE PROTOCOL *********************************

	// URL - http://localhost:8081/protocol/{userId}

	@RequestMapping(
			path = "/protocol/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProtocols(
			@PathVariable("userId") String userId,
			@RequestBody ProtocolBoundary updateProtocols) {

		this.protocolService.updateProtocols(userId, updateProtocols);

	}





	//********************************* DELETE PROTOCOL *********************************

	// URL - http://localhost:8081/protocol/{userId}

//	@RequestMapping(
//			path = "/protocol/{userId}", 
//			method = RequestMethod.DELETE, 
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void deleteProtocols(
//			@PathVariable("userId") String userId,
//			@RequestBody ProtocolBoundary deleteProtocols) {
//
//		this.protocolService.deleteProtocols(userId, deleteProtocols);
//
//	}


	
	
	
	//********************************* GET PROTOCOL BY ID *********************************

	// URL - http://localhost:8081/protocol/{userId}/byProtocolId{ProtocolId}

	@RequestMapping(
			path = "/protocol/{userId}/byProtocolId/{ProtocolId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProtocolBoundary getProtocolById(
			@PathVariable("userId") String userId,
			@PathVariable("ProtocolId") String ProtocolId) {

		return this.protocolService.getProtocolById(userId, ProtocolId);

	}
	
	
	
	//********************************* GET ALL PROTOCOL *********************************

	// URL - http://localhost:8081/protocol/{userId}/all

	@RequestMapping(
			path = "/protocol/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProtocolBoundary[] getAllProtocol(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "500") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.protocolService.getAllProtocols(userId, page, size).toArray(new ProtocolBoundary[0]);



	}
	
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleUserUnvalidException(RuntimeException e) {
		

		String message = e.getMessage();
		if (message == null) {
			message = "Unvalid protocol";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message); 

		return errorMessage;
	}
	
	
	
}
