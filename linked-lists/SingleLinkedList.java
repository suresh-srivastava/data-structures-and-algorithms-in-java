//SingleLinkedList.java : Program of Single linked list.

class Node
{
	public int info;
	public	Node link;

	public	Node(int data)
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
	
	public SingleLinkedList(SingleLinkedList list)
	{
		if(list.start == null)
			start = null;
		else
		{
			Node p1, p2;
			p1 = list.start;
			p2 = start = new Node(p1.info);
			p1 = p1.link;

			while(p1 != null)
			{		
				p2.link = new Node(p1.info);
				p2 = p2.link;
				p1 = p1.link;
			}
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
				p = p.link;
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
			p = p.link;
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
			p = p.link;
		}

		return 0;
	}//End of find()	
	
	public void insertAtBeginning(int data)
	{
		Node temp;

		temp = new Node(data);
		if(!isEmpty())
			temp.link = start;

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
			while(p.link != null)
				p = p.link;

			p.link = temp;
		}
	}//End of insertAtEnd()
	
	public void insertBefore(int data, int nodeData)
	{
		Node p, temp;

		p = start;

		if(isEmpty())
			System.out.println("List is empty");
		else if(p.info == nodeData) //nodeData is in first node
		{
			temp = new Node(data);
			temp.link = p;
			start = temp;
		}
		else
		{
			//Get the reference to predecessor of node containing nodeData
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

		p = start;
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
			temp.link = start;
			start = temp;
		}
		else
		{
			p = start;
			int index = 1;
			while(p!=null && index < position-1) //Get the reference to (position-1)th node
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
			start = start.link;

	}//End of deleteAtBeginning()	
	
	public void deleteAtEnd()
	{
		Node p;

		p = start;

		if(isEmpty())
			System.out.println("List is empty");
		else if(p.link == null)
			start = p.link;
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

		p = start;
		if(isEmpty())
			System.out.println("List is empty");
		else if(p.info == nodeData) //Deletion of first node
		{
			start = p.link;
		}
		else //Deletion in between or at the end
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
			{
				p.link = p.link.link;
			}
		}//End of else
	}//End of deleteNode()	
	
	public void deleteAtPosition(int position)
	{
		Node p;

		p = start;

		if(isEmpty())
			System.out.println("List is empty");
		else if(position == 1)
		{
			start = start.link;
		}
		else
		{
			int index = 1;

			while(p.link!=null && index < position-1)
			{
				p = p.link;
				index++;
			}

			if(p.link!=null && position>0)
			{
				p.link = p.link.link;
			}
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
			p = start;
			prev = null;

			while(p != null)
			{
				next = p.link;
				p.link = prev;
				prev = p;
				p = next;
			}
			start = prev;
		}
	}//End of reverse()	
	
	public void selectionSortExchangeData()
	{
		Node p, q;
		int temp;

		p = start;

		for(p=start; p.link!=null; p=p.link)
		{
			for(q=p.link; q!=null; q=q.link)
			{
				if(p.info > q.info)
				{
					temp = p.info;
					p.info = q.info;
					q.info = temp;
				}
			}
		}
	}//End of selectionSortExchangeData()	
	
	public void selectionSortExchangeLinks()
	{
		Node p, q, r, s, temp;

		for(r=p=start; p.link!=null; r=p,p=p.link)
		{
			for(s=q=p.link; q!=null; s=q,q=q.link)
			{
				if(p.info > q.info)
				{
					temp = p.link;
					p.link = q.link;
					q.link = temp;
					if(p != start)
						r.link = q;
					s.link = p;
					if(p == start)
						start = q;
					temp = p;
					p = q;
					q = temp;
				}//End of if
			}//End of for
		}//End of for
	}//End of selectionSortExchangeLinks()	
	
	public void bubbleSortExchangeData()
	{
		Node p, q=null, end;
		int temp;

		for(end=null; end!=start.link; end=q)
		{
			for(p=start; p.link!=end; p=p.link)
			{
				q = p.link;
				if(p.info > q.info)
				{
					temp = p.info;
					p.info = q.info;
					q.info = temp;
				}
			}
		}
	}//End of bubbleSortExchangeData()
	
	public void bubbleSortExchangeLinks()
	{
		Node p, q=null, r, end, temp;

		for(end=null; end!=start.link; end=q)
		{
			for(r=p=start; p.link!=end; r=p,p=p.link)
			{
				q = p.link;
				if(p.info > q.info)
				{
					p.link = q.link;
					q.link = p;
					if(p != start)
						r.link = q;
					else
						start = q;

					//Rearranging the position of p and q for next pass
					temp = p;
					p = q;
					q = temp;
				}//End of if
			}//End of for
		}//End of for
	}//End of bubbleSortExchangeLinks()	
	
	public void mergeLists1(SingleLinkedList list, SingleLinkedList mergedList)
	{
		mergedList.start = merge1(start, list.start);
	}//End of mergeLists1()

	//Merging 2 lists to another new list
	private Node merge1(Node p1, Node p2)
	{
		Node startM;

		if(p1.info <= p2.info)
		{
			startM = new Node(p1.info);
			p1 = p1.link;
		}
		else
		{
			startM = new Node(p2.info);
			p2 = p2.link;
		}

		Node pM = startM;

		while(p1!=null && p2!=null)
		{
			if(p1.info <= p2.info)
			{
				pM.link = new Node(p1.info);
				p1 = p1.link;
			}
			else
			{
				pM.link = new Node(p2.info);
				p2 = p2.link;
			}
			pM = pM.link;
		}

		//Second list is finished. Add the elements of first list.
		while(p1 != null)
		{
			pM.link = new Node(p1.info);
			p1 = p1.link;
			pM = pM.link;
		}

		//First list is finished. Add the elements of second list.
		while(p2 != null)
		{
			pM.link = new Node(p2.info);
			p2 = p2.link;
			pM = pM.link;
		}

		return startM;
	}//End of merge1()	
	
	public void mergeLists2(SingleLinkedList list, SingleLinkedList mergedList)
	{
		mergedList.start = merge2(start, list.start);
		start = null;
		list.start = null;
	}//End of mergeLists2()

	//Merging lists by exchanging links
	private Node merge2(Node p1, Node p2)
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

		while(p1!=null && p2!=null)
		{
			if(p1.info <= p2.info)
			{
				pM.link = p1;
				p1 = p1.link;
				pM = pM.link;
			}
			else
			{
				pM.link = p2;
				p2 = p2.link;
				pM = pM.link;
			}
		}

		//Second list is finished. Add the remaining elements of first list
		if(p1 != null)
			pM.link = p1;

		//First list is finished. Add the remaining elements of second list
		if(p2 != null)
			pM.link = p2;

		return startM;
	}//End of merge2()	
	
	public void mergeSort()
	{
		start = mergeSortRec(start);
	}//End of mergeSort()

	private Node mergeSortRec(Node listStart)
	{
		//If the list is empty or has only one node
		if(listStart==null || listStart.link==null)
			return listStart;

		//If more than one element
		Node start1 = listStart;
		Node start2 = divideList(listStart);
		start1 = mergeSortRec(start1);
		start2 = mergeSortRec(start2);
		Node startM = merge2(start1, start2);

		return startM;
	}//End of mergeSortRec()

	private Node divideList(Node p)
	{
		Node q = p.link.link;

		while(q!=null && q.link!=null)
		{
			p = p.link;
			q = q.link.link;
		}

		Node start2 = p.link;
		p.link = null;

		return start2;
	}//End of divideList()	
	
	public void concatenate(SingleLinkedList list)
	{
		if(start == null)
		{
			start = list.start;
			return;
		}

		if(list.start == null)   
			return;

		Node p = start;
		while(p.link != null)
			p = p.link;

		p.link = list.start;	
		list.start = null;
	}//End of concatenate()
	
	public void insertCycle(int nodeData)
	{
		Node p, prev, cycleP;

		cycleP = null;

		if(start==null || start.link==null)
			System.out.println("Cycle cannot be inserted");
		else
		{
			p = start;
			prev = start;
			while(p != null)
			{
				if(p.info == nodeData)
				{
					cycleP = p;
				}
				prev = p;
				p = p.link;
			}

			if(cycleP != null)
			{
				System.out.println("cycleP.info : " + cycleP.info);
				prev.link = cycleP;

				//Display the list
				p = start;
				while(p != cycleP)
				{
					System.out.println("p.info = " + p.info);
					p = p.link;
				}

				p = cycleP;
				do
				{
					System.out.println("p.info = " + p.info);
					p = p.link;
				}while(p != cycleP);
			}//End of if
			else
				System.out.println(nodeData + " is not found in the list");
		}//End of else
	}//End of insertCycle()

	private Node findCycle()
	{
		Node slowP, fastP;

		if(start==null || start.link==null)
			return null;
		else
		{
			slowP = start;
			fastP = start;

			while(fastP!=null && fastP.link!=null)
			{
				slowP = slowP.link;
				fastP = fastP.link.link;

				if(slowP == fastP)
				{
					System.out.println("slowP and fastP meets here");
					System.out.println("slowP.info = " + slowP.info);
					System.out.println("fastP.info = " + fastP.info);

					return slowP;
				}
			}//End of while

			return null;
		}//End of else

	}//End of findCycle()

	public boolean hasCycle()
	{
		if(findCycle() != null)
			return true;
		else
			return false;
	}//End of hasCycle()

	public void removeCycle()
	{
		Node p, p1, p2;

		p = findCycle();

		if(p == null)
			System.out.println("There is no cycle in list.");
		else
		{
			System.out.println("Node where cycle was detected : " + p.info);
			p1 = p;
			p2 = p;

			//Find the length of cycle
			int cycleLength = 0;
			do
			{
				cycleLength++;
				p1 = p1.link;
			}while(p1 != p2);

			System.out.println("Cycle Length : " + cycleLength);

			//Find the remaining length
			int remLength = 0;
			p1 = start;
			while(p1 != p2)
			{
				remLength++;
				p1 = p1.link;
				p2 = p2.link;
			}

			System.out.println("Remaining Length : " + remLength);

			int listLength = cycleLength + remLength;

			System.out.println("The List is :");
			p1 = start;
			for(int i=1; i<=listLength-1; i++)
			{
				System.out.println(p1.info);
				p1 = p1.link;
			}
			System.out.println(p1.info);
			p1.link = null;
		}
	}//End of removeCycle()
	
}//End of class SingleLinkedList

class SingleLinkedListDemo
{
	public static void main(String[] args)
	{
		SingleLinkedList list1 = new SingleLinkedList();

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
		
		SingleLinkedList list2 = new SingleLinkedList(list1);
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
		
		SingleLinkedList list3 = new SingleLinkedList();
		list3.insertAtEnd(20);
		list3.insertAtEnd(10);
		list3.insertAtEnd(50);
		list3.insertAtEnd(30);
		list3.insertAtEnd(40);

		System.out.println("List3 Items :");
		list3.display();		
		
		SingleLinkedList list4 = new SingleLinkedList(list3);
		list4.selectionSortExchangeData();
		System.out.println("List4 Items after selection sort (exchange data) :");
		list4.display();		
		
		SingleLinkedList list5 = new SingleLinkedList(list3);
		list5.selectionSortExchangeLinks();
		System.out.println("List5 Items after selection sort (exchange links) :");
		list5.display();
		
		SingleLinkedList list6 = new SingleLinkedList(list3);
		list6.bubbleSortExchangeData();
		System.out.println("List6 Items after bubble sort (exchange data) :");
		list6.display();

		SingleLinkedList list7 = new SingleLinkedList(list3);
		list7.bubbleSortExchangeLinks();
		System.out.println("List7 Items after bubble sort (exchange links) :");
		list7.display();
		
		SingleLinkedList list8 = new SingleLinkedList();
		SingleLinkedList list9 = new SingleLinkedList();
		SingleLinkedList list10 = new SingleLinkedList();
		SingleLinkedList list11 = new SingleLinkedList();		
		
		list8.insertAtEnd(10);
		list8.insertAtEnd(20);
		list8.insertAtEnd(30);
		list8.insertAtEnd(40);
		list8.insertAtEnd(50);

		System.out.println("List8 Items :");
		list8.display();

		list9.insertAtEnd(15);
		list9.insertAtEnd(25);
		list9.insertAtEnd(30);
		list9.insertAtEnd(45);
		list9.insertAtEnd(55);

		System.out.println("List9 Items :");
		list9.display();

		list8.mergeLists1(list9, list10);
		System.out.println("List10 Items - Mergeing 2 Lists to another new list :");
		list10.display();

		list8.mergeLists2(list9, list11);
		System.out.println("List11 Items - Merged List (exchange links) :");
		list11.display();		
		
		SingleLinkedList list12 = new SingleLinkedList();

		list12.insertAtEnd(10);
		list12.insertAtEnd(5);
		list12.insertAtEnd(20);
		list12.insertAtEnd(15);
		list12.insertAtEnd(30);
		list12.insertAtEnd(25);
		list12.insertAtEnd(40);
		list12.insertAtEnd(35);

		System.out.println("List12 Items :");
		list12.display();

		list12.mergeSort();

		System.out.println("List12 Items after merge sort :");
		list12.display();		
		
		SingleLinkedList list13 = new SingleLinkedList();
		SingleLinkedList list14 = new SingleLinkedList();

		list13.insertAtEnd(10);
		list13.insertAtEnd(20);
		list13.insertAtEnd(30);
		list13.insertAtEnd(40);
		list13.insertAtEnd(50);

		System.out.println("List13 Items :");
		list13.display();

		list14.insertAtEnd(5);
		list14.insertAtEnd(15);
		list14.insertAtEnd(25);
		list14.insertAtEnd(35);
		list14.insertAtEnd(45);

		System.out.println("List14 Items :");
		list14.display();

		list13.concatenate(list14);

		System.out.println("List13 Items after concatenation :");
		list13.display();		
		
		SingleLinkedList list15 = new SingleLinkedList();

		list15.insertAtEnd(10);
		list15.insertAtEnd(20);
		list15.insertAtEnd(30);
		list15.insertAtEnd(40);
		list15.insertAtEnd(50);
		list15.insertAtEnd(60);
		list15.insertAtEnd(70);
		list15.insertAtEnd(80);

		System.out.println("Finding a cycle and its removal in list :");
		System.out.println("find(40) = " + list15.find(40));
		list15.insertCycle(40);
		System.out.println("Has cycle : " + (list15.hasCycle() ? "True" : "False"));
		list15.removeCycle();
		System.out.println("Has cycle : " + (list15.hasCycle() ? "True" : "False"));		
		
	}//End of main()	
	
}//End of class SingleLinkedListDemo
