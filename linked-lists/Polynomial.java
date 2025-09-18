//Polynomial.java : Program of Polynomial expression creation, addition and multiplication using linked list.

class Node
{
	public int coeff;
	public int expo;
	public Node link;

	public Node(int coefficient, int exponent)
	{
		coeff = coefficient;
		expo = exponent;
		link = null;
	}
}//End of class Node

class Polynomial
{
	private Node start;

	public Polynomial()
	{
		start = null;
	}//End of Polynomial()

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
				System.out.print(p.coeff);
				if(p.expo == 1)
					System.out.print("x");
				else if(p.expo > 1)
					System.out.print("x^" + p.expo);
				p = p.link;
				if(p!=null)
					System.out.print(" + ");
			}
			System.out.println();
		}
		else
			System.out.println("Zero polynomial");
	}//End of display()

	public void insert(int coefficient, int exponent)
	{
		Node p, temp;

		temp = new Node(coefficient, exponent);
		//List empty or exponent greater than first one
		if(isEmpty() || exponent > start.expo)
		{
			temp.link = start;
			start = temp;	
		}
		else
		{
			p = start;
			while(p.link!=null && p.link.expo >= exponent)
				p = p.link;
			temp.link = p.link;
			p.link = temp;
		}
	}//End of insert()	
	
	//Required for addition of polynomials
	public void insertAtEnd(int coefficient, int exponent)
	{
		Node p, temp;

		temp = new Node(coefficient,exponent);

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

	public void addition(Polynomial list, Polynomial resultList)
	{
		Node p1 = start;
		Node p2 = list.start;
		
		while(p1!=null && p2!=null)
		{
			if(p1.expo > p2.expo)
			{
				resultList.insert(p1.coeff, p1.expo);
				p1 = p1.link;
			}
			else if(p2.expo > p1.expo)
			{
				resultList.insert(p2.coeff, p2.expo);
				p2 = p2.link;
			}
			else if(p1.expo == p2.expo)
			{
				resultList.insert(p1.coeff+p2.coeff, p1.expo);
				p1 = p1.link;
				p2 = p2.link;
			}
		}
		//If poly2 is finished and elements left in poly1
		while(p1 != null)
		{
			resultList.insert(p1.coeff, p1.expo);
			p1 = p1.link;
		}
		//If poly1 is finished and elements left in poly2
		while(p2 != null)
		{
			resultList.insert(p2.coeff, p2.expo);
			p2 = p2.link;
		}

	}//End of addition()	
	
	public void multiplication(Polynomial list, Polynomial resultList)
	{
		Node p1 = start;
		Node p2 = list.start;
		Node p2Start = p2;

		if(p1==null || p2==null)
			System.out.println("Multiplied polynomial is zero polynomial");
		else
		{
			while(p1 != null)
			{
				p2 = p2Start;
				while(p2 != null)
				{
					resultList.insert(p1.coeff*p2.coeff, p1.expo+p2.expo);
					p2 = p2.link;	
				}
				p1 = p1.link;
			}
		}

	}//End of multiplication()	
	
}//End of class Polynomial

class PolynomialDemo
{
    public static void main(String[] args)
    {
    	Polynomial list1 = new Polynomial();
    	Polynomial list2 = new Polynomial();
    	Polynomial list3 = new Polynomial();
    	Polynomial list4 = new Polynomial();

    	list1.insert(4,3);
    	list1.insert(5,2);
    	list1.insert(-3,1);

    	System.out.println("Polynomial List1 :");
    	list1.display();

    	list2.insert(2,5);
    	list2.insert(6,4);
    	list2.insert(1,2);
    	list2.insert(8,0);

    	System.out.println("Polynomial List2 :");
    	list2.display();

    	//Polynomial addition
    	list1.addition(list2, list3);

    	System.out.println("After addition of list1 and list2 :");
    	list3.display();

    	//Polynomial multiplication
    	list1.multiplication(list2, list4);

    	System.out.println("After multiplication of list1 and list2 :");
    	list4.display();

    }//End of main()
}//End of class PolynomialDemo
