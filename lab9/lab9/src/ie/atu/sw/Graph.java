package ie.atu.sw;

import java.util.*;
public class Graph {
	//Use a hash-map to create an adjacency list implementation of a directed graph
	private Map<Vertex, List<Vertex>> adjacencyList = new HashMap<Vertex, List<Vertex>>();
	
	public boolean addVertex(Vertex vertex){
		if (adjacencyList.containsKey(vertex)){
			return false; //Vertex is already in graph
		}else{
			//Add a new vertex to the adjacency list with its own linked list
			adjacencyList.put(vertex, new LinkedList<Vertex>());
			return true;
		}
	}
	
	public Vertex[] getEdges(Vertex source){
		List<Vertex> edges = adjacencyList.get(source);
		if (edges == null){
			return null;
		}else{
			return edges.toArray(new Vertex[edges.size()]);
		}
	}
	
	public boolean addEdge(Vertex source, Vertex destination){
		//If the vertices are not already in the graph, add them.
		addVertex(source);
		addVertex(destination);
		
		//Get the list of vertices emanating from this source
		List<Vertex> edges = adjacencyList.get(source);
		return edges.add(destination); //Add to the adjacency list of edges
	}
	
	public int size(){
		return adjacencyList.size();
	}
	
	public boolean isEmpty(){
		return adjacencyList.isEmpty();
	}
	
	public int getEdgeCount(){
		int counter = 0;
		Collection<List<Vertex>> edgeLists = adjacencyList.values();
		for (List<Vertex> edges: edgeLists){
			counter += edges.size();
		}
		return counter;
	}
	
	public boolean isEulerian(){
		return false;
	}
}