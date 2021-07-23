package com.example.demo.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROTOCOLS")
public class ProtocolEntity {




	private Long protocolId;
	private String name;
	private int dosesNumber;

	private List<String> concentration;
	private List<String> dosage;
	private List<String> colors;

	private Set<AllergenProtocolsEntity> allergenProtocols; 



	public ProtocolEntity() {
		this.setAllergenProtocols(null);
	}




	public ProtocolEntity(Long protocolId, String name, int dosesNumber, List<String> concentration,
			List<String> dosage, List<String> colors, Set<AllergenProtocolsEntity> allergenProtocols) {
		super();
		this.protocolId = protocolId;
		this.name = name;
		this.dosesNumber = dosesNumber;
		this.concentration = concentration;
		this.dosage = dosage;
		this.colors = colors;
		this.allergenProtocols = allergenProtocols;

	}





	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getProtocolId() {
		return protocolId;
	}




	public String getName() {
		return name;
	}




	public int getDosesNumber() {
		return dosesNumber;
	}



	@Convert(converter = ListToJsonStringConveter.class)
	@Lob
	@Column(name="CONCENTRATION")
	public List<String> getConcentration() {
		return concentration;
	}



	@Convert(converter = ListToJsonStringConveter.class)
	@Lob
	@Column(name="DOSAGE")
	public List<String> getDosage() {
		return dosage;
	}
	

	@Convert(converter = ListToJsonStringConveter.class)
	@Lob
	@Column(name="COLORS")
	public List<String> getColors() {
		return colors;
	}



	@OneToMany(mappedBy = "protocol", fetch = FetchType.LAZY)
	public Set<AllergenProtocolsEntity> getAllergenProtocols() {
		return allergenProtocols;
	}




	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setDosesNumber(int dosesNumber) {
		this.dosesNumber = dosesNumber;
	}




	public void setConcentration(List<String> concentration) {
		this.concentration = concentration;
	}




	public void setDosage(List<String> dosage) {
		this.dosage = dosage;
	}

	
	
	public void setColors(List<String> colors) {
		this.colors = colors;
	}


	public void setAllergenProtocols(Set<AllergenProtocolsEntity> allergenProtocols) {
		if(allergenProtocols == null)
			this.allergenProtocols = new HashSet<AllergenProtocolsEntity>();
		else
			this.allergenProtocols = allergenProtocols;
	}







}

