package com.example.demo.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@Table(name = "ALLERGEN_PROTOCOL")
public class AllergenProtocolsEntity {




	private Long allergenProtocolsId;	// ID PK
	private ProtocolEntity protocol;	// foreign key
	private DiagnosisEntity diagnosis; // foreign key
	private Boolean completed;

	private Set<InjectionEntity> injections;








	public AllergenProtocolsEntity() {
		this.setInjections(null);
	}



	public AllergenProtocolsEntity(Long allergenProtocolsId, ProtocolEntity protocol, DiagnosisEntity diagnosis,
			Set<InjectionEntity> injections, Boolean completed) {
		super();
		this.allergenProtocolsId = allergenProtocolsId;
		this.protocol = protocol;
		this.diagnosis = diagnosis;
		this.completed = completed;
		this.setInjections(injections);

	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getAllergenProtocolsId() {
		return allergenProtocolsId;
	}



	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "protocol_id", nullable = false)
	public ProtocolEntity getProtocol() {
		return protocol;
	}



	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "diagnos_id", nullable = false)
	public DiagnosisEntity getDiagnosis() {
		return diagnosis;
	}



	@OneToMany(mappedBy = "allergenProtocol", fetch = FetchType.LAZY)
	public Set<InjectionEntity> getInjections() {
		return injections;
	}



	public void setAllergenProtocolsId(Long allergenProtocolsId) {
		this.allergenProtocolsId = allergenProtocolsId;
	}



	public void setProtocol(ProtocolEntity protocol) {
		this.protocol = protocol;
	}



	public void setDiagnosis(DiagnosisEntity diagnosis) {
		this.diagnosis = diagnosis;
	}

	
	


	public Boolean getCompleted() {
		return completed;
	}



	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}



	public void setInjections(Set<InjectionEntity> injections) {
		if(injections == null)
			this.injections = new HashSet<InjectionEntity>();
		else
			this.injections = injections;
	}



}
