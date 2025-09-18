//Queue.java : Program to implement queue using array.

class Queue
{
	private static final int MAX_SIZE = 5;
	private	int[] queueArray;
	private	int front;
	private int rear;
	
	public Queue()
	{
		queueArray = new int[MAX_SIZE];
		front = -1;
		rear = -1;
	}//End of Queue()

	public boolean isEmpty()
	{
		return (front==-1 || front==rear+1);
	}//End of isEmpty()

	public boolean isFull()
	{
		return (rear == MAX_SIZE-1);
	}//End of isFull()
	
	public void enqueue(int data)
	{
		if(isFull())
			System.out.println("Queue Overflow");
		else
		{
			if(front == -1)
				front = 0;

			rear = rear+1;
			queueArray[rear] = data;
		}
	}//End of enqueue()
	
	public int dequeue() throws Exception
	{
		if(isEmpty())
			throw new Exception("Queue is empty");

		return queueArray[front++];
	}//End of dequeue()	
	
	public int peek() throws Exception
	{
		if(isEmpty())
			throw new Exception("Queue is empty");

		return queueArray[front];
	}//End of peek()
	
	public void display()
	{
		System.out.println("Front = " + front + "	rear = " + rear);

		if(isEmpty())
			System.out.println("Queue is empty");
		else
			for(int i=front; i<=rear; i++)
				System.out.println(queueArray[i]);
	}//End of display()

	public int size()
	{
		int retValue=0;

		if(!isEmpty())
			retValue = rear-front+1;

		return retValue;
	}//End of size()

}//End of class Queue

class QueueDemo
{
	public static void main(String[] args)
	{
		Queue qu = new Queue();

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
}//End of class QueueDemo
