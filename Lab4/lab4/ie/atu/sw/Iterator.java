package ie.atu.sw;

/*
 * An Iterator is a structural design pattern that allows us to traverse (loop over)
 * a complex structure in a uniform way. Iterators hide the underlying implementation
 * of a data structure from the user. This iterator can be used in exactly the same 
 * way as the implementations of java.util.Iterator:
 * 
 *     Iterator<Integer> i = list.iterator();
 *     while (i.hasNext()){
 *        int e = i.next();
 *        //Do something with e 
 *     }
 * 
 * The A better approach for traversing a collection is to used a for-in / for-each 
 * loop. The for-in loop uses an iterator under the hood but is simplier to declare
 * and use:
 * 
 *   for (Integer e : list){
 *      //Do something with e 
 *   }
 *     
 */
public class Iterator<E> {
    private Node<E> node; //The current node being processed

    public Iterator(Node<E> node) { //The starting node is passed to the constructor
        this.node = node;
    }

    public boolean hasNext() {
        return node != null; //Check if there are any elements left to iterate over
    }

    public E next() {
        if (node == null) { //This condition is only true if the user misused the class
        	/*
        	 * The user of the class should only call this method
        	 * after a call to hasNext() return true.
        	 */
        	throw new java.util.NoSuchElementException(); //Unchecked FATAL exception.
        }
        
        E data = node.getData(); //Get the data from the current node
        node = node.getNext(); //Point to the next node in the linked list
        return data;
    }
}