//DoublyLinkedList.java : Program of Doubly linked list.

class Node
{
    public int info;
    public Node prev;
	public Node next;

	public Node(int data)
	{
		info = data;
		prev = null;
		next = null;
	}
}//End of class Node

class DoublyLinkedList
{
	private Node start;
    
	public DoublyLinkedList()
	{
	    start = null;		
	}//End of DoublyLinkedList() 

	public DoublyLinkedList(DoublyLinkedList list)
	{
		if(list.start == null)
			start = null;
		else
		{
			Node p1, p2, previous;
			
			p1 = list.start;
			p2 = start = new Node(p1.info);
			previous = null;
		
			p1 = p1.next;

			while(p1 != null)
			{		
				p2.next = new Node(p1.info);
				p2.prev = previous;
				previous = p2;
				p2 = p2.next;
				p1 = p1.next;
			}
			p2.prev = previous;
		}
	}//End of copy constructor	
	
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
				p = p.next;
			}
		}
		else
			System.out.println("List is empty");
	}//End of display()
	
	public int size()
	{
		Node p;
		int count = 0;

		p = start;
		while(p != null)
		{
			count++;
			p = p.next;
		}

		return count;
	}//End of size()	

	public int find(int nodeData)
	{
		Node p = start;
		int position = 0;

		while(p != null)
		{
			position++;
			if(p.info == nodeData)
				return position;
			p = p.next;
		}

		return 0;
	}//End of find()	
	
	public void insertAtBeginning(int data)
	{
		Node temp;

		temp = new Node(data);
		if(!isEmpty())
		{
			temp.next = start;
			start.prev = temp;
		}

		start = temp;
	}//End of insertAtBeginning()	
	
	public void insertAtEnd(int data)
	{
		Node p, temp;

		temp = new Node(data);

		if(isEmpty())
			start = temp;
		else
		{
			p = start;
			while (p.next != null)
				p = p.next;

			p.next = temp;
			temp.prev = p;
		}
	}//End of insertAtEnd()	
	
	public void insertBefore(int data, int nodeData)
	{
		Node p, temp;

		if(isEmpty())
			System.out.println("List is empty");
		else if(start.info == nodeData) //nodeData is in first node
		{
	        temp = new Node(data);
	        temp.next = start;
	        start.prev = temp;
	        start = temp;
		}
		else
		{
			p = start.next;
			while(p != null)
			{
				if(p.info == nodeData)
				{
					temp = new Node(data);
					temp.prev = p.prev;
					temp.next = p;
					p.prev.next = temp;
					p.prev = temp;
					break;
				}

				p = p.next;
			}

			if(p == null)
				System.out.println("Item " + nodeData + " is not in the List");

		}//End of else
	}//End of insertBefore()	
	
	public void insertAfter(int data, int nodeData)
	{
		Node p, temp;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = start;
			while(p != null)
			{
				if(p.info == nodeData)
				{
					temp = new Node(data);
					temp.prev = p;
					temp.next = p.next;
					if(p.next != null)
						p.next.prev = temp; //should not be done when p refers to last node
					p.next = temp;

					break;
				}
				p = p.next;
			}

			if(p == null)
				System.out.println("Item " + nodeData + " is not in the list");

		}//End of else
	}//End of insertAfter()	
	
	public void insertAtPosition(int data, int position)
	{
		Node temp, p;

		if(position == 1)
		{
			temp = new Node(data);
			if(!isEmpty())
			{
				temp.next = start;
				start.prev = temp;
			}
			start = temp;
		}
		else
		{
			p = start;
			int index = 1;
			while(p != null && index < position-1) //Find a reference to (position-1)th node
			{
				p = p.next;
				index++;
			}

			if(p!=null && position>0)
			{
				temp = new Node(data);
				temp.prev = p;
				temp.next = p.next;
				if(p.next != null)
					p.next.prev = temp; //should not be done when p refers to last node
				p.next = temp;
			}
			else
				System.out.println("Item cannot be inserted at position : " + position);

		}//End of else

	}//End of insertAtPosition()	

	public void deleteAtBeginning()
	{
		if(isEmpty())
			System.out.println("List is empty");
		else 
		{
			if(start.next == null) //If list has only 1 node
				start = null;
			else
			{
				start = start.next;
				start.prev = null;
			}
		}
	}//End of deleteAtBeginning()	
	
	public void deleteAtEnd()
	{
		Node p;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = start;
			while(p.next != null)
				p = p.next;

			if(p.prev != null)
				p.prev.next = null;
			else
				start = null;

		}
	}//End of deleteAtEnd()	
	
	public void deleteNode(int nodeData)
	{
		Node p;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = start;
			while(p.next != null)
			{
				if(p.info == nodeData)
					break;
	        
				p = p.next;
			}

			if(p.info == nodeData)
			{
				if(p.next == null)
				{
					if(p.prev == null) //First and only node
						start = null;
					else
						p.prev.next = null; //Last node
				}
				else
				{
					if(p.prev == null) //First node
					{
						p.next.prev = null;
						start = p.next;
					}
					else //Node in between
					{
						p.prev.next = p.next;
						p.next.prev = p.prev;
					}
				}

			}//End of if
			else
				System.out.println(nodeData + " not found in list");

		}//End of else

	}//End of deleteNode()	
	
	public void deleteAtPosition(int position)
	{
	    Node p;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = start;
			int index = 1;
			while(p.next != null)
			{
				if(index == position)
					break;
	        
				index++;
				p = p.next;
			}

			if(position == 1)
			{
				if(p.next == null) //First node of only node in list
					start = null;
				else //First node of more than one node in list
				{
					p.next.prev = null;
					start = p.next;
				}
			}
			else if(index == position)
			{
				if(p.next == null)
					p.prev.next = null;
				else
				{
					p.next.prev = p.prev;
					p.prev.next = p.next;
				}
			}
			else
				System.out.println("Node cannot be deleted at position : " + position);

		}//End of else

	}//End of deleteAtPosition()	
	
	public void reverse()
	{
	    Node p, temp;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = start;
			while(p.next != null)
			{
				temp = p.next;
				p.next = p.prev;
				p.prev = temp;
				p = temp;
			}

			p.next = p.prev;
			p.prev = null;
			start = p;
		}
	}//End of reverse()	
	
}//End of class DoublyLinkedList

class DoublyLinkedListDemo
{
    public static void main(String[] args)
    {
    	DoublyLinkedList list1 = new DoublyLinkedList();

    	//Create the List
    	list1.insertAtBeginning(10);
    	list1.insertAtEnd(30);
    	list1.insertAfter(50,30);
    	list1.insertAtPosition(20,2);
    	list1.insertBefore(40,50);

    	System.out.println("List1 Items after insertion :");
    	list1.display();

    	System.out.println("Total items : " + list1.size());
    	System.out.println("find(40) = " + list1.find(40));
    	
    	DoublyLinkedList list2 = new DoublyLinkedList(list1);
    	System.out.println("List2 Items after using copy constructor :");
    	list2.display();
    	
    	list1.deleteAtBeginning();   	
    	list1.deleteAtEnd();
    	list1.deleteNode(30);
    	list1.deleteAtPosition(2);
    	
    	System.out.println("List1 Items after deletion :");
    	list1.display();    	
    	
    	list2.reverse();
    	System.out.println("List2 Items after reverse :");
    	list2.display();   	
    	    	
    }//End of main()
}//End of class DoublyLinkedListDemo
