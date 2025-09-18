//StackL.java : Program to implement stack using linked list.

class Node
{
	public int info;
	public Node link;

	public Node(int data)
	{
		info = data;
		link = null;
	}
}//End of class Node

class StackL
{
	private Node top;

	public StackL()
	{ 
		top = null;
	}//End of StackL()
    
	public boolean isEmpty()
	{
		return (top == null);
	}//End of isEmpty()
    
	public void push(int data)
	{
		Node temp;

		temp = new Node(data);
		if(!isEmpty())
			temp.link = top;

		top = temp;
	}//End of push()
    
	public int pop() throws Exception
	{
		int retValue;

		if(isEmpty())
			throw new Exception("Stack is empty");
		else 
		{
			retValue = top.info;
			top = top.link;
		}

		return retValue;
	}//End of pop()
    
	public int peek() throws Exception
	{
		if(isEmpty())
			throw new Exception("Stack is empty");

		return top.info;
	}//End of peek()

	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = top;
			while(p != null)
			{
				System.out.println(p.info);
				p = p.link;
			}
		}
		else
			System.out.println("Stack is empty");

	}//End of display()

	public int size()
	{
		Node p;
		int count = 0;

		p = top;
		while(p != null)
		{
			count++;
			p = p.link;
		}

		return count;
	}//End of size()
    
}//End of class StackL

class StackLDemo
{
	public static void main(String[] args)
	{
		StackL st = new StackL();

		try
		{
			st.push(1);
			st.push(2);
			st.push(3);
			st.push(4);

			System.out.println("Stack Items :");
			st.display();
			System.out.println("Top Item : " + st.peek());
			System.out.println("Total items : " + st.size());

			System.out.println("Popped Item : " + st.pop());
			System.out.println("Stack Items :");
			st.display();

			st.push(4);
			st.push(5);

			System.out.println("Stack Items :");
			st.display();

			System.out.println("Popped Item : " + st.pop());
			System.out.println("Popped Item : " + st.pop());
			System.out.println("Popped Item : " + st.pop());
			System.out.println("Popped Item : " + st.pop());
			System.out.println("Popped Item : " + st.pop());

			System.out.println("Stack Items :");
			st.display();

		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}//End of main()
}//End of class StackLDemo