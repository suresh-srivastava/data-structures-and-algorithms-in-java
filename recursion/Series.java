//Series.java : Program to display and find out the sum of series.
//Series : 1 + 2 + 3 + 4 + 5 +.......

class SeriesDemo
{
	static int rseries(int n)
	{
		int sum;
		if(n == 0)
			return 0;
		sum = (n + rseries(n-1));
		System.out.print(n + " + ");
		return sum;
	}//End of rseries()
	
	public static void main(String[] args)
	{
		int num = 5;

		System.out.println("\b\b= " + rseries(num)); //\b to erase last + sign
	}//End of main()
}//End of class SeriesDemo