package ie.atu.sw;

import java.util.Arrays;

public class BracketChecker {
	private char[] open = {'(','{','[', '<'};
	private char[] close = {')','}',']', '>'};
	
    public boolean isBalanced(String expression) {
        var s = new Stack<Character>();
        var balanced = true;
        var index = 0;
        while (balanced && index < expression.length()) {
            char c = expression.charAt(index);
            if (contains(open, c)) {
                s.push(c);
            } else if (contains(close, c)) {
            	balanced = contains(open, s.pop()) ==  contains(close, c);
            }
            index++;
        }
        return (balanced && s.isEmpty());
    }

    private boolean contains(char[] list, char c) {
    	return Arrays.binarySearch(list, c) > -1;
    }
}