//HeapTree.java : Program for Heap Tree.

class HeapTree
{
	private static final int MAX_SIZE = 30;
	private int[] heapArr;
	private int n;	//Number of elements in heap

	public HeapTree()
	{
		heapArr = new int[MAX_SIZE];
		n = 0;
		heapArr[0] = 9999;
	}//End of HeapTree()
	
	public boolean isEmpty()
	{
		return n==0;
	}//End of isEmpty()

	public void insert(int key)
	{
		n++;	//Increase the heap size by 1
		heapArr[n] = key;
		restoreUp(n);
	}//End of insert()	
	
	private void restoreUp(int i)
	{
		int k = heapArr[i];
		int iParent = i/2;

		while(heapArr[iParent] < k)
		{
			heapArr[i] = heapArr[iParent];
			i = iParent;
			iParent = i/2;
		}
		heapArr[i] = k;
	}//End of restoreUp()	
	
	public int deleteHeap() throws Exception
	{
		if(isEmpty())
			throw new Exception("Tree is empty\n");

		int maxValue = heapArr[1];	//Save the element present at the root
		heapArr[1] = heapArr[n];	//Place the last element in the root
		n--;						//Decrease the heap size by 1
		restoreDown(1);

		return maxValue;
	}//End of deleteHeap()	
	
	private void restoreDown(int i)
	{
		int k = heapArr[i];
		int lchild = 2*i;
		int rchild = lchild+1;

		while(rchild <= n)
		{
			if(heapArr[lchild]<=k && heapArr[rchild]<=k)
			{
				heapArr[i] = k;
				return;
			}
			else if(heapArr[lchild] > heapArr[rchild])
			{
				heapArr[i] = heapArr[lchild];
				i = lchild;
			}
			else
			{
				heapArr[i] = heapArr[rchild];
				i = rchild;
			}

			lchild = 2*i;
			rchild = lchild+1;

		}//End of while

		//If number of nodes is even
		if(lchild==n && k<heapArr[lchild])
		{
			heapArr[i] = heapArr[lchild];
			i = lchild;
		}

		heapArr[i] = k;
	}//End of restoreDown()	
	
	public void display()
	{
		for(int i=1; i<=n; i++)
			System.out.print(heapArr[i] + " ");
		System.out.println();
		System.out.println("Number of elements = " + n);
	}//End of display()	
	
}//End of class HeapTree

class HeapTreeDemo
{
	public static void main(String[] args)
    {
		HeapTree heapTree = new HeapTree();

		try
		{
			heapTree.insert(25);
			heapTree.display();
			heapTree.insert(35);
			heapTree.display();
			heapTree.insert(18);
			heapTree.display();
			heapTree.insert(9);
			heapTree.display();
			heapTree.insert(46);
			heapTree.display();
			heapTree.insert(70);
			heapTree.display();
			heapTree.insert(48);
			heapTree.display();
			heapTree.insert(23);
			heapTree.display();
			heapTree.insert(78);
			heapTree.display();
			heapTree.insert(12);
			heapTree.display();
			heapTree.insert(95);

			System.out.println("After Insertion :");
			heapTree.display();

			System.out.println("After Deletion :");
			System.out.println("Maximum Element : " + heapTree.deleteHeap());
			heapTree.display();
			System.out.println("Maximum Element : " + heapTree.deleteHeap());
			heapTree.display();
		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}		
    }
}//End of class HeapTreeDemo
