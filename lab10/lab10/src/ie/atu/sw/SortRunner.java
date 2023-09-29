package ie.atu.sw;

public class SortRunner {
	public static void main(String[] args) throws Exception{
		var dictionary = new Dictionary(); //Instantiate the dictionary
		dictionary.load(); //Load the dictionary into memory
		
		Word[] shuffled = dictionary.getShuffledWords(); 
		//Sortable s = new SelectionSort();
		//Sortable s = new BubbleSort();
		//Sortable s = new InsertionSort();
		//Sortable s = new MergeSort();
		Sortable s = new QuickSort();
		
		long start = System.currentTimeMillis();
		Word[] sorted = s.sort(shuffled);
		System.out.println("Time (ns): " + (System.currentTimeMillis() - start));
		
		for (int i = 0; i < sorted.length; i++) {
			Word word = sorted[i];
			System.out.println(word);
		}
	}
}
