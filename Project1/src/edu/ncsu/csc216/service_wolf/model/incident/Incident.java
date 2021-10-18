package edu.ncsu.csc216.service_wolf.model.incident;


import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * This class represents the idea of an incident. An incident has 
 a set of data like its id, it's state, title, caller, owner, log messages,etc.
 These data members can only be set by the developer but it can be accessed to the client
 A set of incidents make up a ServiceGroup. You can modify the state of the incident
 by editing the incident itself by executing commands. 
 * @author abhin
 *
 */
public class Incident {

	/** represents a new State  **/
	private final IncidentState newState = new NewState();
	/** represents the state when Incident is in progress  **/
	private final IncidentState inProgress = new InProgressState();
	/** the state of OnHold  **/
	private final IncidentState onHold = new OnHoldState();
	/** the state when an incident is resolved **/
	private final IncidentState resolved = new ResolvedState();
	/** the state when an Incident is canceled **/
	private final IncidentState canceled = new CanceledState();
	
	
	/** the current state of the incident  **/
	private IncidentState currentState;
	
	
	/** the id of the incident **/
	private int incidentId;
	/** the title of the incident **/
	private String title;
	/** the caller of the incident **/
	private String caller;
	/** the reopenCount of the incident **/
	private int reopenCount;
	/** the owner of the incident **/
	private String owner;
	/** the status details of the incident **/
	private String statusDetails;
	/** the list of log messages of the incident **/
	private ArrayList<String> incidentLog;
	/** a counter **/
	public static int counter = 1; 
	/** the id of the incident **/
	public static final String NEW_NAME  = "New";
	/** the id of the incident **/
	public static final String IN_PROGRESS_NAME  = "In Progress";
	/** the id of the incident **/
	public static final String ON_HOLD_NAME = "On Hold";
	/** the id of the incident **/
	public static final String RESOLVED_NAME  = "Resolved";
	/** the id of the incident **/
	public static final String CANCELED_NAME  = "Canceled";
	/** the id of the incident **/
	public static final String HOLD_AWAITING_CALLER  = "Awaiting Caller";
	/** the id of the incident **/
	public static final String HOLD_AWAITING_CHANGE  = "Awaiting Change";
	/** the id of the incident **/
	public static final String HOLD_AWAITING_VENDOR  = "Awaiting Vendor";
	/** the id of the incident **/
	public static final String RESOLUTION_PERMANENTLY_SOLVED  = "Permanently Solved";
	/** the id of the incident **/
	public static final String RESOLUTION_WORKAROUND  = "Workaround";
	/** the id of the incident **/
	public static final String RESOLUTION_CALLER_CLOSED  = "Caller Closed";
	/** the id of the incident **/
	public static final String CANCELLATION_DUPLICATE  = "Duplicate";
	/** the id of the incident **/
	public static final String CANCELLATION_UNNECESSARY  = "Unnecessary";
	/** the id of the incident **/
	public static final String CANCELLATION_NOT_AN_INCIDENT  = "Not an Incident";
	/** the id of the incident **/
	public static final String CANCELLATION_CALLER_CANCELLED  = "Caller Canceled";
	/** the id of the incident **/
	public static final String UNOWNED  = "Unowned";
	/** the id of the incident **/
	public static final String NO_STATUS  = "No Status";

	/**
	 * this constructs an Incident with some default data and some
	 based off the parameters
	 * @param title is the title of the incident
	 * @param caller is the caller id or name
	 * @param message is the incident message
	 */
	public Incident(String title, String caller, String message) {
		setId(counter);
		setTitle(title);
		setCaller(caller);
		setReopenCount(0);
		setOwner("Unowned");
		setStatusDetails(NO_STATUS);
		this.incidentLog = new ArrayList<String>();
		if("".equals(message) || message == null) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		incrementCounter();
		addMessageToIncidentLog(message);
		setState("New");

	}
	
	/**
	 * creates an Incident Record based off of the parameter values 
	 * @param id is the incidentId
	 * @param state is the state of the Incident
	 * @param title is the title of the incident
	 * @param caller is the owner of the incident
	 * @param reopenCount is the reopenCount 
	 * @param owner is the owner of the incident
	 * @param statusDetails are the status details of the incident throughout modifications
	 * @param incidentLog is the list of incident log messages
	 */
	public Incident(int id, String state, String title, String caller, int reopenCount, String owner, String statusDetails, ArrayList<String> incidentLog) {
		setId(id);
		setTitle(title);
		setCaller(caller);
		setReopenCount(reopenCount);
		setOwner(owner);
		setStatusDetails(statusDetails);
		this.incidentLog = new ArrayList<String>();
		incrementCounter();
		if(incidentLog == null || incidentLog.size() == 0) {
			//System.out.println("The messages List is Null");
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		else {
			for(int i = 0; i < incidentLog.size(); i++) {
				addMessageToIncidentLog(incidentLog.get(i));
			}
		}
		
		setState(state);


	}
	
	/**
	 * sets the id of an incident
	 * @param id is the parameter id that is set as the id of the incident
	 */
	public void setId(int id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.incidentId = id;
	}
	
	/**
	 * returns the id of the incident
	 * @return the id of the incident 
	 */
	public int getId() {
		
		return incidentId;
	}
	
	/**
	 * accesses the current state of the incident
	 * @return the state
	 */
	public String getState() {
		return currentState.getStateName();
	}

	/**
	 * sets the current state of the incident
	 * @param state the state to set
	 */
	private void setState(String state) {
		

		if(NEW_NAME.equals(state)) {
			if(!UNOWNED.equals(getOwner())) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(!NO_STATUS.equals(getStatusDetails())) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			currentState = newState;
		}
		else if(ON_HOLD_NAME.equals(state)) {
			if("".equals(owner) || null == owner || UNOWNED.equals(owner)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(NO_STATUS.equals(statusDetails)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(!HOLD_AWAITING_CALLER.equals(statusDetails) && !HOLD_AWAITING_CHANGE.equals(statusDetails) && !HOLD_AWAITING_VENDOR.equals(statusDetails)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			currentState = onHold;
		}
		else if(RESOLVED_NAME.equals(state)) {
			if("".equals(owner) || null == owner || UNOWNED.equals(owner)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(NO_STATUS.equals(statusDetails)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(!RESOLUTION_PERMANENTLY_SOLVED.equals(statusDetails) && !RESOLUTION_WORKAROUND.equals(statusDetails) && !RESOLUTION_CALLER_CLOSED.equals(statusDetails)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			currentState = resolved;
		}
		else if(CANCELED_NAME.equals(state)) {
			if(!UNOWNED.equals(getOwner())) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(NO_STATUS.equals(statusDetails)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(!CANCELLATION_DUPLICATE.equals(statusDetails) && !CANCELLATION_UNNECESSARY.equals(statusDetails) && !CANCELLATION_NOT_AN_INCIDENT.equals(statusDetails) && !CANCELLATION_CALLER_CANCELLED.equals(statusDetails)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			currentState = canceled;
		}
		else if (IN_PROGRESS_NAME.equals(state)) {
			if("".equals(owner) || null == owner || UNOWNED.equals(owner)) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			if(!NO_STATUS.equals(getStatusDetails())) {
				throw new IllegalArgumentException("Incident cannot be created.");
			}
			currentState = inProgress;
		}
		else {
			throw new IllegalArgumentException("Incident cannot be created.");
		}

	}

	/**
	 * returns the title of the incident
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title of the incident
	 * @param title -> title of the incident
	 */
	private void setTitle(String title) {
		if("".equals(title) || title == null) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.title = title;
	}

	/**
	 * returns the caller id of the incident
	 * @return the caller
	 */
	public String getCaller() {
		return caller;
	}

	/**
	 * sets the caller id of the incident
	 * @param caller the caller to set
	 */
	private void setCaller(String caller) {
		if("".equals(caller) || caller == null) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.caller = caller;
	}

	/**
	 * returns the reopenCount of the incident
	 * @return the reopenCount
	 */
	public int getReopenCount() {
		return reopenCount;
	}

	/**
	 * sets the reopenCount of the incident
	 * @param reopenCount the reopenCount to set
	 */
	private void setReopenCount(int reopenCount) {
		if(reopenCount < 0) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.reopenCount = reopenCount;
	}

	/**
	 * returns the owner name of the incident
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * sets the owner name of the incident
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * returns the status details of the incident
	 * @return the statusDetails
	 */
	public String getStatusDetails() {
		return statusDetails;
	}

	/**
	 * sets the status details of the incident
	 * @param statusDetails the statusDetails to set
	 */
	private void setStatusDetails(String statusDetails) {
//		if(getState().equals("Canceled") && "".equals(statusDetails)) {
//			throw new IllegalArgumentException("Incident cannot be created.");
//		}
//		else if(getState().equals("Resolved") && "".equals(statusDetails)) {
//			throw new IllegalArgumentException("Incident cannot be created.");
//		}
//		else if(getState().equals("On Hold") && "".equals(statusDetails)) {
//			throw new IllegalArgumentException("Incident cannot be created.");
//		}
//		else if("No Status".equals(statusDetails) && "In Progress".equals(getState())) {
//			throw new IllegalArgumentException("Incident cannot be created.");
//		}
		if(null == statusDetails) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		this.statusDetails = statusDetails;
	}
	
	/**
	 * adds a message to the incident message log
	 * @param message that has to be added after a change in the state of incident
	 * @return a number
	 */
	private int addMessageToIncidentLog(String message) {
		incidentLog.add(message);
		return incidentLog.size() - 1;
	}
	
	/**
	 * returns the list of log messages
	 * @return a String of log messages
	 */
	public String getIncidentLogMessages() {
		String log = "";
		for(int i = 0; i < incidentLog.size(); i++) {
			//System.out.println("Adding Message: " + incidentLog.get(i));
//			if(i == incidentLog.size() - 1) {
//				log = log + "- " + incidentLog.get(i);
//			}
//			else {
//				log = log + "- " + incidentLog.get(i) + "\n";
//			}
			log = log + "- " + incidentLog.get(i) + "\n";
		}
		return log;
	}
	
	/**
	 * sets the counter to a value
	 * @param counter is set to a value of choice 
	 */
	public static void setCounter(int counter) {
		Incident.counter = counter;
	}
	
	/**
	 * increments the counter by 1
	 */
	public static void incrementCounter() {
		counter += 1;
	}
	
	@Override
	public String toString() {
		return "* " + incidentId + "," + getState() + "," + title + "," + caller + "," + String.valueOf(reopenCount) + "," + owner + "," + statusDetails + "\n" + getIncidentLogMessages();
		
	}
	
	
	/**
	 * Incident updates its state based off a command
	 * @param command is a Command object that modifies the incident 
	 */
	public void update(Command command) {
		currentState.updateState(command);
		
	}
	
	/**
	 * Interface for states in the Incident State Pattern.  All 
	 * concrete incident states must implement the IncidentState interface.
	 * The IncidentState interface should be a private interface of the 
	 * Incident class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface IncidentState {
		
		/**
		 * Update the Incident based on the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Incident's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
	
	private class NewState implements IncidentState {

		@Override
		public void updateState(Command command) {
			if(command.getCommand() == Command.CommandValue.ASSIGN) {
				System.out.println("Successfull command test!");
				currentState = inProgress;
				setOwner(command.getCommandInformation());
			}
			else if (command.getCommand() == Command.CommandValue.CANCEL) {
				currentState = canceled;
				setStatusDetails(command.getCommandInformation());
			}
			else {
				throw new UnsupportedOperationException();
			}
			addMessageToIncidentLog(command.getCommandMessage());
		}

		@Override
		public String getStateName() {
			return "New";
		}
		
	}
	
	private class InProgressState implements IncidentState {

		@Override
		public void updateState(Command command) {
			
			if(command.getCommand() == Command.CommandValue.HOLD) {
				currentState = onHold;
				setStatusDetails(command.getCommandInformation());
			}
			else if (command.getCommand() == Command.CommandValue.RESOLVE) {
				currentState = resolved;
				setStatusDetails(command.getCommandInformation());
								
			}
			else if (command.getCommand() == Command.CommandValue.ASSIGN) {
				setOwner(command.getCommandInformation());
			}
			else if (command.getCommand() == Command.CommandValue.CANCEL) {
				currentState = canceled;
				setStatusDetails(command.getCommandInformation());
				setOwner("Unowned");
			}
			else {
				throw new UnsupportedOperationException();
			}
			addMessageToIncidentLog(command.getCommandMessage());	
		}

		@Override
		public String getStateName() {
			return "In Progress";
		}
		
	}
	
	private class CanceledState implements IncidentState {

		@Override
		public void updateState(Command command) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public String getStateName() {
			return "Canceled";
		}
		
	}
	
	private class OnHoldState implements IncidentState {

		
		private OnHoldState() {
			
		}
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			if (command.getCommand() == Command.CommandValue.INVESTIGATE) {
				setStatusDetails("No Status");
				currentState = inProgress;
				//setOwner("Unowned");
			}
			else {
				throw new UnsupportedOperationException();
			}
			
			addMessageToIncidentLog(command.getCommandMessage());
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "On Hold";
		}
	}
	
	
	
	private class ResolvedState implements IncidentState {

		
		private ResolvedState() {
			
		}
		@Override
		public void updateState(Command command) {
			if (command.getCommand() == Command.CommandValue.REOPEN) {
				currentState = inProgress;
				setStatusDetails("No Status");
				reopenCount++;
				setReopenCount(reopenCount);
			}
			else if (command.getCommand() == Command.CommandValue.CANCEL) {
				setStatusDetails(command.getCommandInformation());
				setOwner("Unowned");
				currentState = canceled;
			}
			else {
				throw new UnsupportedOperationException();
			}
			
			addMessageToIncidentLog(command.getCommandMessage());
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "Resolved";
		}
	}

		
}