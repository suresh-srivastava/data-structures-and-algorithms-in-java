//LinearSearch.java : Program of sequential search (linear search) in an array.

class LinearSearchDemo
{
	static int linearSearch(int[] arr, int n, int item)
	{
		int i=0;
		while(i<n && arr[i]!=item)
			i++;

		if(i<n)
			return i;
		else
			return -1;
	}//End of linearSearch()
	
	public static void main(String[] args)
    {
		int[] arr = {96, 19, 85, 9, 16, 29, 2, 36, 41, 67};
		int item = 29;
		
		int index = linearSearch(arr, arr.length, item);

		if(index==-1)
			System.out.println(item + " not found in array");
		else
			System.out.println(item + " found at position " + index);

    }//End of main()
}//End of class LinearSearchDemo