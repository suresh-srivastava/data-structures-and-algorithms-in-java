//ReverseString.java : Program of reversing a string using stack.

import java.util.Stack;

class ReverseStringDemo
{
	static String reverseString(String str)
	{
		String temp = "";
		Stack<Character> st = new Stack<Character>();

		for(int i=0; i<str.length(); i++)
			st.push(str.charAt(i));

		while(!st.empty())
			temp += st.pop();

		return temp;
	}//End of reverseString()	
	
	public static void main(String[] args)
    {
		String str = "algorithms";
		System.out.println("String is : " + str);
	
		System.out.println("Reversed string is : " + reverseString(str));
    }//End of main()
}//End of class ReverseStringDemo
