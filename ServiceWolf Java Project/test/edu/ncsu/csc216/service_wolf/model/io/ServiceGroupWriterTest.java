package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;
/**
 * tests the functionality of the ServiceGroupsWriter class
 * which is responsible for writing servicegroup records to 
 * a file
 * @author abhin
 *
 */
public class ServiceGroupWriterTest {


	/**
	 * tests to see if the ServiceGroup records 
	 are successfuly outputted to a file
	 */
	@Test
	public final void testWriteServiceGroupsToFile() {
		ArrayList<ServiceGroup> groups = new ArrayList<ServiceGroup>();
//		ServiceGroup s = new ServiceGroup("CSC IT");
//		ArrayList<String> messages = new ArrayList<String>();
//		messages.add("Set up piazza for Spring 2021");
//		messages.add("Canceled; not an NC State IT service");
//		Incident i = new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", messages);
//		s.addIncident(i);
//		assertEquals(1, s.getIncidents().size());
//		ArrayList<String> messages2 = new ArrayList<String>();
//		messages2.add("When I go to wolfware.ncsu.edu, I get a 500 error");
//		Incident i2 = new Incident(3, "New", "Moodle down", "sesmith5", 0, "Unowned", "No Status", messages2);
//		System.out.println("Checking output of Incident 2:\n");
//		System.out.println(i2);
//		s.addIncident(i2);
		ServiceGroup s = new ServiceGroup("First");
		s.addIncident(new Incident("Moodle", "Abhinav", "Random message"));
		
		ServiceGroup s2 = new ServiceGroup("Abhinav");
		s2.addIncident(new Incident("Piazza", "Abhinav", "Random message"));
		
		ServiceGroup s3 = new ServiceGroup("Last");
		s3.addIncident(new Incident("Jenkins", "Abhinav", "Random message"));
		s3.addIncident(new Incident("Github", "Abhinav", "Random message"));

		
		groups = ServiceGroupsReader.readServiceGroupsFile("test-files/grouprecords.txt");
		assertEquals(4, groups.get(0).getIncidents().size());
		//groups.add(s);
		ServiceGroupWriter.writeServiceGroupsToFile("test-files/outputrecords.txt", groups);
		checkFiles("test-files/grouprecords.txt", "test-files/outputrecords.txt");
		//Incident i2 = new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", messages);
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
//				String expLine = String.valueOf(expScanner.nextLine());
//				String actLine = String.valueOf(actScanner.nextLine());
//				System.out.println("Exp: " + expLine);
//				System.out.println("Act: " + actLine);
//				assertEquals(expLine, actLine);
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
