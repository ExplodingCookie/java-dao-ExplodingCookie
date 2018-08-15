package com.cooksys.java_dao_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDAO {
	private String url;
	private String user;
	private String password;
	
	public LocationDAO (String url, String user, String password) {
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
	
	public Location get(long id) {
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
		    ResultSet rs = stmt.executeQuery("select * from location where id=" + id);
		        
		    while(rs.next()) {
		        return new Location(rs.getLong("id"), rs.getString("city"), rs.getString("state"), rs.getString("country"));
		    }
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
			
		return null;
	}
	
	public Location save(Location l) {
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
			stmt.executeUpdate("insert into location (\"city\", \"state\", \"country\") values ('" + l.getCity() + "', '" + l.getState() + "', '" + l.getCountry() + "')");
			ResultSet rs = stmt.executeQuery("select * from location where city='" + l.getCity() + "'");
			
			while(rs.next()) {
	        	return new Location(rs.getInt("id"), rs.getString("city"), rs.getString("state"), rs.getString("country"));
	        }
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
		
		return null;
	}
}
