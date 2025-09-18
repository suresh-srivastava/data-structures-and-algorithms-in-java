//LinkedList4.java : Program to display the elements of a single linked list in reverse order.

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

	private void rdisplay(Node p)
	{
		if(p == null)
			return;

		rdisplay(p.link);
		System.out.println(p.info);		
	}//End of rdisplay()

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
		
	public void rdisplay()
	{
		rdisplay(start);			
	}//End of rdisplay()
	
}//End of class SingleLinkedList

class LinkedList4Demo
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

    	System.out.println("List Items in reverse order :");
    	singleLinkedList.rdisplay();    	
    }//End of main()
}//End of class LinkedList4Demo
