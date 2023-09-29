package ie.atu.sw;

import java.awt.List;
import java.time.LocalDate;
import java.util.LinkedList;

public class Runner {

	public static void main(String[] args) {
		var list = new LinkedList<String>();
		list.add("Galway");
		list.add("Sligo");
		list.add("Mayo");
		list.add(2, "Donegal");
		
		java.util.Iterator<String> i = list.iterator();
		while(i.hasNext()){
		 System.out.println(i.next());
		}
		for(String s: list) {
			System.out.println(s);
			
		}
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		
		List col = new LinkedList();
		col.add("Oisin");
		cold.add(new Object());
		col.add(LocalDate.now());
		
		for(Object o : col) {
			
		}
	}

}
