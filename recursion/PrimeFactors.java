//PrimeFactors.java : Program to find the prime factors of a number.

class PrimeFactorsDemo
{
	static void primeFactors(int num)
	{
		int i=2;

		if(num == 1)
			return;

		while(num%i != 0)
			i++;

		System.out.print(i + " ");
		primeFactors(num/i);
	}//End of primeFactors()	

	public static void main(String[] args)
	{
		int num = 84;

		System.out.println("Prime factors of " + num + " :");
		primeFactors(num);
		System.out.println();		
	}//End of main()
}//End of class PrimeFactorsDemo