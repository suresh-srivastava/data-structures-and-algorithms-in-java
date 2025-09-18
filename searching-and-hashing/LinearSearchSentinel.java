//LinearSearchSentinel.java : Program of sequential search (linear search) using sentinel in an array.

class LinearSearchSentinelDemo
{
	static final int MAX_SIZE = 30;
	
	static int linearSearch(int[] arr, int n, int item)
	{
		arr[n] = item;

		int i=0;

		while(arr[i] != item)
			i++;

		if(i < n)
			return i;
		else
			return -1;
	}//End of linearSearch()
	
	public static void main(String[] args)
    {
		int[] arr = new int[MAX_SIZE];
		arr[0]=96; arr[1]=19; arr[2]=85; arr[3]=9; arr[4]=16;
		arr[5]=29; arr[6]=2; arr[7]=36; arr[8]=41; arr[9]=67;

		int n = 10;
		int item = 29;
		
		int index = linearSearch(arr, n, item);

		if(index==-1)
			System.out.println(item + " not found in array");
		else
			System.out.println(item + " found at position " + index);

    }//End of main()

}//End of class LinearSearchSentinelDemo