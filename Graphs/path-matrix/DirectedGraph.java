//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraph.java : Program to find out the path matrix by powers of adjacency matrix.

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

    public void pathMatrix()
    {
        int[][] adjp, x, temp;
        int[][] path;

        adjp = new int[MAX_SIZE][MAX_SIZE];
        x = new int[MAX_SIZE][MAX_SIZE];
        temp = new int[MAX_SIZE][MAX_SIZE];
        path = new int[MAX_SIZE][MAX_SIZE];

	    //Initialize x
        for(int i=0; i<nVertices; i++)
        {
     	    for(int j=0; j<nVertices; j++)
		    {
			    x[i][j] = 0;
		    }
        }

	    //Initially adjp and x is equal to adj
        for(int i=0; i<nVertices; i++)
        {
     	    for(int j=0; j<nVertices; j++)
		    {
			    x[i][j] = adjp[i][j] = adj[i][j];
		    }
        }

	    //Get the matrix x by adding all the adjp
	    for(int p=2; p<=nVertices; p++)
	    {
		    //adjp(1...n) x adj
		    for(int i=0; i<nVertices; i++)
		    {
			    for(int j=0; j<nVertices; j++)
			    {
				    temp[i][j]=0;
				    for(int k=0; k<nVertices; k++)
				    {
					    temp[i][j] = temp[i][j] + adjp[i][k]*adj[k][j];
				    }
			    }
		    }

		    //Now adjp will be equal to temp
		    for(int i=0; i<nVertices; i++)
		    {
     		    for(int j=0; j<nVertices; j++)
			    {
				    adjp[i][j] = temp[i][j];
			    }
		    }

		    //x = adjp1 + adjp2 + ...... + adjpn
		    for(int i=0; i<nVertices; i++)
		    {
     		    for(int j=0; j<nVertices; j++)
			    {
				    x[i][j] = x[i][j] + adjp[i][j];
			    }
		    }
	    }//End of for

	    //Display x
	    System.out.println("x matrix is :");
        for(int i=0; i<nVertices; i++)
        {
     	    for(int j=0; j<nVertices; j++)
		    {
     	    	System.out.print(x[i][j] + " ");
		    }
     	   System.out.println();
        }

	    //Assign values to path matrix
        for(int i=0; i<nVertices; i++)
        {
     	    for(int j=0; j<nVertices; j++)
		    {
			    if(x[i][j] == 0)
				    path[i][j] = 0;
			    else
				    path[i][j] = 1;
		    }
        }

	    //Display path matrix
        System.out.println("Path matrix is :");
        for(int i=0; i<nVertices; i++)
        {
     	    for(int j=0; j<nVertices; j++)
     	    	System.out.print(path[i][j] + " ");
     	   System.out.println();
        }

    }//End of pathMatrix()

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

            dGraph.insertEdge("0","1");
            dGraph.insertEdge("0","3");
            dGraph.insertEdge("1","0");
            dGraph.insertEdge("1","2");
            dGraph.insertEdge("1","3");
            dGraph.insertEdge("2","3");
            dGraph.insertEdge("3","0");
            dGraph.insertEdge("3","2");

            //Display the graph
            dGraph.display();
            System.out.println();

            System.out.println("Find the path matrix :");
            dGraph.pathMatrix();
        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
