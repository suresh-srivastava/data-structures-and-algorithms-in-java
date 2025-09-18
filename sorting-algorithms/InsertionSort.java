//InsertionSort.java : Program of sorting using insertion sort.

class InsertionSortDemo
{
	static void insertionSort(int[] arr, int n)
	{
		int i,j,k;

		for(i=1; i<n; i++)
		{
			k = arr[i]; 
			for(j=i-1; j>=0 && arr[j]>k; j--)
				arr[j+1] = arr[j];
			arr[j+1] = k;
		}//End of for
	}//End of insertionSort()

	public static void main(String[] args)
    {
		int[] arr = {82, 42, 49, 8, 25, 52, 36, 93, 59, 15};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		insertionSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
    }//End of main()
}//End of class InsertionSortDemo
