package com.example.demo.data;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="ALLERGENS")
public class AllergensEntity {


	private Long id;
	private String name;

	private Set<DiagnosisEntity> diagnosis;	// many to many







	public AllergensEntity() {}



	public AllergensEntity(Long id, String name, Set<DiagnosisEntity> diagnosis) {
		super();
		this.id = id;
		this.name = name;
		this.diagnosis = diagnosis;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	@ManyToMany(fetch = FetchType.EAGER , mappedBy = "allergens")
	public Set<DiagnosisEntity> getDiagnosis() {
		return diagnosis;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setDiagnosis(Set<DiagnosisEntity> diagnosis) {
		this.diagnosis = diagnosis;
	}





}
