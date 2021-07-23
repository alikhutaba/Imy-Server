package com.example.demo.logic.Converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.ProtocolEntity;
import com.example.demo.rest.boundaries.ProtocolBoundary;

@Component
public class ProtocolConverter {

	
	
	@Autowired
	public ProtocolConverter() {
		super();
	}



	public ProtocolBoundary fromEntity(ProtocolEntity protocolEntity) {

		try {

			return new ProtocolBoundary(
					protocolEntity.getProtocolId(),
					protocolEntity.getName(),
					protocolEntity.getDosesNumber(),
					protocolEntity.getConcentration(),
					protocolEntity.getDosage(),
					protocolEntity.getColors()
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Protocols from Entity to Boundary: "+ e.getMessage());
		}

	}




	public ProtocolEntity toEntity(ProtocolBoundary protocolBoundary) {


		try {

			return new ProtocolEntity(protocolBoundary.getProtocolId(),
					protocolBoundary.getName(),
					protocolBoundary.getDosesNumber(),
					protocolBoundary.getConcentration(),
					protocolBoundary.getDosage(),
					protocolBoundary.getColors(),
					null
					);

		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Protocols from Boundary to Entity: "+ e.getMessage());
		}	



	}
}
