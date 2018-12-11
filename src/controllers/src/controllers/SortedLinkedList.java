package controllers;

import java.util.Comparator;

/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <T>
 */
public class SortedLinkedList<T extends Comparable<T>> {
	
	//SortedLinkedList elements
	public Node head; //Start of list
	public Node tail; //End of list
	int length; //Length of list
	
	/**
	 * Sorted Linked List constructor
	 */
	public SortedLinkedList()
	{
		this.length = 0;
	}
	
	/**
	 * Inner Node Class
	 */
	public class Node
	{
		//Node elements
		private T data; //data of type T to be added to node
		private Node next; //link to next Node in list
		private Node prev; //Link to previous Node in list
		
		//Node Constructor
		public Node(T data, Node next, Node prev)
		{
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		//Node Getters & Setters
		public T getData() { return data; }
		public void setData(T data) { this.data = data; }
		public Node getNext() { return next; }
		public void setNext(Node node) { this.next = node; }
		public Node getPrev() { return prev; }
		public void setPrev(Node node) { this.prev = node; }
	}
	
	/**
	 * Add Node to list
	 * 
	 * @param data
	 */
	public void push(T data)
	{
		//If list is empty create new list and insert Node as head
		if(length == 0)
		{
			head = new Node(data, null, null);
			tail = head;
			length++;
			return;
		} else {
			Node current = head.getNext();
			while (current != null)
			{
				if (data.compareTo(current.getData()) <= 0)
				{
					Node newNode = new Node (data, head, null);
				
					head.setPrev(newNode);
				
					head = newNode;
				
					length ++;
				
					return;
				}
				current = current.getNext();
			}
			
			Node newNode = new Node(data, null, tail);
			tail.setNext(newNode);
			tail = newNode;
			length++;
			return;
				}
	}
	
	/**
	 * Delete Node from list
	 * 
	 * @param data
	 */
	public void remove(T data, Comparator<T> c)
	{
		Node del = find(data, c);
		if (del != null)
		{
			if(del.getPrev() == null)
			{
				del.getNext().setPrev(null);
				head = del.getNext();
			} else
			{
				if(del.getNext() == null)
				{
					del.getPrev().setNext(null);
					tail = del.getPrev();
				} else
				{
					del.getPrev().setNext(del.getNext());
					del.getNext().setPrev(del.getPrev());
				}
			}
			length --;
			}
		}
	/**
	 * Clears Nodes from list
	 * 
	 * @param data
	 */
	public void clear()
	{		
		head = null;
		tail = null;
	}
	
	/**
	 * Search Node's to find matching data input
	 * 
	 * @param data
	 * @return 
	 */
	public Node find(T data, Comparator<T> c)
	{
		//Start a list head
		Node current = head;
		
		//while list is not finished compare input data to Node's data 
		while (current != null)
		{
			//If matching node is found return Node
			if(c.compare(current.data, data) < 0)
				return current;
			
			current = current.getNext();
		}
		return null;
	}
	
	/**
	 * Find size of list
	 * 
	 * @return size of list
	 */
	public int size()
	{
		//Start a list head
		Node current = head;
		length = 0;
		
		//While list is not finished adding to count
		while(current != null){
			current = current.getNext();
			length++;
		}
		return length;
	}
	
	/**
	 * Find first Node in list
	 * 
	 * @return first node in list
	 */
	public T getListHead()
	{
		return head.getData();
	}
	
	/**
	 * Find last Node in list
	 * 
	 * @return last node in list
	 */
	public T getListTail()
	{
		return tail.getData();
	}
	
	/**
	 *Print list ascending
	 *
	 *@param node
	 */	
	private void printListAscend(Node node) {
		System.out.print(node.getData().toString() + "");
		if(node.getNext() != null) {
			printListAscend(node.getNext());
		}
	}
	
 	public void printAscend() {
		printListAscend(head);
	}
	
 	/**
 	 * Print list Descending
 	 * 
 	 * @param node
 	 */
	private void printListDescend(Node node) {
		System.out.print(node.getData().toString() + "");
		if(node.getPrev() != null) {
			printListDescend(node.getPrev());
		}
	}
	
	public void printDescend() {
		printListDescend(tail);
	}
	
	/**
	 * Find the Middle Node in a list
	 * 
	 * @return middle node
	 */
	public Node getMiddle(Node head)
	{
		//if list is empty do nothing
		if(head == null) {
			return head;
		}
		
		Node fast = head.next;
		Node slow = head;
		
		//iterate through list moving fast by two places and slow by one place
		while(fast != null)
		{
			fast = fast.next;
			if(fast != null)
			{
				slow = slow.next;
				fast = fast.next;
			}
			
		}
		
		return slow;
	}
	
	/**
	 * Function to split list into 2
	 * 
	 * @param node
	 * 
	 * @return split lists merged and sorted
	 */
	public Node sort(Node node, Comparator<T> c)
	{
		//if list is empty or only contains one entry do nothing
		if (node == null || node.next == null)
		{
			return node;
		}
		
		//reference for middle node in list
		Node middleHead = getMiddle(node);
		
		//repeat for each new split list
		node = sort(node, c);
		middleHead = sort(middleHead, c);
		
		//merge sorted split lists
		return merge(node, middleHead, c);
	}
	
	/**
	 * Function to sort and merge split lists
	 * 
	 * @param Node one
	 * @param Node two
	 * @return Merged sorted list
	 */
	public Node merge(Node one, Node two, Comparator<T> c)
	{
		//if list one is empty return list two
		if(one == null)
		{
			return two;
		}
		
		//if list two is empty return list one
		if(two == null) {
			return one;
		}
		
		//Compare items and put the smaller item first
		if(c.compare(one.data, two.data) < 0)
		{
			one.next = merge(one.next, two, c);
			one.next.prev = one;
			one.prev = null;
			return one;
		}
		else
		{
			two.next = merge(one, two.next, c);
			two.next.prev = two;
			two.prev = null;
			return two;
		}
	}
	
	public Node listBy(T data, Comparator<T> c)
	{
		//Start a list head
		Node current = head;
		
		//while list is not finished compare input data to Node's data 
		while (current != null)
		{
			//If matching node is found print Node's data .toString()
			if(c.compare(current.data, data) == 0) {
				System.out.print(current.data.toString());
			}
			
			current = current.getNext();
		}
		return null;
	}
	
	/**
	 * Remove duplicates from list post-sorting
	 */
	public void delDupe()
	{
		Node current  = head;
		
		Node next;
		
		//if list is empty do nothing
		if (head == null)
		{
			return;
		}
		
		//Iterate through list till end
		while(current.next != null)
		{
			//check if current node and next node are the same
			if(current.data == current.next.data) 
			{
				next = current.next.next;
				current.next = null;
				current.next = next;
			} 
			//continue iterating if no deletion
			else  
			{
				current = current.next;
			}
		}
	}
}
