package ie.atu.sw;

import java.util.*;
import java.io.*;
public class Dictionary {
	private List<String> words = new ArrayList<>();
	private static final String DICTIONARY_FILE = "./dictionary.txt";
	
	public void load() throws Exception{
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(DICTIONARY_FILE)))){
			String s;
			while ((s = br.readLine()) != null)   {
				words.add(s);
			}
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the dictionary. " + e.getMessage());
		}
	}
	
	public int size(){
		return words.size();
	}
	
	public String[] getSortedWords(){
		return words.stream().toArray(String[]::new);
	}	
	
	/*
	 * This method is for demonstration purposes only and can be
	 * used to convert the List words into a String[] using a slow
	 * O(n^2) approach or an amortized O(1) method. Normally, you 
	 * should use the stream() method to convert the list into an
	 * array as shown above in getSortedWords().
	 */
	public String[] getSortedWords(boolean slow){
		final int INITIAL_CAPACITY = 8; 
		String[] array = new String[INITIAL_CAPACITY];
		for (int i = 0; i < words.size(); i++){
			if (i == array.length - 1) array = expand(array, slow);
			array[i] = words.get(i);
		}
		return array;
	}

	private String[] expand(String[] tmp, boolean slow){
		int newCapacity = slow ? tmp.length + 1 : (tmp.length * 3) / 2;
		return new String[newCapacity];
	}
}