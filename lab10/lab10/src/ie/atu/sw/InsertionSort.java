package ie.atu.sw;

public class InsertionSort implements Sortable{
	public Word[] sort(Word[] words){
		for (int i = 1; i < words.length; i++){
			int j = i;
		    Word nextVal = words[i];
		    while ((j > 0) && (words[j-1].compareTo(nextVal) > 0)){
		    	words[j] = words[j-1];
		    	j--;
		    }
		    words[j] = nextVal;
		}
		return words;
	}
}
