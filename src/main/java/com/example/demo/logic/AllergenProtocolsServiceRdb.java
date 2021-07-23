package com.example.demo.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.AllergenProtocolsDao;
import com.example.demo.dal.DiagnosisDao;
import com.example.demo.dal.PatientDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.data.DiagnosisEntity;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;
import com.example.demo.logic.Converters.AllergenProtocolsConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.AllergenProtocolsValidator;
import com.example.demo.rest.boundaries.AllergenProtocolsBoundary;

@Service
public class AllergenProtocolsServiceRdb implements AllergenProtocolsService{



	private AllergenProtocolsDao allergenProtocolsDao;
	private AllergenProtocolsConverter allergenProtocolsConverter;
	private AllergenProtocolsValidator allergenProtocolsValidator;
	private UserDao userDao;
	private PatientDao patientDao;
	private DiagnosisDao diagnosisDao;



	public AllergenProtocolsServiceRdb(AllergenProtocolsDao allergenProtocolsDao, AllergenProtocolsConverter allergenProtocolsConverter, AllergenProtocolsValidator allergenProtocolsValidator, UserDao userDao, PatientDao patientDao, DiagnosisDao diagnosisDao) {

		this.allergenProtocolsDao = allergenProtocolsDao;
		this.allergenProtocolsValidator = allergenProtocolsValidator;
		this.allergenProtocolsConverter = allergenProtocolsConverter;
		this.userDao = userDao;
		this.patientDao = patientDao;
		this.diagnosisDao = diagnosisDao;
	}



	@Override
	@Transactional
	public AllergenProtocolsBoundary addAllergenProtocols(AllergenProtocolsBoundary newAllergenProtocols, String userId) {

		this.allergenProtocolsValidator.ValidateAllergenProtocol(newAllergenProtocols);

		UserEntity user =  this.getActiveUserById(userId);

		PatientEntity patient = this.getPatientById(newAllergenProtocols.getDiagnosis().getPatient().getPatientId());

		patient.setAdded_by(user);
		patient.setValidated_by(null); 	// TODO check if user->addedPatients delete this user connection!!
		this.patientDao.save(patient);
		
		newAllergenProtocols.setAllergenProtocolsId(null);
		newAllergenProtocols.setCompleted(false);

		return this
				.allergenProtocolsConverter
				.fromEntity(this
						.allergenProtocolsDao
						.save(this
								.allergenProtocolsConverter
								.toEntity(newAllergenProtocols)));
	}

	@Override
	@Transactional
	public void updateAllergenProtocols(String userId, AllergenProtocolsBoundary updateAllergenProtocols) {

		this.allergenProtocolsValidator.ValidateAllergenProtocol(updateAllergenProtocols);

		UserEntity user =  this.getActiveUserById(userId);

		PatientEntity patient = this.getPatientById(updateAllergenProtocols.getDiagnosis().getPatient().getPatientId());

		AllergenProtocolsEntity exist = this.allergenProtocolsDao.findById(updateAllergenProtocols.getAllergenProtocolsId()).orElseThrow(()-> new NotFoundException("No allergen protocol could be found with id : "+updateAllergenProtocols.getAllergenProtocolsId()));
		
		AllergenProtocolsEntity allergenProtocol = this.allergenProtocolsConverter.toEntity(updateAllergenProtocols);
		
		exist.setProtocol(allergenProtocol.getProtocol());
		exist.setCompleted(allergenProtocol.getCompleted());
		
		patient.setAdded_by(user);
		patient.setValidated_by(null); 	// TODO check if user->addedPatients delete this user connection!!

		this.patientDao.save(patient);
		
		this.allergenProtocolsDao.save(exist);
	}

//	@Override
//	@Transactional
//	public void deleteAllergenProtocols(String userId, AllergenProtocolsBoundary deleteAllergenProtocols) {
//		// TODO Logic
//
//	}

	@Override
	@Transactional(readOnly = true)
	public List<AllergenProtocolsBoundary> getAllergenProtocolsByDiagnosis(String userId, String diagnosisId, int page, int size) {
		
		this.getActiveUserById(userId);

		
		DiagnosisEntity diagnosis = this.diagnosisDao.findById(Long.parseLong(diagnosisId)).orElseThrow(()-> new NotFoundException("No diagnosis could be found with id: "+diagnosisId));
		
		
		
		return this.allergenProtocolsDao
				.findAllByDiagnosis(diagnosis, PageRequest.of(page, size)).stream()
				.map(this.allergenProtocolsConverter::fromEntity)
				.collect(Collectors.toList());
	
		
		// TODO check if dao function work with patient id
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<AllergenProtocolsBoundary> getAllAllergenProtocols(String userId, int page, int size) {
		
	UserEntity user = this.getActiveUserById(userId);
		
		if(user.getRole().equals(UserRole.Nurse))
			throw new UnvalidException("user do not have a permistion for this action!");

		
		return this
				.allergenProtocolsDao
				.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.allergenProtocolsConverter :: fromEntity)
				.collect(Collectors.toList());
	}




	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new NotFoundException("No user could be found with id: " + userId);

		return user;
	}

	@Transactional(readOnly = true)
	private PatientEntity getPatientById(String patientId) {

		PatientEntity patient =  this.patientDao.findById(patientId).orElseThrow(() -> new NotFoundException("No patient could be found with id: " + patientId));

		return patient;
	}



}
