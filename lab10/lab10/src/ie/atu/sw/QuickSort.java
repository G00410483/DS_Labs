package ie.atu.sw;

public class QuickSort implements Sortable{
	private Word[] words;
	
	public Word[] sort(Word[] words){
		// Check for empty or null array
		if (words == null || words.length==0){
			return null;
		}
		this.words = words;
		quicksort(0, words.length - 1);		
		return words;
	}
	
	private void quicksort(int low, int high) {
		int upcounter = low, downcounter = high;
		Word pivot = words[low + (high-low)/2]; //Calculate pivot item in middle of array
		
		//Partition the array. Keep going until upcounter > downcounter
		while (upcounter <= downcounter) {
			
			//Upcounter starts at position 1 (low) and works upward, stopping when item >= pivot
			while (words[upcounter].compareTo(pivot) < 0) {
				upcounter++;
			}
			
			//Downcounter works backwards from high, stopping when item <= pivot.
			while (words[downcounter].compareTo(pivot) > 0) {
				downcounter--;
			}
			
			/* If we have found an item in the left list that is greater than the pivot and an item in 
			 * the right list that is less than the pivot, we swap their values.
			 */
			if (upcounter <= downcounter) {
				swap(upcounter, downcounter);
				upcounter++;
				downcounter--;
			}
		}
		
		//Recursively merge each partition.
		if (low < downcounter) quicksort(low, downcounter);
		if (upcounter < high) quicksort(upcounter, high);
	}
	
	private void swap(int i, int j) {
		Word temp = words[i];
		words[i] = words[j];
		words[j] = temp;
	}
}