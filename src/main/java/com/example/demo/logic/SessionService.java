package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.SessionBoundary;

public interface SessionService {

	public SessionBoundary addSession(SessionBoundary newSession);

	public void updateSession(SessionBoundary updateSession);

	public void deleteSession(SessionBoundary deleteSession);

	public List<SessionBoundary> getSessionsByPatient(String userId, String patientId,int page, int size);
}
