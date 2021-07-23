package com.example.demo.rest.boundaries;



public class AllergenProtocolsBoundary {
	
	
	private Long allergenProtocolsId;
	private ProtocolBoundary protocol;
	private DiagnosisBoundary diagnosis;
	private Boolean completed;
	
	
	
	
	
	public AllergenProtocolsBoundary() {}
	
	
	
	public AllergenProtocolsBoundary(Long allergenProtocolsId, ProtocolBoundary protocol,
			DiagnosisBoundary diagnosis, Boolean completed) {
		super();
		this.allergenProtocolsId = allergenProtocolsId;
		this.protocol = protocol;
		this.diagnosis = diagnosis;
		this.completed = completed;
	}




	public Long getAllergenProtocolsId() {
		return allergenProtocolsId;
	}




	public void setAllergenProtocolsId(Long allergenProtocolsId) {
		this.allergenProtocolsId = allergenProtocolsId;
	}




	public ProtocolBoundary getProtocol() {
		return protocol;
	}




	public void setProtocol(ProtocolBoundary protocol) {
		this.protocol = protocol;
	}




	public DiagnosisBoundary getDiagnosis() {
		return diagnosis;
	}




	public void setDiagnosis(DiagnosisBoundary diagnosis) {
		this.diagnosis = diagnosis;
	}




	public Boolean getCompleted() {
		return completed;
	}



	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}



	@Override
	public String toString() {
		return "AllergenProtocolsBoundary [AllergenProtocolsId=" + allergenProtocolsId + ", protocol=" + protocol
				+ ", diagnosis=" + diagnosis + "]";
	}

	
	
	
	
	
	
}
