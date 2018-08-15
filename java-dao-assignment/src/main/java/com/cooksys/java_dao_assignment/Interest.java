package com.cooksys.java_dao_assignment;

public class Interest {
	private long id;
	private String title;
	
	public Interest () {}
	
	public Interest (long id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public Interest (String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString () {
		return this.title;
	}
}
