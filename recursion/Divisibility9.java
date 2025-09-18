//Divisibility9.java : Program to find a number is divisible from 9 or not.
//A number is divisible by 9 if and only if the sum of digits of the number is divisible by 9.

class Divisibility9Demo
{
	static boolean isDivisibleBy9(long n)
	{
		int sumOfDigits = 0;

		if(n == 9)
			return true;

		if(n < 9)
			return false;

		while(n > 0)
		{
			sumOfDigits += n%10;
			n /= 10;
		}

		return isDivisibleBy9(sumOfDigits);
		
	}//End of isDivisibleBy9()	
	
	public static void main(String[] args)
	{
		long num = 1469358;

		System.out.println(num + " is divisible by 9 : " + (isDivisibleBy9(num) ? "True" : "False"));		
	}//End of main()
}//End of class Divisibility9Demo