//GCD.java : Program to find greatest common divisor of two numbers.

class GCDDemo
{
	static int GCD(int a, int b)
	{
		if(b == 0)
			return a;

		return GCD(b, a%b);
	}//End of GCD()	
	
	public static void main(String[] args)
	{
		int num1=35, num2=21;

		System.out.println("GCD = " + GCD(num1, num2));		
	}//End of main()
}//End of class GCDDemo