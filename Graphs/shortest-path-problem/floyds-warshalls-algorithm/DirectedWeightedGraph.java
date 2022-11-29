//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedWeightedGraph.java : Program to find shortest path matrix by Modified Warshall's (Floyd) algorithm.

class Vertex
{
    public String name;

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
	private int[][] D; //Shortest Path Matrix
	private int[][] Pred; //Predecessor Matrix
	private static final int INFINITY = 9999;

    public DirectedWeightedGraph()
    {
        adj = new int[MAX_SIZE][MAX_SIZE];
        vertexList = new Vertex[MAX_SIZE];
        D = new int[MAX_SIZE][MAX_SIZE];
        Pred = new int[MAX_SIZE][MAX_SIZE];
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

    private void display(int[][] mat)
    {
        for(int i=0; i<nVertices; i++)
        {
 	        for(int j=0; j<nVertices; j++)
 	        	System.out.print(mat[i][j] + " ");
 	       System.out.println();
        }
    }//End of display()

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

    private void floydWarshallsAlgorithm(int s)
    {
        //Getting D(-1), Pred(-1)
        for(int i=0; i<nVertices; i++)
        {
	        for(int j=0; j<nVertices; j++)
	        {
		        if(adj[i][j] == 0)
		        {
			        D[i][j] = INFINITY;
			        Pred[i][j] = -1;
		        }
		        else
		        {
			        D[i][j] = adj[i][j];
			        Pred[i][j] = i;
		        }
	        }
        }//End of for

        //Getting D(k), Pred(k)
        for(int k=0; k<nVertices; k++)
        {
	        for(int i=0; i<nVertices; i++)
	        {
		        for(int j=0; j<nVertices; j++)
		        {
			        if(D[i][k]+D[k][j] < D[i][j])
			        {
				        D[i][j] = D[i][k]+D[k][j];
				        Pred[i][j] = Pred[k][j];
			        }
		        }
	        }
        }//End of for

        //Finding negative cycle
        for(int i=0; i<nVertices; i++)
        {
	        if(D[i][i] < 0)
	        {
		        throw new RuntimeException("There is negative cycle in graph.");
	        }
        }

        System.out.println("Shortest Path Matrix :");
        display(D);
        System.out.println("\nPredecessor Matrix :");
        display(Pred);

    }//End of floydWarshallsAlgorithm()

    private void findPath(int s, int v)
    {
        int[] path = new int[MAX_SIZE]; //stores the shortest path
        int count=-1;			       //number of vertices in the shortest path			

        if(D[s][v] == INFINITY)
        {
        	System.out.println("No path");
        }
        else
        {
	        do
	        {
		        path[++count] = v;
		        v = Pred[s][v];
	        }while(v!=s);

	        path[++count] = s;
	        System.out.print("Shortest Path : ");
	        for(int i=count; i>=0; i--)
	        	System.out.print(path[i] + " ");
	        System.out.println();
        }

    }//End of findPath()

    public void findPaths(String source)
    {
        int s = getIndex(source);

        floydWarshallsAlgorithm(s);

        System.out.println("Source : " + source);

        for(int v=0; v<nVertices; v++)
        {
        	System.out.println("Destination : " + vertexList[v].name);
	        findPath(s, v);
	        System.out.println("Shortest Distance : " + D[s][v]);
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

            dwGraph.insertEdge("0","1",2);
            dwGraph.insertEdge("0","3",9);
            dwGraph.insertEdge("1","0",3);
            dwGraph.insertEdge("1","2",4);
            dwGraph.insertEdge("1","3",7);
            dwGraph.insertEdge("2","1",6);
            dwGraph.insertEdge("2","3",2);
            dwGraph.insertEdge("3","0",14);
            dwGraph.insertEdge("3","2",4);

            //Graph with negative cycle
            //dwGraph.insertVertex("0");
            //dwGraph.insertVertex("1");
            //dwGraph.insertVertex("2");
            //dwGraph.insertVertex("3");
            //dwGraph.insertVertex("4");

            //dwGraph.insertEdge("0","1",2);
            //dwGraph.insertEdge("0","2",7);
            //dwGraph.insertEdge("1","3",-9);
            //dwGraph.insertEdge("2","4",6);
            //dwGraph.insertEdge("3","0",4);
            //dwGraph.insertEdge("3","4",5);

            //Display the graph
            dwGraph.display();
            System.out.println();

            dwGraph.findPaths("0");

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphDemo
