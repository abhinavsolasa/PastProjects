package edu.ncsu.csc216.service_wolf.model.command;


/**
 * this class represents a command that is executed to modify 
 the state of an incident. A command has a command value which 
 is assumed to be a type of instruction to execute. A command also has
 its own data such as information and a message. This data is also accessible 
 through its getters;
 * @author abhin
 *
 */
public class Command {
	
	/** info about the command **/
	private String commandInformation;
	/** a message of the command **/
	private String commandMessage;
	/** a value or instruction that a command execues **/
	private CommandValue command;
	
	/**
	 * creates a command based off the given parameters
	 * @param value is a command value that the command executes
	 * @param info is just information about the command
	 * @param message is the message of a command
	 */
	public Command(CommandValue value, String info, String message) {
		if(value == null) {
			throw new IllegalArgumentException();
			
		}
		else if (value == Command.CommandValue.ASSIGN || value == Command.CommandValue.HOLD || value == Command.CommandValue.RESOLVE || value == Command.CommandValue.CANCEL){
			if(info ==  null || "".equals(info)) {
				throw new IllegalArgumentException("Invalid information.");
			}
		}
		else if ((value == Command.CommandValue.INVESTIGATE || value == Command.CommandValue.REOPEN) && info != null) {
				throw new IllegalArgumentException();
		}
		if("".equals(message) || null == message) {
			throw new IllegalArgumentException("Invalid information.");
		}
		command = value;
		commandInformation = info;
		commandMessage = message;
	}
	
	/**
	 * represents a command value which is executed by command?
	 * @author abhin
	 *
	 */
	public enum CommandValue { ASSIGN, HOLD, INVESTIGATE, RESOLVE, REOPEN, CANCEL }

	
	/**
	 * accesses the value of the actual command to execute
	 * @return a command Value for the command to execute
	 */
	public CommandValue getCommand() {
		return command;
	}

	/**
	 * information of the command
	 * @return the commandInformation
	 */
	public String getCommandInformation() {
		return commandInformation;
	}

	/**
	 * the message of the command
	 * @return the commandMessage
	 */
	public String getCommandMessage() {
		return commandMessage;
	}

	
	
}
