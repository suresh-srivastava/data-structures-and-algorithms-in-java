//RedBlackTree.java : Program for Red Black Tree.

class Node
{
	public enum Color {BLACK, RED} ;
	public Color color;
	public int info;
	public Node lchild;
	public Node rchild;
	Node parent;

	public Node(int key)
	{
		info = key;
		lchild = null;
		rchild = null;
		parent = null;
	}
}//End of class Node

class RedBlackTree
{
	private Node root;
	private Node sentinel;	//will be parent of root and replace null

	public RedBlackTree()
	{
		sentinel = new Node(-1);
		sentinel.color = Node.Color.BLACK;
		root = sentinel;
	}//End of RedBlackTree()	
	
	public void insert(int key)
	{
		Node parent = sentinel;
		Node p = root;

		while(p != sentinel)
		{
			parent = p;
			if(key < p.info)
				p = p.lchild;
			else if(key > p.info)
				p = p.rchild;
			else
			{
				System.out.println(key + " is already there");
				return;
			}
		}

		Node temp = new Node(key);
		temp.lchild = sentinel;
		temp.rchild = sentinel;
		temp.color = Node.Color.RED;
		temp.parent = parent;

		if(parent == sentinel)
			root = temp;
		else if(temp.info < parent.info)
			parent.lchild = temp;
		else
			parent.rchild = temp;

		insertionBalance(temp);

	}//End of insert()	
	
	private void insertionBalance(Node n)
	{
		Node uncle, parent, grandParent;

		while(n.parent.color == Node.Color.RED)
		{
			parent = n.parent;
			grandParent = parent.parent;

			if(parent == grandParent.lchild)
			{
				uncle = grandParent.rchild;
				if(uncle.color == Node.Color.RED)	//Case L_1
				{
					parent.color = Node.Color.BLACK;
					uncle.color = Node.Color.BLACK;
					grandParent.color = Node.Color.RED;
					n = grandParent;
				}
				else	//uncle is black
				{
					if(n == parent.rchild)		//Case L_2a
					{
						rotateLeft(parent);
						n = parent;
						parent = n.parent;
					}
					parent.color = Node.Color.BLACK;	//Case L_2b
					grandParent.color = Node.Color.RED;
					rotateRight(grandParent);
				}
			}//End of if
			else
			{
				if(parent == grandParent.rchild)
				{
					uncle = grandParent.lchild;
					if(uncle.color == Node.Color.RED)	//Case R_1
					{
						parent.color = Node.Color.BLACK;
						uncle.color = Node.Color.BLACK;
						grandParent.color = Node.Color.RED;
						n = grandParent;
					}
					else	//uncle is black
					{
						if(n == parent.lchild)	//Case R_2a
						{
							rotateRight(parent);
							n = parent;
							parent = n.parent;
						}
						parent.color = Node.Color.BLACK;	//Case R_2b
						grandParent.color = Node.Color.RED;
						rotateLeft(grandParent);
					}

				}//End of if

			}//End of else

		}//End of while

		root.color = Node.Color.BLACK;

	}//End of insertionBalance()	

	private Node getSuccessor(Node location)
	{
		Node p = location.rchild;
		while(p.lchild != sentinel)
			p = p.lchild;
		
		return p;
	}//End of getSuccessor()	

	Node find(int key)
	{
		Node location;
		Node p;

		if(root == sentinel)	//Tree is empty
		{
			location = sentinel;
			return null;
		}

		if(key == root.info)	//key is at root
		{
			location = root;
			return location;
		}

		//Initialize p
		if(key < root.info)
			p = root.lchild;
		else
			p = root.rchild;

		while(p != sentinel)
		{
			if(key == p.info)
			{
				location = p;				
				return location;
			}

			if(key < p.info)
				p = p.lchild;
			else
				p = p.rchild;
		}//End of while

		location = sentinel;	//key not found
		return null;
	}//End of find()		
	
	
	public void del(int key)
	{	
		Node p = find(key);

		if(p==null)
		{
			System.out.println("Key not present");
			return;
		}
	
		if(p.lchild!=sentinel && p.rchild!=sentinel)
		{
			Node succ = getSuccessor(p);
			p.info = succ.info;
			p = succ;
		}

		Node child;

		if(p.lchild != sentinel)
			child = p.lchild;
		else
			child = p.rchild;

		child.parent = p.parent;

		if(p.parent == sentinel)
			root = child;
		else if(p == p.parent.lchild)
			p.parent.lchild = child;
		else
			p.parent.rchild = child;

		if(child == root)
			child.color = Node.Color.BLACK;
		else if(p.color == Node.Color.BLACK)	//black node
		{
			if(child != sentinel)	//one child which is red
				child.color = Node.Color.BLACK;
			else	//no child
				deletionBalance(child);
		}

	}//End of del()
	
	private void deletionBalance(Node n)
	{
		Node sib;

		while(n != root)
		{
			if(n == n.parent.lchild)
			{
				sib = n.parent.rchild;

				if(sib.color == Node.Color.RED)		//Case L_1
				{
					sib.color = Node.Color.BLACK;
					n.parent.color = Node.Color.RED;
					rotateLeft(n.parent);
					sib = n.parent.rchild;	//new sibling
				}

				if(sib.lchild.color==Node.Color.BLACK && sib.rchild.color==Node.Color.BLACK)
				{
					sib.color = Node.Color.RED;
					if(n.parent.color == Node.Color.RED)	//Case L_2a
					{
						n.parent.color = Node.Color.BLACK;
						return;
					}
					else
						n = n.parent;	//Case L_2b
				}
				else
				{
					if(sib.rchild.color == Node.Color.BLACK)	//Case L_3a
					{
						sib.lchild.color = Node.Color.BLACK;
						sib.color = Node.Color.RED;
						rotateRight(sib);
						sib = n.parent.rchild;
					}
					sib.color = n.parent.color;	//Case L_3b
					n.parent.color = Node.Color.BLACK;
					sib.rchild.color = Node.Color.BLACK;
					rotateLeft(n.parent);
					return;
				}

			}//End of if
			else
			{
				sib = n.parent.lchild;
				if(sib.color == Node.Color.RED)		//Case R_1
				{
					sib.color = Node.Color.BLACK;
					n.parent.color = Node.Color.RED;
					rotateRight(n.parent);
					sib = n.parent.lchild;
				}

				if(sib.rchild.color==Node.Color.BLACK && sib.lchild.color==Node.Color.BLACK)
				{
					sib.color = Node.Color.RED;
					if(n.parent.color == Node.Color.RED)	//Case R_2a
					{
						n.parent.color = Node.Color.BLACK;
						return;
					}
					else
						n = n.parent;	//Case R_2b
				}
				else
				{
					if(sib.lchild.color == Node.Color.BLACK)	//Case R_3a
					{
						sib.rchild.color = Node.Color.BLACK;
						sib.color = Node.Color.RED;
						rotateLeft(sib);
						sib = n.parent.lchild;
					}
					
					sib.color = n.parent.color;	//Case R_3b
					n.parent.color = Node.Color.BLACK;
					sib.lchild.color = Node.Color.BLACK;
					rotateRight(n.parent);
					return;
				}

			}//End of else

		}//End of while

	}//End of deletionBalance()	
	
	private void rotateRight(Node p)
	{
		Node a;

		a = p.lchild;			//A is left child of P
		p.lchild = a.rchild;	//Right child of A becomes left child of P

		if(a.rchild != sentinel)
			a.rchild.parent = p;
		a.parent = p.parent;

		if(p.parent == sentinel)
			root = a;
		else if(p == p.parent.rchild)
			p.parent.rchild = a;
		else
			p.parent.lchild = a;

		a.rchild = p;			//P becomes right child of A
		p.parent = a;
	}//End of rotateRight()

	private void rotateLeft(Node p)
	{
		Node a;

		a = p.rchild;			//A is right child of P
		p.rchild = a.lchild;	//Left child of A becomes right child of P

		if(a.lchild != sentinel)
			a.lchild.parent = p;
		a.parent = p.parent;

		if(p.parent == sentinel)
			root = a;
		else if(p == p.parent.lchild)
			p.parent.lchild = a;
		else
			p.parent.rchild = a;

		a.lchild = p;			//P becomes left child of A
		p.parent = a;
		
	}//End of rotateLeft()

	private void inorder(Node p)
	{
		if(p != sentinel)
		{
			inorder(p.lchild);
			System.out.println(p.info);
			inorder(p.rchild);
		}
	}//End of inorder()

	public void inorder()
	{
		inorder(root);
	}//End of inorder()

	private void display(Node p, int level)
	{
		if(p != sentinel)
		{
			display(p.rchild, level+1);
			System.out.println();
			
			for(int i=0; i<level; i++)
				System.out.print("    ");
			System.out.print(p.info);
			if(p.color == Node.Color.RED)
				System.out.print("^");
			else
				System.out.print("*");
			
			display(p.lchild, level+1);
		}
	}//End of display()

	public void display()
	{
		display(root, 1);
	}//End of display()	

}//End of class RedBlackTree

class RedBlackTreeDemo
{
	public static void main(String[] args)
	{
		RedBlackTree rbTree = new RedBlackTree();

		System.out.println("Tree after inserting 50");
		rbTree.insert(50);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 60");
		rbTree.insert(60);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 70");
		rbTree.insert(70);
		rbTree.display();
		System.out.println();

		rbTree.insert(40);
		System.out.println("Tree after inserting 40");
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 55");
		rbTree.insert(55);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 75");
		rbTree.insert(75);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 53");
		rbTree.insert(53);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 54");
		rbTree.insert(54);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 30");
		rbTree.insert(30);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 45");
		rbTree.insert(45);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 35");
		rbTree.insert(35);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after inserting 51");
		rbTree.insert(51);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after deleting 55");
		rbTree.del(55);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after deleting 50");
		rbTree.del(50);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after deleting 75");
		rbTree.del(75);
		rbTree.display();
		System.out.println();

		System.out.println("Tree after deleting 45");
		rbTree.del(45);
		rbTree.display();
		System.out.println();		
	}//End of main()
}//End of class RedBlackTreeDemo
