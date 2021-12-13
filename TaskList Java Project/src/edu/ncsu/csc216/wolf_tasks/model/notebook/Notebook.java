package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * This class represents a Notebook. A notebook has a set of 
 * TaskLists. Each taskList is filled with tasks. This essentially makes
 * up a notebook. A notebook will have a name, but that is about it.
 * It can add, delete, change active TaskLists, and list out the tasks in the 
 * taskList.
 * @author abhin
 *
 */
public class Notebook {
	
	/**
	 * name of the Notebook object 
	 */
	@SuppressWarnings("unused")
	private String notebookName;
	
	/**
	 * checks to see if the notebook is changed. REturns false
	 * otherwise
	 */
	@SuppressWarnings("unused")
	private boolean isChanged;
	
	/**
	 * the selected/displayed taskList on the GUI
	 */
	@SuppressWarnings("unused")
	private ActiveTaskList activeTaskList;
	
	

	/**
	 * represents the taskList that is displayed 
	 */
	@SuppressWarnings("unused")
	private AbstractTaskList currentTaskList;
	
	/**
	 * the sorted list of TaskLists by name
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private ISortedList taskLists;
	/**
	 * creates a new notebook with specified name 
	 * @param name of the notebook 
	 */
	public Notebook(String name) {
		
	}
	
	/**
	 * saves the updated notebook to the specified file
	 * @param notebookFile is the file the notebook will be saved 
	 * to
	 */
	public void saveNotebook(File notebookFile) {
		
	}
	
	/**
	 * accesses the name of the notebook
	 * @return a String of the notebook name
	 */
	public String getNotebookName() {
		return null;
	}
	
	/**
	 * sets the notebookName to a different name
	 * @param name is the name which the notebook name is being chenged 
	 * to 
	 */
	@SuppressWarnings("unused")
	private void setNotebookName(String name) {
		
	}
	
	/**
	 * checks to see if a Notebook has been modified? 
	 * @return true if notebook is modified, false otherwise 
	 */
	public boolean isChanged() {
		
		return false;
	}
	
	/**
	 * sets the value of isChanged variable
	 * @param value is true or false, the value whicht isChanged 
	 * will be set to
	 */
	public void setChanged(boolean value) {
		
	}
	
	/**
	 * adds a taskList to the notebook
	 * @param taskList is the newly created TaskList to be 
	 * added 
	 */
	public void addTaskList(TaskList taskList) {
		
	}
	
	/**
	 * an array of the TaskList names in the notebook in alphabetical order
	 * @return an array of Strings of the taskList names 
	 */
	public String[] getTaskListsNames() {
		
		return null;
	}
	
	/**
	 * Don't quite know what this does 
	 */
	@SuppressWarnings("unused")
	private void getActiveTaskList() {
		
	}
	
	/**
	 * sets the current/displayed TaskList to the list with the specified name
	 * @param taskListName is the name of the taskList to be set as the current
	 * list 
	 */
	public void setCurrentTaskList(String taskListName) {
		
	}
	
	/**
	 * accesses the current TaskList that is displayed
	 * @return the AbstractTaskList object instance
	 */
	public AbstractTaskList getCurrentTaskList() {
		
		return null;
	}
	
	/**
	 * edits the current TaskList which is displayed on GUI
	 * @param taskListName is the name of the Tasklist to edit
	 */
	public void editTaskList(String taskListName) {
		
	}
	
	/**
	 * removes the active taskList
	 */
	public void removeTaskList() {
		
	}
	
	/**
	 * adds a task to the active/current taskList 
	 * @param t is the newly created Task that 
	 */
	public void addTask(Task t) {
		
	}
	
	/**
	 * edits the Task with the specified index with a new specified
	 * taskName and taskDescription
	 * @param idx of the task to be modified
	 * @param taskName is the new name to be changed to 
	 * @param taskDescription is the description to be added to the task
	 * @param recurring is the new recurring status of the task
	 * @param active is the active status of the task
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		
	}

}
