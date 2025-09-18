//TowerOfHanoi.java : Program to solve Tower of Hanoi problem using recursion.

class TowerOfHanoiDemo
{
	static void tofh(int ndisk, char source, char temp, char dest)
	{
		if(ndisk == 1)
		{
			System.out.println("Move Disk " + ndisk + " from " + source + "-->" + dest);
			return;
		}

		tofh(ndisk-1, source, dest, temp);
		System.out.println("Move Disk " + ndisk + " from " + source + "-->" + dest);
		tofh(ndisk-1, temp, source, dest);
	}//End of tofh()	
	
	public static void main(String[] args)
	{
		char source='A', temp='B', dest='C';
		int ndisk = 3;

		System.out.println("Sequence is :");
		tofh(ndisk, source, temp, dest);		
	}//End of main()
}//End of class TowerOfHanoiDemo
