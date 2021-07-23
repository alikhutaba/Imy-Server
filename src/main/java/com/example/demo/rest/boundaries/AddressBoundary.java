package com.example.demo.rest.boundaries;

public class AddressBoundary {

	// TODO change no need for state add house number or building number
	
	private Long id;
	private String state;
	private String city;
	private String streetAddress;
	private int houseNumber;
	private int zipCode;
	
	
	
	public AddressBoundary() {}



	public AddressBoundary(Long id, String state, String city, String streetAddress, int houseNumber, int zipCode) {
		super();
		this.id = id;
		this.state = state;
		this.city = city;
		this.streetAddress = streetAddress;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
	}



	public Long getId() {
		return id;
	}



	public String getState() {
		return state;
	}



	public String getCity() {
		return city;
	}



	public String getStreetAddress() {
		return streetAddress;
	}



	public int getHouseNumber() {
		return houseNumber;
	}



	public int getZipCode() {
		return zipCode;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setState(String state) {
		this.state = state;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}



	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}



	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}



	@Override
	public String toString() {
		return "AddressBoundary [id=" + id + ", state=" + state + ", city=" + city + ", streetAddress=" + streetAddress
				+ ", houseNumber=" + houseNumber + ", zipCode=" + zipCode + "]";
	}



	
	
	
	
	
}
