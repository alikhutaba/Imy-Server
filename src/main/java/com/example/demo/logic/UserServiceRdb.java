package com.example.demo.logic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.AddressDao;
import com.example.demo.dal.UserDao;
import com.example.demo.logic.Converters.UnvalidConverterException;
import com.example.demo.logic.Converters.UserConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.UserValidator;
import com.example.demo.rest.boundaries.UserBoundary;


import com.example.demo.data.AddressEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;


@Service
public class UserServiceRdb implements UserService{

	private UserDao userDao;
	private UserConverter userConverter;
	private AddressDao addressDao;
	private UserValidator userValidator;




	@Autowired
	public UserServiceRdb(
			UserDao userDao,
			AddressDao addressDao,
			UserConverter userConverter,
			UserValidator userValidator) {
		this.userDao = userDao;
		this.userConverter = userConverter;
		this.addressDao = addressDao;
		this.userValidator = userValidator;
	}



	@Override
	@Transactional(readOnly = true)
	public UserBoundary userLogIn(String userEmail, String password) {

		UserEntity user = this.userDao.findByEmail(userEmail);

		if(user == null || user.getActive() == false)
			throw new NotFoundException("No user with this email: "+userEmail);
		if(!user.getPassword().equals(password))
			throw new UnvalidConverterException("Incorrect Password, try again!");

		return this.userConverter.fromEntity(user);
	}



	@Override
	@Transactional
	public UserBoundary userRegistration(UserBoundary user) {

		user.setActive(true);
		user.setSignUpTimestamp(new Date());

		this.userValidator.validateUser(user);

		UserEntity newUser = this.userConverter.toEntity(user);

		System.err.println("userRegistration");
		System.err.println(newUser.getWorkerNumber());

		boolean userExist = false;

		try {
			UserEntity existUser = this.userDao.findById(newUser.getUserId()).orElseThrow(() -> new NotFoundException("No user could be found with id: " + newUser.getUserId()));

			if(existUser.getActive() == true)
				userExist = true;

		} catch (Exception e) {
			System.err.println("It is all right the user does not exist");
		}

		if(userExist)
			throw new UnvalidException("A user with the id: " + newUser.getUserId() + " already exists!");


		UserEntity exist = this.userDao.findByEmail(newUser.getEmail());

		if(exist != null && !exist.getUserId().equals(newUser.getUserId()))
			throw new UnvalidException("A user with the email: " + newUser.getEmail() + " already exists!");


		newUser.setAddress(this.addressDao.save(newUser.getAddress()));

		return this.userConverter.fromEntity(this.userDao.save(newUser));

	}





	@Override
	@Transactional
	public UserBoundary addUser(UserBoundary user, String userId) {

		user.setActive(true);
		user.setSignUpTimestamp(new Date());

		this.userValidator.validateUser(user);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.Nurse))
			throw new UnvalidException("You do not have a permistion for this action!");

		UserEntity newUser = this.userConverter.toEntity(user);

		newUser.setAddress(this.addressDao.save(newUser.getAddress()));

		return this.userConverter.fromEntity(this.userDao.save(newUser));
	}





	@Override
	@Transactional
	public void updateUser(UserBoundary user, String userId) {

		this.userValidator.validateUser(user);

		UserEntity userUpdate = this.userConverter.toEntity(user);
		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.Nurse) && !userUpdate.getUserId().equals(userId))
			throw new UnvalidException("You do not have a permistion for this action!");

		UserEntity existingUser = this.getActiveUserById(user.getUserId());

		existingUser.setFisrtName(userUpdate.getFisrtName());
		existingUser.setLastName(userUpdate.getLastName());
		existingUser.setBirthdate(userUpdate.getBirthdate());
		existingUser.setGender(userUpdate.getGender());
		existingUser.setPhone(userUpdate.getPhone());

		if(!existingUser.getAddress().equals(userUpdate.getAddress())) {

			AddressEntity temp = existingUser.getAddress();
			existingUser.setAddress(this.addressDao.save(userUpdate.getAddress()));
			this.addressDao.delete(temp);
		}

		if(!existingUser.getEmail().equals(userUpdate.getEmail())) {

			UserEntity emailUser = this.userDao.findByEmail(userUpdate.getEmail());

			if(emailUser != null)
				throw new UnvalidException("A user with the email: " + userUpdate.getEmail() + " already exists!");			
		}

		existingUser.setEmail(userUpdate.getEmail());
		existingUser.setPassword(userUpdate.getPassword());

		if(!existingUser.getRole().equals(userUpdate.getRole())){

			if(actionUser.getRole().equals(UserRole.ADMIN))
				existingUser.setRole(userUpdate.getRole());

		}

		this.userDao.save(existingUser);
	}




	@Override
	@Transactional
	public void deleteUser(UserBoundary deleteUser, String userId) {

		this.userValidator.validateUser(deleteUser);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.Nurse) && !deleteUser.getUserId().equals(userId))
			throw new UnvalidException("user do not have a permistion for this action!");


		UserEntity existingUser = this.getActiveUserById(deleteUser.getUserId());

		existingUser.setActive(false);

		this.userDao.save(existingUser);
	}




	@Override
	@Transactional(readOnly = true)
	public UserBoundary getUserById(String userId, String searchUserId) {

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");

		return this
				.userConverter
				.fromEntity(this
						.userDao
						.findById(searchUserId)
						.orElseThrow(() -> new NotFoundException("No user could be found with id: " + searchUserId)));
	}




	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String userId, int page, int size) {

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");


		return this
				.userDao.
				findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.userConverter::fromEntity)	
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
