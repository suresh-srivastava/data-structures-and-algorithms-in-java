//CircularLinkedList.java : Program of Circular linked list.

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

class CircularLinkedList
{
	private Node last;

	public CircularLinkedList()
	{
		last = null;
	}//End of CircularLinkedList()

	public CircularLinkedList(CircularLinkedList list)
	{
		if(list.last == null)
			last = null;
		else
		{
			Node p1, p2;

			p1 = list.last.link;
			p2 = last = new Node(p1.info);
			last.link = last;
			p1 = p1.link;

			while(p1 != list.last.link)
			{
				Node temp = new Node(p1.info);
				temp.link = p2.link;
				p2.link = temp;
				p2 = p2.link;
				last = p2;
				p1 = p1.link;
			}
		}//End of else
	}//End of copy constructor	
	
	public boolean isEmpty()
	{
		return (last == null);
	}//End of isEmpty()

	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = last.link;
			do
			{
				System.out.println(p.info);
				p = p.link;
			}while(p != last.link);
		}
		else
			System.out.println("List is empty");
	}//End of display()

	public void insertAtBeginning(int data)
	{
		Node temp;

		temp = new Node(data);
		if(isEmpty())
		{
			last = temp;
			last.link = temp;
		}
		else
		{
			temp.link = last.link;
			last.link = temp;
		}
		
	}//End of insertAtBeginning()	
	
	public void insertAtEnd(int data)
	{
		Node temp;

		temp = new Node(data);

		if(isEmpty())
		{
			last = temp;
			last.link = temp;
		}
		else
		{
			temp.link = last.link;
			last.link = temp;
			last = temp;
		}
	}//End of insertAtEnd()	
	
	public void insertBefore(int data, int nodeData)
	{
		Node p, temp;

		p = last;

		if(isEmpty())
			System.out.println("List is empty");
		else if(p.link.info == nodeData) //nodeData is in first node
		{
			temp = new Node(data);
			temp.link = p.link;
			p.link = temp;
		}
		else
		{
			p = last.link;
			do
			{
				if(p.link.info == nodeData)
				{
					temp = new Node(data);
					temp.link = p.link;
					p.link = temp;
					break;
				}
				p = p.link;
			}while(p != last.link);

			if(p == last.link)
				System.out.println("Item " + nodeData + " is not in the list");

		}//End of else
	}//End of insertBefore()
	
	public void insertAfter(int data, int nodeData)
	{
		Node p, temp;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = last.link;
			do
			{
				if(p.info == nodeData)
				{
					temp = new Node(data);
					temp.link = p.link;
					p.link = temp;
					if(p == last)
						last = temp;
					break;
				}
				p = p.link;
			}while(p != last.link);

			if(p == last.link)
				System.out.println("Item " + nodeData + " is not in the list");

		}//End of else
	}//End of insertAfter()	
	
	public void insertAtPosition(int data, int position)
	{
		Node p, temp;

		p = last;

		if(isEmpty())
		{
			if(position == 1)
			{
				temp = new Node(data);
				last = temp;
				last.link = temp;
			}
			else
				System.out.println("Item cannot be inserted at position : " + position);
		}
		else if(position == 1)
		{
			temp = new Node(data);
			temp.link = last.link;
			last.link = temp;
		}
		else
		{
			p = last.link;
			int index = 1;
			do
			{
				if(index == position-1)
				{
					temp = new Node(data);
					temp.link = p.link;
					p.link = temp;
					if(p == last)
						last = temp;
					p = p.link;
					break;
				}
				index++;
				p = p.link;
			}while(p != last.link);

			if(p == last.link)
				System.out.println("Item cannot be inserted at position : " + position);

		}//End of else

	}//End of insertAtPosition()	
	
	public void deleteAtBeginning()
	{
		if(isEmpty())
			System.out.println("List is empty");
		else if(last.link == last) //List has only one node
			last = null;
		else 
			last.link = last.link.link;
	}//End of deleteAtBeginning()	
	
	public void deleteAtEnd()
	{
		Node p;

		if(isEmpty())
			System.out.println("List is empty");
		else if(last.link == last) //List has only one node
		{
			last = null;
		}
		else
		{
			p = last.link;
			while(p.link != last)
				p = p.link;

			p.link = last.link;
			last = p;
		}
	}//End of deleteAtEnd()	
	
	public void deleteNode(int nodeData)
	{
		Node p;

		if(isEmpty())
			System.out.println("List is empty");
		else if(last.link.info == nodeData) //Deletion of first node
		{
			if(last.link == last) //List has only one node
				last = null;
			else 
				last.link = last.link.link;
		}
		else //Deletion in between or at the end
		{
			p = last.link;

			while(p.link != last.link)
			{
				if(p.link.info == nodeData)
					break;
				p = p.link;
			}
			if(p.link == last.link)
				System.out.println(nodeData + " not found in list");
			else
			{
				if(p.link == last)
					last = p;
				p.link = p.link.link;
			}
		}//End of else
	}//End of deleteNode()	
	
	public void deleteAtPosition(int position)
	{
		Node p;

		if(isEmpty())
			System.out.println("List is empty");
		else if(position == 1) //Deletion of first node
		{
			if(last.link == last) //List has only one node
				last = null;
			else 
				last.link = last.link.link;
		}
		else //Deletion in between or at the end
		{
			p = last.link;
			int index = 1;

			while(p.link != last.link)
			{
				if(index == position-1)
					break;
				index++;
				p = p.link;
			}
			if(p.link == last.link)
				System.out.println("Node cannot be deleted at position : " + position);
			else
			{
				if(p.link == last)
					last = p;
				p.link = p.link.link;
			}
		}//End of else
	}//End of deleteAtPosition()
	
	public void reverse()
	{
		Node prev, p, next;

		if(isEmpty())
			System.out.println("List is empty");
		else
		{
			p = last.link;
			prev = last;

			while(p.link != last.link)
			{
				next = p.link;
				p.link = prev;
				prev = p;
				p = next;
			}
			last = p.link;
			last.link = p;
			p.link = prev;
		}
	}//End of reverse()	
	
	public void concatenate(CircularLinkedList list)
	{
		Node p;

		if(last == null)
		{
			last = list.last;
			list.last = null;
		}
		else if(list.last != null)
		{
			p = last.link;
			last.link = list.last.link;
			list.last.link = p;
			last = list.last;
			list.last = null;
		}
	}//End of concatenate()	
	
}//End of class CircularLinkedList

class CircularLinkedListDemo
{
	public static void main(String[] args)
    {
		CircularLinkedList list1 = new CircularLinkedList();

		//Create the List
		list1.insertAtBeginning(10);
		list1.insertAtEnd(30);
		list1.insertAfter(50,30);
		list1.insertAtPosition(20,2);
		list1.insertBefore(40,50);
		
		System.out.println("List1 Items after insertion :");
		list1.display();

		CircularLinkedList list2 = new CircularLinkedList(list1);
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
		
		CircularLinkedList list4 = new CircularLinkedList();
		CircularLinkedList list5 = new CircularLinkedList();

		list4.insertAtEnd(10);
		list4.insertAtEnd(20);
		list4.insertAtEnd(30);
		list4.insertAtEnd(40);
		list4.insertAtEnd(50);

		System.out.println("List4 Items :");
		list4.display();

		list5.insertAtEnd(5);
		list5.insertAtEnd(15);
		list5.insertAtEnd(25);
		list5.insertAtEnd(35);
		list5.insertAtEnd(45);

		System.out.println("List5 Items :");
		list5.display();

		list4.concatenate(list5);

		System.out.println("List4 Items after concatenation :");
		list4.display();		
				
    }//End of main()
}//End of class CircularLinkedListDemo
