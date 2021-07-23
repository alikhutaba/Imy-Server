package com.example.demo.logic.Converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.AddressEntity;
import com.example.demo.rest.boundaries.AddressBoundary;


@Component
public class AddressConverter {

	


	@Autowired
	public AddressConverter() {
		super();
	}




	public AddressBoundary fromEntity(AddressEntity addressEntity) {

		try {

			return new AddressBoundary(addressEntity.getId(),
					addressEntity.getState(),
					addressEntity.getCity(),
					addressEntity.getStreetAddress(),
					addressEntity.getHouseNumber(),
					addressEntity.getZipCode()
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Address from Entity to Boundary: "+ e.getMessage());
		}

	}




	public AddressEntity toEntity(AddressBoundary addressBoundary) {



		try {

			return new AddressEntity(
					addressBoundary.getId(),
					addressBoundary.getState(),
					addressBoundary.getCity(),
					addressBoundary.getStreetAddress(),
					addressBoundary.getHouseNumber(),
					addressBoundary.getZipCode()
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Address from Boundary to Entity: "+ e.getMessage());
		}	

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
