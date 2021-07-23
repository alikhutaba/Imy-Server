package com.example.demo.rest.boundaries;

public class AllergensBoundary {
	
	
	private Long id;
	private String name;
	
	
	
	
	
	
	
	
	public AllergensBoundary() {}


	
	

	public AllergensBoundary(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Long getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "AllergensBoundary [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
	
	

}
