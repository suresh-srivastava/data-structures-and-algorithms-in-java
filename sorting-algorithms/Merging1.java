//Merging1.java : Program of merging two sorted lists of an array into another array.

class Merging1Demo
{
	//a[low1]...a[up1] and a[low2]...a[up2] merged to temp[low1]..temp[up2]
	static void merge(int[] arr, int[] temp, int low1, int up1, int low2, int up2)
	{
		int i=low1, j=low2, k=low1;  	

		while(i<=up1 && j<=up2)
		{
			if(arr[i] < arr[j])
				temp[k++] = arr[i++];
			else
				temp[k++] = arr[j++];
		}

		while(i<=up1)
			temp[k++] = arr[i++];
		
		while(j<=up2)
			temp[k++] = arr[j++];
	}//End of merge()

	public static void main(String[] args)
    {	
		int[] arr = {5,8,9,28,34, 4,22,25,30,33,40,42};

		int[] temp = new int[arr.length];

		System.out.println("Array is :");
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + "  ");
		System.out.println();

		merge(arr,temp,0,4, 5,11);
		
		System.out.println("Merged list :");
		for( int i=0; i<temp.length; i++)
			System.out.print(temp[i] + "  ");
		System.out.println();	
	
    }//End of main()
}//End of class Merging1Demo
