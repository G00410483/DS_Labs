package ie.atu.sw;

import java.util.*;
public class Runner {
	public static void main(String[] args) throws Exception {
		System.out.println("---------- SW2 - DSA Lab 2: Lists & Collections ----------");
		Dictionary dictionary = new Dictionary();
		dictionary.load();
		System.out.println("Dictionary contains " + dictionary.size() +  " words.");
		
		String[] words = dictionary.getSortedWords();
		long startTime = System.nanoTime();
		
		//Start writing the lab exercises after this line:

		//ArrayList<String> list = new ArrayList<String>(); 
		//ArrayList<String> list = new ArrayList<>(); 
		//List<String> list = new ArrayList<>(); 
		//Collection<String> col = new ArrayList<>(); 
		//var list = new ArrayList<String>(); 
		
		//Adding elements
		List<String> list = new ArrayList<>();
			for (int i = 0; i < words.length; i++) {
				list.add(0, words[i]);
			} 
		
		//Removing elements
		Collections.shuffle(list); 
			for (int i = 0; i < words.length; i++) { 
				list.remove(0); 
			}
			
		//Searching for elements
		boolean found = list.contains("Aaron"); 
		System.out.println(found);
		System.out.println(list.indexOf("Aaron"));
		
		System.out.println("Running time (ns): " + (System.nanoTime() - startTime));
	}
}