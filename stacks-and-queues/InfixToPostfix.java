//InfixToPostfix.java : Program to covert infix to postfix and evaluate the postfix expression.
//It will evaluate only single digit numbers.

import java.util.Stack;

class InfixToPostfixDemo
{
	static int power(int b, int a)
	{
		int result=1;

		for(int i=1; i<=a; i++)
			result *= b;

		return result;
	}//End of power()
	
	static int evaluatePostfix(String postfix)
	{
		char symbol;
		int a, b, temp=0;
		Stack<Integer> st = new Stack<Integer>();

		for(int i=0; i<postfix.length(); i++)
		{
			symbol = postfix.charAt(i);

			if(symbol-'0' >= 0 && symbol-'0' <= 9)
			{
				st.push(symbol-'0');
			}
			else
			{
				a = st.pop();
				b = st.pop();

				switch(symbol)
				{
					case '+':
						temp = b+a; break;
					case '-':
						temp = b-a; break;
					case '*':
						temp = b*a; break;
					case '/':
						temp = b/a; break;
					case '%':
						temp = b%a; break;
					case '^':
						temp = power(b, a); break;
				}//End of switch

				st.push(temp);
			}//End of else
		}//End of for

		return st.pop();
	}//End of evaluatePostfix()	
	
	static int precedence(char symbol)
	{
		switch(symbol)
		{
			case '(':
				return 0;
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
			case '%':
				return 2;
			case '^':
				return 3;
			default:
				return 0;
		}
	}//End of precedence()	
	
	static String infixToPostfix(String infix)
	{
		String postfix = "";
		char symbol;
		Stack<Character> st = new Stack<Character>();

		for(int i=0; i<infix.length(); i++)
		{
			symbol = infix.charAt(i);

			switch(symbol)
			{
				case '(':
					st.push(symbol);
					break;
				case ')':
					while(st.peek() != '(')
					{
						postfix += st.pop();
					}
					st.pop();
					break;
				case '+':
				case '-':
				case '*':
				case '/':
				case '%':
				case '^':
					while( !st.empty() && ( precedence(st.peek()) >= precedence(symbol) ) )
					{
						postfix += st.pop();
					}
					st.push(symbol);
					break;
				default:
					postfix += symbol;
					break;
			}//End of switch
		}//End of for

		while(!st.empty())
		{
			postfix += st.pop();
		}
		
		return postfix;
	}//End of infixToPostfix()	
	
	public static void main(String[] args)
    {
		String infix = "7+5*3^2/(9-2^2)+6*4";
		String postfix;

		System.out.println("Infix expression is : " + infix);

		postfix = infixToPostfix(infix);

		System.out.println("Postfix expression is :");
		System.out.println(postfix);

		System.out.println("Value of expression is :");
		System.out.println(evaluatePostfix(postfix));
    }//End of main()
}//End of class InfixToPostfixDemo
