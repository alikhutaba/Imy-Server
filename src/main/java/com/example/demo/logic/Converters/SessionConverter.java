package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.SessionEntity;
import com.example.demo.rest.boundaries.SessionBoundary;

@Component
public class SessionConverter {

	
	private PatientConverter patientConverter;
	private UserConverter userConverter;
	private QuestionConverter questionConverter;

	

	@Autowired
	public SessionConverter(PatientConverter patientConverter, UserConverter userConverter, QuestionConverter questionConverter) {
		super();
		this.patientConverter = patientConverter;
		this.userConverter = userConverter;
		this.questionConverter = questionConverter;
	}




	public SessionBoundary fromEntity(SessionEntity sessionEntity) {

		try {

			return new SessionBoundary(
					sessionEntity.getSessionId(),
					this.patientConverter.fromEntity(sessionEntity.getSessionPatient()),
					this.userConverter.fromEntity(sessionEntity.getCreatedBy()),
					sessionEntity.getSignUpTimestamp(),
					this.questionConverter.fromEntity(sessionEntity.getHowYouFeelToday()),
					this.questionConverter.fromEntity(sessionEntity.getHowThePreviousInjectionWent()),
					this.questionConverter.fromEntity(sessionEntity.getAntihistamineBeforeVaccination())
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Session from Entity to Boundary: "+ e.getMessage());
		}

	}




	public SessionEntity toEntity(SessionBoundary sessionBoundary) {



		try {

			return new SessionEntity(
					sessionBoundary.getSessionId(),
					this.patientConverter.toEntity(sessionBoundary.getPatient()),
					this.userConverter.toEntity(sessionBoundary.getCreatedBy()),
					sessionBoundary.getSignUpTimestamp(),
					this.questionConverter.toEntity(sessionBoundary.getHowYouFeelToday()),
					this.questionConverter.toEntity(sessionBoundary.getHowThePreviousInjectionWent()),
					this.questionConverter.toEntity(sessionBoundary.getAntihistamineBeforeVaccination()),
					null
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Session from Boundary to Entity: "+ e.getMessage());
		}	



	}
	
	
	
}
