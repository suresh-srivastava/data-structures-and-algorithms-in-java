//LinkedList6.java : Program to insert a node at the end of a single linked list.

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

class SingleLinkedList
{
	private Node start;

	private Node insertAtEnd(Node p, int data)
	{
		Node temp;

		if(p == null)
		{
			temp = new Node(data);
			return temp;
		}

		p.link = insertAtEnd(p.link, data);

		return p;
	}//End of insertAtEnd()

	public SingleLinkedList()
	{
		start = null;
	}//End of SingleLinkedList()
		
	public boolean isEmpty()
	{
		return start==null;
	}//End of isEmpty()
	
	public void insertAtBeginning(int data)
	{
		Node temp;

		temp = new Node(data);
		if(!isEmpty())
			temp.link = start;

		start = temp;		
	}//End of insertAtBeginning()
	
	public void display()
	{
		Node p;

		if(isEmpty())
		{
			System.out.println("List is empty");
		}
		else
		{
			p = start;
			while(p != null)
			{
				System.out.println(p.info);
				p = p.link;
			}
		}
	}//End of display()
		
	public void insertAtEnd(int data)
	{
		start = insertAtEnd(start, data);
	}//End of insertAtEnd()
	
}//End of class SingleLinkedList

class LinkedList6Demo
{
    public static void main(String[] args)
    {
    	SingleLinkedList singleLinkedList = new SingleLinkedList();

    	singleLinkedList.insertAtBeginning(50);
    	singleLinkedList.insertAtBeginning(40);
    	singleLinkedList.insertAtBeginning(30);
    	singleLinkedList.insertAtBeginning(20);
    	singleLinkedList.insertAtBeginning(10);

    	System.out.println("List Items :");
    	singleLinkedList.display();

    	int data = 75;
    	singleLinkedList.insertAtEnd(data);

    	System.out.println("After inserting " + data + " at the end, list items are :");
    	singleLinkedList.display();
    	
    }//End of main()
}//End of class LinkedList6Demo