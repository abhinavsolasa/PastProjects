package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;


/**
 * This class tests the functionality of the methods 
 in the ServiceGroupReader class. It tests to see if 
 ServiceGroup records can be properly read into the program
 from a file
 * @author abhin
 *
 */
public class ServiceGroupReaderTest {

	/**
	 * tests to see if the ServiceGroups can be properly 
	 read in and created from a file
	 * @throws IllegalArgumentException if file can't be read in
	 */
	@Test
	public final void testReadServiceGroupsFile() {
		
		ArrayList<ServiceGroup> groups = new ArrayList<ServiceGroup>();
		try {
			groups = ServiceGroupsReader.readServiceGroupsFile("test-files/random.txt"); 
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		groups = ServiceGroupsReader.readServiceGroupsFile("test-files/grouprecords.txt"); 
		assertEquals("CSC IT", groups.get(0).getServiceGroupName());
		assertEquals(4, groups.get(0).getIncidents().size());
		System.out.println("Incident 1:\n" + groups.get(0).getIncidents().get(0));
		//System.out.println("Messages:\n" + groups.get(0).getIncidents().get(0).getIncidentLogMessages());
		System.out.println("Incident 2:\n" + groups.get(0).getIncidents().get(1));

		//System.out.println("Messages:\n" + groups.get(0).getIncidents().get(1).getIncidentLogMessages());
		
	}

}
