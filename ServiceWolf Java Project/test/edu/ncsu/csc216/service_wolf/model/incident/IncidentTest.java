package edu.ncsu.csc216.service_wolf.model.incident;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * This class tests the functionality of all the methods 
 in the Incident class
 * @author abhin
 *
 */
public class IncidentTest {


	/**
	 * tests to see if an Incident is properly constructed with
	 corresponding parameters (3 parameter version)
	 */
	@Test
	public final void testIncidentStringStringString() {
		Incident i = new Incident("Piazza", "sesmith5", "Something is Wrong");
		assertEquals("Piazza", i.getTitle());
		assertEquals("sesmith5", i.getCaller());
		assertEquals("Unowned", i.getOwner());
		assertEquals("New", i.getState());
		assertEquals("- Something is Wrong" + "\n", i.getIncidentLogMessages());
		assertEquals(Incident.NO_STATUS, i.getStatusDetails());
		
		
	}

	/**
	 *  tests to see if an Incident is properly constructed with
	 corresponding parameters (full parameter version)
	 */
	@Test
	public final void testIncidentIntStringStringStringIntStringStringArrayListOfString() {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Everything");
		messages.add("Cool");
		Incident i = new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", Incident.CANCELLATION_NOT_AN_INCIDENT, messages);
		assertEquals(2, i.getId());
		assertEquals("Piazza", i.getTitle());
		assertEquals("sesmith5", i.getCaller());
		assertEquals(0, i.getReopenCount());
		assertEquals("Unowned", i.getOwner());
		assertEquals(Incident.CANCELLATION_NOT_AN_INCIDENT, i.getStatusDetails());
		
	}

	/**
	 * tests to see if the id of an Incident can be 
	 set properly
	 */
	@Test
	public final void testSetId() {
		Incident i = new Incident("Piazza", "sesmith5", "Something is Wrong");
		assertEquals(1, i.getId());
		i.setId(5);
		assertEquals(5, i.getId());
		try {
			i.setId(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incident cannot be created.", e.getMessage());
		}
		
	}

	/**
	 * tests to see if the list of log Messages
	 are updated and accessed properly
	 */
	@Test
	public final void testGetIncidentLogMessages() {
		Incident i = new Incident("Piazza", "sesmith5", "Something is Wrong");
//		System.out.println();
//		System.out.println("Messages: " + i.getIncidentLogMessages());
		assertEquals("- Something is Wrong" + "\n", i.getIncidentLogMessages());
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Everything");
		messages.add("Cool");
		i = new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", Incident.CANCELLATION_NOT_AN_INCIDENT, messages);
		assertEquals("- Everything" + "\n" + "- Cool" + "\n", i.getIncidentLogMessages() );
		System.out.println("Printed messages:\n" + i.getIncidentLogMessages());
	}

	/**
	 * tests the output of an Incident record
	 */
	@Test
	public final void testToString() {
		Incident.setCounter(1);
		Incident i = new Incident("Piazza", "sesmith5", "Something is Wrong");
		assertEquals("* 1,New,Piazza,sesmith5,0,Unowned,No Status" + "\n" + i.getIncidentLogMessages(), i.toString());
		Incident i2 = new Incident("Random", "asolasa", "Something");
		assertEquals("* 2,New,Random,asolasa,0,Unowned,No Status" + "\n" + i2.getIncidentLogMessages(), i2.toString());

	}

	/**
	 * tests to see if an Incident is properly updated 
	 based off of the commands that are executed 
	 */
	@Test
	public final void testUpdate() {
		Incident.setCounter(1);
		Incident i = new Incident("Piazza", "sesmith5", "Something is Wrong");
		Command c = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned an Owner.");
		i.update(c);
		assertEquals("In Progress", i.getState());
		assertEquals("Abhinav", i.getOwner());
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Everything");
		messages.add("Cool");
		
		Command c2 = new Command(Command.CommandValue.CANCEL, "Now gone", "Canceled Incident.");
		Incident i2 = new Incident(2, "In Progress", "Piazza", "sesmith5", 0, "Somebody", "No Status", messages);
		i2.update(c2);
		assertEquals("Canceled", i2.getState());
		assertEquals("Now gone", i2.getStatusDetails());
		
		i2 = new Incident(2, "On Hold", "Piazza", "sesmith5", 0, "Me", Incident.HOLD_AWAITING_CALLER, messages);
		Command c3 = new Command(Command.CommandValue.INVESTIGATE, null, "Investigated");
		i2.update(c3);
		assertEquals("In Progress", i2.getState());
		assertEquals("Me", i2.getOwner());
		
		i2 = new Incident(2, "Resolved", "Piazza", "sesmith5", 0, "Him", Incident.RESOLUTION_WORKAROUND, messages);
		Command c4 = new Command(Command.CommandValue.REOPEN, null, "Investigated");
		i2.update(c4);
		assertEquals("In Progress", i2.getState());
		assertEquals("No Status", i2.getStatusDetails());
		assertEquals(1, i2.getReopenCount());
		
		i2 = new Incident(2, "In Progress", "Piazza", "sesmith5", 0, "Her", "No Status", messages);
		Command c5 = new Command(Command.CommandValue.HOLD, "Some status", "Investigated");
		i2.update(c5);
		assertEquals("On Hold", i2.getState());
		assertEquals("Some status", i2.getStatusDetails());
		
		i2 = new Incident(2, "In Progress", "Piazza", "sesmith5", 0, "She", "No Status", messages);
		c5 = new Command(Command.CommandValue.RESOLVE, "Some status", "Investigated");
		i2.update(c5);
		assertEquals("Resolved", i2.getState());
		assertEquals("Some status", i2.getStatusDetails());

		i2 = new Incident(2, "In Progress", "Piazza", "sesmith5", 0, "They", "No Status", messages);
		c5 = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Investigated");
		i2.update(c5);
		assertEquals("In Progress", i2.getState());
		assertEquals("Abhinav", i2.getOwner());
		
		i2 = new Incident(2, "In Progress", "Piazza", "sesmith5", 0, "Abhi", "No Status", messages);
		c5 = new Command(Command.CommandValue.CANCEL, "Some status", "Investigated");
		i2.update(c5);
		assertEquals("Canceled", i2.getState());
		assertEquals("Unowned", i2.getOwner());
		assertEquals("Some status", i2.getStatusDetails());
		
		i2 = new Incident(4, "Canceled", "Piazza", "sesmith4", 0, "Unowned", "Not an Incident", messages);
		c5 = new Command(Command.CommandValue.CANCEL, "Some status", "Investigated");
		try {
			i2.update(c5);
			fail();
		} catch(UnsupportedOperationException e) {
			System.out.println("Unsupported Operation");
		}
		
		i2 = new Incident("Moodle", "sesmith5", "Something happened");
		Command c6 = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned an owner");
		i2.update(c6);
		assertEquals("In Progress", i2.getState());
		assertEquals("Abhinav", i2.getOwner());
		
		i2 = new Incident(9, "Resolved", "Piazza", "sesmith4", 0, "Abhinav", Incident.RESOLUTION_WORKAROUND, messages);
		c6 = new Command(Command.CommandValue.REOPEN, null, "Reopened incident");
		i2.update(c6);
		assertEquals("In Progress", i2.getState());
		assertEquals(1, i2.getReopenCount());
		
		i2 = new Incident(9, "Resolved", "Piazza", "sesmith4", 0, "Abhinav", Incident.RESOLUTION_WORKAROUND, messages);
		c6 = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned incident");
		try {
			i2.update(c6);
			fail();
		} catch(UnsupportedOperationException e) {
			System.out.println("Unsupported Operation");

		}
		
		i2 = new Incident("Moodle", "sesmith5", "Something happened");
		c6 = new Command(Command.CommandValue.CANCEL, "Nothing", "Canceled the  incident");
		i2.update(c6);
		assertEquals("Canceled", i2.getState());
		assertEquals("Nothing", i2.getStatusDetails());
	}

}
