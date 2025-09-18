//CQueue.java : Program to implement circular queue using array.

class CQueue
{
	private static final int MAX_SIZE = 5;
	private int[] queueArray;
	private	int front;
	private int rear;

	public CQueue()
	{ 
		queueArray = new int[MAX_SIZE];
		front = -1;
		rear = -1; 
	}//End of CQueue()
	
	public boolean isEmpty()
	{
		return (front == -1);
	}//End of isEmpty()

	public boolean isFull()
	{
		return ((front==0 && rear==MAX_SIZE-1) || (front==rear+1));
	}//End of isFull()
	
	public void enqueue(int num)
	{
		if(isFull())
		{
			System.out.println("Queue Overflow");
		}
		else
		{
			if(front == -1)
				front = 0;

			if(rear == MAX_SIZE-1) //rear is at last position of queue
				rear = 0;
			else
				rear = rear+1;

			queueArray[rear] = num;
		}
	}//End of enqueue()	
	
	public int dequeue() throws Exception
	{
		int retValue;

		if(isEmpty())
			throw new Exception("Queue is empty");
		else
		{
			retValue = queueArray[front];

			if(front == rear) //queue has only one element
			{
				front = -1;
				rear = -1;
			}
			else if(front == MAX_SIZE-1)
				front = 0;
			else
				front = front+1;
		}

		return retValue;
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
		{
			int i = front;
			if(front <= rear)
			{
				while(i <= rear)
					System.out.println(queueArray[i++]);
			}
			else
			{
				while(i <= MAX_SIZE-1)
					System.out.println(queueArray[i++]);
				
				i=0;
				while(i <= rear)
					System.out.println(queueArray[i++]);
			}
		}
	}//End of display()
	
	public int size()
	{
		if(isEmpty())
			return 0;

		if(isFull())
			return MAX_SIZE-1;

		int i = front;
		int sz = 0;

		if(front <= rear)
		{
			while(i <= rear)
			{
				sz++;
				i++;
			}
		}
		else
		{
			while(i <= MAX_SIZE-1)
			{
				sz++;
				i++;
			}

			i = 0;
			while(i <= rear)
			{
				sz++;
				i++;
			}
		}

		return sz;
	}//End of size()	

}//End of class CQueue

class CQueueDemo
{
	public static void main(String[] args)
	{
		CQueue cq = new CQueue();

		try
		{
			cq.enqueue(1);
			cq.enqueue(2);
			cq.enqueue(3);
			cq.enqueue(4);

			System.out.println("Queue Items :");
			cq.display();
			System.out.println("Front Item : " + cq.peek());
			System.out.println("Total items : " + cq.size());

			System.out.println("Deleted Item : " + cq.dequeue());
			System.out.println("Deleted Item : " + cq.dequeue());
			System.out.println("Queue Items :");
			cq.display();

			cq.enqueue(5);
			cq.enqueue(6);

			System.out.println("Queue Items :");
			cq.display();

			cq.enqueue(7);

			System.out.println("Deleted Item : " + cq.dequeue());
			System.out.println("Deleted Item : " + cq.dequeue());
			System.out.println("Deleted Item : " + cq.dequeue());
			System.out.println("Deleted Item : " + cq.dequeue());
			System.out.println("Deleted Item : " + cq.dequeue());
			
			System.out.println("Queue Items :");
			cq.display();

		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}		
	}//End of main()
}//End of class CQueueDemo