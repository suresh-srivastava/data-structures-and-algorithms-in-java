//ShellSort.java : Program of sorting using shell sort.

class ShellSortDemo
{
	static void shellSort(int[] arr, int n)
	{
		int i,j,k;
		int incr = 5; //maximum increment (odd value)
		
		while(incr >= 1)
		{
			for(i=incr; i<n; i++)
			{
				k=arr[i];
				for(j=i-incr; j>=0 && k<arr[j]; j=j-incr)
					arr[j+incr]=arr[j];
				arr[j+incr]=k;
			}//End of for
			incr=incr-2;	//Decrease the increment
		}//End of while

	}//End of shellSort()

	public static void main(String[] args)
    {
		int[] arr = {19, 63, 2, 6, 7, 10, 1, 18, 9, 4, 45, 3, 5, 17, 16, 12, 56};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		shellSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
    }//End of main()
}//End of class ShellSortDemo
