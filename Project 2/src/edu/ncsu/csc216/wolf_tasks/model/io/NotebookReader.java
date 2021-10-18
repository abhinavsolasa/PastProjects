package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * This class is responsible for reading in data from a file and creating
 * a Notebook object from it. The class will read in the TaskLists and tasks
 * that are in each TaskList. It has a method for reading in a tasklist, then
 * it uses the processTask method inside of the method to read in every task 
 * in the tasklist 
 * @author abhin
 *
 */
public class NotebookReader {

	/**
	 * the constructor for the Notebook Reader class
	 */
	public NotebookReader() {
		
	}
	
	/**
	 * creates a Notebook object based off contents in the file
	 * @param file is the file where the tasks and tasklists are stored
	 * @return a Notebook object 
	 */
	public static Notebook readNotebookFile(File file) {
		
		return null;
		
	}
	
	/**
	 * returns a Task object based off the input 
	 * @param input of the Task data 
	 * @return a Task object 
	 */
	@SuppressWarnings("unused")
	private TaskList processTaskList(String input){
		
		return null;
	}
	
	/**
	 * returns a Task object based off the input 
	 * @param line of the Task data 
	 * @param list is the TaskList which the task is part of 
	 * @return a Task object 
	 */
	@SuppressWarnings("unused")
	private Task processTask(AbstractTaskList list, String line) {
		
		return null;
	}
	
	
}
