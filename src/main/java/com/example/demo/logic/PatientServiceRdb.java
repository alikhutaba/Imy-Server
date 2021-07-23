package com.example.demo.logic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.AddressDao;
import com.example.demo.dal.PatientDao;
import com.example.demo.dal.UserDao;
import com.example.demo.logic.Converters.PatientConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.PatientValidator;
import com.example.demo.rest.boundaries.PatientBoundary;

import com.example.demo.data.UserRole;

import com.example.demo.data.AddressEntity;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.UserEntity;


@Service
public class PatientServiceRdb implements PatientService{


	private PatientDao patientDao;
	private PatientConverter patientConverter;
	private PatientValidator patientValidator;
	private AddressDao addressDao;
	private UserDao userDao;



	@Autowired
	public PatientServiceRdb(
			PatientDao patientDao,
			PatientConverter patientConverter,
			PatientValidator patientValidator,
			AddressDao addressDao,
			UserDao userDao) {

		this.patientDao = patientDao;
		this.patientConverter = patientConverter;
		this.patientValidator = patientValidator;
		this.addressDao = addressDao;
		this.userDao = userDao;
	}




	@Override
	@Transactional
	public PatientBoundary addPatient(PatientBoundary patient, String userId) {


		patient.setActive(true);
		patient.setSignUpTimestamp(new Date());
		patient.setUpdatedTimestamp(new Date());
		
		this.patientValidator.validatePatient(patient);

		UserEntity actionUser = this.getActiveUserById(userId);

		this.checkExistPatient(patient);

		PatientEntity newPatient =  this.patientConverter.toEntity(patient);

		newPatient.setAddress(this.addressDao.save(newPatient.getAddress()));
		newPatient.setAdded_by(actionUser);

		return this.patientConverter.fromEntity(this.patientDao.save(newPatient));

	}




	@Override
	@Transactional
	public void updatePatient(PatientBoundary patient, String userId) {
		
		this.patientValidator.validatePatient(patient);

		PatientEntity patientUpdate = this.patientConverter.toEntity(patient);

		UserEntity actionUser = this.getActiveUserById(userId);

		PatientEntity existPatient = this.getActivePatientById(patient.getPatientId());


		existPatient.setFisrtName(patientUpdate.getFisrtName());
		existPatient.setLastName(patientUpdate.getLastName());
		existPatient.setMiddleName(patientUpdate.getMiddleName());
		existPatient.setBirthdate(patientUpdate.getBirthdate());
		existPatient.setGender(patientUpdate.getGender());
		existPatient.setPhone1(patientUpdate.getPhone1());
		existPatient.setPhone2(patientUpdate.getPhone2());

		if(!existPatient.getAddress().equals(patientUpdate.getAddress())) {

			AddressEntity temp = existPatient.getAddress();
			existPatient.setAddress(this.addressDao.save(patientUpdate.getAddress()));
			this.addressDao.delete(temp);
		}

		existPatient.setHmo(patientUpdate.getHmo());
		existPatient.setUpdatedTimestamp(new Date());
		existPatient.setAdded_by(actionUser);	// TODO check if user->addedPatients delete this user connection!!
		existPatient.setValidated_by(null);		// TODO check if user->validatedPatients delete this user connection!!	

		existPatient.setVaccineStatus(patientUpdate.getVaccineStatus());
		existPatient.setMedicationSensitivity(patientUpdate.getMedicationSensitivity());
		existPatient.setAsthmaRhinitis(patientUpdate.getAsthmaRhinitis());
		existPatient.setAntihistamine(patientUpdate.getAntihistamine());
		existPatient.setNotes(patientUpdate.getNotes());

		this.patientDao.save(existPatient);

	}



	@Override
	@Transactional
	public void deletePatient(PatientBoundary patient, String userId) {

		this.patientValidator.validatePatient(patient);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.Nurse))
			throw new UnvalidException("user do not have a permistion for this action!");


		PatientEntity existPatient = this.getActivePatientById(patient.getPatientId());

		existPatient.setAdded_by(actionUser);	// TODO check if user->addedPatients delete this user connection!!
		existPatient.setValidated_by(null); 	// TODO check if user->validatedPatients delete this user connection!!
		existPatient.setActive(false);			

		this.patientDao.save(existPatient);

	}

	
	
	
	
	@Override
	@Transactional
	public PatientBoundary validatePatient(PatientBoundary patient, String userId) {
		
		this.patientValidator.validatePatient(patient);

		UserEntity actionUser = this.getActiveUserById(userId);

		PatientEntity existPatient = this.getActivePatientById(patient.getPatientId());
		
		if(existPatient.getAdded_by().equals(actionUser)) 
			throw new UnvalidException("you can't add and validate patients");

		existPatient.setValidated_by(actionUser);

		return this.patientConverter.fromEntity(this.patientDao.save(existPatient));

	}
	
	

	@Override
	@Transactional(readOnly = true)
	public PatientBoundary getActivePatientById(String userId, String patientId) {

		this.getActiveUserById(userId);

		return this
				.patientConverter
				.fromEntity(this.getActivePatientById(patientId));
	}	




	@Override
	@Transactional(readOnly = true)
	public PatientBoundary getPatientById(String userId, String patientId) {

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");


		return this
				.patientConverter
				.fromEntity(this
						.patientDao
						.findById(patientId)
						.orElseThrow(() -> new NotFoundException("No patient could be found with id: " + patientId))); 
	}	





	@Override
	@Transactional(readOnly = true)
	public List<PatientBoundary> getAllActivePatients(String userId, int page, int size) {

		this.getActiveUserById(userId);

		return this
				.patientDao
				.getAllActivePatients(true, PageRequest.of(page, size))
				.map(this.patientConverter::fromEntity)	
				.collect(Collectors.toList());

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PatientBoundary> getAllActiveAndUnvalidPatients(String userId, int page, int size) {

		this.getActiveUserById(userId);
		
		return this
				.patientDao
				.getAllActiveAndUnvalidPatients(true, PageRequest.of(page, size))
				.map(this.patientConverter::fromEntity)	
				.collect(Collectors.toList());
	}
	


	@Override
	@Transactional(readOnly = true)
	public List<PatientBoundary> getAllPatients(String userId, int page, int size) {

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");

		return this
				.patientDao.
				findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.patientConverter::fromEntity)	
				.collect(Collectors.toList());


	}





	//	@Override
	//	@Transactional(readOnly = true)
	//	public List<PatientBoundary> getPatientByFirstName(String userId, String patientFisrtName, int size, int page) {
	//		// TODO LOGIC
	//		return null;
	//	}
	//
	//	
	//	
	//	@Override
	//	@Transactional(readOnly = true)
	//	public PatientBoundary getPatientPhone(String userId, String patientPhone) {
	//		// TODO LOGIC
	//		return null;
	//	}





	@Transactional(readOnly = true)
	private PatientEntity getActivePatientById(String patientId) {

		PatientEntity patient = this.patientDao.findById(patientId).orElseThrow(() -> new NotFoundException("No patient could be found with id: " + patientId)); 

		if(!patient.getActive())
			throw new NotFoundException("No patient could be found with id: " + patientId);

		return patient;	
	}




	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new NotFoundException("No user could be found with id: " + userId);

		return user;
	}




	@Transactional(readOnly = true)
	private void checkExistPatient(PatientBoundary patient) {


		boolean isExist = false;

		PatientEntity existPatient;
		try {
			existPatient = this.patientDao.findById(patient.getPatientId()).orElseThrow(() -> new NotFoundException("No patinet could be found with id: " + patient.getPatientId()));

			if(existPatient.getActive() == true)
				isExist = true;

		} catch (Exception e) {
			System.err.println("It is all right the user does not exist");
		}

		if(isExist)
			throw new UnvalidException("A patient with the id: " + patient.getPatientId()+ " already exists!");



	}






}
