package com.example.demo.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.DiagnosisDao;
import com.example.demo.dal.PatientDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.DiagnosisEntity;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;
import com.example.demo.logic.Converters.DiagnosisConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.DiagnosisValidator;
import com.example.demo.rest.boundaries.DiagnosisBoundary;


@Service
public class DiagnosisServiceRdb implements DiagnosisService{



	private DiagnosisDao diagnosisDao;
	private DiagnosisConverter diagnosisConverter;
	private DiagnosisValidator diagnosisValidator;
	private UserDao userDao;
	private PatientDao patientDao;





	@Autowired
	public DiagnosisServiceRdb(DiagnosisDao diagnosisDao, DiagnosisConverter diagnosisConverter, DiagnosisValidator diagnosisValidator, UserDao userDao, PatientDao patientDao) {

		this.diagnosisDao = diagnosisDao;
		this.diagnosisConverter = diagnosisConverter;
		this.diagnosisValidator = diagnosisValidator;
		this.userDao = userDao;
		this.patientDao = patientDao;
	}




	@Override
	@Transactional
	public DiagnosisBoundary addDiagnosis(DiagnosisBoundary newDiagnosis, String userId) {

		this.diagnosisValidator.validateDiagnosis(newDiagnosis);

		UserEntity user = this.getActiveUserById(userId);

		PatientEntity patient = this
				.patientDao
				.findById(newDiagnosis.getPatient().getPatientId()).orElseThrow(()-> new NotFoundException("No patient could be found with id : "+ newDiagnosis.getPatient().getPatientId()));

		patient.setAdded_by(user);
		patient.setValidated_by(null); 	// TODO check if user->addedPatients delete this user connection!!
		
		this.patientDao.save(patient);
		
		return this.diagnosisConverter.fromEntity(this.diagnosisDao.save(this.diagnosisConverter.toEntity(newDiagnosis)));
		
	}
	
	
	

	@Override
	@Transactional
	public void updateDiagnosis(String userId, DiagnosisBoundary updateDiagnosis) {
		
		this.diagnosisValidator.validateDiagnosis(updateDiagnosis);

		UserEntity user = this.getActiveUserById(userId);

		PatientEntity patient = this
				.patientDao
				.findById(updateDiagnosis.getPatient().getPatientId()).orElseThrow(()-> new NotFoundException("No patient could be found with id : "+ updateDiagnosis.getPatient().getPatientId()));

		
		DiagnosisEntity exist =  this.diagnosisDao.findById(updateDiagnosis.getDiagnosId()).orElseThrow(()-> new NotFoundException("No Diagnosis could be found with id : "+updateDiagnosis.getDiagnosId()));
		
		DiagnosisEntity diagnosis =  this.diagnosisConverter.toEntity(updateDiagnosis);
		
		exist.setInjectionLocation(diagnosis.getInjectionLocation());
		exist.setAllergens(diagnosis.getAllergens());
		
		
		patient.setAdded_by(user);
		patient.setValidated_by(null); 	// TODO check if user->addedPatients delete this user connection!!
		
		this.patientDao.save(patient);
		
		this.diagnosisDao.save(exist);

	}

//	@Override
//	public void deleteDiagnosis(String userId, DiagnosisBoundary deleteDiagnosis) {
//		// TODO LOGIC
//
//	}

	@Override
	@Transactional(readOnly = true)
	public List<DiagnosisBoundary> getDiagnosisByPatient(String userId, String patientId, int page, int size) {
		
		
		this.getActiveUserById(userId);

//		PatientEntity patient = this
//				.patientDao
//				.findById(patientId).orElseThrow(()-> new NotFoundException("No patient could be found with id : "+ patientId));

		PatientEntity patient  = this.patientDao.findById(patientId).orElseThrow(()-> new NotFoundException());
		
		return this.diagnosisDao
				.findAllByPatient(patient, PageRequest.of(page, size))
				.stream()
				.map(this.diagnosisConverter::fromEntity)
				.collect(Collectors.toList());
	
		// TODO check if dao function work with patient id
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<DiagnosisBoundary> getAllDiagnosis(String userId, int page, int size) {
		
		UserEntity user = this.getActiveUserById(userId);
		
		if(user.getRole().equals(UserRole.Nurse))
			throw new UnvalidException("user do not have a permistion for this action!");

		
		return this
				.diagnosisDao
				.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.diagnosisConverter :: fromEntity)
				.collect(Collectors.toList());
		
	}




	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new NotFoundException("No user could be found with id: " + userId);

		return user;
	}

}
