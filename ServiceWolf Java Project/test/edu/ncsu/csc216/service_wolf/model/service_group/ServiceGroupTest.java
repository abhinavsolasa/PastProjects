package edu.ncsu.csc216.service_wolf.model.service_group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * tests the functionality of the methods of the ServiceGroup class.
 It tests the addition of Incidents in the Group. Then, it also tests 
 if the user can properly set and access the name of the Group. To sum it up,
 removing and adding Incidents are tested and accessing and modifying an incident 
 in the group is tested 
 * @author abhin
 *
 */
public class ServiceGroupTest {


	/**
	 * makes sure the incident counter is set to 0 before each test 
	 * @throws Exception does not throw an exception anywhere 
	 */
	@Before
	public void setUp() throws Exception {
		//Reset the counter at the beginning of every test.
		Incident.setCounter(1);
	}
	
	/**
	 * tests the construction of the data of a ServiceGroup
	 */
	@Test
	public final void testServiceGroup() {
		ServiceGroup s = new ServiceGroup("First");
		assertEquals("First", s.getServiceGroupName());
		assertEquals(0, s.getIncidents().size());
		
		try {
			@SuppressWarnings("unused")
			ServiceGroup s2 = new ServiceGroup(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		try {
			@SuppressWarnings("unused")
			ServiceGroup s3 = new ServiceGroup("");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
	}

	/**
	 * checks the  value for the Incident counter after it is set
	 */
	@Test
	public final void testSetIncidentCounter() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Some message");
		ServiceGroup s = new ServiceGroup("Blah");
		Incident i2 = new Incident(3, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
		//Incident i = new Incident("Random", "Abhinav", "Just a Random Incident");
		//Incident i3 = new Incident("Blah", "Abhinav", "Just a Random Incident");
		s.addIncident(i2);
		Incident i4 = new Incident("Some", "Abhinav", "Just a Random Incident");
		//assertEquals(0, i.getId());
		assertEquals(2, i4.getId());
	}

	/**
	 * tests to see if you can properly modify the name 
	 * of a ServiceGroup
	 */
	@Test
	public final void testSetServiceGroupName() {
		ServiceGroup s = new ServiceGroup("First");
		s.setServiceGroupName("Bob");
		assertEquals("Bob", s.getServiceGroupName());
	}
//	
	/**
	 * tests the addition of an Incident in the ServiceGroup
	 */
	@Test
	public final void testAddIncident() {
		Incident.setCounter(1);
		ServiceGroup s = new ServiceGroup("Test");
		Incident i = new Incident("Random", "Abhinav", "Just a Random Incident");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Something");
		Incident i2 = new Incident(1, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
		s.addIncident(i);
		assertEquals(1, i.getId());
		//System.out.print("Id: " + i.getId());
		try {
		s.addIncident(i2);
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
		//System.out.print("Id: " + i2.getId());
		assertEquals(1, s.getIncidents().size());
		Incident i3 = new Incident("Piazza", "Abhinav", "Another one");
		s.addIncident(i3);
		assertEquals(3, i3.getId());       
		Incident i4 = new Incident(3, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
		try {
			s.addIncident(i4);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
	}
//
	/**
	 * tests if you can properly access the incident list of the group
	 */
	@Test
	public final void testGetIncidents() {
		Incident.setCounter(1);
		ServiceGroup s = new ServiceGroup("First");
		Incident i = new Incident("Piazza", "Abhinav", "Another one");
		Incident i2 = new Incident("Jenkins", "Sean", "Something");
		s.addIncident(i);
		s.addIncident(i2);
		ArrayList<String> list = new ArrayList<String>();
		list.add("First");
		list.add("Second");
		try {
			Incident i3 = new Incident(1, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
			s.addIncident(i3);
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
		Incident i4 = new Incident(3, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
		s.addIncident(i4);
		assertEquals(3, s.getIncidents().size());
		for(int j = 0; j < s.getIncidents().size(); j++) {
			assertEquals(j + 1, s.getIncidents().get(j).getId());
		}		
	}

	/**
	 * tests to see if you can access an Incident in the group with
	 specified id
	 */
	@Test
	public final void testGetIncidentById() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("First");
		list.add("Second");
		ServiceGroup s = new ServiceGroup("Blah");
		Incident i = new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
		s.addIncident(i);
		Incident i4 = new Incident(4, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", list);
		s.addIncident(i4);
		Incident i3 = new Incident("Piazza", "sesmith5", "Something");
		s.addIncident(i3);
		assertSame(i, s.getIncidentById(2));
		//assertSame(i3, s.getIncidentById(1));
		assertSame(i4, s.getIncidentById(4));
	}
	

	/**
	 * tests to see if a command is properly executed 
	 to modify an Incident state 
	 */
	@Test
	public final void testExecuteCommand() {
		Incident.setCounter(1);
		ServiceGroup s = new ServiceGroup("Something");
		Incident i = new Incident("Piazza", "sesmith5", "Message");
		Incident i3 = new Incident("Jenkins", "asolasa", "Another message");
		s.addIncident(i);
		s.addIncident(i3);
		Command c = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Updated owner");
		s.executeCommand(1, c);
		s.executeCommand(2, c);
		assertEquals("In Progress", i.getState());
		assertEquals("In Progress", i3.getState());
		Command c2 = new Command(Command.CommandValue.RESOLVE, "Resolved Incident", "Resolved the incident");
		s.executeCommand(1, c2);
		assertEquals("Resolved", i.getState());
	}

	/**
	 * tests the deletion of an Incident in the ServiceGroup
	 */
	@Test
	public final void testDeleteIncidentById() {
		ServiceGroup s = new ServiceGroup("Something");
		Incident i = new Incident("Piazza", "sesmith5", "Message");
		Incident i2 = new Incident("Jenkins", "asolasa", "Another message");
		s.addIncident(i);
		s.addIncident(i2);
		assertEquals(2, s.getIncidents().size());
		s.deleteIncidentById(1);
		assertEquals(1, s.getIncidents().size());
		assertEquals(2, s.getIncidents().get(0).getId());
		s.deleteIncidentById(2);
		assertEquals(0, s.getIncidents().size());

	}

}
