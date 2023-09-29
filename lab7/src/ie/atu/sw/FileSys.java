package ie.atu.sw;

import java.io.File;

public class FileSys {
	public static void main(String[] args) {
		File f = new File("./");
		new FileSys().dfs(f, 0, 3);
	}
	
	private void dfs(File node, int index, int limit){ //A recursive depth first search
		if (index == limit) return;
		
		for (int i = 1; i <= index; i++) System.out.print("\t"); //The parameter index is used to tab each level of the tree
		System.out.println(node.getName()); //Visit the node
		
		File[] children = node.listFiles(); //Get the child nodes
		
		if (children == null) return;
		
		for (int i = 0; i < children.length; i++) {
			File next = children[i]; //Get the next child node
			if (next != null) dfs(next, index + 1, limit); //Recursive method call. Adds method call to the top of the JVM method stack
		}
		
	}
}