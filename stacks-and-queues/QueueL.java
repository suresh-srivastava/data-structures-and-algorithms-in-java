//QueueL.java : Program to implement queue using linked list.

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

class QueueL
{
	private Node front;
	private Node rear;

	public QueueL()
	{
		front = null;
		rear = null;
	}//End of QueueL()

	public boolean isEmpty()
	{
		return (front == null);
	}//End of isEmpty()

	public void enqueue(int data)
	{
		Node temp;

		temp = new Node(data);

		if(isEmpty()) //If queue is empty
			front = temp;
		else
			rear.link = temp;

		rear = temp;
	}//End of enqueue()

	public int dequeue() throws Exception
	{
		int retValue;

		if(isEmpty())
			throw new Exception("Queue is empty");
		else 
		{
			retValue = front.info;
			front = front.link;
		}

		return retValue;
	}//End of dequeue()	

	public int peek() throws Exception
	{
		if(isEmpty())
			throw new Exception("Queue is empty");

		return front.info;
	}//End of peek()	

	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = front;
			while(p != null)
			{
				System.out.println(p.info);
				p = p.link;
			}
		}
		else
			System.out.println("Queue is empty");
	}//End of display()	

	public int size()
	{
		Node p;
		int count = 0;

		p = front;
		while(p != null)
		{
			count++;
			p = p.link;
		}

		return count;
	}//End of size()	
		
}//End of class QueueL

class QueueLDemo
{
	public static void main(String[] args)
	{
		QueueL qu = new QueueL();

		try
		{
			qu.enqueue(1);
			qu.enqueue(2);
			qu.enqueue(3);
			qu.enqueue(4);

			System.out.println("Queue Items :");
			qu.display();
			System.out.println("Front Item : " + qu.peek());
			System.out.println("Total items : " + qu.size());

			System.out.println("Deleted Item : " + qu.dequeue());
			System.out.println("Queue Items :");
			qu.display();

			qu.enqueue(5);

			System.out.println("Queue Items :");
			qu.display();

			System.out.println("Deleted Item : " + qu.dequeue());
			System.out.println("Deleted Item : " + qu.dequeue());
			System.out.println("Deleted Item : " + qu.dequeue());
			System.out.println("Deleted Item : " + qu.dequeue());
			
			System.out.println("Queue Items :");
			qu.display();

		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}//End of main()
}//End of class QueueLDemo
