//MergeSortLinkedList.java : Program of sorting using merge sort on linked list.

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
		
	public SingleLinkedList()
	{
		start = null;
	}//End of SingleLinkedList()

	public boolean isEmpty()
	{
		return (start == null);
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
		Node ptr;

		if(!isEmpty())
		{
			ptr = start;
			while(ptr != null)
			{
				System.out.print(ptr.info + " ");
				ptr = ptr.link;
			}
			System.out.println();
		}
		else
			System.out.println("List is empty");
	}//End of display()	
	
	private Node merge(Node p1, Node p2)
	{
		Node startM;

		if(p1.info <= p2.info)
		{
			startM = p1;
			p1 = p1.link;
		}
		else
		{
			startM = p2;
			p2 = p2.link;
		}

		Node pM = startM;

		while(p1 != null && p2 != null)
		{
			if(p1.info <= p2.info)
			{
				pM.link = p1;
				pM = pM.link;
				p1 = p1.link;
			}
			else
			{
				pM.link = p2;
				pM = pM.link;
				p2 = p2.link;
			}
		}//End of while

		if(p1 == null)
			pM.link = p2;
		else
			pM.link = p1;
	     
		return startM;
	}//End of merge()	
	
	private Node divideList(Node p)
	{
		Node q = p.link.link;

		while(q != null && q.link != null)
		{
			p = p.link;
			q = q.link.link;
		}

		Node start2 = p.link;
		p.link = null;

		return start2;
	}//End of divideList()
	
	private Node mergeSortRec(Node listStart)
	{
		if(listStart == null || listStart.link == null) //if list empty or has one element
			return listStart;

		//if more than one element
		Node start1 = listStart;
		Node start2 =divideList(listStart);
		start1 = mergeSortRec(start1);
		start2 = mergeSortRec(start2);
		Node startM = merge(start1, start2);
		return startM;
	}//End of mergeSortRec()

	public void mergeSort()
	{
		start = mergeSortRec(start);
	}//End of mergeSort()	
	
}//End of class SingleLinkedList

class MergeSortLinkedListDemo
{
	public static void main(String[] args)
    {
		SingleLinkedList list = new SingleLinkedList();

		//Create the List
		list.insertAtBeginning(3);
		list.insertAtBeginning(56);
		list.insertAtBeginning(21);
		list.insertAtBeginning(4);
		list.insertAtBeginning(64);
		list.insertAtBeginning(92);
		list.insertAtBeginning(42);
		list.insertAtBeginning(30);
		list.insertAtBeginning(89);
		list.insertAtBeginning(5);
		list.insertAtBeginning(8);

		System.out.println("List items :");
		list.display();

		list.mergeSort();

		System.out.println("List items after merge sort on list :");
		list.display();

    }//End of main()
}//End of class MergeSortLinkedListDemo
