package ie.atu.sw;

public class Runner {
	public static void go(int level) {
		System.out.println(level);
		level++;
		go(level);
	}

	public static void main(String[] args) {
		go(0);
		var s = new Stack<Integer>();
		for(int i = 0; i < 100; i++) {
			s.push(i);
		}
		System.out.println(s);
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}

	}

}
