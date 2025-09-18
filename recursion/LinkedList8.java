//LinkedList8.java : Program to reverse a single linked list.

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

	private Node reverse(Node p)
	{
		Node temp;

		if(p.link == null)
			return p;

		temp = reverse(p.link);
		p.link.link = p;
		p.link = null;

		return temp;
	}//End of reverse()

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
		
	public void reverse()
	{
		start = reverse(start);
	}//End of reverse()
	
}//End of class SingleLinkedList

class LinkedList8Demo
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

    	singleLinkedList.reverse();

    	System.out.println("After reverse, list items are :");
    	singleLinkedList.display();
    	
    }//End of main()
}//End of class LinkedList8Demo
