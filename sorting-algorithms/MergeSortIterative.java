//MergeSortIterative.java : Program of sorting using merge sort without recursion.

class MergeSortIterativeDemo
{
	//arr[low1]...arr[up1] and arr[low2]...arr[up2] merged to temp[low1]..temp[up2]
	static void merge(int[] arr, int[] temp, int low1, int up1, int low2, int up2)
	{
		int i=low1, j=low2, k=low1;  	

		while(i<=up1 && j<=up2)
		{
			if(arr[i] < arr[j])
				temp[k++] = arr[i++];
			else
				temp[k++] = arr[j++];
		}

		while(i <= up1)
			temp[k++] = arr[i++];
		
		while(j <= up2)
			temp[k++] = arr[j++];
	}//End of merge()

	//copies temp[low]....temp[up] to arr[low]...arr[up]
	static void copy(int[] arr, int[] temp, int n)
	{
		for(int i=0; i<n; i++)
			arr[i] = temp[i];
	}//End of copy()	
	
	static void mergePass(int[] arr, int[] temp, int size, int n)
	{
		int i, low1, up1, low2, up2;
		low1 = 0;
		
		while(low1+size <= n-1)
		{
			up1 = low1 + size - 1;
			low2 = low1 + size;
			up2 = low2 + size - 1;
			if(up2 >= n)	//if length of last sublist is less than size
				up2 = n-1;
			merge(arr,temp,low1,up1,low2,up2);
			low1 = up2 + 1;	//Take next two sublists for merging
		}
		
		for(i=low1; i<=n-1; i++)
			temp[i] = arr[i];	//If any sublist is left alone

		copy(arr,temp,n);
	}//End of mergePass()	
	
	static void mergeSort(int[] arr, int n)
	{
		int[] temp = new int[arr.length];
		int size = 1;

		while(size<n)
		{
			mergePass(arr,temp,size,n);
			size = size*2;
		}
	}//End of mergeSort()	

	public static void main(String[] args)
    {
		int[] arr = {8, 5, 89, 30, 42, 92, 64, 4, 21, 56, 3};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		mergeSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

    }//End of main()
}//End of class MergeSortIterativeDemo
