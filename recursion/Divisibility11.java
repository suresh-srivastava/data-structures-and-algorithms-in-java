//Divisibility11.java : Program to find a number is divisible from 11 or not.
//A number is divisible by 11 if and only if the difference of the sums of digits at odd positions and even positions 
//is either zero or divisible by 11.

class Divisibility11Demo
{
	static boolean isDivisibleBy11(long n)
	{
		int s1=0, s2=0, diff;

		if(n == 0)
			return true;

		if(n < 10)
			return false;

		while(n > 0)
		{
			s1 += n%10;
			n /= 10;

			s2 += n%10;
			n /= 10;
		}

		diff = s1>s2 ? (s1-s2) : (s2-s1);

		return isDivisibleBy11(diff);
	}//End of isDivisibleBy11()
	
	public static void main(String[] args)
	{
		long num = 62938194;

		System.out.println(num + " is divisible by 11 : " + (isDivisibleBy11(num) ? "True" : "False"));		
	}//End of main()
}//End of class Divisibility11Demo
