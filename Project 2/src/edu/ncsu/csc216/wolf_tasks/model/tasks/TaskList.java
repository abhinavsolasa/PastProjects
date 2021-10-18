package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * This class represents a normal TaskList which is a part of the 
 * notebook. It is a child class of an AbstractTaskList. The only
 * differences are the display of 2d array display of the tasks and
 * the compareTo method for comparing the names of taskLists to be able
 * to sort them
 * @author abhin
 *
 */
public class TaskList extends AbstractTaskList {

	/**
	 * generates a TaskList Object with specified name and 
	  # of tasks that are marked completed
	 * @param taskListName is the name of the TaskList 
	 * @param completedCount is the number of tasks that are marked 
	 * complete 
	 */
	public TaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * represents a 2d String array which is a "database" for the 
	 * tasks in the taskList 
	 * @return a 2d array of each task and their corresponding
	 data 
	 */
	public String [][] getTasksAsArray(){
		
		return null;
	}
	
	/**
	 * this is used to compare the current TaskList to another 
	 TaskList based off of the name for sorting in alphabetic order
	 * @param list is the TaskList that current TaskList is being compared to 
	 * @return -1 if current TaskList's name is before parameter in alphabetic order 
	 * and returns 1 if current name is after parameter Tasklist's name in alphabetic order
	 */
	public int compareTo(TaskList list) {
		
		return 0;
	}

}
