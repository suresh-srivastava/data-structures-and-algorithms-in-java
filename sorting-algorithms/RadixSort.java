//RadixSort.java : Program of sorting using radix sort.

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

class RadixSortDemo
{
	//Returns kth digit from right in n
	static int getDigit(int n, int k)
	{
		int digit=0;
		for(int i=1; i<=k; i++)
		{
			digit = n%10 ;
			n /= 10;
		}

		return digit;
	}//End of getDigit()
	
	//Returns number of digits in the largest element of the list
	static int digitsInLargest(Node start)
	{
		//Find largest element
		Node p = start;
		int large = 0;

		while(p != null)
		{
			if(p.info > large)
				large = p.info;
			p = p.link;
		}
		
		//Find number of digits in largest element
		int ndigits = 0;
		while(large != 0)
		{
			ndigits++;
			large /= 10;
		}

		return ndigits;
	}//End of digitsInLargest()	

	static void radixSort(int[] arr, int n)
	{
		Node temp;
		Node start = null;

		//Creating linked list by insertion at beginning from arr[n-1]...arr[0]
		for(int i=n-1; i>=0; i--)
		{
			temp = new Node(arr[i]);
			temp.link = start;
			start = temp;
		}

		Node[] rear = new Node[10];
		Node[] front = new Node[10];

		int leastSigPos = 1;
	    int mostSigPos = digitsInLargest(start);

	    int i, digit;
	    Node p;

		for(int k=leastSigPos; k<=mostSigPos; k++) 
		{
			//Make all the queues empty at the beginning of each pass
			for(i=0; i<=9 ; i++)
			{
				rear[i] = null;
				front[i] = null;
			}
			
			for(p=start; p!=null; p=p.link)
			{
				//Find kth digit from right in the number
				digit = getDigit(p.info, k);
			
				//Insert the node in Queue(dig)
				if(front[digit] == null)
					front[digit] = p ;
				else
					rear[digit].link = p;
				rear[digit] = p;
			}
			
			//Join all the queues to form the new linked list
			i=0;
			while(front[i] == null)	//Finding first non empty queue
				i++;

			start = front[i];	
			while(i<=8) 
			{
				if(rear[i+1] != null) //if (i+1)th  queue is not empty
					rear[i].link = front[i+1];	//join end of ith queue to start of (i+1)th queue
				else
					rear[i+1] = rear[i];	//continue with rear[i]
				i++;
			}
			rear[9].link = null;
		}//End of for

		//Copying linked list to arr and deleting the linked list
		i=0;
		p=start;
		while(p != null)
		{
			arr[i++] = p.info;
			p = p.link;
			start = null;
			start = p;
		}

	}//End of radixSort()
	
	public static void main(String[] args)
    {
		int[] arr = {62, 234, 456, 750, 789, 3, 21, 345, 983, 99, 153, 65, 23, 5, 98, 10, 6, 372};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		radixSort(arr, arr.length);

		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
    }//End of main()
}//End of class RadixSortDemo
