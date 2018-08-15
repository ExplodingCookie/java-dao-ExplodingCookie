package com.cooksys.java_dao_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InterestDAO {
	private String url;
	private String user;
	private String password;
	
	public InterestDAO (String url, String user, String password) {
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
	
	/**
	 * Returns an interest based on the input id.
	 * @param id (long) - The Interest ID to look up.
	 * 
	 * @return The target interest. If the interest is not found, returns null.
	 * @throws SQLException if there is a database error.
	 */
	
	public Interest get(long id) {	
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
	        ResultSet rs = stmt.executeQuery("select * from interest where id=" + id);
	        
	        while(rs.next()) {
	        	return new Interest(rs.getLong("id"), rs.getString("title"));
	        }
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Adds a new interest to the table.
	 * @param i (Interest) - The brand new interest to add.
	 * @return The added interest with its newly assigned id. If an exception is encountered, returns null.
	 * @throws SQLException if there is a database error.
	 */
	
	public Interest save (Interest i) {
		try (
			Connection con = DriverManager.getConnection(this.url, this.user, this.password);
		) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set search_path to \"java-schema-assignment\"");
			
			stmt.executeUpdate("insert into interest (\"title\") values ('" + i.getTitle() + "')");
			ResultSet rs = stmt.executeQuery("select * from interest where title='" + i.getTitle() + "'");
			
			while(rs.next()) {
	        	return new Interest(rs.getInt("id"), rs.getString("title"));
	        }
		} catch (SQLException e) {
			System.out.println("ERROR: SQL Error!");
			e.printStackTrace();
		}
		
		return null;
	}
}
