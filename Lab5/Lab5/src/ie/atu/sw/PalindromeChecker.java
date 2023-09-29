package ie.atu.sw;

public class PalindromeChecker {
	private Stack<Character> stack = new Stack<>();
	private String s;
	
    public PalindromeChecker(String s) {
        this.s = s;
        parse();
    }

    private void parse() {
        for (int i = 0; i < s.length(); i++) {
        	stack.push(s.charAt(i)); //Push each character to the top of the stack
        }
    }

    private String buildReverse() {
        var sb = new StringBuilder(); //We could have just called the reverse() method of StringBuilder!
        while (!stack.isEmpty()) {
            sb.append(stack.pop()); // Remove top item from stack and append it to result.
        }
        return sb.toString();
    }

    public boolean isPalindrome() {
        return s.equalsIgnoreCase(buildReverse());
    }
}