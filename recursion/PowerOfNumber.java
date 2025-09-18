//PowerOfNumber.java : Program to find the exponentiation of a number (a power n, example 2 power 3 = 8).

class PowerOfNumberDemo
{
	static long power(int a, int n)
	{
		if(n == 0)
			return 1;

		return (a * power(a, n-1));
	}//End of power()	
	
	public static void main(String[] args)
	{
		int a=2, n=4;

		System.out.println(a + " power " + n + " = " + power(a, n));		
	}//End of main()
}//End of class PowerOfNumberDemo