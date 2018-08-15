package com.cooksys.java_dao_assignment;

public class Location {
	private long id;
	private String city;
	private String state;
	private String country;
	
	public Location () {}
	
	public Location (long id, String city, String state, String country) {
		this.id = id;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	public Location (String city, String state, String country) {
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		if(state != null) {
			return city + ", " + state + ", " + country;
		} else {
			return city + ", " + country;
		}
	}
}
