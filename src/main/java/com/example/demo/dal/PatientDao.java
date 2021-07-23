package com.example.demo.dal;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.PatientEntity;

public interface PatientDao extends PagingAndSortingRepository<PatientEntity, String>{

	
	@Query(value = ""+
			"SELECT * "+
			"FROM PATIENTS AS P "+
			"WHERE P.ACTIVE = :active", nativeQuery  = true)
	Stream<PatientEntity> getAllActivePatients(@Param("active")boolean active, Pageable pageable);

	@Query(value = ""+
			"SELECT * "+
			"FROM PATIENTS AS P "+
			"WHERE P.ACTIVE = :active "+
			"AND P.VALIDATED_USER_ID IS NULL", nativeQuery  = true)
	Stream<PatientEntity> getAllActiveAndUnvalidPatients(@Param("active")boolean active, Pageable pageable);

	
//	@Query(value = ""+
//			"SELECT * "+
//			"FROM PATIENTS AS P "+
//			"WHERE P.VALIDATED_USER_ID IS NULL", nativeQuery  = true)
//	Stream<PatientEntity> getAllActiveAndUnvalidPatients(@Param("user")Object user, Pageable pageable);
	
}
