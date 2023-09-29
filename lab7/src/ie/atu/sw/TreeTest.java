package ie.atu.sw;

public class TreeTest {
	private Stack<Node<Item>> stack = new Stack<>(); //Our custom stack for stackDFS()
	
	public TreeTest() {
		Node<Item> root = new Node<>(); //Root is the base of the tree
		init(root); //Build the tree by adding nodes
		dfs(root, 0); //Call recursive DFS. Uses JVM method stack
		stackDFS(root); //Call non-recursive DFS. Uses our own stack on JVM heap
	}
	
	private void dfs(Node<Item> node, int index){ //A recursive depth first search
		for (int i = 1; i <= index; i++) System.out.print("\t"); //The parameter index is used to tab each level of the tree
		System.out.println(node.getItem().process()); //Process the node
		
		Node<Item>[] children = node.children(); //Get the child nodes
		for (int i = 0; i < children.length; i++) {
			Node<Item> next = children[i]; //Get the next child node
			dfs(next, index + 1); //Recursive method call. Adds method call to the top of the JVM method stack
		}
		
	}
	
	public void stackDFS(Node<Item> root){ //This DFS uses a custom stack
		stack.push(root); //Push root of tree to the top of the stack
		while (!stack.isEmpty()){ //Keep processing until the stack is empty
			Node<Item> node = stack.pop(); //Get and remove from the top of the stack
			System.out.println(node.getItem().process()); //Process the node
			
			Node<Item>[] children = node.children(); //Get the child nodes
			for (int i = 0; i < children.length; i++) { //Loop over the child nodes
				stack.push(children[i]); //Push each child node to the top of the stack
			}
		}
	}
	
	private void init(Node<Item> root){
		/* 
		 * Create a tree of persons objects based on the family tree of William the Conqueror. A few points: 
		 * 	1) Tree constructions is normally created dynamically from a data set, not by manually constructing nodes
		 *     in the manner shown below.
		 *  2) Along with adding a child node, the method addChild(Node n) also sets the parent of node n.
		 *  3) While the Normans were a formidable military force, they certainly had some shortcomings:
		 *     creativity in selecting first names and short life spans!    
		 * 
		 */
		
		root.setItem(new Person("William I")); //The Conqueror. Defeated Harold at Hastings in 1066
		
		Node<Item> robert = new Node<>(new Person("Robert")); //Duke of Normandy, eldest son of William I
		robert.addChild(new Node<>(new Person("William"))); //Unlucky in life. Killed in his 20s.
		root.addChild(robert);
		
		root.addChild(new Node<>(new Person("William II"))); //William Rufus. King of England. Killed by an arrow...
		
		
		Node<Item> adela = new Node<>(new Person("Adela")); //Daughter of William I.
		adela.addChild(new Node<>(new Person("Stephen"))); //King of England. Reign was marked by civil war with cousin Matilda.
		root.addChild(adela);
		
		Node<Item> henryI = new Node<>(root, new Person("Henry I")); //King of England
		root.addChild(henryI);
		henryI.addChild(new Node<>(new Person("William"))); //Dead at 17
		Node<Item> matilda = new Node<>(new Person("Matilda")); //Matilda of England/Maude. Tough lady. Civil war against Stephen. 
		henryI.addChild(matilda);
		
		Node<Item> henryII = new Node<>(root, new Person("Henry II")); //Henry Plantagenet. This is the guy who launched the Norman invasion of Ireland in 1169
		matilda.addChild(henryII);
		
		henryII.addChild(new Node<>(new Person("Henry"))); //Dead at 28
		henryII.addChild(new Node<>(new Person("Richard I"))); //Coeur de Lion. Richard the Lionheart
		henryII.addChild(new Node<>(new Person("Geoffrey"))); //Also claimed by the grim reaper at 28 
		henryII.addChild(new Node<>(new Person("John"))); //King of England. Aka John Lackland/Softsword. Spent some time here in Ireland. King John's Castles...
	}
	
	public static void main(String[] args) {
		new TreeTest(); //Lazy way of instantiating a TreeTest object
	}

}
