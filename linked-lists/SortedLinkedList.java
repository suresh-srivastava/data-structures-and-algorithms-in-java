//SortedLinkedList.java : Program of sorted linked list.

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

class SortedLinkedList
{
	private Node start;
		
	public SortedLinkedList()
	{
		start = null;
	}//End of SortedLinkedList()

	public boolean isEmpty()
	{
		return (start == null);
	}//End of isEmpty()

	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = start;
			while(p != null)
			{
				System.out.println(p.info);
				p = p.link;
			}
		}
		else
			System.out.println("List is empty");
	}//End of display()

	public int find(int nodeData)
	{
		Node p = start;
		int position = 0;

		while(p!=null && p.info<=nodeData)
		{
			position++;
			if(p.info == nodeData)
				return position;
			p = p.link;
		}

		return 0;
	}//End of find()

	public void insert(int data)
	{
		Node p, temp;

		temp = new Node(data);
		
		//List empty or new node to be inserted before first node
		if(isEmpty() || data < start.info)
		{
			temp.link = start;
			start = temp;	
		}
		else
		{
			p = start;
			while(p.link!=null && p.link.info < data)
				p = p.link;
			temp.link = p.link;
			p.link = temp;
		}
	}//End of insert()

}//End of class SortedLinkedList

class SortedLinkedListDemo
{
    public static void main(String[] args)
    {
    	SortedLinkedList list = new SortedLinkedList();

    	list.insert(10);
    	list.insert(20);
    	list.insert(15);
    	list.insert(40);
    	list.insert(50);

    	System.out.println("List Items :");
    	list.display();

    	System.out.println("find(40) = " + list.find(40)); 
    }//End of main()
}//End of class SortedLinkedListDemo
