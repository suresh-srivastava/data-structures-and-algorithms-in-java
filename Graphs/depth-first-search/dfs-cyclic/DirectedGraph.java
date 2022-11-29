//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraph.java : Program for traversing a directed graph through DFS using recursion and finding out that graph is 
//cyclic or not

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
    private boolean hasCycle;

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
			        //Its Tree Edge
			        dfs(i);
		        }
		        else if(vertexList[i].state==VISITED)
		        {
			        //Its Back Edge
			        hasCycle = true;
		        }
	        }
        }//End of for

        vertexList[vertex].state = FINISHED;
        vertexList[vertex].finishingTime = ++time;

    }//End of dfs()

    public boolean isCyclic()
    {
        //Initially all the vertices will have INITIAL state
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].state = INITIAL;
        }

        time = 0;
        hasCycle = false;

        for(int v=0; v<nVertices; v++)
        {
	        if(vertexList[v].state == INITIAL)
		        dfs(v);
        }

        return hasCycle;
    }//End of isCyclic()

}//End of class DirectedGraph

class DirectedGraphDemo
{
	public static void main(String args[])
    {
        DirectedGraph dGraph = new DirectedGraph();

        try
        {
	        //Creating the graph, inserting the vertices and edges
	        //Graph is acyclic
            //dGraph.insertVertex("0");
            //dGraph.insertVertex("1");
            //dGraph.insertVertex("2");
            //dGraph.insertVertex("3");

            //dGraph.insertEdge("0","1");
            //dGraph.insertEdge("0","2");
            //dGraph.insertEdge("0","3");
            //dGraph.insertEdge("1","2");
            //dGraph.insertEdge("3","2");

	        //Graph is cyclic
            dGraph.insertVertex("0");
            dGraph.insertVertex("1");
            dGraph.insertVertex("2");
            dGraph.insertVertex("3");

            dGraph.insertEdge("0","1");
            dGraph.insertEdge("0","2");
            dGraph.insertEdge("1","2");
            dGraph.insertEdge("2","3");
            dGraph.insertEdge("3","0");

	        //Display the graph
	        dGraph.display();
	        System.out.println();

	        if(dGraph.isCyclic())
	        	System.out.println("Graph is Cyclic");
	        else
	        	System.out.println("Graph is Acyclic");

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
