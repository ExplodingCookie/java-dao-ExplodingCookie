package com.cooksys.java_dao_assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	InterestDAO iDAO = new InterestDAO("jdbc:postgresql://localhost:5432", "postgres", "bondstone");

    	System.out.println("------Testing InterestDAO.get(id)---------");
    	
    	Interest i = iDAO.get(1);
    	
    	if(i != null) {
    		System.out.println("Interest: " + i.toString());
    	} else {
    		System.out.println("Retrieval failed, function returned null.");
    	}
    	
    	System.out.println("------Testing InterestDAO.save(Interest)---------");
    	
    	Interest newInt = new Interest ("Coin-Collecting");
    	Interest retInt = iDAO.save(newInt);
    	
    	if(retInt != null) {
    		System.out.println("Successfully added new Interest: " + retInt.toString());
    	} else {
    		System.out.println("Retrieval failed, function returned null.");
    	}

    	LocationDAO lDAO = new LocationDAO("jdbc:postgresql://localhost:5432", "postgres", "bondstone");
    	
    	System.out.println("------Testing LocationDAO.get(id)---------");
    	
    	Location l = lDAO.get(1);
    	
    	System.out.println("Location: " + l.toString());
    	
    	System.out.println("------Testing LocationDAO.get(id)-(null state)---------");
    	
    	l = lDAO.get(3);
    	
    	System.out.println("Location: " + l.toString());
    	
    	System.out.println("------Testing LocationDAO.save(Location)---------");
    	
    	Location newLoc1 = new Location ("Raleigh", "North Carolina", "USA");
    	Location retLoc1 = lDAO.save(newLoc1);
    	
    	if(retLoc1 != null) {
    		System.out.println("Successfully added new Location: " + retLoc1.toString());
    	} else {
    		System.out.println("Retrieval failed, function returned null.");
    	}
    	
    	System.out.println("------Testing LocationDAO.save(Location)-(null state)---------");
    	
    	Location newLoc2 = new Location ("Mexico City", null, "Mexico");
    	Location retLoc2 = lDAO.save(newLoc2);
    	
    	if(retLoc2 != null) {
    		System.out.println("Successfully added new Location: " + retLoc2.toString());
    	} else {
    		System.out.println("Retrieval failed, function returned null.");
    	}
    	
    	PersonDAO pDAO = new PersonDAO("jdbc:postgresql://localhost:5432", "postgres", "bondstone");

    	System.out.println("------Testing PersonDAO.get(id)---------");
    	
    	Person p = pDAO.get(1);
    	
    	if(p != null) {
    		System.out.println("Person: " + p.toString());
    		System.out.println("They are located at: " + p.getLocation().toString());
    		System.out.println("and They are interested in: " + p.getInterestObjects().toString());
    	} else {
    		System.out.println("Retrieval failed, function returned null.");
    	}
    	
    	System.out.println("------Testing PersonDAO.save(Person)---------");
    	
    	List<Integer> interests = new ArrayList<Integer>();
    	
    	interests.add(1);
    	interests.add(3);
    	
    	Person newPer = new Person ("Ray", "Chase", 25, 2, interests);
    	Person retPer = pDAO.save(newPer);
    	
    	if(retPer != null) {
    		System.out.println("Successfully added new Person: " + retPer.toString());
    	} else {
    		System.out.println("Retrieval failed, function returned null.");
    	}
    	
    	System.out.println("------Testing PersonDAO.interestAndLocation(Interest, Location)---------");
    	
    	List<Person> people = pDAO.interestAndLocationGroup(iDAO.get(3), lDAO.get(2));
    	
    	System.out.println("People Who live in " + lDAO.get(2).toString() + " with the interest " + iDAO.get(3).toString());
    	System.out.println(people.toString());
    	
    }
}
