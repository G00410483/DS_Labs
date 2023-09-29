package ie.atu.sw;

import java.util.ArrayList;
import java.util.List;

public class Stack<E> {
	private List<E> list = new ArrayList<E>();

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean push(E o) {
		return list.add(o);
	}
	
	public E pop(){
		E next = peek();
		list.remove(list.size() - 1);
		return next;
	}

	public E peek(){
		return list.get(list.size() - 1);
	}
}