package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * This generic class represents a Swaplist.
 * It implements the ISwapList Interface. It is responsible 
 * for creating a list that will swap elements and move 
 *  elements to the front and back of the list.
 * @author abhin
 *
 * @param <E> represents the generic variable type the list stores 
 */
public class SwapList<E> {

	/** 10 is the initial capacity of the list    **/
	static final int INITIAL_CAPACITY = 10;
	
	/**  the generic array of the list     **/
	E[] list;
	
	/** the number of elements in the list  **/
	int size;
	
	/**
	 * creates an empty array and sets the size variable to 0
	 */
	public SwapList() {
		
	}
	
	/**
	 * adds the element to the list by putting it in the sorted order 
	 * @param element is the element that needs to be added to the list 
	 */
	public void add(E element) {
		
	}
	
	/**
	 * checks the capacity of the list 
	 * @param capacity is the max number of spaces the list can hodl 
	 */
	@SuppressWarnings("unused")
	private void checkCapacity(int capacity) {
		
	}
	
	/**
	 * removes the space at the index in the list 
	 * @param pos is the index that is to be removed from the list 
	 * @return the value stored in the removed index
	 */
	public int remove(int pos) {
		return 0;
		
	}
	
	/**
	 * checks if there is an element in the index?
	 * @param idx of the list
	 */
	@SuppressWarnings("unused")
	private void checkIndex(int idx) {
		
	}
	
	/**
	 * moves the task at specified index up one position
	 * @param idx of the task that is to be moved 
	 */
	public void moveUp(int idx) {
		
	}
	
	/**
	 * moves the task at specified index down one position
	 * @param idx of the task that is to be moved 
	 */
	public void moveDown(int idx) {
		
	}
	
	/**
	 * moves the task at specified index to the top of the taskList
	 * @param idx of the task that is to be moved 
	 */
	public void moveToFront(int idx) {
	
	}
	
	/**
	 * moves the task at specified index down to the bottom of taskList 
	 * @param idx of the task that is to be moved 
	 */
	public void moveToBack(int idx) {
		
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E get(int idx) {
		return null;
		
	}
	
	/**
	 * stores the size of the list 
	 * @return the size of the list
	 */
	public int size() {
		return 0;
	}
	
}
