package com.example.demo.logic;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.PatientDao;
import com.example.demo.dal.SessionDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.SessionEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.logic.Converters.SessionConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.SessionValidator;
import com.example.demo.rest.boundaries.SessionBoundary;



@Service
public class SessionServiceRdb implements SessionService{

	
	private SessionDao sessionDao;
	private SessionConverter sessionConverter;
	private SessionValidator sessionValidator;
	private UserDao userDao;
	private PatientDao patientDao;
	
	
	
	@Autowired
	public SessionServiceRdb(SessionDao sessionDao, SessionConverter sessionConverter, SessionValidator sessionValidator, UserDao userDao, PatientDao patientDao) {

		this.sessionDao = sessionDao;
		this.sessionConverter = sessionConverter;
		this.sessionValidator = sessionValidator;
		this.userDao = userDao;
		this.patientDao = patientDao;
	}
	
	
	
	
	@Override
	@Transactional
	public SessionBoundary addSession(SessionBoundary newSession) {

		newSession.setSignUpTimestamp(new Date());
		newSession.setSessionId(null);

		this.sessionValidator.validateSession(newSession);
		
		PatientEntity patient = this.getPatient(newSession.getPatient().getPatientId());
		
		if(patient.getValidated_by() == null)
			throw new UnvalidException("patient is not validate by any user... need to validate patient information first.");
		
		this.getActiveUserById(newSession.getCreatedBy().getUserId());
		
		
		return this.sessionConverter.fromEntity(this.sessionDao.save(this.sessionConverter.toEntity(newSession)));
	}
	

	@Override
	@Transactional
	public void updateSession(SessionBoundary updateSession) {
		
		this.sessionValidator.validateSession(updateSession);
		
		this.getActiveUserById(updateSession.getCreatedBy().getUserId());

		SessionEntity session = this.sessionConverter.toEntity(updateSession);
		SessionEntity exist = this.sessionDao.findById(session.getSessionId()).orElseThrow(()-> new NotFoundException("No Protocol could be found with id: "+ session.getSessionId()));
		
		exist.setHowYouFeelToday(session.getHowYouFeelToday());
		exist.setHowThePreviousInjectionWent(session.getHowThePreviousInjectionWent());
		exist.setAntihistamineBeforeVaccination(session.getAntihistamineBeforeVaccination());
		
		this.sessionDao.save(exist);
		
	}

	@Override
	@Transactional
	public void deleteSession(SessionBoundary deleteSession) {
		// TODO LOGIC
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<SessionBoundary> getSessionsByPatient(String userId, String patientId, int page, int size) {

		this.getActiveUserById(userId);

		PatientEntity patient = this.patientDao.findById(patientId).orElseThrow(()-> new NotFoundException("No patient could be found with id : "+ patientId));

		
		return this.sessionDao
				.findAllBySessionPatient(patient, PageRequest.of(page, size))
				.stream()
				.map(this.sessionConverter::fromEntity)
				.collect(Collectors.toList());
	
	}
	
	
	
	@Transactional(readOnly = true)
	private PatientEntity getPatient(String patientId) {
		
		return this
				.patientDao
				.findById(patientId)
				.orElseThrow(()-> new NotFoundException("No patient could be found with id: "+patientId));
		
	}

	
	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new NotFoundException("No user could be found with id: " + userId);

		return user;
	}

}
