//String1.java : Program to find the length of a string.

class String1Demo
{
	static int length(char[] str, int index)
	{
		if(str[index] == '\0')
			return 0;

		return (1 + length(str,index+1));
	}//End of length()
	
	public static void main(String[] args)
	{
		char[] stringArr = {'S','u','r','e','s','h','\0'};

		System.out.println("String is : " + new String(stringArr));
		System.out.println("Length : " + length(stringArr,0));
	}//End of main()
}//End of class String1Demo