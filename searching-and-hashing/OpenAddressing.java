//OpenAddressing.java : Program of Open Addressing - Linear Probing

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
	}
	
}//End of class Employee

class HashTable
{
	static final int MAX_SIZE = 11;
	private Employee[] arr;
	private	int m;	//size of array
	private int n;	//number of employee records
	private int status[];
	private static final int EMPTY = 0;
	private	static final int DELETED = 1;
	private	static final int OCCUPIED = 2;

	public HashTable()
	{
		m = MAX_SIZE;
		n = 0;
		arr = new Employee[m];
		status = new int[m];

		for(int i=0; i<m; i++)
			status[i] = EMPTY;

	}//End of HashTable()
	
	public int hash(int key)
	{
		return key%m;
	}//End of hash()	
	
	public void insert(Employee emp)
	{
		int key = emp.getEmployeeId();
		int h = hash(key);

		int location = h;

		for(int i=1; i<m; i++)
		{
			if(status[location]==EMPTY || status[location]==DELETED)
			{
				arr[location] = emp;
				status[location] = OCCUPIED;
				n++;
				return;
			}

			if(arr[location].getEmployeeId() == key)
			{
				System.out.println("Duplicate key");
				return;
			}

			location = (h+i)%m;
		}
		
		System.out.println("Table is full");
	}//End of insert()	
	
	public boolean search(int key)
	{
		int h = hash(key);
		int location = h;

		for(int i=1; i<m; i++)
		{
			if(status[location]==EMPTY || status[location]==DELETED)
				return false;

			if(arr[location].getEmployeeId() == key)
			{
				System.out.println(arr[location]);
				return true; 
			}

			location = (h+i)%m;
		}

		return false;
	}//End of search()
	
	public void del(int key)
	{
		int h = hash(key);
		int location = h;

		for(int i=1; i<m; i++)
		{
			if(status[location]==EMPTY || status[location]==DELETED)
			{
				System.out.println("Key not found");
				return;
			}

			if(arr[location].getEmployeeId() == key)
			{
				status[location] = DELETED;
				n--;
				System.out.println("Record " + arr[location] + " deleted");
				return;
			}

			location = (h + i) % m;
		}
	}//End of del()	
	
	public void display()
	{
		for(int i=0; i<m; i++)
		{
			System.out.print("[" + i + "] --> ");

			if(status[i] == OCCUPIED)
				System.out.println(arr[i]);
			else
				System.out.println("___");
		}
	}//End of display()	
	
}//End of class HashTable

class OpenAddressingDemo
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
}//End of class OpenAddressingDemo
