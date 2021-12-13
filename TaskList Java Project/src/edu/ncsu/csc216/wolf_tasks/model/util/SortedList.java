package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * This class represents a sorted List object. A sorted List 
 * is just like an arrayList except the elements inside are stored in 
 * sorted order. The add method is responsible for the sorting. The get, 
 * remove, size and contains method work just like they do in the ArrayList
 * object. The checkIndex method is the only private method in this class
 * 
 * @author abhin
 *
 * @param <E> is the data type that the list stores. 
 */
public class SortedList<E> {

	/**
	 *size of the list
	 */
	@SuppressWarnings("unused")
	private int size;
	
	/**
	 * sets the size of the list to 0, representing an empty list. 
	 */
	public SortedList() {
		
	}
	
	/**
	 * adds the element to the list by putting it in the sorted order 
	 * @param element is the element that needs to be added to the list 
	 */
	public void add(E element) {
		
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
	 * checks to see if an element is in the list 
	 * @param element is the element that is being checked inside list 
	 * @return true if element is inside the list and false otherwise
	 */
	public boolean contains(E element) {
		return false;
		
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
	 * @return the sized of the list
	 */
	public int size() {
		return 0;
	}
	
	
	/**
	 * This class represents a ListNode. A list of ListNodes makes up a 
	 * LinkedList. Each ListNode has data stored. In this case, the data can
	 * be any data type because the class is generic. A listNode also has 
	 * a variable ListNode that stores the space for another ListNode which
	 * is considered the next Listnode that is linked to the current ListNode. 
	 * @author abhin
	 *
	 */
	public class ListNode {
		/** the data stored in the ListNode        **/
		public E data;
		
		/** represents the next linked ListNode  **/
		@SuppressWarnings("unused")
		private ListNode next;
		
		/**
		 * creates a ListNode object with data and the next ListNode 
		 * @param data stored inside LIstNode
		 * @param next is the listNode that will be linked to the current
		 listNode as the next element in a linked list 
		 */
		public ListNode(E data,  ListNode next) {
			
		}

	}
	
	
}
