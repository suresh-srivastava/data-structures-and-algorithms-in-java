//String3.java : Program to display the string in reverse order.

class String3Demo
{
	static void rdisplay(char[] str, int index)
	{
		if(str[index] == '\0')
			return;

		rdisplay(str, index+1);
		System.out.print(str[index]);
	}//End of rdisplay()	
	
	public static void main(String[] args)
	{
		char[] stringArr = {'S','u','r','e','s','h','\0'};

		System.out.print("String is : ");
		rdisplay(stringArr, 0);
		System.out.println();
	}//End of main()	
	
}//End of class String3Demo