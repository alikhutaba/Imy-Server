package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.InjectionBoundary;

public interface InjectionService {

	public InjectionBoundary addInjection(InjectionBoundary newInjection, String userId);
	
	public InjectionBoundary calculateInjection(InjectionBoundary newInjection, String userId);

	public void updateInjection(String userId, InjectionBoundary updateInjection);

	public void deleteInjection(String userId, InjectionBoundary deleteInjection);

	public InjectionBoundary getInjectionById(String userId, String injectionId);
	
	public List<InjectionBoundary> getInjectionBySession(String userId, String sessionId, int page, int size);

	public List<InjectionBoundary> getInjectionByAllergenProtocol(String userId, String allergenProtocolId, int page, int size);
}
