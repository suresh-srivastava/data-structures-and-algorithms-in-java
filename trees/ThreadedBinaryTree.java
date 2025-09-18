//ThreadedBinaryTree.java : Program for Threaded Binary Tree.

class Node
{
	public int info;
	public Node lchild;
	public Node rchild;
	public boolean lthread;
	public boolean rthread;

	public Node(int key)
	{
		info = key;
		lchild = null;
		rchild = null;
		lthread = true;
		rthread = true;
	}
}//End of class Node

class ThreadedBinaryTree
{
	private Node root;

	public ThreadedBinaryTree()
	{
		root = null;
	}//End of ThreadedBinaryTree()

	public boolean isEmpty()
	{
		return root==null;
	}//End of isEmpty()	
	
	public void insert(int key)
	{
		Node parent = null;
		Node p = root;

		while(p != null)
		{
			parent = p;

			if(key < p.info)
			{
				if(p.lthread == false)
					p = p.lchild;
				else
					break;
			}
			else if(key > p.info)
			{
				if(p.rthread == false)
					p = p.rchild;
				else
					break;
			}
			else
			{
				System.out.println(key + " is already there");
				return;
			}
		}//End of while

		Node temp = new Node(key);

		if(parent == null)
			root = temp;
		else if(key < parent.info)	//Inserted as left child
		{
			temp.lchild = parent.lchild;
			temp.rchild = parent;
			parent.lthread = false;
			parent.lchild = temp;
		}
		else	//Inserted as right child
		{
			temp.lchild = parent;
			temp.rchild = parent.rchild;
			parent.rthread = false;
			parent.rchild = temp;
		}

	}//End of insert()
	
	public void inorder()
	{
		if(root == null)
		{
			System.out.println("Tree is empty");
			return;
		}

		//Find the leftmost node of the tree
		Node p = root;

		while(p.lthread == false)
			p = p.lchild;

		while(p != null)
		{
			System.out.print(p.info + " ");
			
			if(p.rthread == true)
				p = p.rchild;
			else
			{
				p = p.rchild;
				while(p.lthread == false)
					p = p.lchild;
			}
		}
	}//End of inorder()	
	
	public void preorder()
	{
		if(root == null)
		{
			System.out.println("Tree is empty");
			return;
		}

		Node p = root;
		while(p != null)
		{
			System.out.print(p.info + " ");
			if(p.lthread == false)
				p = p.lchild;
			else if(p.rthread == false)
				p = p.rchild;
			else
			{
				while(p!=null && p.rthread==true)
					p = p.rchild;

				if(p != null)
					p = p.rchild;
			}
		}
	}//End of preorder()	
	
	private Node inorderPredecessor(Node p)
	{
		if(p.lthread == true)
		{
			return p.lchild;
		}
		else
		{
			p = p.lchild;
			while(p.rthread == false)
				p = p.rchild;
			return p;
		}
	}//End of inorderPredecessor()	
	
	private Node inorderSuccessor(Node p)
	{
		if(p.rthread == true)
		{
			return p.rchild;
		}
		else
		{
			p = p.rchild;
			while(p.lthread == false)
				p = p.lchild;
			return p;
		}
	}//End of inorderSuccessor()	
	
	public void del(int key)
	{
		Node parent = null;
		Node p = root;

		while(p != null)
		{
			if(p.info == key)
				break;

			parent = p;
			if(key < p.info)
			{
				if(p.lthread == false)
					p = p.lchild;
				else
					break;
			}
			else
			{
				if(p.rthread == false)
					p = p.rchild;
				else
					break;
			}

		}//End of while

		if(p!=null && p.info!=key)
		{
			System.out.println(key + " not found");
			return;
		}

		//Case C : 2 children
		if(p.lthread==false && p.rthread==false)
		{
			//Find inorder successor and its parent
			Node ps = p;
			Node s = p.rchild;

			while(s.lthread == false)
			{
				ps = s;
				s = s.lchild;
			}
			p.info = s.info;
			p = s;
			parent = ps;
		}

		//Case A : no child
		if(p.lthread==true && p.rthread==true)
		{
			if(parent == null)	//key to be deleted is in root node
				root = null;
			else if(p == parent.lchild)
			{
				parent.lthread = true;
				parent.lchild = p.lchild;
			}
			else
			{
				parent.rthread = true;
				parent.rchild = p.rchild;
			}

			return;
		}

		//Case B : 1 child
		Node child;
		//Initialize child
		if(p.lthread == false)	//Node to be deleted has left child
			child = p.lchild;
		else	//Node to be deleted has right child
			child = p.rchild;

		if(parent == null)	//Node to be deleted is root node
			root = child;
		else if(p == parent.lchild)	//Node is left child of its parent
			parent.lchild = child;
		else	//Node is right child of its parent
			parent.rchild = child;

		Node pred = inorderPredecessor(p);
		Node succ = inorderSuccessor(p);

		if(p.lthread == false)	//If p has left child, right is a thread
			pred.rchild = succ;
		else	//p has right child, left is a thread
			succ.lchild = pred;

	}//End of del()
	
}//End of class ThreadedBinaryTree

class ThreadedBinaryTreeDemo
{
	public static void main(String[] args)
    {
		ThreadedBinaryTree threadedTree = new ThreadedBinaryTree();

		threadedTree.insert(67);
		threadedTree.insert(34);
		threadedTree.insert(81);
		threadedTree.insert(12);
		threadedTree.insert(45);
		threadedTree.insert(78);
		threadedTree.insert(95);
		threadedTree.insert(20);
		threadedTree.insert(40);
		threadedTree.insert(89);
		threadedTree.insert(98);

		System.out.println("Inorder traversal :");
		threadedTree.inorder();
		System.out.println();
		System.out.println("Preorder traversal :");
		threadedTree.preorder();
		System.out.println();

		threadedTree.del(81);		//Case C
		System.out.println("Inorder traversal after deleting 81 :");
		threadedTree.inorder();
		System.out.println();

		threadedTree.del(45);		//Case B (has only left child)
		System.out.println("Inorder traversal after deleting 45 :");
		threadedTree.inorder();
		System.out.println();

		threadedTree.del(12);		//Case B (has only right child)
		System.out.println("Inorder traversal after deleting 12 :");
		threadedTree.inorder();
		System.out.println();

		threadedTree.del(40);		//Case A (leaf node)
		System.out.println("Inorder traversal after deleting 40 :");
		threadedTree.inorder();
		System.out.println();

		threadedTree.del(67);		//Case C (root node)
		System.out.println("Inorder traversal after deleting 67 :");
		threadedTree.inorder();
		System.out.println();
    }//End of main()
}//End of class ThreadedBinaryTreeDemo