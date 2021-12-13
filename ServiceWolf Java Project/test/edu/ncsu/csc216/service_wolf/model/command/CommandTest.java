package edu.ncsu.csc216.service_wolf.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the functionality of all the methods 
 of the Command Class.
 * @author abhin
 *
 */
public class CommandTest {

	/**
	 * tests to see if command is constructed properly
	 */
	@SuppressWarnings("unused")
	@Test
	public final void testCommand() {
		Command c = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned Owner");
		assertEquals(c.getCommandInformation(), "Abhinav");
		assertEquals(c.getCommandMessage(), "Assigned Owner");
		assertEquals(c.getCommand(), Command.CommandValue.ASSIGN);
	
		try {
			Command c2 = new Command(Command.CommandValue.REOPEN, "John", "Something is done");
			fail();
		} catch (IllegalArgumentException e) {
			System.out.print("Test 1 has passed!");
		}
	}

	/**
	 * tests to see if command is properly accessed
	 */
	@Test
	public final void testGetCommand() {
		Command c = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned Owner");
		assertEquals(c.getCommand(), Command.CommandValue.ASSIGN);

	}
	
	/**
	 * tests to see if commandInformation data is 
	 properly accessed
	 */
	@SuppressWarnings("unused")
	@Test
	public final void testGetCommandInformation() {
		Command c = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned Owner");
		assertEquals(c.getCommandInformation(), "Abhinav");
		try {
			Command c2 = new Command(Command.CommandValue.ASSIGN, "", "Something is done");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid information.", e.getMessage());
		}
		try {
			Command c3 = new Command(Command.CommandValue.INVESTIGATE, "John", "Something is done");
			fail();
		} catch (IllegalArgumentException e) {
			System.out.print("Test passed!");
		}
	}

	/**
	 * tests to see if commandInformation data is 
	 properly accessed
	 */
	@SuppressWarnings("unused")
	@Test
	public final void testGetCommandMessage() {
		Command c = new Command(Command.CommandValue.ASSIGN, "Abhinav", "Assigned Owner");
		assertEquals(c.getCommandMessage(), "Assigned Owner");
		try {
			Command c2 = new Command(Command.CommandValue.ASSIGN, "Abhinav", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid information.", e.getMessage());
		}
		

	}

}
