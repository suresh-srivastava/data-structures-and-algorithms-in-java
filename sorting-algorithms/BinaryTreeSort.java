//BinaryTreeSort.java : Program of sorting using binary tree sort.

class Node
{
	public int info;
	public Node lchild;
	public Node rchild;

	public Node(int data) 
	{
		info = data;
		lchild = null;
		rchild = null;
	}
}//End of class Node

class BinarySearchTree
{ 
	private Node root;
	private static int k;

	public BinarySearchTree()
	{
		root = null;
	}//End of BinarySearchTree()

	public boolean isEmpty()
	{
		return root == null;
	}//End of isEmpty()	
	
	private Node insert(Node p, int data)
	{
		if(p == null)
			p = new Node(data);
		else if(data < p.info)	
			p.lchild = insert(p.lchild, data);
		else 
			p.rchild = insert(p.rchild, data);  
		return p;
	}//End of insert()
	
	public void insert(int data)
	{
		root = insert(root, data);
	}//End of insert()	
	
	//Recursive inorder traversal
	public void inorder(Node p, int[] arr)
	{
		if(p == null)
			return;
		inorder(p.lchild,arr);
		arr[k++] = p.info;
		inorder(p.rchild,arr);
	}//End of inorder()	
	
	public void inorder(int[] arr)
	{
		k = 0;
		inorder(root,arr);
	}//End of inorder()
	
}//End of class BinarySearchTree

class BinaryTreeSortDemo
{
	static void binaryTreeSort(int[] arr, int n)
	{
		BinarySearchTree bst = new BinarySearchTree();

	    for(int i = 0; i < n; i++)
			bst.insert(arr[i]);
	    bst.inorder(arr);
	}//End of binaryTreeSort()		
	
	public static void main(String[] args)
    {
		int[] arr = {19, 35, 10, 12, 46, 6, 40, 3, 90, 8};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		binaryTreeSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();	
    }//End of main()
}//End of class BinaryTreeSortDemo
