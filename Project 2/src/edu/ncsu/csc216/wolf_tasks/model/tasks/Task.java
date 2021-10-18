package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * This class represents a Task object. A task is a part of a 
 * TaskList. It has a description, a recurring status, a name, 
 * and active status. It has getters and setters for each of these 
 * characteristics. The task can be marked completed, which makes it 
 * added to the active task List 
 * @author abhin
 *
 */
public class Task {

	/**
	 * name of the task
	 */
	private String taskName;
	
	/**
	 * description of the task
	 */
	private String taskDescription;
	
	/**
	 * status of the task
	 */
	@SuppressWarnings("unused")
	private boolean recurring;
	
	/** a check if the taskList is an active one    **/
	@SuppressWarnings("unused")
	private boolean active;
	
	/**
	 * a swaplist of TaskLists 
	 */
	@SuppressWarnings("unused")
	private SwapList<TaskList> taskLists;
	
	
	/** 
	 * constructs a Task with a name, detials, and recurring status
	 * @param taskName is name of the task
	 * @param taskDetails are the description/details of the task
	 * @param recurring is the status of the Task
	 * @param active is if the task is marked completed or not 
	 */
	public Task(String taskName, String taskDetails, boolean recurring, boolean active) {
		
	}
	
	/**
	 * sets the task name to specified name
	 * @param taskName is the name of the task
	 */
	public void setTaskName(String taskName) {
		
	}
	
	/**
	 * accesses the name of the current task
	 * @return the name of the  Task
	 */
	public String getTaskName() {
		
		return taskName;
	}
	/**
	 * sets the description of the task
	 * @param taskDescription is the description of the Task 
	 */
	public void setTaskDescription(String taskDescription) {
		
	}
	
	/**
	 * accesses the description of the task
	 * @return the description of task
	 */
	public String getTaskDescription() {
		
		return taskDescription;
	}
	
	/**
	 * accesses the status of the task
	 * @return true if task is recurring, false otherwise 
	 */
	public boolean isRecurring() {
		
		return false;
	}
	
	/**
	 * sets the task to a recurring status
	 * @param recur is the recurring status the task is set to 
	 */
	public void setRecurring(boolean recur) {
		
	}
	
	/**
	 * this is the clone of the Object 
	 */
	@Override
	public Object clone() {
		return null;
	}
	
	/**
	 * sets the task to the active 
	 * @param active is the active status of the task
	 */
	public void setActive(boolean active) {
		
	}
	/**
	 * checks to see if the task is in the active Tasklist 
	 * @return true if task is active, false otherwise 
	 */
	public boolean isActive() {
		
		return false;
	}
	
	/**
	 * displays a string of the task and its details 
	 * @return a string of the task 
	 */
	public String toString() {
		
		return null;
	}
	
	/**
	 * adds the TaskList to which the task is added to 
	 * @param taskList is the taskList the task is added to ?
	 */
	public void addTaskList(AbstractTaskList taskList) {
		
	}
	
	/**
	 * accesses the taskList name
	 * @return the name of the TaskList the task is in 
	 */
	public String getTaskListName() {
		
		return null;
	}
	
	/**
	 * marks the task as complete
	 */
	public void completeTask() {
		
	}
	
}
