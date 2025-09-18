//BinaryTree.java : Program for Binary Tree.

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class Node
{
	public char info;
	public Node lchild;
	public Node rchild;

	public Node(char data)
	{
		info = data;
		lchild = null;
		rchild = null;
	}
}//End of class Node

class BinaryTree
{
	private Node root;

	public BinaryTree()
	{
		root = null;
	}//End of BinaryTree()

	public boolean isEmpty()
	{
		return root==null;
	}//End of isEmpty()

	public void createTree()
	{
		root = new Node('P');
		root.lchild = new Node('Q');
		root.rchild = new Node('R');

		root.lchild.lchild = new Node('A');
		root.lchild.rchild = new Node('B');

		root.rchild.lchild = new Node('X');
	}//End of createTree()	
	
	private void preorder(Node p)
	{
		if(p == null)	//Base case
			return;

		System.out.print(p.info + " ");
		preorder(p.lchild);
		preorder(p.rchild);
	}//End of preorder()

	public void preorder()
	{
		preorder(root);
		System.out.println();
	}//End of preorder()	
	
	private void inorder(Node p)
	{
		if(p == null)	//Base case
			return;

		inorder(p.lchild);
		System.out.print(p.info + " ");
		inorder(p.rchild);
	}//End of inorder()

	public void inorder()
	{
		inorder(root);
		System.out.println();
	}//End of inorder()	
	
	private void postorder(Node p)
	{
		if(p == null)	//Base case
			return;

		postorder(p.lchild);
		postorder(p.rchild);
		System.out.print(p.info + " ");
	}//End of postorder()

	public void postorder()
	{
		postorder(root);
		System.out.println();
	}//End of postorder()	
	
	public void levelOrder()
	{
		Queue<Node> qu = new LinkedList<Node>();
		Node p;

		qu.add(root);
		while(!qu.isEmpty())
		{
			p = qu.remove();
			System.out.print(p.info + " ");

			if(p.lchild != null)
				qu.add(p.lchild);

			if(p.rchild != null)
				qu.add(p.rchild);
		}
		System.out.println();
	}//End of levelOrder()
	
	private int height(Node p)
	{
		int hLeft, hRight;

		if(p == null)	//Base case
			return 0;

		hLeft = height(p.lchild);
		hRight = height(p.rchild);

		if(hLeft > hRight)
			return 1+hLeft;
		else
			return 1+hRight;
	}//End of height()

	public int height()
	{
		return height(root);
	}//End of height()	
	
	//Non recursive Preorder traversal
	public void nrecPreorder()
	{
		Stack<Node> st = new Stack<Node>();
		Node p = root;

		if(p != null)
		{
			st.push(p);
			while(!st.empty())
			{
				p = st.pop();
				System.out.print(p.info + " ");
				if(p.rchild != null)
					st.push(p.rchild);
				if(p.lchild != null)
					st.push(p.lchild);
			}
			System.out.println();
		}
		else
			System.out.println("Tree is empty");
	}//End of nrecPreorder()	
	
	//Non recursive Inorder traversal
	public void nrecInorder()
	{
		Stack<Node> st = new Stack<Node>();
		Node p = root;

		if(p != null)
		{
			while(true)
			{
				while(p.lchild != null)
				{
					st.push(p);
					p = p.lchild;
				}

				while(p.rchild == null)
				{
					System.out.print(p.info + " ");
					if(st.empty())
					{
						System.out.println();
						return;
					}
					p = st.pop();
				}
				System.out.print(p.info + " ");
				p = p.rchild;
			}
		}
		else
			System.out.println("Tree is empty");
	}//End of nrecInorder()

	//Non recursive Postorder traversal
	public void nrecPostorder()
	{
		Stack<Node> st = new Stack<Node>();
		Node p = root;
		Node visited = root;

		if(p != null)
		{
			while(true)
			{
				while(p.lchild != null)
				{
					st.push(p);
					p = p.lchild;
				}

				while(p.rchild==null || p.rchild==visited)
				{
					System.out.print(p.info + " ");
					visited = p;
					if(st.empty())
					{
						System.out.println();
						return;
					}
					p = st.pop();
				}
				st.push(p);
				p = p.rchild;
			}
		}
		else
			System.out.print("Tree is empty");
	}//End of nrecPostorder()	
	
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
		System.out.println();
	}//End of display()	
	
	//Creation of binary tree from inorder and preorder traversal
	private Node construct(String in, String pre, int num)
	{
		Node temp;
		
		if(num == 0)
			return null;

		temp = new Node(pre.charAt(0));

		if(num == 1)	//if only one node in tree
			return temp;
	
		int i;
		for(i=0; in.charAt(i)!=pre.charAt(0); i++)
			;

		//Number of nodes in its left subtree is i
		//For left subtree
		temp.lchild = construct(in, pre.substring(1), i);

		//For right subtree
		int j;
		for(j=1; j<=i+1; j++)
			;
		temp.rchild = construct(in.substring(i+1), pre.substring(j-1), num-i-1);

		return temp;
	}//End of construct()

	public void construct(String in, String pre)
	{
		root = construct(in, pre, in.length());
	}//End of construct()	
	
	//Creation of binary tree from inorder and postorder traversal
	private Node construct1(String in, String post, int num)
	{
		Node temp;
		int i, j, k;

		if(num == 0)
			return null;

		for(i=1; i<num; i++)
			;

		temp = new Node(post.charAt(i-1));

		if(num == 1)	//if only one node in tree
			return temp;
		
		for(k=0; in.charAt(k)!=post.charAt(i-1); k++)
			;

		//Now k denotes the number of nodes in left subtree
		//For left subtree
		temp.lchild = construct1(in, post, k);

		//For right subtree
		for(j=1; j<=k; j++)
			;

		temp.rchild = construct1(in.substring(k+1), post.substring(j-1), num-k-1);

		return temp;
	}//End of construct1()

	public void construct1(String in, String post)
	{
		root = construct1(in, post, in.length());
	}//End of construct1()
	
}//End of class BinaryTree

class BinaryTreeDemo
{
	public static void main(String[] args)
    {
		BinaryTree bnTree = new BinaryTree();

		bnTree.createTree();
		bnTree.display();
		System.out.println();

		System.out.println("Preorder traversal :");
		bnTree.preorder();
		
		System.out.println("Inorder traversal :");
		bnTree.inorder();

		System.out.println("Postorder traversal :");
		bnTree.postorder();

		System.out.println("Level order traversal :");
		bnTree.levelOrder();

		System.out.println("Height = " + bnTree.height());		
		
		System.out.println("Non Recursive Preorder traversal :");
		bnTree.nrecPreorder();
		
		System.out.println("Non Recursive Inorder traversal :");
		bnTree.nrecInorder();

		System.out.println("Non Recursive Postorder traversal :");
		bnTree.nrecPostorder();
		
		String inorder = "GDHBEIACJFK";

		String preorder = "ABDGHEICFJK";
		BinaryTree bnTree1 = new BinaryTree();
		System.out.println("Creation of binary tree from Inorder = " + inorder + ", Preorder = " + preorder);
		bnTree1.construct(inorder, preorder);
		bnTree1.display();
		System.out.println();
		
		String postorder = "GHDIEBJKFCA";
		BinaryTree bnTree2 = new BinaryTree();
		System.out.println("Creation of binary tree from Inorder = " + inorder + ", Postorder = " + postorder);
		bnTree2.construct1(inorder, postorder);
		bnTree2.display();
		System.out.println();		
		
    }//End of main()
}//End of class BinaryTreeDemo