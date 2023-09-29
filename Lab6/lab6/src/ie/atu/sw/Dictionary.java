package ie.atu.sw;

import java.util.*;
import java.io.*;

public class Dictionary {
	private List<String> words = new ArrayList<>();
	private final String DICTIONARY_FILE = "./dictionary.txt";

	public void load() throws Exception {
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(DICTIONARY_FILE)))) {
			String next;
			while ((next = br.readLine()) != null) {
				words.add(next);
			}
			Collections.shuffle(words); //Randomly re-order the list
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the dictionary. " + e.getMessage());
		}
	}

	public int size() {
		return words.size();
	}

	public String[] getSortedWords() {
		return words.stream().toArray(String[]::new);
	}
}