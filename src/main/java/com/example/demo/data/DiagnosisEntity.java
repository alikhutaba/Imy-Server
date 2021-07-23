package com.example.demo.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "DIAGNOSIS")
public class DiagnosisEntity {



	private Long diagnosId;	// id
	private int diagnosisNumber;
	private List<InjectionLocation> injectionLocation;

	private PatientEntity patient; // foreign key	
	private Set<AllergensEntity> allergens;			// many to many
	
	private Set<AllergenProtocolsEntity> allergenProtocols;		// many to one


//	private List<InjectionLocation> location;



	
//	@Convert(converter = ListToJsonLocationConveter.class)
//	@Lob
//	@Column(name="LOCATION")
//	public List<InjectionLocation> getLocation() {
//		return location;
//	}
//
//	public void setLocation(List<InjectionLocation> location) {
//		this.location = location;
//	}
	
	

	public DiagnosisEntity() {
		this.setAllergens(null);
		this.setAllergenProtocols(null);
	}







	public DiagnosisEntity(Long diagnosId, int diagnosisNumber, List<InjectionLocation> injectionLocation,
			PatientEntity patient, Set<AllergensEntity> allergens, Set<AllergenProtocolsEntity> allergenProtocols) {
		super();
		this.diagnosId = diagnosId;
		this.diagnosisNumber = diagnosisNumber;
		this.injectionLocation = injectionLocation;
		this.patient = patient;
		this.setAllergens(allergens);
		this.setAllergenProtocols(allergenProtocols);
	}




	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getDiagnosId() {
		return diagnosId;
	}





	public int getDiagnosisNumber() {
		return diagnosisNumber;
	}



//	@ElementCollection(fetch = FetchType.EAGER)

//	@Enumerated(EnumType.STRING)
	@Convert(converter = ListToJsonLocationConveter.class)
	@Lob
	@Column(name="LOCATION")
	public List<InjectionLocation> getInjectionLocation() {
		return injectionLocation;
	}





	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "patient_id_d", nullable = false)
	public PatientEntity getPatient() {
		return patient;
	}




	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "ALLERGENS_DIAGNOSIS",
			joinColumns = @JoinColumn(name = "diagnos_id", referencedColumnName="diagnosId"),
			inverseJoinColumns = @JoinColumn(name = "allergen_id", referencedColumnName="id") 
			)
	public Set<AllergensEntity> getAllergens() {
		return allergens;
	}




	@OneToMany(mappedBy = "diagnosis", fetch = FetchType.LAZY)
	public Set<AllergenProtocolsEntity> getAllergenProtocols() {
		return allergenProtocols;
	}





	public void setDiagnosId(Long diagnosId) {
		this.diagnosId = diagnosId;
	}





	public void setDiagnosisNumber(int diagnosisNumber) {
		this.diagnosisNumber = diagnosisNumber;
	}





	public void setInjectionLocation(List<InjectionLocation> injectionLocation) {
		this.injectionLocation = injectionLocation;
	}





	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}





	public void setAllergens(Set<AllergensEntity> allergens) {
		if(allergens == null)
			this.allergens = new HashSet<AllergensEntity>();
		else
			this.allergens = allergens;
	}





	public void setAllergenProtocols(Set<AllergenProtocolsEntity> allergenProtocols) {
		if(allergenProtocols == null)
			this.allergenProtocols = new HashSet<AllergenProtocolsEntity>();
		else
			this.allergenProtocols = allergenProtocols;
	}





	@Override
	public String toString() {
		return "DiagnosisEntity [diagnosId=" + diagnosId + ", diagnosisNumber=" + diagnosisNumber
				+ ", injectionLocation=" + injectionLocation + ", patient=" + patient + ", allergens=" + allergens
				+ ", allergenProtocols=" + allergenProtocols + "]";
	}


	
	
	
	
	
	

}


