//SumOfDigits.java : Program to display digits of a number and sum of digits of that number.

class SumOfDigitsDemo
{
	//Displays digits of a number and finds the sum of digits of an integer
	static long sumOfDigits(long n)
	{
		long sum;

		if(n/10 == 0) //if n is a single digit number
		{
			System.out.print(n%10);
			return n;
		}

		sum = (n%10 + sumOfDigits(n/10));
		System.out.print(n%10);

		return sum;
	}//End of sumOfDigits()	
	
//	//Finds the sum of digits of an integer
//	static long sumOfDigits(long n)
//	{
//		if(n/10 == 0) //if n is a single digit number
//			return n;
//		return n%10 + sumOfDigits(n/10);
//	}//End of sumOfDigits()	
	
//	//Displays the digits of an integer
//	static void display(long n)
//	{
//		if(n/10 == 0)
//		{
//			System.out.print(n);
//			return;
//		}
//		System.out.print(n%10);
//		display(n/10);
//	}//End of display()

//	//Displays the digits of an integer
//	static void display(long n)
//	{
//		if(n/10 == 0)
//		{
//			System.out.print(n);
//			return;
//		}
//		display(n/10);
//		System.out.print(n%10);
//	}//End of display()

	
	public static void main(String[] args)
	{
		long num = 45329;

		System.out.print("Digits = ");
		//display(num);
		System.out.println("\nSum of digits = " + sumOfDigits(num));		
	}//End of main()
}//End of class SumOfDigitsDemo