//LinkedList5.java : Program to find an element in a single linked list.

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

	private boolean search(Node p, int data)
	{
		if(p == null)
			return false;

		if(p.info == data)
			return true;

		return search(p.link, data);		
	}//End of search()

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
		
	public boolean search(int data)
	{
		return search(start, data);		
	}//End of search()
	
}//End of class SingleLinkedList

class LinkedList5Demo
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

    	int nodeData = 40;

    	System.out.println("List node " + nodeData + " found : " + (singleLinkedList.search(nodeData) ? "True" : "False"));
    	
    }//End of main()
}//End of class LinkedList5Demo