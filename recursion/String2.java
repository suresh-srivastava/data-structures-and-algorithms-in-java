//String2.java : Program to display the string.

class String2Demo
{
	static void display(char[] str, int index)
	{
		if(str[index] == '\0')
			return;

		System.out.print(str[index]);

		display(str, index+1);
	}//End of display()	
	
	public static void main(String[] args)
	{
		char[] stringArr = {'S','u','r','e','s','h','\0'};

		System.out.print("String is : ");
		display(stringArr, 0);
		System.out.println();		
	}//End of main()
}//End of class String2Demo