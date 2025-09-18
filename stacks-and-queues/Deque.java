//Deque.java : Program to implement deque using circular array.

class Deque
{
	private static final int MAX_SIZE = 5;
	private int[] queueArray;
	private int front;
	private int rear;

	public Deque()
	{
		queueArray = new int[MAX_SIZE];
		front = -1;
		rear = -1; 
	}//End of Deque()
	
	public boolean isEmpty()
	{
		return (front == -1);
	}//End of isEmpty()
	
	public boolean isFull()
	{
		return ((front==0 && rear==MAX_SIZE-1) || (front==rear+1));
	}//End of isFull()	
	
	public void insertFrontEnd(int data)
	{
		if(isFull())
			System.out.println("Queue Overflow");
		else
		{
			if(front == -1) //If queue is initially empty
			{
				front = 0;
				rear = 0;
			}
			else if(front == 0)
				front = MAX_SIZE-1;
			else
				front = front-1;

			queueArray[front] = data;
		}
	}//End of insertFrontEnd()	
	
	public void insertRearEnd(int data)
	{
		if(isFull())
			System.out.println("Queue Overflow");
		else
		{
			if(front == -1) //If queue is initially empty
			{
				front = 0;
				rear = 0;
			}
			else if(rear == MAX_SIZE-1) //rear is at last position of queue
				rear = 0;
			else
				rear = rear+1;

			queueArray[rear] = data;
		}
	}//End of insertRearEnd()	
	
	public int deleteFrontEnd() throws Exception
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
	}//End of deleteFrontEnd()	
	
	public int deleteRearEnd() throws Exception
	{
		int retValue;

		if(isEmpty())
			throw new Exception("Queue is empty");
		else
		{
			retValue = queueArray[rear];

			if(front == rear) //queue has only one element
			{
				front = -1;
				rear = -1;
			}
			else if(rear == 0)
				rear = MAX_SIZE-1;
			else
				rear = rear-1;
		}

		return retValue;
	}//End of deleteRearEnd()	
	
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
	
}//End of class Deque

class DequeDemo
{
	public static void main(String[] args)
	{
		Deque dq = new Deque();

		try
		{
			dq.insertFrontEnd(2);
			dq.insertFrontEnd(1);
			dq.insertRearEnd(3);
			dq.insertRearEnd(4);

			System.out.println("Queue Items :");
			dq.display();
			System.out.println("Total items : " + dq.size());

			System.out.println("Deleted Item from front : " + dq.deleteFrontEnd());
			System.out.println("Deleted Item from Rear : " + dq.deleteRearEnd());
			System.out.println("Queue Items :");
			dq.display();

			dq.insertFrontEnd(5);
			dq.insertFrontEnd(6);

			System.out.println("Queue Items :");
			dq.display();

			dq.insertRearEnd(7);	

			System.out.println("Deleted Item : " + dq.deleteFrontEnd());
			System.out.println("Deleted Item : " + dq.deleteRearEnd());
			System.out.println("Deleted Item : " + dq.deleteFrontEnd());
			System.out.println("Deleted Item : " + dq.deleteRearEnd());
			System.out.println("Deleted Item : " + dq.deleteFrontEnd());

			System.out.println("Queue Items :");
			dq.display();

		}//End of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}		
	}//End of main()
}//End of class DequeDemo
