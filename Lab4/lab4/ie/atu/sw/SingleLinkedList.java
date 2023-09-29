package ie.atu.sw;

/*
 * The class SingleLinkedList is a container for a set of Node objects that are
 * chained together in one direction only (forward). The class stores the head
 * of the list (the starting element in the chain) as an instance variable and
 * provides a suite of methods for searching, adding, removing and modifying
 * elements. From a practical point of view, these methods require looping
 * over the entire linked list from the starting node before applying an
 * operation. The running time of these methods is therefore O(n), where n
 * is the number of elements in the list (the size of the list). 
 */
public class SingleLinkedList<E> {
	private Node<E> head = null; //The start of the single linked list
	private int size = 0; //The number of elements in the single linked list
	
	/*
	 * A list is an indexed structure. This method searches for and returns
	 * the ith node. The running time for this method is O(n), as the worst
	 * case for the index is the last element in the list. Any other method 
	 * that calls this method will have a time complexity of O(n * other),
	 * where other is the running time of the other method.
	 */
	private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) { //O(n)
            node = node.getNext();
        }
        return node;
    }
	
	//Throw an exception if someone tries to access an index out of bounds
	private boolean checkBounds(int index) { //This method is O(1)
		if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
		return true;
	}
	
	//Searches and returns the element data at the given index 
    public E get(int index) { //O(n)
    	checkBounds(index); //O(1)
        Node<E> node = getNode(index); //Calls getNode() above => O(n)
        return node.getData();
    }
    
    //Modifies the value at an index
    public E set(int index, E value) { //O(n)
    	checkBounds(index); //O(1)
        Node<E> node = getNode(index); //Calls getNode() above => O(n)
        E result = node.getData(); //O(1)
        node.setData(value); //O(1)
        return result;
    }
    
    //Add to the start of the linked list 
	public void addFirst(E item) { //O(1)
	    head = new Node<E>(item, head); //Point to the existing head as next
	    size++;
	}
	
	//This private method is called by the other add methods 
    private void addAfter(Node<E> node, E item) { //O(1)
		Node<E> temp = new Node<E>(item, node.getNext());
		node.setNext(temp);  //Point to the next element
        size++;
    }

	//Add to the ith index in the list
    public void add(int index, E item) { //O(n)
    	checkBounds(index); //O(1)

        if (index == 0) { //No elements in list, so add first
            addFirst(item); //O(1)
        } else {
            Node<E> node = getNode(index - 1); //O(n)
            addAfter(node, item); //O(n)
        }
    }
	
    //Adds an element to the end of the list. 
    public boolean add(E item) { //O(n)
        add(size, item); //O(n)
        return true;
    }
    
	//Adds an element after some other element
    public void addAfter(E current, E next) { //Have to search for current => O(n) 
		Node<E> node = head; //Start at the head of the list
		while (node != null){ //Loop over the list
			if (node.getData().equals(current)){ //*** test for a match with equals() ***
				addAfter(node, next); //O(1)
				return;
			}
			node = node.getNext(); //O(1)
		}
	}
	
    //Removes (pops / polls) the element at the start of the list
	public E removeFirst() { //O(1)
        Node<E> temp = head;
        if (head != null) head = head.getNext(); //O(1)
        
        if (temp != null) {
            size--;
            return temp.getData();
        } else {
            return null;
        }
    }
	
	//Searches for an remove the element after the specified element
	public E removeAfter(E item) { //Have to search for item => O(n) O(n)
		Node<E> node = head;
		while (node != null){ //Start looping from the head of the list
			if (node.getData().equals(item)){ //*** test for a match with equals() ***
				return removeAfter(node);
			}
			node = node.getNext();
		}		
		return null; //Nothing found. This is the worst-case and is O(n)
	}
	
	//Removes the node after the specified node
	private E removeAfter(Node<E> node) { //No looping / traversal => O(1)
        Node<E> temp = node.getNext();
        if (temp != null) {
            node.setNext(temp.getNext());
            size--;
            return temp.getData();
        } else {
            return null;
        }
    }
	
    public int size() { //Returns the number of elements in the list => O(1)
    	return size;
    }
    
    public Iterator<E> iterator() { //Returns an instance of Iterator
        return new Iterator<E>(head);
    }
}