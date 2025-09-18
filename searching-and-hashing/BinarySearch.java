//BinarySearch.java : Program of binary search in an array

class BinarySearchDemo
{
	static int binarySearch(int[] arr, int n, int item)
	{
		int low=0, up=n-1, mid;

		while(low <= up)
		{
			mid = (low+up)/2;
			if(item > arr[mid])
				low = mid+1;	//Search in right half
			else if(item < arr[mid])
				up = mid-1;		//Search in left half
			else
				return mid;
		}

		return -1;
	}//End of binarySearch()

	public static void main(String[] args)
    {
		int[] arr = {2, 9, 16, 19, 29, 36, 41, 67, 85, 96};
		int item = 29;
		
		int index = binarySearch(arr, arr.length, item);

		if(index==-1)
			System.out.println(item + " not found in array");
		else
			System.out.println(item + " found at position " + index);

    }//End of main()
}//End of class BinarySearchDemo