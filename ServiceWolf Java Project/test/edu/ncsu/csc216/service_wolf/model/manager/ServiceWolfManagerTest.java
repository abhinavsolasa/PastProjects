package edu.ncsu.csc216.service_wolf.model.manager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * This class tests the functionality of the ServiceWolfManager class.
 * ServiceWolfManager is the main system where most of the 
 * operations happen. 
 * @author abhin
 *
 */
public class ServiceWolfManagerTest {

	
	/** represents an instance of the Registration manager    **/
	private ServiceWolfManager manager;
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = ServiceWolfManager.getInstance();
		manager.clearServiceGroups();
	}
	
	/**
	 * tests if ServiceGroups can be saved to a file
	 */
	@Test
	public final void testSaveToFile() {
		//manager.clearServiceGroups();
//		manager.addServiceGroup("CSC IT");
//		manager.addIncidentToServiceGroup("Piazza", , null);
		manager.loadFromFile("test-files/grouprecords.txt");
		assertEquals(3, manager.getServiceGroupList().length);
		manager.saveToFile("test-files/outputrecords2.txt");
		checkFiles("test-files/grouprecords.txt", "test-files/outputrecords2.txt");
		
		
	}



	/**
	 * tests to see if all the ServiceGroup records
	 have successfully loaded into the manager system from a file
	 */
	@Test
	public final void testLoadFromFile() {
		manager.clearServiceGroups();
		assertEquals(0, manager.getServiceGroupList().length);
		manager.loadFromFile("test-files/grouprecords.txt");
		assertEquals("CSC IT", manager.getServiceGroupList()[0]);
	}

	/**
	 * tests to see if the array of the active ServiceGroup's incidents
	 has the correct contents
	 */
	@Test
	public final void testGetIncidentsAsArray() {
		Incident.setCounter(1);
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		assertEquals("First", manager.getServiceGroupName());
		manager.addIncidentToServiceGroup("Piazza", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Jenkins", "asolasa", "Random message");
		String [][] incidentArray = manager.getIncidentsAsArray();
		String [][] temp = new String [2][4];
		for(int i = 0; i < 2; i++) {
			temp[i][0] = String.valueOf(i + 1);
			temp[i][1] = "New";
			if(i == 0) {
				temp[i][2] = "Piazza";
			}
			else if(i == 1) {
				temp[i][2] = "Jenkins";
			}
			temp[i][3] = "No Status";
		}
		for(int j = 0; j < 2; j++) {
			assertEquals(incidentArray[j][0], temp[j][0]);
			assertEquals(incidentArray[j][1], temp[j][1]);
			assertEquals(incidentArray[j][2], temp[j][2]);
			assertEquals(incidentArray[j][3], temp[j][3]);
		}
	}

	/**
	 * tests to see if you can properly access an Incident
	 based on its id in the active ServiceGroup
	 */
	@Test
	public final void testGetIncidentById() {
		Incident.setCounter(1);
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		manager.addIncidentToServiceGroup("Jenkins", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Jenkins", "asolasa", "Random message");
		assertNull(manager.getIncidentById(4));
	}

	/**
	 * tests to see if a command executed on an Incident
	 properly somehow changes its state
	 */
	@Test
	public final void testExecuteCommand() {
		Incident.setCounter(1);
		manager.clearServiceGroups();
		assertEquals(0, manager.getServiceGroupList().length);
		manager.addServiceGroup("First");
		assertEquals("First", manager.getServiceGroupName());
		manager.addIncidentToServiceGroup("Piazza", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Jenkins", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Something", "asolasa", "Random message");
		Command c = new Command(Command.CommandValue.ASSIGN, "abhinav", "Blah");
		System.out.println("Execute Command check: ");
		manager.executeCommand(2, c);

	}

	/**
	 * tests the deletion of an Incident in the activeServiceGroup
	 */
	@Test
	public final void testDeleteIncidentById() {
		System.out.println("Testing Deleting Incident By ID");
		Incident.setCounter(1);
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		assertEquals("First", manager.getServiceGroupName());
		manager.addIncidentToServiceGroup("Piazza", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Jenkins", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Something", "asolasa", "Random message");
		manager.deleteIncidentById(2);
	}

	/**
	 * tests the addition of an Incident in the activeServiceGroup
	 */
	@Test
	public final void testAddIncidentToServiceGroup() {
		System.out.println("At Adding Incidents Test");
		Incident.setCounter(1);
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		assertEquals("First", manager.getServiceGroupName());
		manager.addIncidentToServiceGroup("Piazza", "asolasa", "Random message");
		manager.addIncidentToServiceGroup("Jenkins", "asolasa", "Random message");	
		
	}
		
	/**
	 * tests to see if a new active ServiceGroup has loaded properly in the manager
	 */
	@Test
	public final void testLoadServiceGroup() {
		Incident.setCounter(1);
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		manager.addServiceGroup("Second");
		manager.addServiceGroup("Abhinav");
		assertEquals("Abhinav", manager.getServiceGroupName());
		manager.loadServiceGroup("Second");
		assertEquals("Second", manager.getServiceGroupName());

	
	}

	/**
	 * tests the access of the name of the active ServiceGroup in the manager
	 */
	@Test
	public final void testGetServiceGroupName() {
		manager.clearServiceGroups();
		manager.addServiceGroup("Abhinav");
		manager.addServiceGroup("First");
		//manager.addServiceGroup("Second");
		assertEquals("First", manager.getServiceGroupName());
		assertEquals("Abhinav", manager.getServiceGroupList()[0]);

	}

	/**
	 * tests the access of a ServiceGroup in the manager
	 */
	@Test
	public final void testGetServiceGroupList() {
		Incident.setCounter(1);
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		manager.addServiceGroup("Abhinav");
		manager.addServiceGroup("Second");
		manager.addServiceGroup("Third");
		assertEquals(4, manager.getServiceGroupList().length);
		assertEquals("Abhinav", manager.getServiceGroupList()[0]);
		assertEquals("First", manager.getServiceGroupList()[1]);
		assertEquals("Second", manager.getServiceGroupList()[2]);
		assertEquals("Third", manager.getServiceGroupList()[3]);

		//manager.addServiceGroup("Second");
//		assertEquals(1, manager.getServiceGroupList().length);
//		for(int i = 0; i < 1; i++) {
//			assertEquals("First", manager.getServiceGroupList()[i]);
//		}
		
		manager.clearServiceGroups();
		assertEquals(0, manager.getServiceGroupList().length);	
		}

	/**
	 * tests the clearing of all ServiceGroups in the manager
	 */
	@Test
	public final void testClearServiceGroups() {
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		//manager.addServiceGroup("Second");
		assertEquals(1, manager.getServiceGroupList().length);
		manager.clearServiceGroups();
		assertEquals(0, manager.getServiceGroupList().length);
	}

	/**
	 * tests the editing of a ServiceGroup in the manager
	 */
	@Test
	public final void testEditServiceGroup() {
		manager.clearServiceGroups();
		manager.addServiceGroup("Abhinav");
		manager.addServiceGroup("First");
		try {
			manager.editServiceGroup(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		try {
			manager.editServiceGroup("");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		assertEquals("First", manager.getServiceGroupName());
		//manager.addServiceGroup("Second");
		manager.editServiceGroup("Aaron");
		assertEquals("Aaron", manager.getServiceGroupName());
		assertEquals(2, manager.getServiceGroupList().length);
		assertEquals("Aaron", manager.getServiceGroupList()[0]);
		assertEquals("Abhinav", manager.getServiceGroupList()[1]);
		//assertEquals("Third", manager.getServiceGroupList()[1]);
		
		
	}

	/**
	 * tests the addition of a ServiceGroup in the manager
	 */
	@Test
	public final void testAddServiceGroup() {
		manager.clearServiceGroups();
		manager.addServiceGroup("First");
		assertEquals(1, manager.getServiceGroupList().length);
		manager.addServiceGroup("Zebra");
		assertEquals(2, manager.getServiceGroupList().length);
	}

	/**
	 * tests the deletion of a ServiceGroup in the manager
	 */
	@Test
	public final void testDeleteServiceGroup() {
		manager.clearServiceGroups();
		assertEquals(0, manager.getServiceGroupList().length);
		manager.addServiceGroup("First");
		assertEquals(1, manager.getServiceGroupList().length);
		assertEquals("First", manager.getServiceGroupList()[0]);
		assertEquals("First", manager.getServiceGroupName());
		manager.deleteServiceGroup();
		assertEquals(0, manager.getServiceGroupList().length);

}

	/**
	 * tests the resetting of the ServiceWolf manager system
	 */
	@Test
	public final void testResetManager() {
		manager.clearServiceGroups();
		manager.resetManager();
		assertNull(manager.getServiceGroupName());
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
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}


}