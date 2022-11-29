//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//UndirectedWeightedGraph.java : Program to create minimum spanning tree using prim's algorithm.

class Vertex
{
    public String name;
    public int status;
    public int predecessor;
    public int length;

    public Vertex(String name)
    {
        this.name = name;
    }
}//End of class Vertex

class Edge
{
    public int u;
	public int v;

    public Edge(int u, int v)
    {
        this.u = u;
        this.v = v;
    }
}//End of class Edge

class UndirectedWeightedGraph
{
    private static final int MAX_SIZE = 30;
    private int nVertices;
    private int nEdges;
    private int[][] adj;
    private Vertex[] vertexList;
    private Edge[] treeEdges;
	private static final int TEMPORARY = 0;
	private static final int PERMANENT = 1;
	private static final int INFINITY = 9999;
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
        for (int i = 0; i < nVertices; i++)
        {
            if (vertexName == vertexList[i].name)
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

    //Returns the temporary vertex with minimum value of length,
    //Returns NIL if no temporary vertex left or all temporary vertices left have length INFINITY
    private int getMinimumTemporary()
    {
        int min = INFINITY;
        int k = NIL;

        for(int i=0; i<nVertices; i++)
        {
	        if(vertexList[i].status==TEMPORARY && vertexList[i].length<min)
	        {
		        min = vertexList[i].length;
		        k=i;
	        }
        }

        return k;

    }//End of getMinimumTemporary()

    private void primsAlgorithm(int r)
    {
        int count = 0;	//Number of edges in the tree

        //Make all vertices temporary
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].status = TEMPORARY;
	        vertexList[i].predecessor = NIL;
	        vertexList[i].length = INFINITY;
        }

        //Make pathLength of source vertex equal to 0
        vertexList[r].length = 0;

        while(true)
        {
	        //Search for temporary vertex with minimum pathLength and make it current vertex
	        int current = getMinimumTemporary();

	        if(current == NIL)
	        {
		        if(count == nVertices-1)
		        {
			        break;	//No temporary vertex left
		        }
		        else		//Temporary vertices left with length INFINITY
		        {
			        throw new RuntimeException("Graph is not connected, spanning tree is not possible.");
		        }
	        }

	        //Make current vertex PERMANENT
	        vertexList[current].status = PERMANENT;

	        //Insert the edge (vertexList[current]->predecessor,current) into the tree,
	        //except when the current vertex is root
	        if(current != r)
	        {
                treeEdges[++count] = new Edge(vertexList[current].predecessor,current);
	        }

	        for(int v=0; v<nVertices; v++)
	        {
		        //Checks for adjacent temporary vertices
		        if(isAdjacent(current,v) && vertexList[v].status==TEMPORARY)
		        {
			        if((adj[current][v]) < vertexList[v].length)
			        {
				        vertexList[v].predecessor = current;	//Relabel
				        vertexList[v].length = adj[current][v];
			        }
		        }
	        }//End of for
        }//End of while

    }//End of primsAlgorithm()

    public void minimumSpanningTree(String root)
    {
        int r = getIndex(root);
        int treeWeight=0;

        primsAlgorithm(r);

        System.out.println("Root vertex : " + root);

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

            uwGraph.insertEdge("0","1",6);
            uwGraph.insertEdge("0","2",2);
            uwGraph.insertEdge("0","3",3);
            uwGraph.insertEdge("0","4",10);
            uwGraph.insertEdge("1","3",11);
            uwGraph.insertEdge("1","5",9);
            uwGraph.insertEdge("2","3",14);
            uwGraph.insertEdge("2","4",8);
            uwGraph.insertEdge("3","4",7);
            uwGraph.insertEdge("3","5",5);
            uwGraph.insertEdge("4","5",4);

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

	        uwGraph.minimumSpanningTree("0");

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class UndirectedWeightedGraphDemo
