//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//UndirectedWeightedGraph.java : Program to create minimum spanning tree using Kruskal's algorithm.

class Vertex
{
    public String name;
    public int father;

    public Vertex(String name)
    {
        this.name = name;
    }
}//End of class Vertex

class Edge
{
    public int u;
	public int v;
	public int wt;

    public Edge(){}
    public Edge(int u, int v, int wt)
    {
        this.u = u;
        this.v = v;
        this.wt = wt;
    }

}//End of class Edge

class QueueNode
{
    public Edge info;  
    public QueueNode link;

    public QueueNode(Edge edge) 
    {
	    info = edge;
	    link = null;
    }
}//End of QueueNode

class PriorityQueue
{
    private QueueNode front;

    public PriorityQueue()
    {
	    front = null;
    }//End of PriorityQueue()

    public boolean isEmpty()
    {
    	return (front == null);
    }//End of isEmpty()

    public void enqueue(Edge edge)
    {
	    QueueNode temp, ptr;
	
	    temp = new QueueNode(edge);

	    //Queue is empty or element to be added has priority more than first element
	    if(isEmpty() || edge.wt<front.info.wt)
	    {
		    temp.link = front;
		    front = temp;
	    }
	    else
	    {
		    ptr = front;
		    while(ptr.link!=null && ptr.link.info.wt<=edge.wt)
		    	ptr = ptr.link;
		    temp.link = ptr.link;
		    ptr.link = temp;
	    }
    }//End of enqueue()

    public Edge dequeue()
    {
	    Edge edge;

	    if(isEmpty())
        {
	      throw new RuntimeException("Queue Underflow");
        }
	    else
	    {
		    edge = front.info;
		    front = front.link;
	    }

	    return edge;
    }//End of dequeue()

}//End of class PriorityQueue

class UndirectedWeightedGraph
{
    private static final int MAX_SIZE = 30;
    private int nVertices;
    private int nEdges;
    private int[][] adj;
    private Vertex[] vertexList;
    private Edge[] treeEdges;
	private static final int NIL = -1;

    public UndirectedWeightedGraph()
    {
        adj = new int[MAX_SIZE][MAX_SIZE];
        vertexList = new Vertex[MAX_SIZE];
        nVertices = 0;
        nEdges = 0;

        for(int i=0; i<MAX_SIZE; i++)
        {
            for(int j=0; j<MAX_SIZE; j++)
            {
                adj[i][j] = 0;
            }
        }

        treeEdges = new Edge[MAX_SIZE];

    }//End of UndirectedWeightedGraph()

    public void insertVertex(String vertexName)
    {
        vertexList[nVertices++] = new Vertex(vertexName);
    }//End of insertVertex()

    private int getIndex(String vertexName)
    {
        for(int i=0; i<nVertices; i++)
        {
            if(vertexName == vertexList[i].name)
                return i;
        }

        throw new RuntimeException("Invalid Vertex");
    }//End of getIndex()

    public void insertEdge(String source, String destination, int weight)
    {
        int u = getIndex(source);
        int v = getIndex(destination);

        if (u == v)
        	System.out.println("Not a valid edge");
        else if(adj[u][v] != 0)
        	System.out.println("Edge already present");
        else
        {
            adj[u][v] = weight;
            adj[v][u] = weight;
            nEdges++;
        }
    }//End of insertEdge()

    public void display()
    {
        for(int i=0; i<nVertices; i++)
        {
            for(int j=0; j<nVertices; j++)
            	System.out.print(adj[i][j] + " ");
            System.out.println();
        }
    }//End of display()

    private boolean isAdjacent(int u, int v)
    {
        return (adj[u][v] != 0);
    }//End of isAdjacent()

    private void kruskalsAlgorithm()
    {
        int count = 0;	//Number of edges in the tree

        PriorityQueue edgeQueue = new PriorityQueue();

        //Inserting all the edges in priority queue
        for(int u=0; u<nVertices; u++)
        {
	        for(int v=u; v<nVertices; v++)
	        {
		        if(isAdjacent(u,v))
		        {
			        edgeQueue.enqueue(new Edge(u,v,adj[u][v]));
		        }
	        }
        }

        //Initialize the father of vertices to NIL
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].father = NIL;
        }

        int v1, v2, v1Root=NIL, v2Root=NIL;
        Edge edge = new Edge();

        while(!edgeQueue.isEmpty() && count<nVertices-1)
        {
	        edge = edgeQueue.dequeue();

	        v1 = edge.u;
	        v2 = edge.v;

	        while(v1!=NIL)
	        {
		        v1Root = v1;
		        v1 = vertexList[v1].father;
	        }

	        while(v2!=NIL)
	        {
		        v2Root = v2;
		        v2 = vertexList[v2].father;
	        }

	        if(v1Root != v2Root)	//Include the edge in tree
	        {
		        treeEdges[++count] = new Edge(edge.u,edge.v,edge.wt);
		        vertexList[v2Root].father = v1Root;
	        }

        }//End of while

        treeEdges[count+1]=null;

        if(count < nVertices-1)
        {
	        throw new RuntimeException("Graph is not connected, spanning tree is not possible.");
        }

    }//End of kruskalsAlgorithm()

    public void minimumSpanningTree()
    {
        int treeWeight=0;

        kruskalsAlgorithm();

        System.out.println("Minimum Spanning Tree Edges :");
        for(int i=1; i<=nVertices-1; i++)
        {
        	System.out.println("Edge - (" + treeEdges[i].u + "-" + treeEdges[i].v + ")");
	        treeWeight += adj[treeEdges[i].u][treeEdges[i].v];
        }

        System.out.println("Minimum Spanning Tree Weight : " + treeWeight);

    }//End of minimumSpanningTree()

}//End of class UndirectedWeightedGraph

class UndirectedWeightedGraphDemo
{
	public static void main(String args[])
    {
        UndirectedWeightedGraph uwGraph = new UndirectedWeightedGraph();

        try
        {
	        //Creating the graph, inserting the vertices and edges
	        //Connected graph
            uwGraph.insertVertex("0");
            uwGraph.insertVertex("1");
            uwGraph.insertVertex("2");
            uwGraph.insertVertex("3");
            uwGraph.insertVertex("4");
            uwGraph.insertVertex("5");
            uwGraph.insertVertex("6");
            uwGraph.insertVertex("7");
            uwGraph.insertVertex("8");

            uwGraph.insertEdge("0","1",9);
            uwGraph.insertEdge("0","3",4);
            uwGraph.insertEdge("0","4",2);
            uwGraph.insertEdge("1","2",10);
            uwGraph.insertEdge("1","4",8);
            uwGraph.insertEdge("2","4",7);
            uwGraph.insertEdge("2","5",5);
            uwGraph.insertEdge("3","4",3);
            uwGraph.insertEdge("3","6",18);
            uwGraph.insertEdge("4","5",6);
            uwGraph.insertEdge("4","6",11);
            uwGraph.insertEdge("4","7",12);
            uwGraph.insertEdge("4","8",15);
            uwGraph.insertEdge("5","8",16);
            uwGraph.insertEdge("6","7",14);
            uwGraph.insertEdge("7","8",20);

	        //Not connected graph
            //uwGraph.insertVertex("0");
            //uwGraph.insertVertex("1");
            //uwGraph.insertVertex("2");
            //uwGraph.insertVertex("3");
            //uwGraph.insertVertex("4");
            //uwGraph.insertVertex("5");
            //uwGraph.insertVertex("6");
            //uwGraph.insertVertex("7");

            //uwGraph.insertEdge("0","1",6);
            //uwGraph.insertEdge("0","2",3);
            //uwGraph.insertEdge("0","3",8);
            //uwGraph.insertEdge("1","4",9);
            //uwGraph.insertEdge("2","3",7);
            //uwGraph.insertEdge("2","4",5);
            //uwGraph.insertEdge("3","4",4);

            //uwGraph.insertEdge("5","6",2);
            //uwGraph.insertEdge("5","7",8);
            //uwGraph.insertEdge("6","7",5);

	        //Display the graph
	        uwGraph.display();
	        System.out.println();

	        uwGraph.minimumSpanningTree();

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class UndirectedWeightedGraphDemo
