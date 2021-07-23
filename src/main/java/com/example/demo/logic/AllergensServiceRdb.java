package com.example.demo.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.AllergensDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.AllergensEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;
import com.example.demo.logic.Converters.AllergensConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.rest.boundaries.AllergensBoundary;

@Service
public class AllergensServiceRdb implements AllergensService{



	private AllergensDao allergensDao;
	private AllergensConverter allergensConverter;
	private UserDao userDao;


	
	

	@Autowired
	public AllergensServiceRdb(AllergensDao allergensDao, AllergensConverter allergensConverter, UserDao userDao) {
		this.allergensDao = allergensDao;
		this.allergensConverter = allergensConverter;
		this.userDao = userDao;
	}


	
	
	

	@Override
	@Transactional
	public AllergensBoundary addAllergen(AllergensBoundary newAllergen, String userId) {

		UserEntity user = this.getActiveUserById(userId);

		if(user.getRole().equals(UserRole.Nurse))
			throw new UnvalidException("user do not have a permistion for this action!");		

		if(newAllergen.getName().equals("") || newAllergen.getName() == null)
			throw new UnvalidException("Allergen name is Empty..!");


		AllergensEntity allergen =  this.allergensDao.findByName(newAllergen.getName());
		if(allergen != null)
			throw new UnvalidException("allergen with name "+newAllergen.getName()+" is allready exist..!");


		return this.allergensConverter.fromEntity(this.allergensDao.save(this.allergensConverter.toEntity(newAllergen)));
	}

	
	
	
	
//	@Override
//	@Transactional
//	public void updateAllergen(String userId, AllergensBoundary updateAllergen) {
//		// TODO LOGIC
//
//	}
//
//	@Override
//	@Transactional
//	public void deleteAllergen(String userId, AllergensBoundary deleteAllergen) {
//		// TODO LOGIC
//
//	}

	
	
	
	
	@Override
	@Transactional(readOnly = true)
	public AllergensBoundary getAllergenById(String userId, String allergenId) {

		this.getActiveUserById(userId);

		return this
				.allergensConverter
				.fromEntity(this
						.allergensDao
						.findById(Long.parseLong(allergenId))
						.orElseThrow(() -> new NotFoundException("No patient could be found with id: " + allergenId))); 

	}

	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AllergensBoundary> getAllAllergen(String userId, int page, int size) {
		
		this.getActiveUserById(userId);

		return this
				.allergensDao
				.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.allergensConverter :: fromEntity)
				.collect(Collectors.toList());
		
	}




	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new NotFoundException("No user could be found with id: " + userId);

		return user;
	}




}
