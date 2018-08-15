package com.cooksys.java_dao_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	private String url;
	private String user;
	private String password;
	
	public PersonDAO (String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: Class not found");
			e.printStackTrace();
		}
	}
	
	private Person proccessPerson(ResultSet rs) {
		Person returnedPerson = new Person();

		try {
			returnedPerson.setId(rs.getInt("id"));
			returnedPerson.setFirst_name(rs.getString("first_name"));
			returnedPerson.setLast_name(rs.getString("last_name")); 
			returnedPerson.setAge(rs.getInt("age"));
			returnedPerson.setLocation_id(rs.getInt("location_id"));
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
		
		returnedPerson.setLocation(new LocationDAO(this.url, this.user, this.password).get(returnedPerson.getLocation_id()));
		
		return returnedPerson;
	}
	
	private List<Interest> setPersonInterests (List<Integer> interestList) {
		List<Interest> interestListO = new ArrayList<Interest>();
		InterestDAO iDAO = new InterestDAO(this.url, this.user, this.password);
		
		for(Integer i : interestList) {
			Interest it = iDAO.get(i);
			
			interestListO.add(it);
		}
		
		return interestListO;
	}
	
	public Person get(long id) {
		Person returnedPerson = null;
		
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
			ResultSet rs = stmt.executeQuery("select * from person where id=" + id);
			        
			while(rs.next()) {
				returnedPerson = proccessPerson(rs);
			}
			
			ResultSet intRS = stmt.executeQuery("select * from person_interest where person_id=" + id);
			List<Integer> interestList = new ArrayList<Integer>();
			
			while(intRS.next()) {
				interestList.add(intRS.getInt("interest_id"));
			}
			
			returnedPerson.setInterestObjects(setPersonInterests(interestList));
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
				
		return returnedPerson;
	}
	
	public Person save (Person p) {
		Person returnedPerson = null;
		
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
			stmt.executeUpdate("insert into person (\"first_name\", \"last_name\", \"age\", \"location_id\") values ('" + p.getFirst_name() + "', '" + p.getLast_name() + "', '" + p.getAge() + "', '" + p.getLocation_id() + "')");
			ResultSet rs = stmt.executeQuery("select * from person where first_name='" + p.getFirst_name() + "'");
			
			while(rs.next()) {
				returnedPerson = proccessPerson(rs);
	        }
			
			returnedPerson.setInterestObjects(setPersonInterests(p.getInterests()));
			
			for(int i : p.getInterests()) {
				stmt.executeUpdate("insert into person_interest (\"person_id\", \"interest_id\") values ('" + returnedPerson.getId() + "', '" + i + "')");
			}
			
			stmt.executeUpdate("insert into person_location (\"person_id\", \"location_id\") values ('" + returnedPerson.getId() + "', '" + returnedPerson.getLocation_id() + "')");
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
		
		return returnedPerson;
	}
	
	public List<Person> interestAndLocationGroup (Interest targetInterest, Location targetLocation) {
		List<Person> people = new ArrayList<Person>();
		
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
			ResultSet rs = stmt.executeQuery("select person_interest.person_id from person_interest inner join person_location on location_id=" + targetLocation.getId() + " and person_location.person_id = person_interest.person_id where interest_id=" + targetInterest.getId());
			
			while(rs.next()) {
				people.add(get(rs.getInt("person_id")));
			}
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
		
		return people;
	}
}
