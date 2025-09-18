//PQueue.java : Program to implement priority queue using linked list.

class Node
{
	public int info;
	public int priority;
	public Node link;

	public Node(int data, int priority)
	{
		info = data;
		this.priority = priority;
		link = null;
	}
}//End of class Node

class PQueue
{
	private Node front;

	public PQueue()
	{
		front = null;
	}//End of PQueue()

	public boolean isEmpty()
	{
	    return (front == null);
	}//End of isEmpty()
	
	public void enqueue(int data, int priority)
	{
		Node temp, p;

		temp = new Node(data, priority);

		//Queue is empty or element to be added has priority more than first element
		if(isEmpty() || priority < front.priority)
		{
			temp.link = front;
			front = temp;
		}
		else
		{
			p = front;
			while(p.link!=null && p.link.priority<=priority)
				p = p.link;
			temp.link = p.link;
			p.link = temp;
		}
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
			System.out.println("Priority, Data Item");
			p = front;
			while(p != null)
			{
				System.out.println(p.priority + ", " + p.info);
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

}//End of class PQueue

class PQueueDemo
{
	public static void main(String[] args)
	{
		PQueue pq = new PQueue();

		try
		{
			pq.enqueue(20,2);
			pq.enqueue(10,1);
			pq.enqueue(50,4);
			pq.enqueue(30,3);

			System.out.println("Queue Items :");
			pq.display();
			System.out.println("Front Item : " + pq.peek());
			System.out.println("Total Items : " + pq.size());

			System.out.println("Deleted Item : " + pq.dequeue());
			System.out.println("Queue Items :");
			pq.display();

			pq.enqueue(40,5);

			System.out.println("Queue Items :");
			pq.display();

			System.out.println("Deleted Item : " + pq.dequeue());
			System.out.println("Deleted Item : " + pq.dequeue());
			System.out.println("Deleted Item : " + pq.dequeue());
			System.out.println("Deleted Item : " + pq.dequeue());
			
			System.out.println("Queue Items :");
			pq.display();

		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}//End of main()
}//End of class PQueueDemo