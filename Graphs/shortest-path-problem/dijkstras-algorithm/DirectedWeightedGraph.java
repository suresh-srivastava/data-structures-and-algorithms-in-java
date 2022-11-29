//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedWeightedGraph.java : Program to find shortest paths using Dijkstra's algorithm.

class Vertex
{
    public String name;
    public int status;
    public int predecessor;
    public int pathLength;

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
    private static final int TEMPORARY = 0;
    private static final int PERMANENT = 1;
    private static final int INFINITY = 9999;
    private static final int NIL = -1;

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

    //Returns the temporary vertex with minimum value of pathLength,
    //Returns NIL if no temporary vertex left or all temporary vertices left have pathLength INFINITY
    private int getMinimumTemporary()
    {
        int min = INFINITY;
        int k = NIL;

        for(int i=0; i<nVertices; i++)
        {
	        if(vertexList[i].status==TEMPORARY && vertexList[i].pathLength<min)
	        {
		        min = vertexList[i].pathLength;
		        k=i;
	        }
        }

        return k;

    }//End of getMinimumTemporary()

    private void dijkstrasAlgorithm(int s)
    {
        //Make all vertices temporary
        for(int i=0; i<nVertices; i++)
        {
	        vertexList[i].status = TEMPORARY;
	        vertexList[i].predecessor = NIL;
	        vertexList[i].pathLength = INFINITY;
        }

        //Make pathLength of source vertex equal to 0
        vertexList[s].pathLength = 0;

        while(true)
        {
	        //Search for temporary vertex with minimum pathLength and make it current vertex
	        int current = getMinimumTemporary();

	        if(current == NIL)
		        break;

	        //Make current vertex PERMANENT
	        vertexList[current].status = PERMANENT;

	        for(int v=0; v<nVertices; v++)
	        {
                //Checks for adjacent temporary vertices
		        if(isAdjacent(current,v) && vertexList[v].status==TEMPORARY)
		        {
                    if((vertexList[current].pathLength + adj[current][v]) < vertexList[v].pathLength)
			        {
                        vertexList[v].predecessor = current;	//Relabel
				        vertexList[v].pathLength = vertexList[current].pathLength + adj[current][v];
			        }
		        }
	        }//End of for
        }//End of while

    }//End of dijkstrasAlgorithm()

    private void findPath(int s, int v)
    {
        int[] path = new int[MAX_SIZE];  //stores the shortest path
        int shortestDistance=0;         //length of shortest path
        int count=0;                    //number of vertices in the shortest path			
        int u;

        //Store the full path in the array path
        while(v!=s)
        {
	        count++;
	        path[count] = v;
	        u = vertexList[v].predecessor;
	        shortestDistance += adj[u][v];
	        v = u;
        }

        count++;
        path[count] = s;
        System.out.print("Shortest Path : ");
        for(int i=count; i>=1; i--)
        	System.out.print(path[i] + " ");
        System.out.println();
        System.out.println("Shortest Distance : " + shortestDistance);

    }//End of findPath()

    public void findPaths(String source)
    {
        int s = getIndex(source);

        dijkstrasAlgorithm(s);

        System.out.println("Source : " + source);

        for(int v=0; v<nVertices; v++)
        {
        	System.out.println("Destination : " + vertexList[v].name);
	        if(vertexList[v].pathLength == INFINITY)
	        	System.out.println("There is no path from " + source + " to vertex " + vertexList[v].name);
	        else
		        findPath(s, v);
        }

    }//End of findPaths()

}//End of class DirectedWeightedGraph

class DirectedWeightedGraphDemo
{
	public static void main(String args[])
    {
        DirectedWeightedGraph dwGraph = new DirectedWeightedGraph();

        try
        {
	        //Creating the graph, inserting the vertices and edges
	        dwGraph.insertVertex("0");
	        dwGraph.insertVertex("1");
	        dwGraph.insertVertex("2");
	        dwGraph.insertVertex("3");
	        dwGraph.insertVertex("4");
	        dwGraph.insertVertex("5");
	        dwGraph.insertVertex("6");
	        dwGraph.insertVertex("7");

	        dwGraph.insertEdge("0","1",8);
	        dwGraph.insertEdge("0","2",2);
	        dwGraph.insertEdge("0","3",7);
	        dwGraph.insertEdge("1","5",16);
	        dwGraph.insertEdge("2","0",5);
	        dwGraph.insertEdge("2","3",4);
	        dwGraph.insertEdge("2","6",3);
	        dwGraph.insertEdge("3","4",9);
	        dwGraph.insertEdge("4","0",4);
	        dwGraph.insertEdge("4","5",5);
	        dwGraph.insertEdge("4","7",8);
	        dwGraph.insertEdge("6","2",6);
	        dwGraph.insertEdge("6","3",3);
	        dwGraph.insertEdge("6","4",4);
	        dwGraph.insertEdge("7","5",2);
	        dwGraph.insertEdge("7","6",5);

	        //Display the graph
	        dwGraph.display();
	        System.out.println();

	        //Finding path from source vertex to other vertices
	        dwGraph.findPaths("0");

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
