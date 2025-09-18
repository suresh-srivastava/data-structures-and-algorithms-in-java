//BinarySearchTree.java : Program for Binary Search Tree.

class Node
{
	public int info;
	public Node lchild;
	public Node rchild;

	public Node(int key)
	{
		info = key;
		lchild = null;
		rchild = null;
	}
}//End of class Node

class BinarySearchTree
{
	private Node root;

	public BinarySearchTree()
	{
		root = null;
	}//End of BinarySearchTree()

	public BinarySearchTree(BinarySearchTree T)
	{
		root = copy(T.root);
	}//End of BinarySearchTree()

	private Node copy(Node p)
	{
		if(p == null)
			return null;

		Node cp = new Node(p.info);
		cp.lchild = copy(p.lchild);
		cp.rchild = copy(p.rchild);
		return cp;
	}//End of copy()	
	
	public boolean isEmpty()
	{
		return root==null;
	}//End of isEmpty()	
	
	//Non Recursive insertion
	public void insert1(int key)
	{
		Node parent = null;
		Node p = root;

		while(p != null)
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

		if(parent == null)
			root = temp;
		else if(key < parent.info)
			parent.lchild = temp;
		else
			parent.rchild = temp;
	}//End of insert1()	
	
	//Recursive insertion
	private Node insert(Node p, int key)
	{
		if(p == null) //Base Case
			p = new Node(key);
		else if(key < p.info) //Insertion in left subtree
			p.lchild = insert(p.lchild, key);
		else if(key > p.info) //Insertion in right subtree
			p.rchild = insert(p.rchild, key);
		else	//Base Case
			System.out.println(key + " is already there");

		return p;
	}//End of insert()

	public void insert(int key)
	{
		root = insert(root, key);
	}//End of insert()	
	
	private void caseA(Node parent, Node p)
	{
		if(parent == null)	//root node to be deleted
			root = null;
		else if(p == parent.lchild)
			parent.lchild = null;
		else
			parent.rchild = null;

	}//End of caseA()	
	
	private void caseB(Node parent, Node p)
	{
		Node child;

		//Initialize child
		if(p.lchild != null)	//node to be deleted has left child
			child = p.lchild;
		else					//node to be deleted has right child
			child = p.rchild;

		if(parent == null)		//node to be deleted is root node
			root = child;
		else if(p == parent.lchild)	//node is left child of its parent
			parent.lchild = child;
		else					//node is right child of its parent
			parent.rchild = child;

	}//End of caseB()
	
	private void caseC(Node parent, Node p)
	{
		Node s, ps;

		//Find inorder successor and its parent
		ps = p;
		s = p.rchild;
		while(s.lchild != null)
		{
			ps = s;
			s = s.lchild;
		}
		p.info = s.info;
		if(s.lchild==null && s.rchild==null)
			caseA(ps, s);
		else
			caseB(ps, s);
	}//End of caseC()	
	
	//Non Recursive deletion
	public void del2(int key)
	{
		Node parent = null;
		Node p = root;

		while(p != null)
		{
			if(p.info == key)
				break;

			parent = p;
			if(key < p.info)
				p = p.lchild;
			else
				p = p.rchild;
		}

		if(p == null)
			System.out.println(key + " is not in the tree");
		else if(p.lchild != null && p.rchild != null)	//2 children
			caseC(parent, p);
		else if(p.lchild != null)	//only left child
			caseB(parent, p);
		else if(p.rchild != null)	//only right child
			caseB(parent, p);
		else	//no child
			caseA(parent, p);

	}//End of del2()	
	
	//Non Recursive deletion
	public void del1(int key)
	{
		Node parent = null;
		Node p = root;

		while(p != null)
		{
			if(p.info == key)
				break;

			parent = p;
			if(key < p.info)
				p = p.lchild;
			else
				p = p.rchild;
		}

		if(p == null)
		{
			System.out.println(key + " is not in the tree");
			return;
		}

		//Case C : 2 children
		//Find inorder successor and parent
		Node s, ps;
		if(p.lchild!=null && p.rchild!=null)
		{
			ps = p;
			s = p.rchild;

			while(s.lchild != null)
			{
				ps = s;
				s = s.lchild;
			}
			p.info = s.info;
			p = s;
			parent = ps;
		}

		//Case B and Case A : 1 or no child
		Node child;
		if(p.lchild != null)	//Node to be deleted has left child
			child = p.lchild;
		else	//Node to be deleted has right child or no child
			child = p.rchild;

		if(parent == null)	//Node to be deleted is root node
			root = child;
		else if(p == parent.lchild)	//Node is left child of its parent
			parent.lchild = child;
		else	//Node is right child of its parent
			parent.rchild = child;

	}//End of del1()	
	
	//Recursive deletion
	private Node del(Node p, int key)
	{
		Node child, s;

		if(p == null)
		{
			System.out.println(key + " not found");
			return p;
		}

		if(key < p.info)	//Delete from left subtree
			p.lchild = del(p.lchild, key);
		else if(key > p.info)	////Delete from right subtree
			p.rchild = del(p.rchild, key);
		else
		{
			//Key to be deleted is found
			if(p.lchild!=null && p.rchild!=null)	//2 children
			{
				s = p.rchild;
				while(s.lchild != null)
					s = s.lchild;
				p.info = s.info;
				p.rchild = del(p.rchild, s.info);
			}
			else	//1 child or no child
			{
				if(p.lchild != null)	//Only left child
					child = p.lchild;
				else	//Only right child or no child
					child = p.rchild;
				p = child;
			}
		}//End of else

		return p;
	}//End of del()

	public void del(int key)
	{
		root = del(root, key);
	}//End of del()
	
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

	public void display()
	{
		display(root, 0);
	}//End of display()	
	
	//Non Recursive search
	public boolean search1(int key)
	{
		Node p = root;

		while(p != null)
		{
			if(key < p.info)
				p = p.lchild;	//Move to left child
			else if(key > p.info)
				p = p.rchild;	//Move to right child
			else	//key found
				return true;
		}

		return false;
	}//End of search1()	
	
	//Recursive search
	private Node search(Node p, int key)
	{
		if(p == null)
			return null;	//key not found
		if(key < p.info)
			return search(p.lchild, key);	//search in left subtree
		if(key > p.info)
			return search(p.rchild, key);	//search in right subtree
		return p;	//key found
	}//End of search()

	public boolean search(int key)
	{
		return search(root, key) != null;
	}//End of search()
	
	//Non Recursive to find minimum key
	public int min1() throws Exception
	{
		if(isEmpty())
			throw new Exception("Tree is empty");

		Node p = root;
		while(p.lchild != null)
			p = p.lchild;

		return p.info;
	}//End of min1()
	
	//Non Recursive to find maximum key
	public int max1() throws Exception
	{
		if(isEmpty())
			throw new Exception("Tree is empty");

		Node p = root;
		while(p.rchild != null)
			p = p.rchild;

		return p.info;
	}//End of max1()
	
	//Recursive to find minimum key
	private Node min(Node p)
	{
		if(p.lchild == null)
			return p;
		return min(p.lchild);
	}//End of min()

	public int min() throws Exception
	{
		if(isEmpty())
			throw new Exception("Tree is empty");

		return min(root).info;
	}//End of min()
	
	//Recursive to find maximum key
	private Node max(Node p)
	{
		if(p.rchild == null)
			return p;
		return max(p.rchild);
	}//End of max()

	public int max() throws Exception
	{
		if(isEmpty())
			throw new Exception("Tree is empty");

		return max(root).info;
	}//End of max()

}//End of class BinarySearchTree

class BinarySearchTreeDemo
{
	public static void main(String[] args)
    {
		BinarySearchTree bst = new BinarySearchTree();

		try
		{
			System.out.println("Insertion to create the BST :");
			bst.insert1(80);
			bst.insert1(70);
			bst.insert1(65);
			bst.insert1(75);
			bst.insert1(90);
			bst.insert1(85);
			bst.insert1(95);
			bst.display();
			System.out.println();
			System.out.println("After deleting 95 :");
			bst.del(95);
			bst.display();
			System.out.println();

			System.out.println("Copy constructor :");
			BinarySearchTree bst1 = new BinarySearchTree(bst);
			bst1.display();
			System.out.println();

			//Search (Iterative) in BST
			System.out.println("search1(75) : " + (bst.search1(75) ? "True" : "False"));
			System.out.println();

			//Search (Recursive) in BST
			System.out.println("search(75) : " + (bst.search(75) ? "True" : "False"));
			System.out.println();

			System.out.println("Min key (Iterative) = " + bst.min1());
			System.out.println("Min key (Recursive) = " + bst.min());

			System.out.println("Max key (Iterative) = " + bst.max1());
			System.out.println("Max key (Recursive) = " + bst.max());

		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

    }//End of main()
}//End of class BinarySearchTreeDemo
