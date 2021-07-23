package com.example.demo.data.util;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.demo.data.AddressEntity;
import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.data.AllergensEntity;
import com.example.demo.data.DiagnosisEntity;
import com.example.demo.data.Gender;
import com.example.demo.data.Hmo;
import com.example.demo.data.InjectionEntity;
import com.example.demo.data.InjectionLocation;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.ProtocolEntity;
import com.example.demo.data.QuestionEntity;
import com.example.demo.data.SessionEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;
import com.example.demo.data.VaccineStatus;

@Component
public class EntityFactoryImplementation implements EntityFactory{


	public EntityFactoryImplementation() {};

	@Override
	public UserEntity createNewUser(String userId, String workerNumber, String fisrtName, String lastName,
			LocalDate birthdate, Gender gender, String phone, AddressEntity address, Boolean active,
			Date signUpTimestamp, String email, String password, UserRole role, Set<PatientEntity> addedPatients,
			Set<PatientEntity> validatedPatients, Set<SessionEntity> session, Set<InjectionEntity> injections) {

		
		return new UserEntity(userId, workerNumber, fisrtName, lastName, birthdate, gender, phone, address, active, signUpTimestamp, email, password, role, addedPatients, validatedPatients, session, injections);

	}

	@Override
	public AddressEntity createNewAddress(Long id, String state, String city, String streetAddress, int houseNumber, int zipCode) {

		return new AddressEntity(id, state, city, streetAddress, houseNumber, zipCode);
	}

	@Override
	public AllergenProtocolsEntity createNewAllergenProtocols(Long allergenProtocolsId, ProtocolEntity protocol,
			DiagnosisEntity diagnosis, Set<InjectionEntity> injections, Boolean completed) {
		
		return new AllergenProtocolsEntity(allergenProtocolsId, protocol, diagnosis, injections, completed);
	}

	@Override
	public AllergensEntity createNewAllergens(Long id, String name, Set<DiagnosisEntity> diagnosis) {

		return new AllergensEntity(id, name, diagnosis);
	}

	@Override
	public DiagnosisEntity createNewDiagnosis(Long diagnosId, int diagnosisNumber, List<InjectionLocation> injectionLocation,
			PatientEntity patient, Set<AllergensEntity> allergens, Set<AllergenProtocolsEntity> allergenProtocols) {

		return new DiagnosisEntity(diagnosId, diagnosisNumber, injectionLocation, patient, allergens, allergenProtocols);
	}

	@Override
	public InjectionEntity createNewInjection(Long injectionId, Date createdTimestamp, int doseNumber,
			InjectionLocation injectionLocation, String notes, AllergenProtocolsEntity allergenProtocol,
			SessionEntity session, UserEntity createdBy, String concentration, String dosage) {

		return new InjectionEntity(injectionId, createdTimestamp, doseNumber, injectionLocation, notes, allergenProtocol, session, createdBy, concentration, dosage);
	}

	@Override
	public PatientEntity createNewPatient(String patientId, String fisrtName, String lastName, String middleName,
			LocalDate birthdate, Gender gender, String phone1, String phone2, AddressEntity address, Boolean active,
			Hmo hmo, Date signUpTimestamp, Date updatedTimestamp, UserEntity added_by, UserEntity validated_by,
			VaccineStatus vaccineStatus, QuestionEntity medicationSensitivity, String asthmaRhinitis, String antihistamine, String notes,
			Set<SessionEntity> session, Set<DiagnosisEntity> diagnosis) {

		return new PatientEntity(patientId, fisrtName, lastName, middleName, birthdate, gender, phone1, phone2, address, active, hmo, signUpTimestamp, updatedTimestamp, added_by, validated_by, vaccineStatus, medicationSensitivity, asthmaRhinitis, notes, antihistamine, session, diagnosis);
	}

	@Override
		public ProtocolEntity createNewProtocol(Long protocolId, String name, int dosesNumber, List<String> concentration,
				List<String> dosage, List<String> colors, Set<AllergenProtocolsEntity> allergenProtocols) {
			
		return new ProtocolEntity(protocolId, name, dosesNumber, concentration, dosage, colors, allergenProtocols);
	}
	
	
	@Override
	public SessionEntity createNewSession(Long sessionId, PatientEntity sessionPatient, UserEntity createdBy,
			Date signUpTimestamp, QuestionEntity howYouFeelToday, QuestionEntity howThePreviousInjectionWent,
			QuestionEntity antihistamineBeforeVaccination, Set<InjectionEntity> injections) {
		
		return new SessionEntity(sessionId, sessionPatient, createdBy, signUpTimestamp, howYouFeelToday, howThePreviousInjectionWent, antihistamineBeforeVaccination, injections);
	}



}
