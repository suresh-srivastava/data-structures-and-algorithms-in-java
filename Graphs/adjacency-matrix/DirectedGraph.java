//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraph.java : Program for directed graph using adjacency matrix.

class Vertex
{
	public String name;

	public Vertex(String name)
	{
		this.name = name;
	}
}//End of class Vertex

class DirectedGraph
{
	private static final int MAX_SIZE = 30;
	private int nVertices;
	private int nEdges;
	private int[][] adj;
	private Vertex[] vertexList;

	public DirectedGraph()
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
    }//End of DirectedGraph()

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

    public void insertEdge(String source, String destination)
    {
        int u = getIndex(source);
        int v = getIndex(destination);

        if(u == v)
        	System.out.println("Not a valid edge");
        else if(adj[u][v] != 0)
        	System.out.println("Edge already present");
        else
        {
            adj[u][v] = 1;
            nEdges++;
        }
    }//End of insertEdge()

    public void deleteEdge(String source, String destination)
    {
        int u = getIndex(source);
        int v = getIndex(destination);

        if(adj[u][v] != 0)
        {
            adj[u][v] = 0;
            nEdges--;
        }
        else
        {
        	System.out.println("Edge doesn't exist");
        }
    }//End of deleteEdge()

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

    public boolean edgeExists(String source, String destination)
    {
        return isAdjacent(getIndex(source), getIndex(destination));
    }//End of edgeExists()

    public int getOutdegree(String vertex)
    {
        int u = getIndex(vertex);

        int outdegree = 0;
        for(int v=0; v< nVertices; v++)
        {
            if(adj[u][v] != 0)
                outdegree++;
        }

        return outdegree;
    }//End of getOutdegree()

    public int getIndegree(String vertex)
    {
        int u = getIndex(vertex);

        int indegree = 0;
        for(int v=0; v<nVertices; v++)
        {
            if(adj[v][u] != 0)
                indegree++;
        }

        return indegree;
    }//End of getIndegree()

}//End of class DirectedGraph

class DirectedGraphDemo
{
	public static void main(String args[])
    {
        DirectedGraph dGraph = new DirectedGraph();

        try
        {
            //Creating the graph, inserting the vertices and edges
            dGraph.insertVertex("0");
            dGraph.insertVertex("1");
            dGraph.insertVertex("2");
            dGraph.insertVertex("3");

            dGraph.insertEdge("0","3");
            dGraph.insertEdge("1","2");
            dGraph.insertEdge("2","3");
            dGraph.insertEdge("3","1");
            dGraph.insertEdge("0","2");

            //Display the graph
            dGraph.display();
            System.out.println();

            //Deleting an edge
            dGraph.deleteEdge("0","2");

            //Display the graph
            dGraph.display();

            //Check if there is an edge between two vertices
            System.out.println("Edge exist : " + (dGraph.edgeExists("2","3") ? "True" : "False"));

            //Display Indegree and Outdegree of a vertex
            System.out.println("Outdegree : " + dGraph.getOutdegree("3"));
            System.out.println("Indegree : " + dGraph.getIndegree("3"));

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo

