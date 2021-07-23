package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.UserEntity;
import com.example.demo.rest.boundaries.Gender;
import com.example.demo.rest.boundaries.UserBoundary;
import com.example.demo.rest.boundaries.UserRole;


@Component
public class UserConverter {

	
	private AddressConverter addressConverter;
	private BirthDateConverter birthDateConverter;

	@Autowired
	public UserConverter(AddressConverter addressConverter, BirthDateConverter birthDateConverter) {
		super();
		this.addressConverter = addressConverter;
		this.birthDateConverter = birthDateConverter;
	}




	public UserBoundary fromEntity(UserEntity userEntity) {

		try {

			return new UserBoundary(
					userEntity.getUserId(),
					userEntity.getWorkerNumber(),
					userEntity.getFisrtName(),
					userEntity.getLastName(),
					this.birthDateConverter.fromEntity(userEntity.getBirthdate()),
					Gender.valueOf(userEntity.getGender().name()),
					userEntity.getPhone(),
					this.addressConverter.fromEntity(userEntity.getAddress()),
					userEntity.getActive(),
					userEntity.getSignUpTimestamp(),
					userEntity.getEmail(),
					userEntity.getPassword(),
					UserRole.valueOf(userEntity.getRole().name())
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert User from Entity to Boundary: "+ e.getMessage());
		}

	}




	public UserEntity toEntity(UserBoundary userBoundary) {



		try {

			return new UserEntity(userBoundary.getUserId(),
					userBoundary.getWorkerNumber(),
					userBoundary.getFisrtName(),
					userBoundary.getLastName(),
					this.birthDateConverter.toEntity(userBoundary.getBirthdate()),
					com.example.demo.data.Gender.valueOf(userBoundary.getGender().name()),
					userBoundary.getPhone(),
					this.addressConverter.toEntity(userBoundary.getAddress()),
					userBoundary.getActive(),
					userBoundary.getSignUpTimestamp(),
					userBoundary.getEmail(),
					userBoundary.getPassword(),
					com.example.demo.data.UserRole.valueOf(userBoundary.getRole().name()),
					null,
					null,
					null,
					null
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert User from Boundary to Entity: "+ e.getMessage());
		}	



	}
	
	
	
}
