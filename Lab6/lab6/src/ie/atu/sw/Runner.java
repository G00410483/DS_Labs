package ie.atu.sw;

import java.util.*;
import java.text.DecimalFormat;

public class Runner {
	public static void main(String[] args) throws Exception {
		var formatter = new DecimalFormat("#,###.00"); //Formats the time output
		var d = new Dictionary();
		d.load();
		
		var words = d.getSortedWords();
		var start = System.currentTimeMillis(); //Start the clock
		
		//Start adding code here:
		Queue<String> queue = new PriorityQueue<>(Comparator.comparing(String::length));
		for(String s : words) {
			queue.offer(s);
		}
		System.out.println(queue.size());
		
		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
		
		//Output the formatted time
		System.out.println("Time (ms):  " + formatter.format((System.currentTimeMillis() - start)));
	}	
}