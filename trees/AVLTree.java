//AVLTree.java : Program for AVL Tree.

class Node
{
	public int info;
	public int balance;
	public Node lchild;
	public Node rchild;

	public Node(int key)
	{
		info = key;
		balance = 0;
		lchild = null;
		rchild = null;
	}
}//End of class Node

class AVLTree
{
	private Node root;
	private boolean taller;
	private boolean shorter;
	
	public AVLTree()
	{
		root = null;
	}//End of AVLTree()

	public boolean isEmpty()
	{
		return root==null;
	}//End of isEmpty()

	public void display()
	{
		display(root, 0);
		System.out.println();
	}//End of display()

	private void display(Node p, int level)
	{
		if(p == null)
			return;

		display(p.rchild, level+1);
		System.out.println();

		for(int i=0; i<level; i++)
			System.out.print("    ");
		System.out.print(p.info);

		display(p.lchild, level+1);
	}//End of display()

	private void inorder(Node p)
	{
		if(p == null)	//Base Case
			return;

		inorder(p.lchild);
		System.out.print(p.info + " ");
		inorder(p.rchild);
	}//End of inorder()
	
	public void inorder()
	{
		inorder(root);
		System.out.println();;
	}//End of inorder()	

	public void insert(int key)
	{
		root = insert(root, key);
	}//End of insert()

	private Node insert(Node p, int key)
	{
		if(p == null)		//Base case
		{
			p = new Node(key);
			taller = true;
		}
		else if(key < p.info)		//Insertion in left subtree
		{
			p.lchild = insert(p.lchild, key);
			if(taller == true)
				p = insertionLeftSubtreeCheck(p);
		}
		else if(key > p.info)		//Insertion in right subtree
		{
			p.rchild = insert(p.rchild, key);
			if(taller == true)
				p = insertionRightSubtreeCheck(p);
		}
		else	//Base case
		{
			System.out.println(key + " is already there");
			taller = false;
		}

		return p;
	}//End of insert()
	
	private Node insertionLeftSubtreeCheck(Node p)
	{
		switch(p.balance)
		{
			case 0:		//Case L_A : was balanced
				p.balance = 1;	//now left heavy
				break;
			case -1:	//Case L_B : was right heavy
				p.balance = 0;	//now balanced
				taller = false;
				break;
			case 1:		//Case L_C : was left heavy
				p = insertionLeftBalance(p);	//left balancing
				taller = false;
				break;
		}

		return p;
	}//End of insertionLeftSubtreeCheck()
	
	private Node insertionLeftBalance(Node p)
	{
		Node a, b;

		a = p.lchild;
		if(a.balance == 1)	//Case L_C1 : Insertion in AL
		{
			p.balance = 0;
			a.balance = 0;
			p = rotateRight(p);	
		}
		else	//Case L_C2 : Insertion in AR
		{
			b = a.rchild;
			switch(b.balance)
			{
				case -1:	//Case L_C2a : Insertion in BR
					p.balance = 0;
					a.balance = 1;
					break;
				case 1:		//Case L_C2b : Insertion in BL
					p.balance = -1;
					a.balance = 0;
					break;
				case 0:		//Case L_C2c : B is the newly inserted node
					p.balance = 0;
					a.balance = 0;
					break;
			}

			b.balance = 0;
			p.lchild = rotateLeft(a);
			p = rotateRight(p);
		}

		return p;
	}//End of insertionLeftBalance()	
	
	private Node insertionRightSubtreeCheck(Node p)
	{
		switch(p.balance)
		{
			case 0:		//Case R_A : was balanced
				p.balance = -1;	//now right heavy
				break;
			case 1:		//Case R_B : was left heavy
				p.balance = 0;		//now balanced
				taller = false;
				break;
			case -1:	//Case R_C : was right heavy
				p = insertionRightBalance(p);	//right balancing
				taller = false;
				break;
		}

		return p;
	}//End of insertionRightSubtreeCheck()	

	private Node insertionRightBalance(Node p)
	{
		Node a, b;

		a = p.rchild;
		if(a.balance == -1)	//Case R_C1 : Insertion in AR
		{
			p.balance = 0;
			a.balance = 0;
			p = rotateLeft(p);
		}
		else	//Case R_C2 : Insertion in AL
		{
			b = a.lchild;
			switch(b.balance)
			{
				case -1:	//Case R_C2a : Insertion in BR
					p.balance = 1;
					a.balance = 0;
					break;
				case 1:		//Case R_C2b : Insertion in BL
					p.balance = 0;
					a.balance = -1;
					break;
				case 0:		//Case R_C2c : B is the newly inserted node
					p.balance = 0;
					a.balance = 0;
					break;
			}
			b.balance = 0;
			p.rchild = rotateRight(a);
			p = rotateLeft(p);
		}

		return p;
	}//End of insertionRightBalance()	
	
	private Node rotateRight(Node p)
	{
		Node a;

		a = p.lchild;			//A is left child of P
		p.lchild = a.rchild;	//Right child of A becomes left child of P	
		a.rchild = p;			//P becomes right child of A
		return a;				//A is the new root of the subtree initially rooted at P
	}//End of rotateRight()

	private Node rotateLeft(Node p)
	{
		Node a;

		a = p.rchild;			//A is right child of P
		p.rchild = a.lchild;	//Left child of A becomes right child of P
		a.lchild = p;			//P becomes left child of A
		return a;				//A is the new root of the subtree initially rooted at P
	}//End of rotateLeft()
	
	public void del(int key)
	{
		root = del(root, key);
	}//End of del()	
	
	private Node del(Node p, int key)
	{
		Node succ;

		if(p == null)		//Base case
		{
			System.out.println(key + " not found");
			shorter = false;
			return p;
		}

		if(key < p.info)	//Delete from left subtree
		{
			p.lchild = del(p.lchild, key);
			if(shorter == true)
				p = deletionLeftSubtreeCheck(p);
		}
		else if(key > p.info)	//Delete from right subtree
		{
			p.rchild = del(p.rchild, key);
			if(shorter == true)
				p = deletionRightSubtreeCheck(p);
		}
		else	//key to be deleted is found, Base case
		{
			
			if(p.lchild!=null && p.rchild!=null)	//2 children
			{
				succ = p.rchild;
				while(succ.lchild != null)
					succ = succ.lchild;
				p.info = succ.info;
				p.rchild = del(p.rchild, succ.info);
				if(shorter == true)
					p = deletionRightSubtreeCheck(p);
			}
			else	//1 child or no child
			{
				if(p.lchild != null)	//Only left child
					p = p.lchild;
				else	//Only right child or no child
					p = p.rchild;
				shorter = true;
			}
		}

		return p;
	}//End of del()	
	
	private Node deletionLeftSubtreeCheck(Node p)
	{
		switch(p.balance)
		{
			case 0:		//Case L_A : was balanced
				p.balance = -1;	//now right heavy
				shorter = false;
				break;
			case 1:		//Case L_B : was left heavy
				p.balance = 0;		//now balanced
				break;
			case -1:	//Case L_C : was right heavy
				p = deletionRightBalance(p);	//right balancing
				break;
		}

		return p;
	}//End of deletionLeftSubtreeCheck()
	
	private Node deletionRightBalance(Node p)
	{
		Node a, b;

		a = p.rchild;
		if(a.balance == 0)		//Case L_C1
		{
			a.balance = 1;
			shorter = false;
			p = rotateLeft(p);
		}
		else if(a.balance == -1)	//Case L_C2
		{
			p.balance = 0;
			a.balance = 0;
			p = rotateLeft(p);
		}
		else	//Case L_C3
		{
			b = a.lchild;
			switch(b.balance)
			{
				case 0:		//Case L_C3a
					p.balance = 0;
					a.balance = 0;
					break;
				case 1:		//Case L_C3b
					p.balance = 0;
					a.balance = -1;
					break;
				case -1:	//Case L_C3c
					p.balance = 1;
					a.balance = 0;
					break;
			}
			b.balance = 0;
			p.rchild = rotateRight(a);
			p = rotateLeft(p);
		}

		return p;
	}//End of deletionRightBalance()	
	
	private Node deletionRightSubtreeCheck(Node p)
	{
		switch(p.balance)
		{
			case 0:		//Case R_A : was balanced
				p.balance = 1;		//now left heavy
				shorter = false;
				break;
			case -1:	//Case R_B : was right heavy
				p.balance = 0;		//now balanced
				break;
			case 1:		//Case R_C : was left heavy
				p = deletionLeftBalance(p);		//left balancing
				break;
		}

		return p;
	}//End of deletionRightSubtreeCheck()	
	
	private Node deletionLeftBalance(Node p)
	{
		Node a, b;

		a = p.lchild;
		if(a.balance == 0)		//Case R_C1
		{
			a.balance = -1;
			shorter = false;
			p = rotateRight(p);
		}
		else if(a.balance == 1)	//Case R_C2
		{
			p.balance = 0;
			a.balance = 0;
			p = rotateRight(p);
		}
		else	//Case R_C3
		{
			b = a.rchild;
			switch(b.balance)
			{
				case 0:		//Case R_C3a
					p.balance = 0;
					a.balance = 0;
					break;
				case 1:		//Case R_C3b
					p.balance = -1;
					a.balance = 0;
					break;
				case -1:	//Case R_C3c
					p.balance = 0;
					a.balance = 1;
					break;
			}
			b.balance = 0;
			p.lchild = rotateLeft(a);
			p = rotateRight(p);
		}

		return p;
	}//End of deletionLeftBalance()	
		
}//End of class AVLTree

class AVLTreeDemo
{
	public static void main(String[] args)
    {
		AVLTree avlTree = new AVLTree();

		avlTree.insert(50);
		System.out.println("Tree after inserting 50");
		avlTree.display();
		System.out.println();

		avlTree.insert(40);
		System.out.println("Tree after inserting 40");
		avlTree.display();
		System.out.println();

		avlTree.insert(35);
		System.out.println("Tree after insertiing 35");
		avlTree.display();
		System.out.println();

		avlTree.insert(58);
		System.out.println("Tree after inserting 58");
		avlTree.display();
		System.out.println();

		avlTree.insert(48);
		System.out.println("Tree after inserting 48");
		avlTree.display();
		System.out.println();

		avlTree.insert(42);
		System.out.println("Tree after inserting 42");
		avlTree.display();
		System.out.println();

		avlTree.insert(60);
		System.out.println("Tree after inserting 60");
		avlTree.display();
		System.out.println();

		avlTree.insert(30);
		System.out.println("Tree after inserting 30");
		avlTree.display();
		System.out.println();

		avlTree.insert(33);
		System.out.println("Tree after inserting 33");
		avlTree.display();
		System.out.println();

		avlTree.insert(25);
		System.out.println("Tree after inserting 25");
		avlTree.display();
		System.out.println();

		avlTree.del(60);
		System.out.println("Tree after deleting 60");
		avlTree.display();
		System.out.println();

		avlTree.del(48);
		System.out.println("Tree after deleting 48");
		avlTree.display();
		System.out.println();

		avlTree.del(25);
		System.out.println("Tree after deleting 25");
		avlTree.display();
		System.out.println();

		avlTree.del(30);
		System.out.println("Tree after deleting 30");
		avlTree.display();
		System.out.println();

		avlTree.del(35);
		System.out.println("Tree after deleting 35");
		avlTree.display();
		System.out.println();

		avlTree.del(33);
		System.out.println("Tree after deleting 33");
		avlTree.display();
		System.out.println();

		avlTree.del(58);
		System.out.println("Tree after deleting 58");
		avlTree.display();
		System.out.println();
		
    }//End of main()
}//End of class AVLTreeDemo
