//Merging.java : Program of merging two sorted arrays into a third sorted array.

class MergingDemo
{
	static void merge(int[] arr1, int[] arr2, int[] temp, int n1, int n2)
	{
		int i = 0;	//Index for first array
		int j = 0;	//Index for second array
		int k = 0;	//Index for merged array

		while(i<=n1-1  && j<=n2-1)
		{
			if(arr1[i] < arr2[j])
				temp[k++] = arr1[i++];
			else
				temp[k++] = arr2[j++];
		}

		//Put remaining elements of arr1 into temp
		while(i <= n1-1)
			temp[k++] = arr1[i++];
		
		//Put remaining elements of arr2 into temp
		while(j <= n2-1)
			temp[k++] = arr2[j++];
	}//End of merge()

	public static void main(String[] args)
    {
		int[] arr1 = {5, 8, 9, 28, 34};

		int[] arr2 = {4, 22, 25, 30, 33, 40, 42};

		int[] temp = new int[arr1.length+arr2.length];

		System.out.println("Array 1 in sorted order :");
		for(int i=0; i<arr1.length; i++)
			System.out.print(arr1[i] + "  ");
		
		System.out.println("\nArray 2 in sorted order :");
		for(int i=0; i<arr2.length; i++)
			System.out.print(arr2[i] + "  ");
		
		merge(arr1,arr2,temp,arr1.length,arr2.length);
		
		System.out.println("\nMerged list :");
		for(int i=0; i<arr1.length+arr2.length; i++)
			System.out.print(temp[i] + "  ");
		System.out.println();

    }//End of main()
}//End of class MergingDemo
