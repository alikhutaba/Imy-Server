package com.example.demo.dal;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.DiagnosisEntity;
import com.example.demo.data.PatientEntity;



public interface DiagnosisDao extends PagingAndSortingRepository<DiagnosisEntity, Long>{
	
	
//	@Query(value = ""+
//			"SELECT * "+
//			"FROM DIAGNOSIS AS D "+
//			"INNER JOIN CLINICS_PATIENTS AS CP "+
//			"ON P.PATIENT_ID = CP.PATIENT_ID "+
//			"WHERE D.CLINIC_ID = :clinicid", nativeQuery  = true)
//	Stream<DiagnosisEntity> getAllPatientsByClinicId(@Param("clinicid")String clinicid, Pageable pageable);
	
	
	public List<DiagnosisEntity> findAllByPatient(@Param("patient") PatientEntity patient, Pageable pageable);


}
