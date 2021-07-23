package com.example.demo.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.data.UserEntity;

public interface UserDao extends PagingAndSortingRepository<UserEntity, String>{

	
	UserEntity findByEmail(String Email);

	UserEntity findByEmailAndPassword(String Email, String Password);

}
