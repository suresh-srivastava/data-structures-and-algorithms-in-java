//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedWeightedGraph.java : Program for directed graph with weight on edge using adjacency matrix.

class Vertex
{
    public String name;

    public Vertex(String name)
    {
        this.name = name;
    }
}//End of class Vertex

class DirectedWeightedGraph
{
    private static final int MAX_SIZE = 30;
    private int nVertices;
    private int nEdges;
    private int[][] adj;
    private Vertex[] vertexList;

    public DirectedWeightedGraph()
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
    }//End of DirectedWeightedGraph()

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

        if(u == v)
        	System.out.println("Not a valid edge");
        else if(adj[u][v] != 0)
        	System.out.println("Edge already present");
        else
        {
            adj[u][v] = weight;
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
        for(int v=0; v<nVertices; v++)
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

}//End of class DirectedWeightedGraph

class DirectedWeightedGraphDemo
{
	public static void main(String[] args)
    {
        DirectedWeightedGraph dwGraph = new DirectedWeightedGraph();

        try
        {
            //Creating the graph, inserting the vertices and edges
            dwGraph.insertVertex("0");
            dwGraph.insertVertex("1");
            dwGraph.insertVertex("2");
            dwGraph.insertVertex("3");

            dwGraph.insertEdge("0","3",1);
            dwGraph.insertEdge("1","2",2);
            dwGraph.insertEdge("2","3",3);
            dwGraph.insertEdge("3","1",4);
            dwGraph.insertEdge("0","2",5);

            //Display the graph
            dwGraph.display();
            System.out.println();

            //Deleting an edge
            dwGraph.deleteEdge("0","2");

            //Display the graph
            dwGraph.display();

            //Check if there is an edge between two vertices
            System.out.println("Edge exist : " + (dwGraph.edgeExists("2","3") ? "True" : "False"));

            //Display Indegree and Outdegree of a vertex
            System.out.println("Outdegree : " + dwGraph.getOutdegree("3"));
            System.out.println("Indegree : " + dwGraph.getIndegree("3"));

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
