//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//UndirectedGraph.java : Program to find connected components in an undirected graph.

import java.util.Queue;
import java.util.LinkedList;

class Vertex
{
    public String name;
    public int state;
    public int componentNumber;

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
    private static final int WAITING = 1;
    private static final int VISITED = 2;

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

    private void bfs(int vertex, int cn)
    {
        Queue<Integer> bfsQueue = new LinkedList<Integer>();

        //Inserting the start vertex into queue and changing its state to WAITING
        bfsQueue.add(vertex);
        vertexList[vertex].state = WAITING;

        while(!bfsQueue.isEmpty())
        {
	        //Deleting front element from the queue and changing its state to VISITED
	        vertex = bfsQueue.remove();
	        vertexList[vertex].state = VISITED;
	        vertexList[vertex].componentNumber = cn;

	        //Looking for the adjacent vertices of the deleted element, and from these insert only those vertices into the
	        //queue which are in the INITIAL state. Change the state of all these inserted vertices from INITIAL to WAITING
	        for(int i=0; i<nVertices; i++)
	        {
		        //Checking for adjacent vertices with INITIAL state
		        if(isAdjacent(vertex,i) && vertexList[i].state==INITIAL)
		        {
			        bfsQueue.add(i);
			        vertexList[i].state = WAITING;
		        }
	        }
        }//End of while

    }//End of bfs()

    public void connectedComponent()
    {
        int componentNumber = 0;
        //Initially all the vertices will have INITIAL state
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].state = INITIAL;
        }

        componentNumber++;
        bfs(0, componentNumber);	//Start BFS from vertex 0

        for(int v=0; v<nVertices; v++)
        {
	        if(vertexList[v].state == INITIAL)
	        {
		        componentNumber++;
		        bfs(v, componentNumber);
	        }
        }

        System.out.println("Number of connected components = " + componentNumber);

        if(componentNumber == 1)
        {
        	System.out.println("Graph is connected");
        }
        else
        {
        	System.out.println("Graph is not connected");
	        for(int v=0; v<nVertices; v++)
	        {
	        	System.out.println(vertexList[v].name + " -> Component Number : " + vertexList[v].componentNumber);
	        }
        }

    }//End of connectedComponent()

}//End of class UndirectedGraph

class UndirectedGraphDemo
{
	public static void main(String args[])
    {
        UndirectedGraph uGraph = new UndirectedGraph();

        try
        {
            //Creating the graph, inserting the vertices and edges
            //Connected Graph
            //uGraph.insertVertex("0");
            //uGraph.insertVertex("1");
            //uGraph.insertVertex("2");
            //uGraph.insertVertex("3");
            //uGraph.insertVertex("4");
            //uGraph.insertVertex("5");
            //uGraph.insertVertex("6");
            //uGraph.insertVertex("7");
            //uGraph.insertVertex("8");
            //uGraph.insertVertex("9");

            //uGraph.insertEdge("0","1");
            //uGraph.insertEdge("0","3");
            //uGraph.insertEdge("1","2");
            //uGraph.insertEdge("1","4");
            //uGraph.insertEdge("1","5");
            //uGraph.insertEdge("2","3");
            //uGraph.insertEdge("2","5");
            //uGraph.insertEdge("3","6");
            //uGraph.insertEdge("4","7");
            //uGraph.insertEdge("5","6");
            //uGraph.insertEdge("5","7");
            //uGraph.insertEdge("5","8");
            //uGraph.insertEdge("6","9");
            //uGraph.insertEdge("7","8");
            //uGraph.insertEdge("8","9");

            //Not Connected Graph
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
            uGraph.insertEdge("0","3");
            uGraph.insertEdge("1","2");
            uGraph.insertEdge("2","3");

            uGraph.insertEdge("4","5");
            uGraph.insertEdge("4","7");
            uGraph.insertEdge("4","8");
            uGraph.insertEdge("5","6");
            uGraph.insertEdge("5","8");
            uGraph.insertEdge("6","9");
            uGraph.insertEdge("7","8");
            uGraph.insertEdge("8","9");

            uGraph.insertEdge("10","11");
            uGraph.insertEdge("10","13");
            uGraph.insertEdge("10","14");
            uGraph.insertEdge("11","12");
            uGraph.insertEdge("12","13");
            uGraph.insertEdge("13","14");

            //Display the graph
            uGraph.display();
            System.out.println();

            //Find the connected components of graph
            uGraph.connectedComponent();

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class UndirectedGraphDemo
