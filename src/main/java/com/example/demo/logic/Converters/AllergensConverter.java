package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.AllergensEntity;
import com.example.demo.rest.boundaries.AllergensBoundary;

@Component
public class AllergensConverter {

	



	@Autowired
	public AllergensConverter() {
		super();
	}




	public AllergensBoundary fromEntity(AllergensEntity allergensEntity) {

		try {

			return new AllergensBoundary(
					allergensEntity.getId(),
					allergensEntity.getName()
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Allergens from Entity to Boundary: "+ e.getMessage());
		}

	}




	public AllergensEntity toEntity(AllergensBoundary allergensBoundary) {



		try {

			return new AllergensEntity(
					allergensBoundary.getId(),
					allergensBoundary.getName(),
					null
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Allergens from Boundary to Entity: "+ e.getMessage());
		}	

	}

	
	
	
	
	
	
}
