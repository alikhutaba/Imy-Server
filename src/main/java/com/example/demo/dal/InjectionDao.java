package com.example.demo.dal;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.data.InjectionEntity;
import com.example.demo.data.SessionEntity;


public interface InjectionDao extends PagingAndSortingRepository<InjectionEntity, Long>{


//	public List<InjectionEntity>  findByUserNameOrderByCreatedDateDesc(String username);


//	@Query(value = ""+
//			"SELECT * "+
//			"FROM DIAGNOSIS AS D "+
//			"INNER JOIN CLINICS_PATIENTS AS CP "+
//			"ON P.PATIENT_ID = CP.PATIENT_ID "+
//			"WHERE D.CLINIC_ID = :clinicid")
//	Stream<InjectionEntity> getAllPatientsByClinicId(@Param("clinicid")String clinicid, Pageable pageable);

	
	//	public SpecializationEntity findByNameLike(@Param("name") String name);


	public List<InjectionEntity> findByAllergenProtocol(@Param("allergenProtocolsEntity") AllergenProtocolsEntity allergenProtocolsEntity, Pageable pageable);
	
	public List<InjectionEntity> findAllBySession(@Param("sessionEntity") SessionEntity sessionEntity, Pageable pageable);


}
