//BubbleSort.java : Program of sorting using bubble sort.

class BubbleSortDemo
{
	static void bubbleSort(int[] arr, int n)
	{
		int temp, xchanges;

		for(int i=0; i<n-1 ;i++)
		{
			xchanges = 0;
			for(int j=0; j<n-1-i; j++)
			{
				if(arr[j] > arr[j+1])
				{
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					xchanges++;
				}
			}
			if(xchanges == 0) //If list is sorted
				break;
		}//End of for
	}//End of bubbleSort()

	public static void main(String[] args)
    {
		int[] arr = {40, 20, 50, 60, 30, 10, 90, 97, 70, 80};

		System.out.println("Unsorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		bubbleSort(arr,arr.length);
		
		System.out.println("Sorted list is :"); 
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
    }//End of main()
}//End of class BubbleSortDemo
