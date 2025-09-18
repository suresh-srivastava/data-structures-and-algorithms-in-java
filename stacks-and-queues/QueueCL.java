//QueueCL.java : Program to implement queue using circular linked list.

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

class QueueCL
{
	private Node rear;

	public QueueCL()
	{
		rear = null;
	}//End of QueueCL()

	public boolean isEmpty()
	{
		return (rear == null);
	}//End of isEmpty()

	public void enqueue(int data)
	{
		Node temp;

		temp = new Node(data);

		if(isEmpty()) //If queue is empty
		{
			rear = temp;
			temp.link = rear;
		}
		else
		{
			temp.link = rear.link;
			rear.link = temp;
			rear = temp;
		}
		
	}//End of enqueue()

	public int dequeue() throws Exception
	{
		int retValue;

		if(isEmpty())
			throw new Exception("Queue is empty");

		if(rear.link == rear) //If only one element
		{
			retValue = rear.info;
			rear = null;
		}
		else
		{
			retValue = rear.link.info;
			rear.link = rear.link.link;
		}

		return retValue;
	}//End of dequeue()	

	public int peek() throws Exception
	{
		if(isEmpty())
			throw new Exception("Queue is empty");

		return rear.link.info;
	}//End of peek()

	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = rear.link;
			do
			{
				System.out.println(p.info);
				p = p.link;
			}while(p != rear.link);
		}
		else
			System.out.println("Queue is empty");

	}//End of display()

	public int size()
	{
		Node p;
		int count = 0;

		if(!isEmpty())
		{
			p = rear.link;
			do
			{
				count++;
				p = p.link;
			}while(p != rear.link);
		}

		return count;
	}//End of size()	
	
}//End of class QueueCL

class QueueCLDemo
{
	public static void main(String[] args)
	{
		QueueCL qu = new QueueCL();

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
}//End of class QueueCLDemo
