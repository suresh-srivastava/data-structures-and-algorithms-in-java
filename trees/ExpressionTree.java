//ExpressionTree.java : Program for Expression Tree.

import java.util.Stack;

class Node
{
	public char info;
	public Node lchild;
	public Node rchild;

	public Node(char ch)
	{
		info = ch;
		lchild = null;
		rchild = null;
	}
}//End of class Node

class ExpressionTree
{
	private Node root;

	public ExpressionTree()
	{
		root = null;
	}//End of ExpressionTree()
	
	private boolean isOperator(char c)
	{
		if(c=='+' || c=='-' || c=='*' || c=='/')
			return true;
		return false;
	}//End of isOperator();	
	
	public void buildTree(String postfix)
	{
		Stack<Node> treeStack = new Stack<Node>();
		Node t;

		for(int i=0; i<postfix.length(); i++)
		{
			if(isOperator(postfix.charAt(i)))
			{
				t = new Node(postfix.charAt(i));
				t.rchild = treeStack.pop();
				t.lchild = treeStack.pop();
				treeStack.push(t);
			}
			else	//operand
			{
				t = new Node(postfix.charAt(i));
				treeStack.push(t);
			}
		}

		root = treeStack.pop();

	}//End of buildTree()	
	
	private void preorder(Node p)
	{
		if(p == null)	//Base case
			return;

		System.out.print(p.info);
		preorder(p.lchild);
		preorder(p.rchild);
	}//End of preorder()

	public void prefix()
	{
		preorder(root);
		System.out.println();
	}//End of prefix()	
	
	private void postorder(Node p)
	{
		if(p == null)	//Base case
			return;

		postorder(p.lchild);
		postorder(p.rchild);
		System.out.print(p.info);
	}//End of postorder()

	public void postfix()
	{
		postorder(root);
		System.out.println();
	}//End of postfix()

	private void inorder(Node p)
	{
		if(p == null)	//Base case
			return;

		if(isOperator(p.info))
			System.out.print("(");

		inorder(p.lchild);
		System.out.print(p.info);
		inorder(p.rchild);

		if(isOperator(p.info))
			System.out.print(")");

	}//End of inorder()

	public void parenthesizedInfix()
	{
		inorder(root);
		System.out.println();
	}//End of parenthesizedInfix()
	
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
	
	private int evaluate(Node p)
	{
		int value = 0;
		
		if(!isOperator(p.info))
			return p.info - 48;

		int leftValue = evaluate(p.lchild);
		int rightValue = evaluate(p.rchild);

		if(p.info == '+')
			value = leftValue + rightValue;
		else if(p.info == '-')
			value = leftValue - rightValue;
		else if(p.info == '*')
			value = leftValue * rightValue;
		else if(p.info == '/')
			value = leftValue / rightValue;
		
		return value;
	}//End of evaluate()

	public int evaluate()
	{
		if(root == null)
			return 0;

		return evaluate(root);
	}//End of evaluate()
		
}//End of class ExpressionTree

class ExpressionTreeDemo
{
	public static void main(String[] args)
    {
		ExpressionTree expTree = new ExpressionTree();

		String postfix = "54+12*3*-";

		expTree.buildTree(postfix);
		expTree.display();

		System.out.print("Prefix : ");
		expTree.prefix();

		System.out.print("Postfix : ");
		expTree.postfix();

		System.out.print("Infix : ");
		expTree.parenthesizedInfix();

		System.out.println("Evaluated Value : " + expTree.evaluate());
    }//End of main()
}//End of class ExpressionTreeDemo
