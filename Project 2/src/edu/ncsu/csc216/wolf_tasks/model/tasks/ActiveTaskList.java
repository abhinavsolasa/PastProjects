package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * This TaskList is the Active TaskList class. It represents 
 * the taskList that will be stored as the first and initial taskList 
 * in the notebook. All the completed tasks will be moved towards
 * the Active task list. 
 * @author abhin
 *
 */
public class ActiveTaskList extends AbstractTaskList {
	
	/**
	 *  the default name of the Active Task List 
	 */
	static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * constructs the Active TaskList with the specified name 
	 * and number of tasks completed 
	 * @param taskListName is the name of the ActivetaskList
	 * @param completedCount is number of tasks completed
	 */
	public ActiveTaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
		
	}
	
	/**
	 * Adds the Task to the end of the list
	 * @param t s the task to be added 
	 */
	public void addTask(Task t) {
		
	}
	
	/**
	 * sets the current Tasklist name to the new name
	 * @param taskListName is the new name of the Tasklist 
	 */
	public void setTaskListName(String taskListName) {
		
	}
	
	/**
	 * 2d array of the tasks and their corresponding details
	 * @return a 2d array of the tasks in the TaskList 
	 */
	public String [][] getTasksAsArray(){
		
		return null;
	}
	
	/**
	 * clears all the tasks in the taskList 
	 */
	public void clearTasks() {
		
	}
	

}
