package ie.atu.sw;

import java.util.*;
public class Node<E> {
	private Node<E> parent;
	private E data;
	private List<Node<E>> children = new ArrayList<>();
	
	public Node(){
	}
	
	public Node(Node<E> parent){
		this.parent = parent;
	}
	
	public Node(E data){
		this.data = data;
	}
	
	public Node(Node<E> parent, E item){
		this(parent);
		this.data = item;
	}
	
	public boolean isRoot(){
		return this.parent == null;
	}
	
	public boolean hasChildren(){
		return this.children.size() > 0;
	}
	
	public void addChild(Node<E> child){
		child.setParent(this);
		children.add(child);
	}
	
	public void removeChild(Node<E> child){
		child.setParent(null);
		children.remove(child);
	}
	
	public Node<E> getParent(){
		return this.parent;
	}
	
	public void setParent(Node<E> parent){
		this.parent = parent;
	}
	
	@SuppressWarnings("unchecked")
	public Node<E>[] children(){
		return (Node<E>[]) children.toArray(new Node[children.size()]);
	}
	
	public void setItem(E item){
		this.data = item;
	}
	
	public E getItem(){
		return this.data;
	}
}