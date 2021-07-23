package com.example.demo.logic.Converters;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.rest.boundaries.BirthDate;

@Component
public class BirthDateConverter {


	
	@Autowired
	public BirthDateConverter() {
		super();

	}
	
	
	
	public BirthDate fromEntity(LocalDate localDate) {

		try {
				
			return new BirthDate(
					localDate.getDayOfMonth(),
					localDate.getMonthValue(),
					localDate.getYear()
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert BirthDate from Entity to Boundary: "+ e.getMessage());
		}

	}




	public LocalDate toEntity(BirthDate birthDate) {


		try {

			return LocalDate.of(
					birthDate.getYears(),
					birthDate.getMonths(),
					birthDate.getDays()
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert BirthDate from Boundary to Entity: "+ e.getMessage());
		}	

	}

	
}
