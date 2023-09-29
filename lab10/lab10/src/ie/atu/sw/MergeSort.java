package ie.atu.sw;

public class MergeSort implements Sortable{
	public Word[] sort(Word[] words) {
		Word[] temp = new Word[words.length];
		mergeSort(words, temp,  0,  words.length - 1);
		return words;
	}

	private void mergeSort(Word[] words, Word[] temp, int left, int right){
		if (left < right){
			int center = (left + right) / 2;
			mergeSort(words, temp, left, center);
			mergeSort(words, temp, center + 1, right);
			merge(words, temp, left, center + 1, right);
		}
	}


    private void merge(Word[] words, Word[] temp, int left, int right, int rightEnd){
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        
        while(left <= leftEnd && right <= rightEnd){
            if(words[left].compareTo(words[right]) <= 0){
                temp[k++] = words[left++];
            }else{
                temp[k++] = words[right++];
            }
        }

        while(left <= leftEnd){    // Copy rest of first half
            temp[k++] = words[left++];
    	}
    
        while(right <= rightEnd){  // Copy rest of right half
            temp[k++] = words[right++];
        }
        
        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--){
            words[rightEnd] = temp[rightEnd];
        }
    }
 }