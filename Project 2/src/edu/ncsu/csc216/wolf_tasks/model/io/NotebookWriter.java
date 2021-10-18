package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
//import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
//import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * NotebookWriter is responsible for writing the updated Tasklists and tasks
 * in the notebook to a file. It formats it where each TaskList begins
 * with a hashtag, and each task is begun with a hyphen
 * @author abhin
 *
 */
public class NotebookWriter {

	/**
	 * writes the updated TaskLists and tasks in the notebook to a 
	 * specified file 
	 */
	public NotebookWriter() {
		
	}
	
	/**
	 * main method for writing the task records to the specified file using
	 * task's toString method 
	 * @param file is the file where the records will be written to
	 * @param name is the name of the notebook
	 * @param list is the TaskList to be written
	 */
	@SuppressWarnings("rawtypes")
	public static void writeNotebookToFile(File file, String name, ISortedList list) {
		
	}
}
