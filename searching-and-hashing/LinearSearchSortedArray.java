//LinearSearchSortedArray.java : Program of sequential search (linear search) in sorted array

class LinearSearchSortedArrayDemo
{
	static int linearSearch(int[] arr, int n, int item)
	{
		int i=0;

		while(i<n && arr[i]<item)
			i++;

		if(i==n || arr[i]!=item)
			return -1;
		else
			return i;
	}//End of linearSearch()

	public static void main(String[] args)
    {
		int[] arr = {2, 9, 16, 19, 29, 36, 41, 67, 85, 96};
		int item = 29;
		
		int index = linearSearch(arr, arr.length, item);

		if(index==-1)
			System.out.println(item + " not found in array");
		else
			System.out.println(item + " found at position " + index);

    }//End of main()
}//End of class LinearSearchSortedArrayDemo