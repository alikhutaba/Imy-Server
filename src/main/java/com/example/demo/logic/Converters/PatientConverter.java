package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.PatientEntity;
import com.example.demo.rest.boundaries.Gender;
import com.example.demo.rest.boundaries.Hmo;
import com.example.demo.rest.boundaries.PatientBoundary;
import com.example.demo.rest.boundaries.VaccineStatus;


@Component
public class PatientConverter {
	
	
	private AddressConverter addressConverter;
	private UserConverter userConverter;
	private QuestionConverter questionConverter;
	private BirthDateConverter birthDateConverter;

	
	@Autowired
	public PatientConverter(AddressConverter addressConverter, UserConverter userConverter, QuestionConverter questionConverter, BirthDateConverter birthDateConverter) {
		super();
		this.addressConverter = addressConverter;
		this.userConverter = userConverter;
		this.questionConverter = questionConverter;
		this.birthDateConverter = birthDateConverter;
	}
	
	
	
	public PatientBoundary fromEntity(PatientEntity patientEntity) {

		
		try {

			return new PatientBoundary(patientEntity.getPatientId(), patientEntity.getFisrtName(),
					patientEntity.getLastName(),
					patientEntity.getMiddleName(),
				 	patientEntity.getBirthdate() !=  null ? this.birthDateConverter.fromEntity(patientEntity.getBirthdate()): null,
					Gender.valueOf(patientEntity.getGender().name()),
					patientEntity.getPhone1(),
					patientEntity.getPhone2(),
					this.addressConverter.fromEntity(patientEntity.getAddress()),
					patientEntity.getActive(),
					Hmo.valueOf(patientEntity.getHmo().name()),
					patientEntity.getSignUpTimestamp(),
					patientEntity.getUpdatedTimestamp(),
					this.userConverter.fromEntity(patientEntity.getAdded_by()),
					patientEntity.getValidated_by() == null ? null : this.userConverter.fromEntity(patientEntity.getValidated_by()),
					VaccineStatus.valueOf(patientEntity.getVaccineStatus().name()),
					this.questionConverter.fromEntity(patientEntity.getMedicationSensitivity()),
					patientEntity.getAsthmaRhinitis(),
					patientEntity.getAntihistamine(),
					patientEntity.getNotes()
					);


			

		} catch (Exception e) {
			System.err.println(e);
			throw new UnvalidConverterException("Coulde not convert Patient from Entity to Boundary: "+ e.getMessage());
		}

	}




	public PatientEntity toEntity(PatientBoundary patientBoundary) {

		try {

			return new PatientEntity(patientBoundary.getPatientId(),
					patientBoundary.getFisrtName(),
					patientBoundary.getLastName(),
					patientBoundary.getMiddleName(),
					this.birthDateConverter.toEntity(patientBoundary.getBirthdate()),
					com.example.demo.data.Gender.valueOf(patientBoundary.getGender().name()),
					patientBoundary.getPhone1(),
					patientBoundary.getPhone2(),
					this.addressConverter.toEntity(patientBoundary.getAddress()),
					patientBoundary.getActive(),
					com.example.demo.data.Hmo.valueOf(patientBoundary.getHmo().name()),
					patientBoundary.getSignUpTimestamp(),
					patientBoundary.getUpdatedTimestamp(),
					this.userConverter.toEntity(patientBoundary.getAdded_by()),
					patientBoundary.getValidated_by() == null ? null : this.userConverter.toEntity(patientBoundary.getValidated_by()),
					com.example.demo.data.VaccineStatus.valueOf(patientBoundary.getVaccineStatus().name()),
					this.questionConverter.toEntity(patientBoundary.getMedicationSensitivity()),
					patientBoundary.getAsthmaRhinitis(),
					patientBoundary.getAntihistamine(),
					patientBoundary.getNotes(),
					null,
					null
					);



		} catch (Exception e) {
			System.err.println(patientBoundary);
			throw new UnvalidConverterException("Coulde not convert Patient from Boundary to Entity: "+ e.getMessage());
		}	

	}

	
	
	
}
