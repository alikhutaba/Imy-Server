package com.example.demo.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.data.AllergensEntity;


public interface AllergensDao extends PagingAndSortingRepository<AllergensEntity, Long>{
	
	
	
	AllergensEntity findByName(String Name);

}
