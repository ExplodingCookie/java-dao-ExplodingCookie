package com.cooksys.java_dao_assignment;

import java.util.List;

public class Person {
	private long id;
	private String first_name;
	private String last_name;
	private int age;
	private int location_id;
	private Location location;
	private List<Integer> interests;
	private List<Interest> interestObjects;
	
	public Person () {}
	
	public Person (long id, String first_name, String last_name, int age, int location_id, List<Integer> interests) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.location_id = location_id;
		this.interests = interests;
	}
	
	public Person (String first_name, String last_name, int age, int location_id, List<Integer> interests) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.location_id = location_id;
		this.interests = interests;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Integer> getInterests() {
		return interests;
	}

	public void setInterests(List<Integer> interests) {
		this.interests = interests;
	}

	public List<Interest> getInterestObjects() {
		return interestObjects;
	}

	public void setInterestObjects(List<Interest> interestObjects) {
		this.interestObjects = interestObjects;
	}
	
	@Override
	public String toString () {
		return first_name + " " + last_name + " age: " + age;
	}
}
