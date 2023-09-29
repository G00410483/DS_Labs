package ie.atu.sw;

public class BubbleSort implements Sortable{
	public Word[] sort(Word[] words){
		 int i, j;
		 Word temp;
		 for (i = (words.length - 1); i >= 0; i--){
			 for (j = 1; j <= i; j++){
				 if (words[j-1].compareTo(words[j]) > 0){
				        temp = words[j-1];
				        words[j-1] = words[j];
				        words[j] = temp;
				 }
			 }
		 }
		 return words;
	}
}
