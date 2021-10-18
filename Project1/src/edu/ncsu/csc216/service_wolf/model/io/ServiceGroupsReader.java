package edu.ncsu.csc216.service_wolf.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * This class is responsible for reading in ServiceGroup Records
 along with their Incidents into the system. It has a method for 
 reading in ServiceGroups. Then, it has a separate method for reading 
 in Incidents which makes things a lot easier for the readServiceGroups 
 method. In the end, the system will end up having several ServiceGroups filled 
 with either no incident records or some incident records in a ServiceGroup
 * @author abhin
 *
 */
public class ServiceGroupsReader {
	
	/**
	 * reads in multiple service group records and returns a list
	 * @param fileName is the file name of the records that needs to read in
	 * @return an arraylist of ServiceGroups
	 * @throws IllegalArgumentException if file can't be read in or loaded 
	 */
	public static ArrayList<ServiceGroup> readServiceGroupsFile(String fileName) {
		Scanner fileReader;
	    ArrayList<ServiceGroup> groups = new ArrayList<ServiceGroup>(); //Create an empty array of Course objects
		try {
			fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	    ServiceGroup currentGroup = null;
	    Incident currentIncident = null;
	    String line; 
	    boolean fileValid = true;
	    ArrayList<String> messages = new ArrayList<String>();
		while (fileReader.hasNextLine() && fileValid) { //While we have more lines in the file 	        	
//	    	System.out.println("NextLine value: " + fileReader.nextLine());
			
			line = String.valueOf(fileReader.nextLine());
	    	//System.out.println("Actual line: " + line);
			if(line.charAt(0) == '#') {
	    		if(currentGroup != null) {
//	    			if(messages.size() == 0) {
//	    				throw new IllegalArgumentException("Incident cannot be created.");
//	    			}
	    			currentIncident = new Incident(currentIncident.getId(), currentIncident.getState(), currentIncident.getTitle(), currentIncident.getCaller(), currentIncident.getReopenCount(), currentIncident.getOwner(), currentIncident.getStatusDetails(), messages);
	    			currentGroup.addIncident(currentIncident);
	    			
	    			groups.add(currentGroup);
	    			messages.clear();
	    			currentIncident = null;
	    		}
	    		ServiceGroup s = processServiceGroup(line);
	    		currentGroup = s;	
	    	}
	    	else if(line.charAt(0) == '*') {
	    		if(currentIncident != null) {
//	    			if(messages.size() == 0) {
//	    				throw new IllegalArgumentException("Incident cannot be created.");
//	    			}
	    			currentIncident = new Incident(currentIncident.getId(), currentIncident.getState(), currentIncident.getTitle(), currentIncident.getCaller(), currentIncident.getReopenCount(), currentIncident.getOwner(), currentIncident.getStatusDetails(), messages);
	    			if(currentGroup != null) {
	    				currentGroup.addIncident(currentIncident);
		        		messages.clear();
	    			}
	    			else {
	    				fileValid = false;
	    			}
//	    			else {
//	    				throw new IllegalArgumentException();
//	    			}
//	    			currentGroup.addIncident(currentIncident);
//	        		messages.clear();
	        		
	    		}
	    		try {
	    			Incident i = processIncident(line);
		    		currentIncident = i;
	    		} catch(IllegalArgumentException e) {
	    			System.out.println("Incident is not valid because not full!!");
	    		}	
	    	}
	    	else if(line.charAt(0) == '-') {
	    		String message = line.substring(2);
	    		messages.add(message);		
	    	}
	}
		if(fileValid && currentIncident != null) {
			try {
				currentIncident = new Incident(currentIncident.getId(), currentIncident.getState(), currentIncident.getTitle(), currentIncident.getCaller(), currentIncident.getReopenCount(), currentIncident.getOwner(), currentIncident.getStatusDetails(), messages);
				currentGroup.addIncident(currentIncident);
				groups.add(currentGroup);
			} catch(IllegalArgumentException e) {
				System.out.println("Illegal Argument Exception thrown");
			}	
		}
		else {
			groups.clear();
		}
		return groups;	
		
	}
	
	private static ServiceGroup processServiceGroup(String line) {
		ServiceGroup s = new ServiceGroup(line.substring(2));
		return s;
	}
	
	/**
	 * returns an Incident based off of the line input
	 * @param line is the Incident String
	 * @return an Incident based off the fields in the String 
	 */
	private static Incident processIncident(String line) {
		String incident = line.substring(2);
		String[] input = incident.split(",");
		if(input.length < 7) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("filler");
		Incident i = new Incident(Integer.valueOf(input[0]), input[1], input[2], input[3], Integer.valueOf(input[4]), input[5], input[6], messages);  
		return i;
		
	}
	
}
