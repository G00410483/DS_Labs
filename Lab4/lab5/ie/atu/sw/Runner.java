package ie.atu.sw;

import java.util.*;
import java.util.concurrent.*;

public class Runner {
	public void go() throws Exception {
		//Declare the list and list methods here
		
		
		
	}
	
	
	//Returns a random element from the list 
	public Ninja getRandom(List<Ninja> ninjas) {
		return ninjas.get(ThreadLocalRandom.current()
				                           .nextInt(0, ninjas.size()));
	}
	
	//Start the ball running
	public static void main(String[] args) throws Exception {
		new Runner().go();
	}
}