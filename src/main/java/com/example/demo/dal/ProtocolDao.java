package com.example.demo.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.data.ProtocolEntity;

public interface ProtocolDao extends PagingAndSortingRepository<ProtocolEntity, Long>{

}
