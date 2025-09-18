//BaseConversion.java : Program to convert a positive decimal number to Binary, Octal or Hexadecimal.

class BaseConversionDemo
{
	static void convertBase(int num, int base)
	{
		int rem = num%base;

		if (num==0)
			return;

		convertBase(num/base, base);

		if(rem < 10)
			System.out.print(rem);
		else
			System.out.print((char)(rem-10+'A'));
	}//End of convertBase()	
	
	public static void main(String[] args)
	{
		int num = 15;

		System.out.print("Binary : ");		convertBase(num, 2);	System.out.println();
		System.out.print("Octal : ");		convertBase(num, 8);	System.out.println();
		System.out.print("Hexadecimal : ");	convertBase(num, 16);	System.out.println();		
	}//End of main()
}//End of class BaseConversionDemo