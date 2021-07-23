package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.InjectionEntity;
import com.example.demo.rest.boundaries.InjectionBoundary;
import com.example.demo.rest.boundaries.InjectionLocation;



@Component
public class InjectionConverter {

	
	private AllergenProtocolsConverter allergenProtocolsConverter;
	private SessionConverter sessionConverter;
	private UserConverter userConverter;
	
	
	
	@Autowired
	public InjectionConverter(AllergenProtocolsConverter allergenProtocolsConverter, SessionConverter sessionConverter, UserConverter userConverter) {
		super();
	
		this.allergenProtocolsConverter = allergenProtocolsConverter;
		this.sessionConverter = sessionConverter;
		this.userConverter = userConverter;
	}
	
	
	
	public InjectionBoundary fromEntity(InjectionEntity injectionEntity) {

		try {

			return new InjectionBoundary(
					injectionEntity.getInjectionId(),
					injectionEntity.getCreatedTimestamp(),
					injectionEntity.getDoseNumber(),
					InjectionLocation.valueOf(injectionEntity.getInjectionLocation().name()),
					injectionEntity.getNotes(),
					this.allergenProtocolsConverter.fromEntity(injectionEntity.getAllergenProtocol()),
					this.sessionConverter.fromEntity(injectionEntity.getSession()),
					this.userConverter.fromEntity(injectionEntity.getCreatedBy()),
					injectionEntity.getConcentration(),
					injectionEntity.getDosage(),
					false);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Injection from Entity to Boundary: "+ e.getMessage());
		}

	}




	public InjectionEntity toEntity(InjectionBoundary injectionBoundary) {



		try {

			return new InjectionEntity(
					injectionBoundary.getInjectionId(),
					injectionBoundary.getSignUpTimestamp(),
					injectionBoundary.getDoseNumber(),
					com.example.demo.data.InjectionLocation.valueOf(injectionBoundary.getInjectionLocation().name()),
					injectionBoundary.getNotes(),
					this.allergenProtocolsConverter.toEntity(injectionBoundary.getAllergenProtocols()),
					this.sessionConverter.toEntity(injectionBoundary.getSession()),
					this.userConverter.toEntity(injectionBoundary.getCreatedBy()),
					injectionBoundary.getConcentration(),
					injectionBoundary.getDosage()
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Injection from Boundary to Entity: "+ e.getMessage());
		}	

	}

	
	
	
	
	
	
}
