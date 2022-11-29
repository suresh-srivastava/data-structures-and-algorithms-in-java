//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//UndirectedGraph.java : Program for traversing an undirected graph through DFS using recursion and classify all the edges in graph.

class Vertex
{
    public String name;
    public int state;
    public int predecessor;

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
    private static final int INITIAL = 0;
    private static final int VISITED = 1;
    private static final int FINISHED = 2;

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

        for(int i=0; i<nVertices; i++)
        {
	        if(isAdjacent(vertex,i) && vertexList[vertex].predecessor!=i)
	        {
		        if(vertexList[i].state == INITIAL)
		        {
			        vertexList[i].predecessor = vertex;
			        System.out.println("Tree Edge - (" + vertexList[vertex].name + "," + vertexList[i].name + ")");

			        dfs(i);
		        }
		        else if(vertexList[i].state == VISITED)
		        {
		        	System.out.println("Back Edge - (" + vertexList[vertex].name + "," + vertexList[i].name + ")");
		        }

	        }//End of if
        }//End of for

        vertexList[vertex].state = FINISHED;

    }//End of dfs()

    public void dfsClassifyEdges()
    {
        //Initially all the vertices will have INITIAL state
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].state = INITIAL;
        }

        for(int v=0; v<nVertices; v++)
        {
	        if(vertexList[v].state == INITIAL)
		        dfs(v);
        }

        System.out.println();
    }//End of dfsClassifyEdges()

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
	        uGraph.insertVertex("4");
	        uGraph.insertVertex("5");
	        uGraph.insertVertex("6");
	        uGraph.insertVertex("7");
	        uGraph.insertVertex("8");
	        uGraph.insertVertex("9");
	        uGraph.insertVertex("10");
	        uGraph.insertVertex("11");
	        uGraph.insertVertex("12");
	        uGraph.insertVertex("13");
	        uGraph.insertVertex("14");

	        uGraph.insertEdge("0","1");
	        uGraph.insertEdge("0","2");
	        uGraph.insertEdge("0","3");
	        uGraph.insertEdge("2","3");

	        uGraph.insertEdge("4","5");
	        uGraph.insertEdge("4","6");
	        uGraph.insertEdge("4","7");
	        uGraph.insertEdge("4","8");
	        uGraph.insertEdge("5","7");
	        uGraph.insertEdge("6","8");
	        uGraph.insertEdge("6","9");

	        uGraph.insertEdge("10","11");
	        uGraph.insertEdge("10","12");
	        uGraph.insertEdge("10","13");
	        uGraph.insertEdge("11","12");
	        uGraph.insertEdge("11","13");
	        uGraph.insertEdge("11","14");
	        uGraph.insertEdge("13","14");

	        //Display the graph
	        uGraph.display();
	        System.out.println();

	        //classify all the edges in graph
	        uGraph.dfsClassifyEdges();

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class UndirectedGraphDemo
