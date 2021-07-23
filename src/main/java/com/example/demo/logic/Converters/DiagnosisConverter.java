package com.example.demo.logic.Converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.DiagnosisEntity;
import com.example.demo.rest.boundaries.DiagnosisBoundary;
import com.example.demo.rest.boundaries.InjectionLocation;



@Component
public class DiagnosisConverter {

	
	
	private PatientConverter patientConverter;
	private AllergensConverter allergensConverter;
	
	@Autowired
	public DiagnosisConverter(PatientConverter patientConverter, AllergensConverter allergensConverter) {
		super();
		this.patientConverter = patientConverter;
		this.allergensConverter = allergensConverter;
	}
	
	
	
	public DiagnosisBoundary fromEntity(DiagnosisEntity diagnosisEntity) {

		try {

			return new DiagnosisBoundary(
					diagnosisEntity.getDiagnosId(),
					this.patientConverter.fromEntity(diagnosisEntity.getPatient()),
					diagnosisEntity.getAllergens().stream().map(this.allergensConverter:: fromEntity).collect(Collectors.toSet()),
					diagnosisEntity.getDiagnosisNumber(),
					diagnosisEntity.getInjectionLocation().stream().map(location -> InjectionLocation.valueOf(location.name())).collect(Collectors.toList())
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Diagnosis from Entity to Boundary: "+ e.getMessage());
		}

	}




	public DiagnosisEntity toEntity(DiagnosisBoundary diagnosisBoundary) {


		try {
			System.err.println("toEntity:: "+diagnosisBoundary);

			return new DiagnosisEntity(diagnosisBoundary.getDiagnosId(),
					diagnosisBoundary.getDiagnosisNumber(),
					diagnosisBoundary.getInjectionLocation().stream().map(location -> com.example.demo.data.InjectionLocation.valueOf(location.name())).collect(Collectors.toList()),
					this.patientConverter.toEntity(diagnosisBoundary.getPatient()),
					diagnosisBoundary.getAllergens().stream().map(this.allergensConverter:: toEntity).collect(Collectors.toSet()),
					null
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Diagnosis from Boundary to Entity: "+ e.getMessage());
		}	

	}

	
	
}
