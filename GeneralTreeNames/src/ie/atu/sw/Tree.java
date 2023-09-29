package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TimeZone;
import static java.lang.System.*;

public class Tree {
	private static final int MAX_DEPTH = 10; //The maximum depth of the tree
	private Node root = new Node('+'); //The root node
	private int nodeCount; 
	private int edgeCount;
	private int treeDepth;
	
	public Tree(){
	}
	
	//Parses the contents of a file, creates a student object from each line and adds the object to the tree
	public void buildTree(String file){ 
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){ //Stuff can go wrong...
			String line = null;
			
			while ((line = br.readLine()) != null){ //Loop over each line in the file
				String[] words = line.split(">>"); //The attributes on each line are delimited by >>
				add(new Student(words[0], words[1], words[2], 
						LocalDate.ofInstant(Instant.ofEpochSecond(Long.parseLong(words[3])), 
								            TimeZone.getDefault().toZoneId()))
				); //Create a new Student record and add to the tree
			}
		} catch (IOException e) { //Handle any error
			out.println("Yikes...! " + e.getMessage());
		}
	} //What is the running time for building the tree?
	
	
	//Add a Student object to the tree
	private boolean add(Student s) {
		Node current = root; //Assign the root node to a temporary variable
		String firstName = s.firstName().toLowerCase(); //Convert all names to LC
		
		//When this loop has finished, the correct node will have either been located or created
		for (int i = 0; i < firstName.length() && i < MAX_DEPTH; i++) {
			char next = firstName.charAt(i); //Get the ith character in the first name
			Node n = current.getChild(next); //Check if the current node has a child with that letter
			if (n == null) { //If the current node does not have a child with that letter...
				n = new Node(current, next); //Create the child node and add it to the current node
			}
			current = n; //Set the value of current to the node we just processed...
		}
		
		//After the loop has finished, the node current is our insertion node
		current.addStudent(s); //Add the Student object to the node current 
		return true;
	} //What is the running time of this method?
	
	
	//This search method returns true if a student with a given name exists in the tree
	public boolean exists(Student s){
		Node current = root; //Get the root node
		String firstName = s.firstName().toLowerCase(); //Convert the search string to LC
		
		//Transverse down a branck in the tree and look for the node containing a student with the given name 
		for (int i = 0; i < firstName.length() && i < MAX_DEPTH; i++){
			char next = firstName.charAt(i); //Get the ith character in the first name
			Node n = current.getChild(next); //Get the child node containing that letter
			if (n == null) return false; //If the node doesn't exist, the first name does not exist in the tree
			current = n; //Move on to the next node
		}
		//If we get this far, then the current node contains a list of students with a matching prefix
		return current.contains(s); //Return true if the current node contains the student
	} //What is the running time of this method?
	
	//Returns all students with a given prefix
	public Collection<Student> getAll(String prefix){
		Collection<Student> results = new ArrayList<Student>(); //A temporary array to store results
		Node current = root; //Start at the root node
		String prefixFirstName = prefix.toLowerCase(); //Convert the prefix to LC
		
		//Find the node, if it exists, whose path is the full prefix and return all students who belong to its descendant nodes
		for (int i = 0; i < prefixFirstName.length() && i < MAX_DEPTH; i++){
			char next = prefixFirstName.charAt(i);
			Node n = current.getChild(next);
			if (n == null){
				break; //Bail out if the full prefix does not exist in the tree
			}else if (i == prefixFirstName.length() -1 ){ //The node matching the full prefix
				stackDFS(n, results);  //Use a DFS to transverse the subtree
			}else{
				results.addAll(n.students()); //Add the students of the current node to the result list
			}
			current = n; //Move to the next node in the tree
		}
		return results; //Return the list of results 
	} //What is the running time of this method?
	
	
	//Perform a DFS at a given node to transverse a sub-tree 
	private void  stackDFS(Node current, Collection<Student> results){
		LinkedList<Node> stack = new LinkedList<Node>(); //Use a LinkedList to act as a stack
		stack.push(current); //Push the starting node onto the stack

		while (!stack.isEmpty()){ //Keep looping until the stack is empty
			current = stack.pop(); //Pop the LIFO node from the topn of the stack
			results.addAll(current.students()); //Add all its student objects to the list of results
			
			Collection<Node> children = current.children(); //Get the child nodes of the current node
			for (Node child: children){ //Loop over the child nodes
				stack.push(child); //Push each child node to the top of the stack (LIFO)
			}
		}
	}//What is the running time of this method?
	
	
	//Print some stats
	public void stats(){
		dfs(root, 0); //Do a recursive DFS to transverse the tree
		
		out.println("--------------------------------------");
		out.println("Nodes: " + nodeCount);
		out.println("Edges: " + edgeCount);
		out.println("Depth: " + treeDepth);
		out.println("Branching Factor: " + (edgeCount/(float)nodeCount));
	}
	
	public void dfs(Node node, int depth) {
		if (depth == 0){
			nodeCount = 0;
			treeDepth = 0;
		}
		
		if (depth > treeDepth) treeDepth = depth;
		
		for (int i = 0; i < depth; i++) System.out.print("\t");
			
		out.println(node);
		nodeCount++;
		
		Collection<Node> col = node.children();
		edgeCount += col.size();
		
		for (Node child : col) {
			dfs(child, depth + 1);//recursive method
		}		
	}
	
	public static void main(String[] args) {
		out.println("\n\n----------------------------------------------------");
		out.println("            First Name Prefix Tree         ");
		out.println("----------------------------------------------------");
		out.println("[INFO] " + (Runtime.getRuntime().maxMemory()/(1024L * 1024L) + "MB of total memory available to JVM"));
		
		var start = System.currentTimeMillis(); //Set the start time
		var tree = new Tree();  //Create a new tree
		tree.buildTree("./students-100000.stuff"); //Build the tree from a file
		
		var stop = ((System.currentTimeMillis() - start)/1000); //Stop the clock
		out.println("[INFO] Tree loaded in " + stop + " seconds.");
		out.println("[INFO] " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024L * 1024L) + "MB of memory used.");
		tree.stats(); //Compute stats
		
		
		//Search the tree for a name
		start = System.currentTimeMillis();
		var result = tree.exists(new Student(null,"Michael", null, null));
		stop = ((System.currentTimeMillis() - start)/1000);
		out.println("[INFO] Tree searched " + stop + " seconds: " + result);
		
		
		//Search the tree for a prefix
		start = System.currentTimeMillis();
		out.println(tree.getAll("Pa"));
		stop = ((System.currentTimeMillis() - start)/1000);
		out.println("[INFO] Tree searched " + stop + " seconds: " + result);
	}
}