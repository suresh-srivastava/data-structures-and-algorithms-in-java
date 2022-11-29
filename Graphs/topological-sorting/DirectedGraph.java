//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraph.java : Program for topological sorting of directed acyclic graph.

import java.util.Queue;
import java.util.LinkedList;

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

    public void display()
    {
        for(int i=0; i<nVertices; i++)
        {
            for(int j=0; j<nVertices; j++)
            	System.out.print(adj[i][j] + " ");
            System.out.println();
        }
    }//End of display()

    //Returns number of edges coming to a vertex
    private int getIndegree(int vertex)
    {
        int indegree = 0;
        for(int v=0; v<nVertices; v++)
            if(adj[v][vertex] != 0)
                indegree++;

        return indegree;
    }//End of getIndegree()

    public void topologicalOrder()
    {
        int[] topoOrder = new int[MAX_SIZE];
        int[] indegree = new int[MAX_SIZE];
        Queue<Integer> q = new LinkedList<Integer>();
        int v, count;

        //Get the indegree of each vertex
        for(v=0; v<nVertices; v++)
        {
	        indegree[v] = getIndegree(v);
	        if(indegree[v] == 0)
		        q.add(v);
        }

        count=0;
        while(!q.isEmpty() && count<nVertices)
        {
	        v = q.remove();

	        topoOrder[++count] = v;	//Add vertex v to topoOrder array

	        //Delete all the edges going from vertex v
	        for(int i=0; i<nVertices; i++)
	        {
		        if(adj[v][i] != 0)
		        {
			        adj[v][i] = 0;
			        indegree[i] = indegree[i]-1;
			        if(indegree[i] == 0)
				        q.add(i);
		        }
	        }

        }//End of while

        if(count < nVertices)
        {
	        throw new RuntimeException("Graph contains cycle. Topological order is not possible.");
        }

        System.out.println("Vertices in topological order are :");
        for(int i=1; i<=count; i++)
        	System.out.print(topoOrder[i] + " ");
        System.out.println();

    }//End of topologicalOrder()

}//End of class DirectedGraph

class DirectedGraphDemo
{
	public static void main(String args[])
    {
        DirectedGraph dGraph = new DirectedGraph();

        try
        {
            //Creating the graph, inserting the vertices and edges
            //Graph without cycle
            dGraph.insertVertex("0");
            dGraph.insertVertex("1");
            dGraph.insertVertex("2");
            dGraph.insertVertex("3");
            dGraph.insertVertex("4");
            dGraph.insertVertex("5");
            dGraph.insertVertex("6");

            dGraph.insertEdge("0","1");
            dGraph.insertEdge("0","5");
            dGraph.insertEdge("1","4");
            dGraph.insertEdge("1","5");
            dGraph.insertEdge("2","1");
            dGraph.insertEdge("2","3");
            dGraph.insertEdge("3","1");
            dGraph.insertEdge("3","4");
            dGraph.insertEdge("4","5");
            dGraph.insertEdge("6","4");
            dGraph.insertEdge("6","5");

            //Graph with cycle
            //dGraph.insertVertex("0");
            //dGraph.insertVertex("1");
            //dGraph.insertVertex("2");
            //dGraph.insertVertex("3");
            //dGraph.insertVertex("4");

            //dGraph.insertEdge("0","1");
            //dGraph.insertEdge("0","2");
            //dGraph.insertEdge("1","3");
            //dGraph.insertEdge("2","4");
            //dGraph.insertEdge("3","0");
            //dGraph.insertEdge("3","4");

            //Display the graph
            dGraph.display();

            dGraph.topologicalOrder();

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
