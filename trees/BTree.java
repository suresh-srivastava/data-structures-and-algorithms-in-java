//BTree.java : Program for B tree.

class Node
{
	public int[] key;
	public Node[] child;
	public int numKeys;
	public Node(int size)
	{
		key = new int[size+1];
		child = new Node[size+1];
		numKeys = 0;
	}
}//End of class Node

class NodeHolder 
{
	public Node value;
	public NodeHolder(Node x)
	{
		value = x;
	}
}//End of class NodeHolder

class IntHolder 
{
   public int value;
   public IntHolder(int n) 
   {
        value = n;
   }
}//End of class IntHolder

class BTree
{
	private static final int M = 5; //Order of B tree
	private static final int MAX = M-1; //Maximum number of permissible keys in a node
	private static final int MIN = (M%2==0)?((M/2)-1):(((M+1)/2)-1); //Minimum number of permissible keys in a node except root
	
	private Node root;		
		
	public BTree()
	{
		root = null;
	}//End of BTree()		

	public void insert(int key)
	{
		IntHolder iKey = new IntHolder(0);
        NodeHolder iKeyRchild = new NodeHolder(null);

		boolean taller = insert(key, root, iKey, iKeyRchild);

		if(taller)	//tree grown in height, new root is created
		{
			Node temp = new Node(MAX);
			temp.child[0] = root;
			temp.key[1] = iKey.value;
			temp.child[1] = iKeyRchild.value;
			temp.numKeys = 1;
			root = temp;
		}
	}//End of insert()	
	
	private boolean insert(int key, Node p, IntHolder iKey, NodeHolder iKeyRchild)
	{
		if(p == null)	//First Base case : key not found
		{
			iKey.value = key;
			iKeyRchild.value = null;
			return true;
		}

		IntHolder n = new IntHolder(0);
		if(searchNode(key, p, n) == true)	//Second Base case : key found
		{
			System.out.println("Key already present in the tree");
			return false;	//No need to insert the key
		}

		boolean flag = insert(key, p.child[n.value], iKey, iKeyRchild);

		if (flag == true)
		{
			if(p.numKeys < MAX)
			{
				insertByShift(p, n.value, iKey.value, iKeyRchild.value);
				return false;	//Insertion over
			}
			else
			{
				split(p, n.value, iKey, iKeyRchild);
				return true;	//Insertion not over : Median key yet to be inserted
			}
		}

		return false;
	}//End of insert()	
	
	public boolean search(int key)
	{
		if(search(key,root) == null)
			return false;
		return true;
	}//End of search()	
	
	private Node search(int key, Node p)
	{
		if(p == null)
			return null; //Base case 1 : key is not present in the tree

		IntHolder n = new IntHolder(0);
		if(searchNode(key, p, n) == true) //Base case 2 : key is found in node p
			return p;

		return search(key, p.child[n.value]); //Recursive case : Search in node p.child[n]
	}//End of search()
	
	private boolean searchNode(int key, Node p, IntHolder n)
	{
		if(key < p.key[1])		//key is less than leftmost key
		{
			n.value = 0;
			return false;
		}

		n.value = p.numKeys;
		while(key<p.key[n.value] && n.value>1)
			n.value--;

		if(key == p.key[n.value])
			return true;
		else
			return false;

	}//End of searchNode()
	
	private void insertByShift(Node p, int n, int iKey, Node iKeyRchild)
	{
		for(int i=p.numKeys; i>n; i--)
		{
			p.key[i+1] = p.key[i];
			p.child[i+1] = p.child[i];
		}
		p.key[n+1] = iKey;
		p.child[n+1] = iKeyRchild;
		p.numKeys++;
	}//End of insertByShift()	
	
	private void split(Node p, int n, IntHolder iKey, NodeHolder iKeyRchild)
	{
		int i, j;
		int lastKey;
		Node lastChild;

		if(n == MAX)
		{
			lastKey = iKey.value;
			lastChild = iKeyRchild.value;
		}
		else
		{
			lastKey = p.key[MAX];
			lastChild = p.child[MAX];

			for(i=p.numKeys-1; i>n; i--)
			{
				p.key[i+1] = p.key[i];
				p.child[i+1] = p.child[i];
			}
			p.key[i+1] = iKey.value;
			p.child[i+1] = iKeyRchild.value;
		}

		int d = (M+1)/2;
		int medianKey = p.key[d];
		Node newNode = new Node(MAX);

		for(i=1,j=d+1; j<=MAX; i++,j++)
		{
			newNode.key[i] = p.key[j];
			newNode.child[i] = p.child[j];
		}
		newNode.key[i] = lastKey;
		newNode.child[i] = lastChild;
		newNode.numKeys = M-d;	//Number of keys in the right splitted node

		newNode.child[0] = p.child[d];
		p.numKeys = d-1;	//Number of keys in the left splitted node

		iKey.value = medianKey;
		iKeyRchild.value = newNode;
	}//End of split()
	
	public void del(int key)
	{
		if(root != null)
		{
			del(key, root);

			//If tree becomes shorter, root is changed
			if(root!=null && root.numKeys==0)
			{
				root = root.child[0];
			}
		}
		else
			System.out.println("Tree is empty");
	}//End of del()	
	
	private void del(int key, Node p)
	{
		IntHolder n = new IntHolder(0);

		if(p == null)	//reached leaf node, key does not exist
		{
			System.out.println("Key " + key + "not found");
		}
		else
		{
			if(searchNode(key, p, n))	//If found in current node p
			{
				if(p.child[n.value] == null)	//Node p is a leaf node
				{
					delByShift(p, n.value);
				}
				else	//Node p is a non leaf node
				{
					Node succ = p.child[n.value]; //refers to the right subtree
					while(succ.child[0] != null) //move down till leaf node arrives
						succ = succ.child[0];
					p.key[n.value] = succ.key[1];
					del(succ.key[1], p.child[n.value]);
				}
			}
			else	//Not found in current node p
			{
				del(key, p.child[n.value]);
			}

			if(p.child[n.value] != null)	//If p is not a leaf node
			{
				if(p.child[n.value].numKeys < MIN)	//Check underflow in p->child[n]
					restore(p, n.value);
			}
		}
	}//End of del()	
	
	private void delByShift(Node p, int n)
	{
		for(int i=n+1; i<=p.numKeys; i++)
		{
			p.key[i-1] = p.key[i];
			p.child[i-1] = p.child[i];
		}
		p.numKeys--;
	}//End of delByShift()	
	
	private void restore(Node p, int n)
	{
		if(n!=0 && p.child[n-1].numKeys > MIN)
			borrowLeft(p, n);
		else if(n!=p.numKeys && p.child[n+1].numKeys > MIN)
			borrowRight(p, n);
		else
		{
			if(n==0)	//If underflow node is leftmost node
				combine(p, n+1);	//combine nth child of p with its right sibling
			else
				combine(p, n);		//combine nth child of p with its left sibling
		}
	}//End of restore()	
	
	private void borrowLeft(Node p, int n)
	{
		Node u = p.child[n];		//underflow node
		Node ls = p.child[n-1];	//left sibling of node u

		//Shift all the keys and children in underflow node u one position right
		for(int i=u.numKeys; i>0; i--)
		{
			u.key[i+1] = u.key[i];
			u.child[i+1] = u.child[i];
		}
		u.child[1] = u.child[0];

		//Move the separator key from parent node p to underflow node u
		u.key[1] = p.key[n];
		
		u.numKeys++;

		//Move the rightmost key of node ls to the parent node p
		p.key[n] = ls.key[ls.numKeys];
		
		//Rightmost child of ls becomes leftmost child of node u
		u.child[0] = ls.child[ls.numKeys];
		
		ls.numKeys--;
	}//End of borrowLeft()
	
	private void borrowRight(Node p, int n)
	{
		Node u = p.child[n];		//underflow node
		Node rs = p.child[n+1];	//right sibling of node u

		//Move the separator key from the parent node p to the underflow node u
		u.numKeys++;
		u.key[u.numKeys] = p.key[n+1];

		//Leftmost child of node rs becomes the rightmost child of node u
		u.child[u.numKeys] = rs.child[0];

		//Move the leftmost key from node rs to parent node p
		p.key[n+1] = rs.key[1];
		rs.numKeys--;

		//Shift all the keys and children of node rs one position left
		rs.child[0] = rs.child[1];
		for(int i=1; i<=rs.numKeys; i++)
		{
			rs.key[i] = rs.key[i+1];
			rs.child[i] = rs.child[i+1];
		}
	}//End of borrowRight()
	
	private void combine(Node p, int m)
	{
		Node x = p.child[m];
		Node y = p.child[m-1];

		//Move the separator key from parent node p to node y
		y.numKeys++;
		y.key[y.numKeys] = p.key[m];

		//Shift all the keys and children in node p one position left to fill the gap
		for(int i=m; i<p.numKeys; i++)
		{
			p.key[i] = p.key[i+1];
			p.child[i] = p.child[i+1];
		}
		p.numKeys--;

		//Leftmost child of x becomes rightmost child of y
		y.child[y.numKeys] = x.child[0];

		//Insert all the keys and children of node x at the end of node y
		for(int i=1; i<=x.numKeys; i++)
		{
			y.numKeys++;
			y.key[y.numKeys] = x.key[i];
			y.child[y.numKeys] = x.child[i];
		}

	}//End of combine()
	
	public void inorder()
	{
		inorder(root);
	}//End of inorder()	
	
	private void inorder(Node p)
	{
		if(p != null)
		{
			int i;
			for(i=0; i<p.numKeys; i++)
			{
				inorder(p.child[i]);
				System.out.print(p.key[i+1] + " ");
			}
			inorder(p.child[i]);
		}
	}//End of inorder()	
	
	public void display()
	{
		display(root, 0);
	}//End of display()	
	
	private void display(Node p, int spaces)
	{
		if(p != null)
		{
			int i;
			for(i=1; i<=spaces; i++)
				System.out.print(" ");

			for(i=1; i<=p.numKeys; i++)
				System.out.print(p.key[i] + " ");
			System.out.println();

			for(i=0; i<=p.numKeys; i++)
				display(p.child[i], spaces+10);
		}
	}//End of display()
		
}//End of class BTree

class BTreeDemo
{
	public static void main(String[] args)
	{
		BTree btree = new BTree();

		System.out.println("Tree after inserting 10");
		btree.insert(10);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 40");
		btree.insert(40);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 30");
		btree.insert(30);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 35");
		btree.insert(35);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 20");
		btree.insert(20);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 15");
		btree.insert(15);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 50");
		btree.insert(50);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 28");
		btree.insert(28);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 25");
		btree.insert(25);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 5");
		btree.insert(5);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 60");
		btree.insert(60);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 19");
		btree.insert(19);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 12");
		btree.insert(12);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 38");
		btree.insert(38);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 27");
		btree.insert(27);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 90");
		btree.insert(90);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 45");
		btree.insert(45);
		btree.display();
		System.out.println();

		System.out.println("Tree after inserting 48");
		btree.insert(48);
		btree.display();
		System.out.println();

		System.out.println("Tree after deleting 28");
		btree.del(28);
		btree.display();
		System.out.println();

		System.out.println("Tree after deleting 40");
		btree.del(40);
		btree.display();
		System.out.println();

		System.out.println("Tree after deleting 15");
		btree.del(15);
		btree.display();
		System.out.println();

		//Search in B-tree
		System.out.print("search(48) : " + (btree.search(48) ? "True" : "False"));
		System.out.println();

		System.out.print("search(15) : " + (btree.search(15) ? "True" : "False"));
		System.out.println();	
		
	}//End of main()
}//End of class BTreeDemo
