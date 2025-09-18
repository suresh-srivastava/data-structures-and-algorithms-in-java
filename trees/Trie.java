//Trie.java : Program to implement trie.

class Node
{
	public final int MAX_SIZE = 26;
	public Node[] links; //links to the child node
	public boolean eok; //end of key

	public Node() 
	{
		links = new Node[MAX_SIZE];
		eok = false;
		for(int i=0; i<MAX_SIZE; i++)
			links[i] = null;
	}
}//End of class Node

class Trie 
{
	public final int MAX_SIZE = 26;
	private Node root;

	public Trie()
	{
		root = new Node();
	}//End of Trie()

	public void insert(String key)
	{
		Node p = root;

		for(int i=0; i<key.length(); i++)
		{
			//If letter of key is not there
			if(p.links[key.charAt(i)-'a'] == null)
			{
				p.links[key.charAt(i)-'a'] = new Node();
			}

			p = p.links[key.charAt(i)-'a']; //Move to the next child node
		}
		p.eok = true; //end of key
	}//End of insert()	
	
	public boolean search(String key)
	{
		Node p = root;

		for(int i=0; i<key.length(); i++)
		{
			if(p.links[key.charAt(i)-'a'] == null)
			{
				//key is not in the Trie
				return false;
			}

			p = p.links[key.charAt(i)-'a']; //Move to the next child node
		}

		return p.eok;
	}//End of search()	
	
	public boolean startsWith(String prefix)
	{
		Node p = root;

		for(int i=0; i<prefix.length(); i++)
		{
			if(p.links[prefix.charAt(i)-'a'] == null)
			{
				//No prefix
				return false;
			}

			p = p.links[prefix.charAt(i)-'a']; //Move to the next child node
		}

		//prefix found
		return true;
	}//End of startsWith()	
	
	private void display(Node p, String prefix)
	{
		if(p.eok)
			System.out.println(prefix);

		for (int i=0; i<MAX_SIZE; i++) 
		{
			if(p.links[i] != null)
			{
				display(p.links[i], prefix+(char)('a'+i));
			}
		}
	}//End of display()

	public void display()
	{
		String str = "";
		display(root,str);
	}//End of display()	
	
	public void del(String key)
	{
		Node p = root;

		for(int i=0; i<key.length(); i++)
		{
			if(p.links[key.charAt(i)-'a'] == null)
			{
				System.out.println("key is not in the Trie");
				return;
			}
			p = p.links[key.charAt(i)-'a']; //Move to the next child node
		}

		if(p.eok == false)
			System.out.println("key is not in the Trie");
		else
			p.eok = false;
	}//End of del()	
	
}//End of class Trie

class TrieDemo
{
	public static void main(String[] args)
    {
		Trie trie = new Trie();

		trie.insert("lucknow");
		trie.insert("lucknowcity");
		trie.insert("luxembourg");
		trie.insert("lux");
	    
		//Search in trie
		System.out.println("search(\"lucknowp\") : " + (trie.search("lucknowp") ? "True" : "False"));

		System.out.println("search(\"luxembourg\") : " + (trie.search("luxembourg") ? "True" : "False"));

		//Prefix in trie
		System.out.println("startsWith(\"luxe\") : " + (trie.startsWith("luxe") ? "True" : "False"));

		System.out.println("Trie keys are :");
		trie.display();

		//Deletion of key in trie
		trie.del("luxembourg");

		System.out.println("After deleting luxembourg, trie keys are :");
		trie.display();		
    }//End of main()
}//End of class TrieDemo
