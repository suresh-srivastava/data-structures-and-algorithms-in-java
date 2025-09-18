//Factorial.java : Program to find the factorial of a number using recursion.

class FactorialDemo
{
	static long factorial(int n)
	{
		if(n==0)
			return 1;

		return (n * factorial(n-1));
	}//End of factorial()
	
	public static void main(String[] args)
    {
		int num = 5;

		if(num < 0)
			System.out.println("No factorial for negative number");
		else
			System.out.println("Factorial of " + num + " = " + factorial(num));
		
    }//End of main()
}//End of class FactorialDemo
