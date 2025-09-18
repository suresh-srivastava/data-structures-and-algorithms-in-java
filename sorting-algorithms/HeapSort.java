//HeapSort.java : Program of sorting using heap sort.

class HeapSortDemo
{
	static void restoreDown(int i, int[] arr, int n)
	{
		int k = arr[i];
		int lchild = 2*i;
		int rchild = lchild+1;

		while(rchild <= n)
		{
			if(k>=arr[lchild] && k>=arr[rchild])
			{
				arr[i] = k;
				return;
			}
			else if(arr[lchild] > arr[rchild])
			{
				arr[i] = arr[lchild];
				i = lchild;
			}
			else
			{
				arr[i] = arr[rchild];
				i = rchild;
			}
			lchild = i*2;
			rchild = lchild+1;
		}//End of while

		//If number of nodes is even
		if(lchild==n && k<arr[lchild])
		{
			arr[i] = arr[lchild];
			i = lchild;
		}

		arr[i] = k;
	}//End of restoreDown()
	
	static void buildHeapBottomUp(int[] arr, int n)
	{
		for(int i=n/2; i>=1; i--)
			restoreDown(i,arr,n);
	}//End of buildHeapBottomUp()	
	
	static void heapSort(int[] arr, int n)
	{
		buildHeapBottomUp(arr,n);

		System.out.println("Heap is :");
		for(int i=1; i<=n; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		//deleting the root and moving it(maxValue) to arr[n]
		int maxValue;
		while(n > 1)
		{
			maxValue = arr[1];
			arr[1] = arr[n];
			arr[n] = maxValue;
			n--;
			restoreDown(1,arr,n);

			System.out.println("Heap is :");
			for(int i=1; i<=n; i++)
				System.out.print(arr[i] + " ");
			System.out.println();
		}
	}//End of heapSort()	

	public static void main(String[] args)
    {
		int[] arr = {9999, 25, 35, 18, 9, 46, 70, 48, 23, 78, 12, 95}; //data is from arr[1]
		int n = 11; //data is from arr[1]...arr[11]

		System.out.println("Unsorted list is :");
		for(int i=1; i<=n; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		heapSort(arr,n);
		
		System.out.println("Sorted list is :"); 
		for(int i=1; i<=n; i++ )
			System.out.print(arr[i] + " ");
		System.out.println();
		
    }//End of main()
}//End of class HeapSortDemo
