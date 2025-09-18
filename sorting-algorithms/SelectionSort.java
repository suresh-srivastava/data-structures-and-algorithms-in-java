//SelectionSort.java : Program of sorting using selection sort.

class SelectionSortDemo
{
	static void selectionSort(int[] arr, int n)
	{
		int min, temp;

		for(int i=0; i<n-1; i++)
		{
			//Find the index of smallest element
			min = i;
			for(int j=i+1; j<n; j++)
			{
				if(arr[min] > arr[j])
					min = j;
			}

			if(i != min)
			{
				temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp ;
			}
		}//End of for
	}//End of selectionSort()	

	public static void main(String[] args)
    {
		int[] arr = {82, 42, 49, 8, 25, 52, 36, 93, 59, 15};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		selectionSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
    }//End of main()
}//End of class SelectionSortDemo
