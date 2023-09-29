package ie.atu.sw;

public class SelectionSort implements Sortable{
	public Word[] sort(Word[] words){
		int i, j, min;
		Word temp;

		for (i = 0; i < words.length-1; i++){
			min = i;
			for (j = i+1; j < words.length; j++) {
				if (words[j].compareTo(words[min]) < 0) min = j;
			}
			temp = words[i];
			words[i] = words[min];
			words[min] = temp;
		}
		return words;
	}
}
