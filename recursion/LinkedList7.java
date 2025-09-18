//LinkedList7.java : Program to delete the last node of a single linked list.

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

	private Node delAtEnd(Node p)
	{
		if(p.link == null)
			return null;

		p.link = delAtEnd(p.link);

		return p;
	}//End of delAtEnd()

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
		
	public void delAtEnd()
	{
		start = delAtEnd(start);
	}//End of delAtEnd()
	
}//End of class SingleLinkedList

class LinkedList7Demo
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

    	singleLinkedList.delAtEnd();

    	System.out.println("After Deletion of last node, list items are :");
    	singleLinkedList.display();
    	
    }//End of main()
}//End of class LinkedList7Demo