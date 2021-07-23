package com.example.demo.dal;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.data.DiagnosisEntity;

public interface AllergenProtocolsDao extends PagingAndSortingRepository<AllergenProtocolsEntity, Long>{

	
	
	public List<AllergenProtocolsEntity> findAllByDiagnosis(@Param("diagnosis") DiagnosisEntity diagnosis, Pageable pageable);

}
