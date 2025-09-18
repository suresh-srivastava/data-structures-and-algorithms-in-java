//AddressCalculationSort.java : Program of sorting using address calculation sort.

class Node
{
	public int info;
	public Node link;
	
	public Node(int data)
	{
		info = data;
		link = null;
	}
}//End of class Node

class SortedLinkedList
{
	private Node start;

	public SortedLinkedList()
	{
		start = null;
	}//End of SortedLinkedList()
	
	public Node getStart()
	{ 
		return start;
	}//End of getStart()	
	
	private boolean isEmpty()
	{
		return start == null;
	}//End of isEmpty()
	
	public void insert(int data)
	{
		Node temp = new Node(data);
		
		//List empty or new node to be inserted before first node
		if(isEmpty() || data < start.info)
		{
			temp.link = start;
			start = temp;	
		}
		else
		{
			Node p = start;
			while(p.link != null && p.link.info < data)
				p = p.link;
			temp.link = p.link;
			p.link = temp;
		}
	}//End of insert()
	
}//End of class SortedLinkedList

class AddressCalculationSortDemo
{
	static int hashFn(int x, int large)
	{
		float temp;
	    temp = (float)x / large;
	    return (int)(temp * 5);
	}//End of hashFn()	
	
	static void addressCalculationSort(int[] arr, int n)
	{
		int i;
		
		SortedLinkedList[] list = new SortedLinkedList[6];
		for(i=0; i<6; i++)
			list[i] = new SortedLinkedList();

		int large = 0;
	    for(i=0; i<n; i++)
	    {
			if(arr[i] > large)
				large = arr[i];
	    }

		int x;
		for(i=0; i<n; i++)
		{
			x = hashFn(arr[i],large);
			list[x].insert(arr[i]);
		}

	   //Elements of linked lists are copied to array
	   Node p;
	   i = 0;
	   for(int j=0; j<=5; j++)
	   {
			p = list[j].getStart();
	        while(p != null)
	        {
				arr[i++] = p.info;
	            p = p.link;
			}
	    }//End of for
	}//End of addressCalculationSort()
	
	public static void main(String[] args)
    {	
		int[] arr = {194, 289, 566, 432, 654, 98, 232, 415, 345, 276, 532, 254, 165, 965, 476};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		addressCalculationSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();	
	
    }//End of main()
}//End of class AddressCalculationSortDemo