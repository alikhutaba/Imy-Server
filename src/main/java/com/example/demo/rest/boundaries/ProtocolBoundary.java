package com.example.demo.rest.boundaries;

import java.util.List;


public class ProtocolBoundary {
	
	
	
	private Long protocolId;
	private String name;
	private int dosesNumber;

	private List<String> concentration;
	private List<String> dosage;
	private List<String> colors;

	public ProtocolBoundary() {}
	
	

	public ProtocolBoundary(Long protocolId, String name, int dosesNumber, List<String> concentration,
			List<String> dosage, List<String> colors) {
		super();
		this.protocolId = protocolId;
		this.name = name;
		this.dosesNumber = dosesNumber;
		this.concentration = concentration;
		this.dosage = dosage;
		this.colors = colors;

	}

	
	
	
	public Long getProtocolId() {
		return protocolId;
	}

	public String getName() {
		return name;
	}

	public int getDosesNumber() {
		return dosesNumber;
	}

	public List<String> getConcentration() {
		return concentration;
	}

	public List<String> getDosage() {
		return dosage;
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



	public List<String> getColors() {
		return colors;
	}



	public void setColors(List<String> colors) {
		this.colors = colors;
	}



	@Override
	public String toString() {
		return "ProtocolBoundary [protocolId=" + protocolId + ", name=" + name + ", dosesNumber=" + dosesNumber
				+ ", concentration=" + concentration + ", dosage=" + dosage + ", colors=" + colors + "]";
	}

	
	
	
}


