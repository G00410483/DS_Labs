package ie.atu.sw;

import java.util.*;

public class GraphSearch implements Search{
	private LinkedList<Vertex> queue = new LinkedList<Vertex>();
	private SearchType type;
	private Graph graph;
	
	public GraphSearch(Graph graph, SearchType type){
		this.graph = graph;
		this.type = type;
	}
	
	public void search(Vertex vertex) {
		queue.offer(vertex);
		while (!queue.isEmpty()){
			Vertex next = queue.poll();
			if (!next.isVisited()){
				next.setVisited(true);
				System.out.println(next.getName());
				
				Vertex[] destinations = graph.getEdges(next);
				for (int i = 0; i < destinations.length; i++) {
					if (!destinations[i].isVisited()){
						if (type == SearchType.BREADTH_FIRST){
							queue.offer(destinations[i]);
						}else{
							queue.push(destinations[i]);
						}
					}
				}
			}
		}
	}
}
