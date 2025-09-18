//CheckParentheses.java : Program to check that parentheses in expression are valid or not.

import java.util.Stack;

class CheckParenthesesDemo
{
	static boolean matchParentheses(char leftPar, char rightPar)
	{
		if(leftPar=='(' && rightPar==')')
			return true;

		if(leftPar=='{' && rightPar=='}')
			return true;

		if(leftPar=='[' && rightPar==']')
			return true;

		return false;
	}//End of matchParentheses()
	
	static boolean isValid(String expr)
	{
		Stack<Character> st = new Stack<Character>();

		for(int i=0; i<expr.length(); i++)
		{
			if(expr.charAt(i)=='(' || expr.charAt(i)=='{' || expr.charAt(i)=='[')
				st.push(expr.charAt(i));

			if(expr.charAt(i)==')' || expr.charAt(i)=='}' || expr.charAt(i)==']')
			{
				if(st.empty())
				{
					System.out.println("Right parentheses are more than left parentheses");
					return false;
				}
				else
				{
					char ch = st.pop();
					if(!matchParentheses(ch, expr.charAt(i)))
					{
						System.out.println("Parentheses are : ");
						System.out.println(ch + " and " + expr.charAt(i));
						return false;
					}
				}
			}//End of if
		}//End of for

		if(st.empty())
		{
			System.out.println("Balanced Parentheses");
			return true;
		}
		else
		{
			System.out.println("Left parantheses are more than right parantheses");
			return false;
		}
	}//End of isValid()	
	
	public static void main(String[] args)
    {
		String expression = "[A/(B-C)*D]";

		System.out.println("Expression is : " + expression);

		if(isValid(expression))
			System.out.println("Valid expression");
		else
			System.out.println("Invalid expression");

    }//End of main()
}//End of class CheckParenthesesDemo