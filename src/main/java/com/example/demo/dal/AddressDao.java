package com.example.demo.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.data.AddressEntity;


public interface AddressDao extends PagingAndSortingRepository<AddressEntity, Long>{

}
