package com.example.demo.dal;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.PatientEntity;
import com.example.demo.data.SessionEntity;

public interface SessionDao extends PagingAndSortingRepository<SessionEntity, Long>{

	
	public List<SessionEntity> findAllBySessionPatient(@Param("patient") PatientEntity patient, Pageable pageable);

	
}
