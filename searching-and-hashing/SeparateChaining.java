//SeparateChaining.java : Program of Separate Chaining.

class Employee
{
	private int employeeId;
	private String employeeName;

	public Employee(int id, String name)
	{
		employeeId = id;
		employeeName = name;
	}//End of Employee()	
	
	public int getEmployeeId()
	{
		return employeeId;
	}//End of getEmployeeId()

	public void setEmployeeId(int id)
	{
		employeeId = id;
	}//End of setEmployeeId()	
	
	public String toString()
	{
		return  employeeId + " " + employeeName + " ";
	}//End of toString()
	
}//End of class Employee

class Node
{
	public Employee info;
	public Node link;

	public Node(Employee data)
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
	
	public void display()
	{
		Node p;

		if(!isEmpty())
		{
			p = start;
			while(p != null)
			{
				System.out.print(p.info);
				p = p.link;
			}
			System.out.println();
		}
		else
			System.out.println(" ___");
	}//End of display()
	
	public Node search(int key)
	{
		Node p = start;
		
		while(p != null)
		{
			if(p.info.getEmployeeId() == key)
				break;
			p = p.link;
		}

		return p;
	}//End of search()	
	
	public void insertAtBeginning(Employee data)
	{
		Node temp;

		temp = new Node(data);
		if(!isEmpty())
			temp.link = start;

		start = temp;
	}//End of insertAtBeginning()	
	
	public void deleteNode(int key)
	{
		Node p;

		p = start;
		if(isEmpty())
			System.out.println("Key " + key + " not present");
		else if(p.info.getEmployeeId() == key) //Deletion of first node
			start = p.link;
		else //Deletion in between or at the end
		{
			while(p.link != null)
			{
				if(p.link.info.getEmployeeId() == key)
					break;
				p = p.link;
			}
			if(p.link == null)
				System.out.println("Key " + key + " not present");
			else
				p.link = p.link.link;
		}//End of else
	}//End of deleteNode()	
	
}//End of class SingleLinkedList

class HashTable
{
	public static final int MAX_SIZE = 11;
	
	private SingleLinkedList[] arr;
	private	int m;  //size of the array
	private	int n;  //number of records

	public HashTable()
	{
		m = MAX_SIZE;
		n = 0;
		arr = new SingleLinkedList[m];
	}//End of HashTable()
	
	private int hash(int key)
	{
		return key%m;
	}//End of hash()	
	
	public boolean search(int key)
	{
		int h = hash(key);
		Node p = arr[h].search(key);

		if(p != null)
		{
			System.out.println(p.info);
			return true;
		}

		return false;
	}//End of search()
	
	public void insert(Employee emp)
	{
		int key = emp.getEmployeeId();
		int h = hash(key);
		
		if(arr[h]==null)
			arr[h] = new SingleLinkedList();		
		
		if(search(key))
		{
			System.out.println(" Duplicate key");
			return;
		}
		arr[h].insertAtBeginning(emp);
		n++;
	}//End of insert()	
	
	public void del(int key)
	{
		int h = hash(key);
		arr[h].deleteNode(key);
		n--;
	}//End of del()	
	
	public void display()
	{
		for(int i=0; i<m; i++)
		{
			System.out.print("[" + i + "]  -->");

			if(arr[i] != null)
				arr[i].display() ;
			else
				System.out.println(" ___");
		}
	}//End of display()	
	
}//End of class HashTable

class SeparateChainingDemo
{
	public static void main(String[] args)
    {
		HashTable table = new HashTable();

		table.insert(new Employee(15,"Suresh"));
		table.insert(new Employee(28,"Manish"));
		table.insert(new Employee(20,"Abhishek"));
		table.insert(new Employee(45,"Srikant"));
		table.insert(new Employee(82,"Rajesh"));
		table.insert(new Employee(98,"Amit"));
		table.insert(new Employee(77,"Vijay"));
		table.insert(new Employee(9,"Alok"));
		table.insert(new Employee(34,"Vimal"));
		table.insert(new Employee(49,"Deepak"));

		table.display();

		System.out.println((table.search(15) ? "Key found" : "Key not found"));

		table.del(15);
		System.out.println((table.search(15) ? "Key found" : "Key not found"));

		table.display();

    }//End of main()
}//End of class SeparateChainingDemo
