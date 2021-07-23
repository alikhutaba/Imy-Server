package com.example.demo.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.AllergenProtocolsDao;
import com.example.demo.dal.InjectionDao;
import com.example.demo.dal.PatientDao;
import com.example.demo.dal.SessionDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.data.InjectionEntity;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.ProtocolEntity;
import com.example.demo.data.SessionEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.logic.Converters.InjectionConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.InjectionValidator;
import com.example.demo.rest.boundaries.InjectionBoundary;


@Service
public class InjectionServiceRdb implements InjectionService{


	private InjectionDao injectionDao;
	private InjectionValidator injectionValidator;
	private InjectionConverter injectionConverter;
	private UserDao userDao;
	private PatientDao patientDao;
	private AllergenProtocolsDao allergenProtocolsDao;
	private SessionDao sessionDao;




	@Autowired
	public InjectionServiceRdb(InjectionDao injectionDao, InjectionValidator injectionValidator, InjectionConverter injectionConverter, UserDao userDao, PatientDao patientDao, AllergenProtocolsDao allergenProtocolsDao, SessionDao sessionDao) {

		this.injectionDao = injectionDao;
		this.injectionValidator = injectionValidator;
		this.injectionConverter = injectionConverter;
		this.userDao = userDao;
		this.patientDao = patientDao;
		this.allergenProtocolsDao = allergenProtocolsDao;
		this.sessionDao = sessionDao;
	}






	@Override
	@Transactional
	public InjectionBoundary addInjection(InjectionBoundary newInjection, String userId) {

		newInjection.setInjectionId(null);
		newInjection.setSignUpTimestamp(new Date());
		this.injectionValidator.validateInjection(newInjection);

		this.getActiveUserById(userId);
		PatientEntity patientEntity = this.getPatientById(newInjection.getSession().getPatient().getPatientId());

		if(patientEntity.getValidated_by() == null) 
			throw new UnvalidException("patient is not validate by any user... need to validate patient information first.");


//		InjectionEntity injection = this.injectionConverter.toEntity(newInjection);
//
//		this.calculateNextInjection(injection);
//
//		InjectionBoundary returnInjection =  this.injectionConverter.fromEntity(this.injectionDao.save(injection));
//
//
//		if(returnInjection.getDosage() == 0 && returnInjection.getConcentration() == 0)
//			returnInjection.setDoctorInstruction(true);
//		else
//			returnInjection.setDoctorInstruction(false);


//		return returnInjection;
		
		
		return this.injectionConverter.fromEntity(this.injectionDao.save(this.injectionConverter.toEntity(newInjection)));
		
		
		
		
	}

	
	
	@Override
	@Transactional
	public InjectionBoundary calculateInjection(InjectionBoundary newInjection, String userId) {

		newInjection.setInjectionId(null);
		newInjection.setSignUpTimestamp(new Date());
		this.injectionValidator.validateInjection(newInjection);

		this.getActiveUserById(userId);
		PatientEntity patientEntity = this.getPatientById(newInjection.getSession().getPatient().getPatientId());

		if(patientEntity.getValidated_by() == null) 
			throw new UnvalidException("patient is not validate by any user... need to validate patient information first.");


		InjectionEntity injection = this.injectionConverter.toEntity(newInjection);

		this.calculateNextInjection(injection);

		InjectionBoundary returnInjection =  this.injectionConverter.fromEntity(injection);


		if(returnInjection.getDosage() == "0" && returnInjection.getConcentration() == "0")
			returnInjection.setDoctorInstruction(true);
		else
			returnInjection.setDoctorInstruction(false);


		return returnInjection;
	}
	
	
	
	@Override
	@Transactional
	public void updateInjection(String userId, InjectionBoundary updateInjection) {

		UserEntity user = this.getActiveUserById(userId);
		this.getPatientById(updateInjection.getSession().getPatient().getPatientId());

		InjectionEntity exist = this.injectionDao.findById(updateInjection.getInjectionId()).orElseThrow(()-> new NotFoundException("No injection with id +"+updateInjection.getInjectionId()));
		InjectionEntity injection = this.injectionConverter.toEntity(updateInjection);


		exist.setInjectionLocation(injection.getInjectionLocation());
		exist.setNotes(injection.getNotes());
		exist.setDoseNumber(injection.getDoseNumber());
		exist.setDosage(injection.getDosage());
		exist.setConcentration(injection.getConcentration());

		exist.setCreatedBy(user);

		this.injectionDao.save(exist);

	}


	@Override
	@Transactional
	public void deleteInjection(String userId, InjectionBoundary deleteInjection) {
		// TODO ADD LOGIC

	}

	@Override
	@Transactional(readOnly = true)
	public InjectionBoundary getInjectionById(String userId, String injectionId) {

		this.getActiveUserById(userId);

		return this.
				injectionConverter.
				fromEntity(
						this
						.injectionDao
						.findById(Long.parseLong(injectionId))
						.orElseThrow(()-> new NotFoundException("No injection with id +"+injectionId)));

	}

	@Override
	@Transactional(readOnly = true)
	public List<InjectionBoundary> getInjectionBySession(String userId, String sessionId, int page, int size) {

		this.getActiveUserById(userId);

		SessionEntity sessionEntity = this.sessionDao.findById(Long.parseLong(sessionId)).orElseThrow(()-> new NotFoundException("No session wit id: "+sessionId));

		return this
				.injectionDao
				.findAllBySession(sessionEntity, PageRequest.of(page, size))
				.stream()
				.map(this.injectionConverter :: fromEntity)
				.collect(Collectors.toList());

	}

	@Override
	@Transactional(readOnly = true)
	public List<InjectionBoundary> getInjectionByAllergenProtocol(String userId, String allergenProtocolId, int page, int size) {

		this.getActiveUserById(userId);

		AllergenProtocolsEntity allergenProtocolsEntity = this.allergenProtocolsDao.findById(Long.parseLong(allergenProtocolId)).orElseThrow(()-> new NotFoundException("No allergen Protocol wit id: "+allergenProtocolId));

		return this
				.injectionDao
				.findByAllergenProtocol(allergenProtocolsEntity, PageRequest.of(page, size, Sort.Direction.DESC, "createdTimestamp"))
				.stream()
				.map(this.injectionConverter :: fromEntity)
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




	private InjectionEntity calculateNextInjection(InjectionEntity injection) {

		List<InjectionEntity> previousInjectionList = this.injectionDao.findByAllergenProtocol(injection.getAllergenProtocol(), PageRequest.of(0, 1, Sort.Direction.DESC, "createdTimestamp"));

		ProtocolEntity protocol = injection.getAllergenProtocol().getProtocol();

		if(previousInjectionList.isEmpty()) 
			return this.setInjectionDose(injection, 0, protocol.getDosage().get(0), protocol.getConcentration().get(0));

		
		InjectionEntity previousInjection = this.checkPreviousInjection(previousInjectionList.get(0));

		Long dateDiff = this.computeDateDiff(previousInjection.getCreatedTimestamp(), new Date());

		int doseNumber = this.calculateNextDoseNumber(dateDiff, previousInjection.getDoseNumber(), protocol.getDosesNumber());


		this.print(previousInjection, dateDiff);

		if(doseNumber == 0)
			return this.setInjectionDose(injection, 0, "0", "0");

		return this.setInjectionDose(injection, doseNumber, protocol.getDosage().get(doseNumber-1), protocol.getConcentration().get(doseNumber-1));

	}

	
	
	private InjectionEntity checkPreviousInjection(InjectionEntity previousInjection) {
		
		if(previousInjection.getDosage() == "0" && previousInjection.getConcentration() == "0")
			throw new UnvalidException("Previous injection dosage and concentration is invalid..!");
		
		return previousInjection;
		
	}



	private InjectionEntity setInjectionDose(InjectionEntity injection, int doseNumber, String dosage, String concentration) {

		injection.setDoseNumber(doseNumber);
		injection.setDosage(dosage);
		injection.setConcentration(concentration);
		return injection;
	}




	private int calculateNextDoseNumber(Long dateDiff, int previousInjectionDoseNumber, int protocolDoseNumber) {

		int doseNumber = 0;
		if(previousInjectionDoseNumber == protocolDoseNumber) { //		Maintenance ----------------------------------------------

			if(dateDiff < 42) 
				doseNumber = previousInjectionDoseNumber;
			else
				if(dateDiff >= 42) 
					doseNumber = 0;
		}
		else { //														Increase ----------------------------------------------

			if(dateDiff >= 4 && dateDiff < 7) 
				doseNumber = previousInjectionDoseNumber+1;
			else
				if(dateDiff >=7 && dateDiff < 15)
					doseNumber = previousInjectionDoseNumber;
				else
					if(dateDiff >=15 && dateDiff < 21 && previousInjectionDoseNumber > 1)
						doseNumber = previousInjectionDoseNumber-1;
					else
						if(dateDiff >= 21) 
							doseNumber = 0;
		}

		return doseNumber;
	}

	
	private Long computeDateDiff(Date date1, Date date2) {

		long diffInMillies = date2.getTime() - date1.getTime();

		//create the list
		List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);
		
		//create the result map of TimeUnit and difference
		Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
		long milliesRest = diffInMillies;

		for ( TimeUnit unit : units ) {

			//calculate difference in millisecond 
			long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;

			//put the result in the map
			result.put(unit,diff);
		}


		if(result.get(TimeUnit.DAYS) < 0)
			throw new UnvalidException("Error date calculation..!");

		return result.get(TimeUnit.DAYS);
	}






	private void print(InjectionEntity previousInjection, Long dateDiff) {

		System.err.println("previous Injection Id : "+previousInjection.getInjectionId());
		System.err.println("previous Dose Number : "+previousInjection.getDoseNumber());
		System.err.println("previous Concentration : "+previousInjection .getConcentration());
		System.err.println("previous Dosage : "+previousInjection.getDosage());
		System.err.println("date Diff : "+dateDiff);

	}







}




