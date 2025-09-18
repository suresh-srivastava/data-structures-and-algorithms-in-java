//BinarySearchRecursive.java : Program of recursive binary search in an array

class BinarySearchRecursiveDemo
{
	static int rbinarySearch(int[] arr, int low, int up, int item)
	{
		int mid;

		if(low > up)
			return -1;

		mid = (low+up)/2;

		if(item > arr[mid]) //Search in right half
			return rbinarySearch(arr, mid+1, up, item);
		else if (item < arr[mid]) //Search in left half
			return rbinarySearch(arr, low, mid-1, item);
		else
			return mid;
	}//End of rbinarySearch()
	
	static int rbinarySearch(int[] arr, int n, int item)
	{
		return rbinarySearch(arr, 0, n-1, item);

	}//End of rbinarySearch()	
	
	public static void main(String[] args)
    {
		int[] arr = {2, 9, 16, 19, 29, 36, 41, 67, 85, 96};
		int item = 29;
		
		int index = rbinarySearch(arr, arr.length, item);

		if(index==-1)
			System.out.println(item + " not found in array");
		else
			System.out.println(item + " found at position " + index);

    }//End of main()
}//End of class BinarySearchRecursiveDemo
