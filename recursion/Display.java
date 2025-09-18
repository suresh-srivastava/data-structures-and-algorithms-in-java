//Display.java : Program to display numbers 1 to n and n to 1

class DisplayDemo
{
	static void display1(int n)
	{
		if(n==0)
			return;

		System.out.println(n);
		display1(n-1);
	}//End of display1()

	static void display2(int n)
	{
		if(n==0)
			return;

		display2(n-1);
		System.out.println(n);
	}//End of display2()	
	
	public static void main(String[] args)
    {
		int num = 5;

		System.out.println(num + " to 1 :");
		display1(num);

		System.out.println("1 to " + num + " :");
		display2(num);
    }//End of main()
}//End of class DisplayDemo
