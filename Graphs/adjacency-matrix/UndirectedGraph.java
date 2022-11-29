//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//UndirectedGraph.java : Program for undirected graph using adjacency matrix.

class Vertex
{
    public String name;

    public Vertex(String name)
    {
        this.name = name;
    }
}//End of class Vertex

class UndirectedGraph
{
    private static final int MAX_SIZE = 30;
    private int nVertices;
    private int nEdges;
    private int[][] adj;
    private Vertex[] vertexList;

    public UndirectedGraph()
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
    }//End of UndirectedGraph()

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
            adj[v][u] = 1;
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
            adj[v][u] = 0;
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

    public int getDegree(String vertex)
    {
        int u = getIndex(vertex);

        int degree = 0;
        for(int v=0; v<nVertices; v++)
        {
            if(adj[u][v] != 0)
                degree++;
        }

        return degree;
    }//End of getDegree()

}//End of class UndirectedGraph

class UndirectedGraphDemo
{
	public static void main(String args[])
    {
        UndirectedGraph uGraph = new UndirectedGraph();

        try
        {
            //Creating the graph, inserting the vertices and edges
            uGraph.insertVertex("0");
            uGraph.insertVertex("1");
            uGraph.insertVertex("2");
            uGraph.insertVertex("3");

            uGraph.insertEdge("0","3");
            uGraph.insertEdge("1","2");
            uGraph.insertEdge("2","3");
            uGraph.insertEdge("3","1");
            uGraph.insertEdge("0","2");

            //Display the graph
            uGraph.display();
            System.out.println();

            //Deleting an edge
            uGraph.deleteEdge("0","2");

            //Display the graph
            uGraph.display();

            //Check if there is an edge between two vertices
            System.out.println("Edge exist : " + (uGraph.edgeExists("2","3") ? "True" : "False"));

            //Display Degree of a vertex
            System.out.println("Degree : " + uGraph.getDegree("3"));

        }//End of try
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class UndirectedGraphDemo
