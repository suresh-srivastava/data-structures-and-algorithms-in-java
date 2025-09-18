//Fibonacci.java : Program to generate fibonacci series.

class FibonacciDemo
{
	static int fib(int n)
	{
		if(n==0 || n==1)
			return 1;

		return (fib(n-1) + fib(n-2));
	}//End of fib()	
	
	public static void main(String[] args)
	{
		int nterms = 10;

		for (int i=0; i < nterms ; i++)
			System.out.print(fib(i) + " ");

		System.out.println();		
	}//End of main()
}//End of class FibonacciDemo