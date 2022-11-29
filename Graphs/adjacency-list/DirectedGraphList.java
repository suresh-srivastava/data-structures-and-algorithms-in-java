//Copyright (C) Suresh Kumar Srivastava - All Rights Reserved
//DSA Masterclass courses are available on CourseGalaxy.com

//DirectedGraphList.java : Program for directed graph using adjacency list.

class VertexNode
{
    public String name;
    public VertexNode nextVertex;
    public EdgeNode firstEdge;

    public VertexNode(String name)
    {
        this.name = name;
        nextVertex = null;
        firstEdge = null;
    }
}//End of class VertexNode

class EdgeNode
{
    public VertexNode endVertex;
    public EdgeNode nextEdge;

    public EdgeNode(VertexNode v)
    {
        this.endVertex = v;
        nextEdge = null;
    }
}//End of class EdgeNode

class DirectedGraphList
{
    private int nVertices;
    private int nEdges;
    private VertexNode start;

    public DirectedGraphList()
    {
        nVertices = 0;
        nEdges = 0;
        start = null;
    }//End of DirectedGraphList()

    public void insertVertex(String vertexName)
    {
        VertexNode ptr, temp;
        boolean vertexFound = false;

        ptr = start;

        if(ptr == null)
        {
	        temp = new VertexNode(vertexName);
	        start = temp;
	        nVertices++;
        }
        else
        {
	        while(ptr.nextVertex != null)
	        {
		        if(ptr.name == vertexName)
		        {
			        vertexFound = true;
			        break;
		        }
		        ptr = ptr.nextVertex;
	        }//End of while

	        if(vertexFound || ptr.name==vertexName)
	        {
	        	System.out.println("Vertex already present");
	        }
	        else
	        {
		        temp = new VertexNode(vertexName);
		        ptr.nextVertex = temp;
		        nVertices++;
	        }
        }//End of else

    }//End of insertVertex()

    private VertexNode findVertex(String vertexName)
    {
        VertexNode ptr = start;

        while(ptr != null)
        {
	        if(ptr.name == vertexName)
                break;

	        ptr = ptr.nextVertex;
        }

        return ptr;
    }//End of findVertex()

    public void insertEdge(String source, String destination)
    {
        VertexNode u, v;
        EdgeNode edgePtr, temp;

        boolean edgeFound = false;

        if(source == destination)
        {
        	System.out.println("Inavlid Edge : source and destination vertices are same");
        }
        else
        {
	        u = findVertex(source);
	        v = findVertex(destination);

	        if(u == null)
	        {
	        	System.out.println("Source vertex not present, first insert vertex " + source);
	        }
	        else if(v == null)
	        {
	        	System.out.println("Destination vertex not present, first insert vertex " + destination);
	        }
	        else
	        {
		        if(u.firstEdge == null)
		        {
			        temp = new EdgeNode(v);
			        u.firstEdge = temp;
			        nEdges++;
		        }
		        else
		        {
			        edgePtr = u.firstEdge;

			        while(edgePtr.nextEdge != null)
			        {
				        if(edgePtr.endVertex.name == v.name)
				        {
					        edgeFound = true;
					        break;
				        }

				        edgePtr = edgePtr.nextEdge;
			        }//End of while

			        if(edgeFound || edgePtr.endVertex.name==destination)
			        {
			        	System.out.println("Edge already present");
			        }
			        else
			        {
				        temp = new EdgeNode(v);
				        edgePtr.nextEdge = temp;
				        nEdges++;
			        }
		        }//End of else
	        }//End of else
        }//End of else

    }//End of insertEdge()

    public void deleteVertex(String vertexName)
    {
        deleteFromEdgeLists(vertexName);
        deleteFromVertexList(vertexName);
    }//End of deleteVertex()

    //Delete incoming edges
    private void deleteFromEdgeLists(String vertexName)
    {
        VertexNode vertexPtr;
        EdgeNode edgePtr;

        vertexPtr = start;

        while(vertexPtr != null)
        {
	        if(vertexPtr.firstEdge != null)
	        {
		        if(vertexPtr.firstEdge.endVertex.name == vertexName)
		        {
			        vertexPtr.firstEdge = vertexPtr.firstEdge.nextEdge;
			        nEdges--;
			        continue;
		        }

		        edgePtr = vertexPtr.firstEdge;
		        while(edgePtr.nextEdge != null)
		        {
			        if(edgePtr.nextEdge.endVertex.name == vertexName)
			        {
				        edgePtr.nextEdge = edgePtr.nextEdge.nextEdge;
				        nEdges--;
				        continue;
			        }
			        edgePtr = edgePtr.nextEdge;
		        }
	        }//End of if

	        vertexPtr = vertexPtr.nextVertex;
        }//End of while
    }//End of deleteFromEdgeLists()

    //Delete outgoing edges and vertex
    private void deleteFromVertexList(String vertexName)
    {
        VertexNode vertexPtr, tempVertex=null;
        EdgeNode edgePtr;

        if(start == null)
        {
        	System.out.println("No vertices to be deleted");
	        return;
        }

        if(start.name == vertexName)
        {
	        tempVertex = start;
	        start = start.nextVertex;
        }
        else	//vertex to be deleted is in between or at last
        {
	        vertexPtr = start;
	        while(vertexPtr.nextVertex != null)
	        {
		        if(vertexPtr.nextVertex.name == vertexName)
			        break;
		        vertexPtr = vertexPtr.nextVertex;
	        }

	        if(vertexPtr.nextVertex != null)
	        {
		        tempVertex = vertexPtr.nextVertex;
		        vertexPtr.nextVertex = vertexPtr.nextVertex.nextVertex;
	        }
	        else
	        {
	        	System.out.println("Vertex not found");
	        }
        }//End of else

        if(tempVertex != null)
        {
	        edgePtr = tempVertex.firstEdge;
	        while(edgePtr != null)
	        {
		        edgePtr = edgePtr.nextEdge;
		        nEdges--;
	        }

	        nVertices--;
        }

    }//End of deleteFromVertexList()

    public void deleteEdge(String source, String destination)
    {
        VertexNode vertexPtr;
        EdgeNode edgePtr;

        vertexPtr = findVertex(source);

        if(vertexPtr == null)
        {
        	System.out.println("Edge not found");
        }
        else
        {
	        edgePtr = vertexPtr.firstEdge;

	        if(edgePtr == null)
	        {
	        	System.out.println("Edge not found");
	        }
	        else
	        {
		        if(edgePtr.endVertex.name == destination)
		        {
			        vertexPtr.firstEdge = edgePtr.nextEdge;
			        nEdges--;
		        }
		        else
		        {
			        while(edgePtr.nextEdge != null)
			        {
				        if(edgePtr.nextEdge.endVertex.name == destination)
				        {
					        break;
				        }
				        edgePtr = edgePtr.nextEdge;
			        }

			        if(edgePtr.nextEdge == null)
			        {
			        	System.out.println("Edge not found");
			        }
			        else
			        {
				        edgePtr.nextEdge = edgePtr.nextEdge.nextEdge;
				        nEdges--;
			        }
		        }//End of else
	        }//End of else
        }//End of else

    }//End of deleteEdge()

    public void display()
    {
        VertexNode vertexPtr;
        EdgeNode edgePtr;

        vertexPtr = start;

        while(vertexPtr != null)
        {
        	System.out.println("Vertex : " + vertexPtr.name);

	        edgePtr = vertexPtr.firstEdge;
	        while(edgePtr != null)
	        {
	        	System.out.println("Edge : " + vertexPtr.name + " -> " + edgePtr.endVertex.name);
		        edgePtr = edgePtr.nextEdge;
	        }

	        vertexPtr = vertexPtr.nextVertex;
        }
    }//End of display()

    public boolean edgeExists(String source, String destination)
    {
        VertexNode vertexPtr;
        EdgeNode edgePtr;
        boolean edgeFound = false;

        vertexPtr = findVertex(source);

        if(vertexPtr != null)
        {
	        edgePtr = vertexPtr.firstEdge;
	        while(edgePtr != null)
	        {
		        if(edgePtr.endVertex.name == destination)
		        {
			        edgeFound = true;
			        break;
		        }
		        edgePtr = edgePtr.nextEdge;
	        }
        }

        return edgeFound;
    }//End of edgeExists()

    public int getOutdegree(String vertex)
    {
        VertexNode vertexPtr;
        EdgeNode edgePtr;
        int outdegree = 0;

        vertexPtr = findVertex(vertex);
        if(vertexPtr == null)
	        throw new RuntimeException("Invalid Vertex");

        edgePtr = vertexPtr.firstEdge;
        while(edgePtr != null)
        {
	        outdegree++;
	        edgePtr = edgePtr.nextEdge;
        }

        return outdegree;
    }//End of getOutdegree()

    public int getIndegree(String vertex)
    {
        VertexNode vertexPtr;
        EdgeNode edgePtr;
        int indegree = 0;

        if(findVertex(vertex) == null)
	        throw new RuntimeException("Invalid Vertex");

        vertexPtr = start;
        while(vertexPtr != null)
        {
	        edgePtr = vertexPtr.firstEdge;
	        while(edgePtr != null)
	        {
		        if(edgePtr.endVertex.name == vertex)
		        {
			        indegree++;
		        }
		        edgePtr = edgePtr.nextEdge;
	        }
	        vertexPtr = vertexPtr.nextVertex;
        }

        return indegree;
    }//End of getIndegree()

}//End of class DirectedGraphList

class DirectedGraphListDemo
{
	public static void main(String args[])
    {
        DirectedGraphList dGraph = new DirectedGraphList();

        try
        {
	        //Creating the graph, inserting the vertices and edges
	        dGraph.insertVertex("0");
	        dGraph.insertVertex("1");
	        dGraph.insertVertex("2");
	        dGraph.insertVertex("3");

	        dGraph.insertEdge("0","3");
	        dGraph.insertEdge("1","2");
	        dGraph.insertEdge("2","3");
	        dGraph.insertEdge("3","1");
	        dGraph.insertEdge("0","2");

	        //Display the graph
	        dGraph.display();
	        System.out.println();

	        //Deleting an edge
	        dGraph.deleteEdge("0","2");

	        //Display the graph
	        dGraph.display();
	        System.out.println();

	        //Deleting a vertex
	        dGraph.deleteVertex("0");

	        //Display the graph
	        dGraph.display();

	        //Check if there is an edge between two vertices
	        System.out.println("Edge exist : " + (dGraph.edgeExists("2","3") ? "True" : "False"));

	        //Display Indegree and Outdegree of a vertex
	        System.out.println("Outdegree : " + dGraph.getOutdegree("3"));
	        System.out.println("Indegree : " + dGraph.getIndegree("3"));

        }//End of try
        catch (RuntimeException e)
        {
        	System.out.println(e.getMessage());
        }
    }//End of main()
}//End of class DirectedGraphListDemo
