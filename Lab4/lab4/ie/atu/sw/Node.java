package ie.atu.sw;

/*
 * A node acts as a wrapper for one of the elements in the linked list. The
 * class Node is generically typed with an <E> to allow an element of any
 * type to be stored in the list. The element is stored using the instance
 * variable private E data. Each instance of Node has a link to the next item 
 * in the list to form a chain of linked nodes. The links are implemented with 
 * the instance variable private Node<E> next. This creates a single-linked
 * list and allows for iteration and operation in direction only (the forward
 * direction). We can extend this class to implement a doubly-linked list by
 * including an additional instance variable defined as private Node<E> previous
 * and then adding the necessary additional functionality to the class. 
 */
public class Node<E> {
	private E data;
	private Node<E> next;

	public Node(E data) {
		this.data = data;
		next = null;
	}

	public Node(E data, Node<E> node) {
		this.data = data;
		next = node;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}
}