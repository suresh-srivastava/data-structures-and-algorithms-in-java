//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraph.java : Program for traversing a directed graph through DFS using recursion and classify all the edges in graph.

class Vertex
{
    public String name;
    public int state;
    public int discoveryTime;
    public int finishingTime;

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
    private static final int INITIAL = 0;
    private static final int VISITED = 1;
    private static final int FINISHED = 2;
    private static int time;

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

    private void dfs(int vertex)
    {
        vertexList[vertex].state = VISITED;
        vertexList[vertex].discoveryTime = ++time;

        for(int i=0; i<nVertices; i++)
        {
	        //Checking for adjacent vertices with INITIAL state
	        if(isAdjacent(vertex,i))
	        {
		        if(vertexList[i].state==INITIAL)
		        {
		        	System.out.println("Tree Edge - (" + vertexList[vertex].name + "-" + vertexList[i].name + ")");
			        dfs(i);
		        }
		        else if(vertexList[i].state==VISITED)
		        {
		        	System.out.println("Back Edge - (" + vertexList[vertex].name + "-" + vertexList[i].name + ")");
		        }
		        else if(vertexList[vertex].discoveryTime < vertexList[i].discoveryTime)
		        {
		        	System.out.println("Forward Edge - (" + vertexList[vertex].name + "-" + vertexList[i].name + ")");
		        }
		        else
		        {
		        	System.out.println("Cross Edge - (" + vertexList[vertex].name + "-" + vertexList[i].name + ")");
		        }
	        }
        }//End of for

        vertexList[vertex].state = FINISHED;
        vertexList[vertex].finishingTime = ++time;

    }//End of dfs()

    public void dfsClassifyEdges()
    {
        //Initially all the vertices will have INITIAL state
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].state = INITIAL;
        }

        time = 0;

        for(int v=0; v<nVertices; v++)
        {
	        if(vertexList[v].state == INITIAL)
		        dfs(v);
        }

        System.out.println();
    }//End of dfsClassifyEdges()

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
	        dGraph.insertVertex("4");
	        dGraph.insertVertex("5");
	        dGraph.insertVertex("6");
	        dGraph.insertVertex("7");
	        dGraph.insertVertex("8");
	        dGraph.insertVertex("9");
	        dGraph.insertVertex("10");
	        dGraph.insertVertex("11");
	        dGraph.insertVertex("12");
	        dGraph.insertVertex("13");
	        dGraph.insertVertex("14");
	        dGraph.insertVertex("15");

	        dGraph.insertEdge("0","1");
	        dGraph.insertEdge("0","2");
	        dGraph.insertEdge("0","3");
	        dGraph.insertEdge("1","2");
	        dGraph.insertEdge("3","2");
	        dGraph.insertEdge("4","1");
	        dGraph.insertEdge("4","5");
	        dGraph.insertEdge("4","6");
	        dGraph.insertEdge("4","7");
	        dGraph.insertEdge("5","6");
	        dGraph.insertEdge("6","3");
	        dGraph.insertEdge("6","9");
	        dGraph.insertEdge("7","8");
	        dGraph.insertEdge("8","4");
	        dGraph.insertEdge("8","5");
	        dGraph.insertEdge("8","9");
	        dGraph.insertEdge("9","5");
	        dGraph.insertEdge("10","11");
	        dGraph.insertEdge("10","14");
	        dGraph.insertEdge("11","8");
	        dGraph.insertEdge("11","12");
	        dGraph.insertEdge("11","14");
	        dGraph.insertEdge("11","15");
	        dGraph.insertEdge("12","15");
	        dGraph.insertEdge("13","10");
	        dGraph.insertEdge("14","13");
	        dGraph.insertEdge("14","15");

	        //Display the graph
	        dGraph.display();
	        System.out.println();

	        //classify all the edges in graph
	        dGraph.dfsClassifyEdges();

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
