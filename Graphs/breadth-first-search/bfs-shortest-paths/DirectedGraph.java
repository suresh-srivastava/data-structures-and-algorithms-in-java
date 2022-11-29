//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraph.java : Program for traversing a directed graph through BFS and finding shortest distance and shortest path 
//of any vertex from start vertex.

import java.util.Queue;
import java.util.LinkedList;

class Vertex
{
    public String name;
    public int state;
    public int predecessor;
    public int distance;

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
    private static final int WAITING = 1;
    private static final int VISITED = 2;
    private static final int NIL = -1;
    private static final int INFINITY = 9999;

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

    private void bfs(int vertex)
    {
        Queue<Integer> bfsQueue = new LinkedList<Integer>();

        //Inserting the start vertex into queue and changing its state to WAITING
        bfsQueue.add(vertex);
        vertexList[vertex].state = WAITING;
        vertexList[vertex].predecessor = NIL;
        vertexList[vertex].distance = 0;

        while(!bfsQueue.isEmpty())
        {
	        //Deleting front element from the queue and changing its state to VISITED
	        vertex = bfsQueue.remove();
	        vertexList[vertex].state = VISITED;

	        //Looking for the adjacent vertices of the deleted element, and from these insert only those vertices into the
	        //queue which are in the INITIAL state. Change the state of all these inserted vertices from INITIAL to WAITING
	        for(int i=0; i<nVertices; i++)
	        {
		        //Checking for adjacent vertices with INITIAL state
		        if(isAdjacent(vertex,i) && vertexList[i].state==INITIAL)
		        {
			        bfsQueue.add(i);
			        vertexList[i].state = WAITING;
			        vertexList[i].predecessor = vertex;
			        vertexList[i].distance = vertexList[vertex].distance + 1;
		        }
	        }
        }//End of while

    }//End of bfs()

    public void bfsTraversal(String vertexName)
    {
        //Initially all the vertices will have INITIAL state
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].state = INITIAL;
	        vertexList[i].predecessor = NIL;
	        vertexList[i].distance = INFINITY;
        }

        bfs(getIndex(vertexName));
    }//End of bfsTraversal()

    public void bfsShortestPath(String source, String destination)
    {
        bfsTraversal(source);

        if(vertexList[getIndex(destination)].distance == INFINITY)
        {
        	System.out.println("There is no path from " + source + " to " + destination);
        }
        else
        {
        	System.out.println("Shortest distance is : " + vertexList[getIndex(destination)].distance);

	        int v = getIndex(destination);
	        int x, y = v;
	        int count=0;
            int[] path = new int[MAX_SIZE];

	        while(y != NIL)
	        {
		        count++;
		        path[count] = y;
		        x = vertexList[y].predecessor;
		        y = x;
	        }

	        System.out.println("Shortest Path is :");

	        int i;
	        for(i=count; i>1; i--)
	        {
	        	System.out.print(path[i] + "->");
	        }
	        System.out.println(path[i]);

        }//End of else
    }//End of bfsShortestPath()

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

            dGraph.insertEdge("0","1");
            dGraph.insertEdge("0","3");
            dGraph.insertEdge("0","4");
            dGraph.insertEdge("1","2");
            dGraph.insertEdge("1","4");
            dGraph.insertEdge("2","5");
            dGraph.insertEdge("3","4");
            dGraph.insertEdge("3","6");
            dGraph.insertEdge("4","5");
            dGraph.insertEdge("4","7");
            dGraph.insertEdge("6","4");
            dGraph.insertEdge("6","7");
            dGraph.insertEdge("7","5");
            dGraph.insertEdge("7","8");

            //Display the graph
            dGraph.display();
            System.out.println();

            //BFS traversal, finding shortest distance and shortest path
            dGraph.bfsShortestPath("0","8");

        }//End of try
        catch(RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
