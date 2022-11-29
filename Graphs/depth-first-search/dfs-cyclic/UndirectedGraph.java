//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//UndirectedGraph.java : Program for traversing an undirected graph through DFS using recursion and finding out that graph is 
//cyclic or not

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
    private boolean hasCycle;

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
			        //Its Tree Edge
			        vertexList[i].predecessor = vertex;
			        dfs(i);
		        }
		        else if(vertexList[i].state == VISITED)
		        {
			        //Its Back Edge
			        hasCycle = true;
		        }

	        }//End of if
        }//End of for

        vertexList[vertex].state = FINISHED;

    }//End of dfs()

    public boolean isCyclic()
    {
        //Initially all the vertices will have INITIAL state
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].state = INITIAL;
        }

        hasCycle = false;

        for(int v=0; v<nVertices; v++)
        {
	        if(vertexList[v].state == INITIAL)
		        dfs(v);
        }

        return hasCycle;
    }//End of isCyclic()

}//End of class UndirectedGraph

class UndirectedGraphDemo
{
	public static void main(String args[])
    {
        UndirectedGraph uGraph = new UndirectedGraph();

        try
        {
	        //Creating the graph, inserting the vertices and edges
	        //Graph is acyclic
            //uGraph.insertVertex("0");
            //uGraph.insertVertex("1");
            //uGraph.insertVertex("2");
            //uGraph.insertVertex("3");
            //uGraph.insertVertex("4");
            //uGraph.insertVertex("5");

            //uGraph.insertEdge("0","1");
            //uGraph.insertEdge("1","3");
            //uGraph.insertEdge("2","3");
            //uGraph.insertEdge("3","4");
            //uGraph.insertEdge("4","5");

	        //Graph is cyclic
            uGraph.insertVertex("0");
            uGraph.insertVertex("1");
            uGraph.insertVertex("2");
            uGraph.insertVertex("3");
            uGraph.insertVertex("4");
            uGraph.insertVertex("5");

            uGraph.insertEdge("0","1");
            uGraph.insertEdge("0","2");
            uGraph.insertEdge("1","3");
            uGraph.insertEdge("2","3");
            uGraph.insertEdge("3","4");
            uGraph.insertEdge("4","5");

	        //Display the graph
	        uGraph.display();
	        System.out.println();

	        if(uGraph.isCyclic())
	        	System.out.println("Graph is Cyclic");
	        else
	        	System.out.println("Graph is Acyclic");

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class UndirectedGraphDemo
