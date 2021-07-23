package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.rest.boundaries.AllergenProtocolsBoundary;


@Component
public class AllergenProtocolsConverter {


	private ProtocolConverter ProtocolConverter;
	private DiagnosisConverter diagnosisConverter;

	@Autowired
	public AllergenProtocolsConverter(ProtocolConverter ProtocolConverter, DiagnosisConverter diagnosisConverter) {
		super();
		this.ProtocolConverter = ProtocolConverter;
		this.diagnosisConverter = diagnosisConverter;
	}




	public AllergenProtocolsBoundary fromEntity(AllergenProtocolsEntity allergenProtocolsEntity) {

		try {

			return new AllergenProtocolsBoundary(
					allergenProtocolsEntity.getAllergenProtocolsId(),
					this.ProtocolConverter.fromEntity(allergenProtocolsEntity.getProtocol()),
					this.diagnosisConverter.fromEntity(allergenProtocolsEntity.getDiagnosis()),
					allergenProtocolsEntity.getCompleted());


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Allergen Protocols from Entity to Boundary: "+ e.getMessage());
		}

	}




	public AllergenProtocolsEntity toEntity(AllergenProtocolsBoundary allProtocolsBoundary) {



		try {

			return new AllergenProtocolsEntity(
					allProtocolsBoundary.getAllergenProtocolsId(),
					this.ProtocolConverter.toEntity(allProtocolsBoundary.getProtocol()),
					this.diagnosisConverter.toEntity(allProtocolsBoundary.getDiagnosis()),
					null,
					allProtocolsBoundary.getCompleted());


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Allergen Protocols from Boundary to Entity: "+ e.getMessage());
		}	



	}
}