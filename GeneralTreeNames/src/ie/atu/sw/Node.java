package ie.atu.sw;

/* The class Node is an abstraction of a node in a generalised tree. Each node (except the root node)
 * maintains a link (object reference) to its parent node and a collection of child nodes. Nodes are
 * composite and recursive structures - we can keep adding nodes to other nodes to build a tree. Note
 * that the whole point of using a tree structure is to enable the application of the "Divide and Conquer"
 * principle during a search. 
 * 
 * A hierarchy of nodes is of no real use to anyone unless it is capable representing or modeling a real-
 * world problem. In this example, as the reason for using a tree is to allow for an O(log(n)) search
 * by first name, each node in the tree is decorated with a letter and contains satellite data of matching
 * names, stored as a list at each node.
 * 
 *                    -----------------------------------------
 *                    UML Representation of a Composite Pattern
 *                    -----------------------------------------
 *                    
 *                                    ___________                                            
 *                               0..*|           |                                
 *                              ----------       |
 *                              |  Node  |<>------    
 *                              ----------
 * 
 * 						Read: A Node is composed with 0 or many nodes
 * 										       OR
 * 						      A Node HAS-A 0 or many associated nodes
 * 
 */

import java.util.*; //Provides access to the Java Collections Framework

public class Node { //Class Node is a recursive structure the implements the Composite Design Pattern
	private Node parent; //Keep an object reference to our parent. All nodes except the root have exactly one parent
	
	//The following collection is used to store 0..* child nodes. This is the "composite" part of the Composite Pattern
	private Collection<Node> children = new ArrayList<Node>(); //A Node HAS-A collection (0..*) of other Nodes
	
	private char letter = '*'; //Each instance of Node will be assigned a letter. We'll use a (+) for the root
	
	//All first names that have a prefix equivalent to the path from the root to this node will be grouped together in this list
	private List<Student> students = new ArrayList<Student>(); 
	
	//Use the following constructor to add a non-root node to the tree
	public Node(Node parent, char letter){
		this.letter = letter;
		setParent(parent); //Sets the parent of this node and adds this node as a child to the parent
	}

	public Node(char letter){ //Use this constructor to create a root node
		this.letter = letter;
	}
	
	//If we assign a node a new parent we must ensure that we are removed as a child of the previous parent
	public void setParent(Node node) { 
		if (this.getParent() != null) { //If we already have an existing parent. What would happen if we did not do the next step?
			this.getParent().removeChild(this);
		}
		this.parent = node; //Set our new parent
		parent.addChild(this); //Tell our new parent to add us to its list of children
	}
	
	public Node getParent(){ //Returns the parent node
		return this.parent;
	}
	
	public boolean isRoot(){ //Only the root node has no parent
		return this.parent == null;
	}
	
	private void addChild(Node child) { //Add child nodes to the end of out collection of children.
		children.add(child); //What is the running time of this operation?
	}
	
	public void removeChild(Node node) { //Remove a child and break the link with its parent, i.e. us.
		if (children.remove(node)) node.setParent(null); //Why is the IF statement necessary?
		//What is the running time of this method?
	}
	
	public boolean hasChild(Node node){	//Checks of a node is a child of ours. What is the running time?	
		return children.contains(node);
	}
	
	public Collection<Node> children(){ //Returns a collection of our child nodes
		return new ArrayList<Node>(children); //Why return a new ArrayList?
	}
	
	public void addStudent(Student s){ //Adds a student only if its first name prefix is on this branch of the tree
		students.add(s); //What is the running time of this operation?
	}
	
	public Collection<Student> students(){ //Returns a collection of Student objects at this node
		return new ArrayList<Student>(students); //Why return a new ArrayList?
	}
	
	public char getLetter(){ //Returns the letter associated with this node
		return this.letter;
	}
	public void setLetter(char c){ //Sets the latter associated with this node
		this.letter = c;
	}
	
	public Node getChild(char c){ //Returns the child node for a given character or null if we don't have one
		for (Node child : children) { //Loop over the child node
			if (child.getLetter() == c) { //We found a node for that letter
				return child;
			}
		}
		return null; //No node found, return null. What is the running time of this method?
	}
	
	public boolean isLeaf(){ //Leaf nodes have no children
		return children.size() == 0;
	}
	
	public boolean hasSiblings(){ //Return all sibling nodes
		if (!isRoot()){ //The root node cannot have any siblings!
			return parent.children.size() > 1;
		}
		return false;
	}
	
	public Collection<Node> getSiblings(){ //Return our siblings as a collection
		if (hasSiblings()){ //Check if we have any first
			
			//Use a hash set to store a collection of all the child nodes of our parent
			Collection<Node> siblings = new HashSet<Node>(parent.children);
			siblings.remove(this); //Remove ourselves from the list. We can't be our own sibling!
			return siblings;
		}else{
			return new ArrayList<Node>(); //No siblings, so return an empty collection 
		}
		//What is the running time of this method? Pay attention to the collection we are using...
	}
	
	public boolean contains(Student s){ //Check if we are storing a given student at this node in the tree
		return students.contains(s);
	}

	public String toString() { //Convert this Node into a string representation. Useful for debugging and printing the tree
		var sb = new StringBuffer();
		sb.append(this.letter + ": [" + students.size() + "] ");
		
		for (Student s: students){
			sb.append(s + ",");
		}
		return sb.toString();
	}
}