//TRFactorial.java : Program to find the factorial of a number using tail recursion.

class TRFactorialDemo
{
	static long trFactorial(int n, int result)
	{
		if(n == 0)
			return result;

		return trFactorial(n-1, n*result);
	}//End of trFactorial()

	static long trFactorial(int n)
	{
		return trFactorial(n, 1);
	}//End of trFactorial()	
	
	public static void main(String[] args)
	{
		int num = 5;

		if(num < 0)
			System.out.println("No factorial for negative number");
		else
			System.out.println("Factorial of " + num + " = " + trFactorial(num));		
	}//End of main()
}//End of class TRFactorialDemo