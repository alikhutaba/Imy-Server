package com.example.demo.logic;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dal.ProtocolDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.ProtocolEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;
import com.example.demo.logic.Converters.ProtocolConverter;
import com.example.demo.logic.Exceptions.NotFoundException;
import com.example.demo.logic.Exceptions.UnvalidException;
import com.example.demo.logic.Validators.ProtocolValidator;
import com.example.demo.rest.boundaries.ProtocolBoundary;


@Service
public class ProtocolServiceRdb implements ProtocolService{


	private ProtocolDao potocolDao;
	private ProtocolConverter protocolConverter;
	private ProtocolValidator protocolValidator; 
	private UserDao userDao;


	@Autowired
	public ProtocolServiceRdb(ProtocolDao potocolDao, ProtocolConverter protocolConverter, ProtocolValidator protocolValidator, UserDao userDao) {
		this.potocolDao = potocolDao;
		this.protocolConverter = protocolConverter;
		this.protocolValidator = protocolValidator;
		this.userDao = userDao;
	}




	@Override
	@Transactional
	public ProtocolBoundary addProtocols(ProtocolBoundary newProtocol, String userId) {

		this.protocolValidator.validateProtocol(newProtocol);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");

		return this
				.protocolConverter
				.fromEntity(this
						.potocolDao
						.save(this
								.protocolConverter
								.toEntity(newProtocol)));
	}

	
	
	
	@Override
	@Transactional
	public void updateProtocols(String userId, ProtocolBoundary Protocol) {

		this.protocolValidator.validateProtocol(Protocol);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");

		ProtocolEntity newProtocol = this.protocolConverter.toEntity(Protocol);
		
		ProtocolEntity existingProtocol = this
				.potocolDao
				.findById(newProtocol.getProtocolId())
				.orElseThrow(()-> 
		new NotFoundException("No Protocol could be found with id: "+ newProtocol.getProtocolId()));
		
		existingProtocol.setName(newProtocol.getName());
		existingProtocol.setDosesNumber(newProtocol.getDosesNumber());
		existingProtocol.setConcentration(newProtocol.getConcentration());
		existingProtocol.setDosage(newProtocol.getDosage());
		
		
		this.potocolDao.save(existingProtocol);
	
	}
	
	
	

//	@Override
//	@Transactional
//	public void deleteProtocols(String userId, ProtocolBoundary deleteProtocols) {
//		
//		// TODO check if can delete protocol because it connected to all injection system, and all
//		// the injection calculations based on it.
//	}
	
	
	

	@Override
	@Transactional(readOnly = true)
	public ProtocolBoundary getProtocolById(String userId, String protocolId) {
	
		this.getActiveUserById(userId);
		
		long id = Long.parseLong(protocolId);
		
		return this
				.protocolConverter
				.fromEntity(this.potocolDao.findById(id).orElseThrow(()-> new NotFoundException("No Protocol could be found with id: "+protocolId)));
		
		
	}



	@Override
	@Transactional(readOnly = true)
	public List<ProtocolBoundary> getAllProtocols(String userId, int page, int size) {
		
	this.getActiveUserById(userId);
		
		
		return this
				.potocolDao
				.findAll(PageRequest.of(page, size))
				.stream()
				.map(this.protocolConverter :: fromEntity)
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
