package ie.atu.sw;

public class Vertex {
	private boolean visited = false;
	private String name;
	
	public Vertex(String name) {
		super();
		this.name = name;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}