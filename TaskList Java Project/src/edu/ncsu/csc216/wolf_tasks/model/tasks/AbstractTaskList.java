package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
//import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * An abstract TaskList represents the concept of a taskList.
 * It is the parent of the Active TaskList class and the normal
 * TaskList class. It has a name and an integer variable that 
 * stores the number of completed tasks. It can add, access,
 * remove, and edit tasks inside itself.
 * @author abhin
 *
 */
public class AbstractTaskList {
	
	/**
	 * name of the taskList 
	 */
	private String taskListName;
	
	/**
	 * number of tasks that are marked completed
	 */
	@SuppressWarnings("unused")
	private int completedCount;

	/**
	 * creates an AbstractTaskList with the specified details 
	 * @param taskListName is the name of the list 
	 * @param completedCount is the number of tasks in the TaskList that 
	 * are marked completed 
	 */
	public AbstractTaskList(String taskListName, int completedCount) {
		
	}
	
	/**
	 * accesses the name of the TaskList 
	 * @return a String of the taskList name
	 */
	public String getTaskListName() {
		
		return taskListName;
	}
	
	/**
	 * sets the current Tasklist name to the new name
	 * @param taskListName is the new name of the Tasklist 
	 */
	public void setTaskListName(String taskListName) {
		
	}
	
	/**
	 * Adds the Task to the end of the list
	 * @param t s the task to be added 
	 */
	public void addTask(Task t){
		
	}
	
	/**
	 * removes and accesses the task of the specified index from the taskList 
	 * @param idx of the task to be removed 
	 * @return the task that was removed 
	 */
	public Task removeTask(int idx) {
		return null;
	}
	
	/**
	 * accesses the number of completed tasks in the TaskList 
	 * @return the number of completed tasks in the TaskList
	 */
	public int getCompletedCount() {
		return 0;
	}
	
	/**
	 *accesses the task at the given index.
	 * @param idx of task to be acessed 
	 * @return returns the task at index
	 */
	public Task getTask(int idx) {
		return null;
	}
	
	/**
	 * accesses the list of tasks in the prioritized order 
	 * @return a SwapList type of list of the tasks
	 */
	public ISwapList<Task> getTasks(){
		
		return null;
	}
	
	/**
	 * marks a task as complete and increments the completedCount
	 * @param t is the task to be marked complete 
	 */
	public void completeTask(Task t) {
		
	}
	
	/**
	 * 2d array of the tasks and their corresponding details
	 * @return a 2d array of the tasks in the TaskList 
	 */
	public String [][] getTasksAsArray(){
		return null;
	}
}
