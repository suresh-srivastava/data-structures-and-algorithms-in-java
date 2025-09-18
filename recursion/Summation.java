//Summation.java : Program to find summation of numbers from 1 to n

class SummationDemo
{
	static int summation(int n)
	{
		if(n==0)
			return 0;

		return (n + summation(n-1));
	}//End of summation()	
	
	public static void main(String[] args)
    {
		int num = 5;

		System.out.println("Summation(" + num + ") = " + summation(num));		
    }//End of main()
}//End of class SummationDemo
