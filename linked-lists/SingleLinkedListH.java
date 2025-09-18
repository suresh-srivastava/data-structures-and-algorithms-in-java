//SingleLinkedListH.java : Program of single linked list with header node.

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

class SingleLinkedListH
{
	private Node head;
		
	public SingleLinkedListH()
	{
		head = new Node(0);		
	}
	
	public SingleLinkedListH(SingleLinkedListH list)
	{
		Node p1, p2;
		p1 = list.head;
		p2 = head = new Node(p1.info);
		p1 = p1.link;

		while(p1 != null)
		{		
			p2.link = new Node(p1.info);
			p2 = p2.link;
			p1 = p1.link;
		}
	}//End of copy constructor	
	
	public boolean isEmpty()
	{
		return (head.link == null);
	}//End of isEmpty()

	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = head.link;
			while(p != null)
			{
				System.out.println(p.info);
				p = p.link;
			}
		}
		else
			System.out.println("List is empty");
	}//End of display()

	public void insertAtBeginning(int data)
	{
		Node temp;

		temp = new Node(data);
		temp.link = head.link;
		head.link = temp;
	}//End of insertAtBeginning()	
	
	public void insertAtEnd(int data)
	{
		Node p, temp;

		temp = new Node(data);

		p = head;
		while(p.link != null)
			p = p.link;
		p.link = temp;

	}//End of insertAtEnd()	
	
	public void insertBefore(int data, int nodeData)
	{
		Node p, temp;

		p = head;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			//Find the reference to predecessor of node containing nodeData
			while(p.link != null)
			{
				if(p.link.info == nodeData)
				{
					temp = new Node(data);
					temp.link = p.link;
					p.link = temp;

					break;
				}
				p = p.link;
			}

			if(p.link == null)
				System.out.println("Item " + nodeData + " is not in the List");

		}//End of else

	}//End of insertBefore()	
	
	public void insertAfter(int data, int nodeData)
	{
		Node p, temp;

		p = head.link;
		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			while(p != null)
			{
				if(p.info == nodeData)
				{
					temp = new Node(data);
					temp.link = p.link;
					p.link = temp;
					break;
				}
				p = p.link;
			}

			if(p == null)
				System.out.println("Item " + nodeData + " is not in the list");

		}//End of else
	}//End of insertAfter()	
	
	public void insertAtPosition(int data, int position)
	{
		Node p, temp;

		if(position == 1)
		{
			temp = new Node(data);
			temp.link = head.link;
			head.link = temp;
		}
		else
		{
			p = head.link;
			int index = 1;
			while(p!=null && index < position-1) //Find the reference to (position-1)th node
			{
				p = p.link;
				index++;
			}

			if(p!=null && position>0)
			{
				temp = new Node(data);
				temp.link = p.link;
				p.link = temp;
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
			head.link = head.link.link;
	}//End of deleteAtBeginning()	
	
	public void deleteAtEnd()
	{
		Node p;

		p = head;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			while(p.link.link != null)
				p = p.link;

			p.link = null;
		}
	}//End of deleteAtEnd()	
	
	public void deleteNode(int nodeData)
	{
		Node p;

		p = head;
		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			while(p.link != null)
			{
				if(p.link.info == nodeData)
					break;
				p = p.link;
			}
			if(p.link == null)
				System.out.println(nodeData + " not found in list");
			else
				p.link = p.link.link;
		}//End of else
	}//End of deleteNode()	
	
	public void deleteAtPosition(int position)
	{
		Node p;

		p = head.link;

		if(isEmpty())
			System.out.println("List is empty");
		else if(position == 1)
			head.link = p.link;
		else
		{
			int index = 1;

			while(p.link!=null && index < position-1)
			{
				p = p.link;
				index++;
			}

			if(p.link!=null && position>0)
				p.link = p.link.link;
			else
				System.out.println("Node cannot be deleted at position : " + position);
		}//End of else

	}//End of deleteAtPosition()

	public void reverse()
	{
		Node prev, p, next;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = head.link;
			prev = null;

			while(p != null)
			{
				next = p.link;
				p.link = prev;
				prev = p;
				p = next;
			}
			head.link = prev;
		}
	}//End of reverse()	
	
	
}//End of class SingleLinkedListH

class SingleLinkedListHDemo
{
	public static void main(String[] args)
	{
		SingleLinkedListH list1 = new SingleLinkedListH();

		//Create the List
		list1.insertAtBeginning(10);
		list1.insertAtEnd(30);
		list1.insertAfter(50,30);
		list1.insertAtPosition(20,2);
		list1.insertBefore(40,50);

		System.out.println("List1 Items after insertion :");
		list1.display();

		SingleLinkedListH list2 = new SingleLinkedListH(list1);
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

}//End of class SingleLinkedListHDemo
