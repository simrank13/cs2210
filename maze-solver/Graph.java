// import Iterator and ArrayList from java library
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents undirected graph using adjacency matrix and implements GraphADT interface
 *
 */
public class Graph implements GraphADT{
	/**
	 * instance variable V represents adjacency list of vertices
	 */
	private GraphNode[] V;
	/**
	 * instance variable E represents adjacency matrix of edges
	 */
	private GraphEdge[][] E;
	/**
	 * instance variable n represents number of nodes
	 */
	private int n;
	
	/**
	 * constructor creates empty graph with n nodes and no edges
	 * @param n number of nodes
	 */
	public Graph(int n) {
		// set specified value to number of nodes
		this.n = n;
		// initialize list and matrix to store vertices and edges of graph
		this.V = new GraphNode[n];
		this.E = new GraphEdge[n][n];
		
		// iterate through all nodes and create nodes in vertices adjacency list
		for(int i = 0; i < n; i++) {
			V[i] = new GraphNode(i);
		}
		
	}

	/**
	 * insertEdge method adds edge connecting u and v to graph
	 * @param u first endpoint of edge
	 * @param v second endpoint of edge
	 * @param edgeType type of edge
	 * @param label label of edge
	 * @throws GraphException if either nodes do not exist or edge connecting given nodes already exist in graph
	 */
	public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label) throws GraphException {
		// if first end point of edge's name is not between 0 to n-1 then throw GraphException with message that node doesnt exist
		if(u.getName() < 0 || u.getName() >= n) {
			throw new GraphException("Node u doesn't exist.");
		}
		
		// if second end point of edge's name is not between 0 to n-1 then throw GraphException with message that node doesnt exist
		else if(v.getName() < 0 || v.getName() >= n) {
			throw new GraphException("Node v doesn't exist.");
		}
		
		// if edge connecting node u and node v already exists in graph meaning isnt null then 
		// throw GraphException with message that edge connecting node u and node v already exist in the graph
		else if(E[u.getName()][ v.getName()] != null) {
			throw new GraphException("Edge connecting u and v already exists.");
		}
		
		// otherwise, create edges connecting node u and v and update the adjacency matrix
		else {
			E[u.getName()][v.getName()] = new GraphEdge(u, v, edgeType, label);
			E[v.getName()][u.getName()] = new GraphEdge(v, u, edgeType, label);
		}
			
	}

	/**
	 * getNode method returns node with specified name and if no node with specified name exists, then throw GraphException
	 * @param name specified name 
	 * @return graph node with specified name
	 * @throws GraphException if no node with specified name is in graph
	 */
	public GraphNode getNode(int name) throws GraphException {
		// iterate through nodes in graph, and if node's name matches specified name and the node exists then graph
		// then return the graph node with that specified name
		for(int i = 0; i < n; i++) {
			if(V[i].getName() == name && V[i] != null) {
				return V[i];
			}
		}
		// otherwise if there is no node with specified name in graph then throw GraphException
		throw new GraphException("No node with name " + name + " exists");
			
	}

	/**
	 * incidentEdges method returns Java Iterator storing all incident edges of node u and null if u doesnt have any incident edges
	 * and throws GraphException of node u does not exist in graph or it already has an edge connecting given nodes
	 * @param u node of which incident edges need to be returned
	 * @return incidentEdges.iterator() iterator storing incident edges for node u
	 * @throws GraphException if specified node is not in graph or already has edge connecting given nodes
	 */
	public Iterator incidentEdges(GraphNode u) throws GraphException {
		// if node u's name is not between 0 to n-1 then throw GraphException with message that node is not in graph
		if(u.getName() < 0 || u.getName() >= n) {
			throw new GraphException("Node u is not a node of the graph.");
		}
		// otherwise
		else {
			// create ArrayList storing incident edges 
			ArrayList<GraphEdge> incidentEdges = new ArrayList<GraphEdge>(); 
			
			// get row of edges that correspond to name of node u
			GraphEdge[] rowEdges = E[u.getName()];
			
			// iterate through row of edges and add edges existing in graph to ArrayList
			for(int i = 0; i < rowEdges.length; i++) {
				if(rowEdges[i] != null) {
					incidentEdges.add(rowEdges[i]);
				}
			}
			
			// if the ArrayList is empty meaning there were no incident edges found then return null
			if(incidentEdges.size() == 0) {
				return null;
			}
			// otherwise return iterator of the ArrayList
			else {
				return incidentEdges.iterator();
			}
		}
	}
	
	/**
	 * getEdge method returns edge connecting nodes u and v and throws GraphException if no edge between u and v or u and v not nodes in graph
	 * @param u first endpoint of edge
	 * @param v second endpoint of edge
	 * @return edge connecting node u and v
	 * @throws GraphException if no edge between node u and v or node u and v are not in graph
	 */
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		// if node u's name is not between 0 to n-1 then throw GraphException with message that node is not in graph
		if(u.getName() < 0 || u.getName() >= n) {
			throw new GraphException("Node u is not a node in the graph.");
		}
		
		// if node v's name is not between 0 to n-1 then throw GraphException with message that node is not in graph
		else if(v.getName() < 0 || v.getName() >= n) {
			throw new GraphException("Node v is not a node in the graph.");
		}
		
		// if edge connecting u and v is not in graph then throw GraphException with message that there is no edge between u and v
		else if(E[u.getName()][v.getName()] == null) {
			throw new GraphException("There is no edge between u and v.");
		}
		
		// otherwise return edge connecting node u and v 
		else {
			return E[u.getName()][v.getName()];
		}
		
	}

	/**
	 * areAdjacent method returns true if node u and v are adjacent, otherwise false and throws GraphException if nodes u and v not in graph
	 * @param u first endpoint of edge
	 * @param v second endpoint of edge
	 * @return true if node u and v are adjacent otherwise false
	 * @throws GraphException if nodes u and v are not in graph
	 */
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		// if node u's name is not between 0 to n-1 then throw GraphException with message that node is not in graph
		if(u.getName() < 0 || u.getName() >= n) {
			throw new GraphException("Node u is not a node in the graph.");
		}
		
		// if node v's name is not between 0 to n-1 then throw GraphException with message that node is not in graph
		else if(v.getName() < 0 || v.getName() >= n) {
			throw new GraphException("Node v is not a node in the graph.");
		}
		
		// otherwise if edge connecting u and v isnt in graph return false otherwise return true 
		else {
			if(E[u.getName()][v.getName()] == null) {
				return false;
			}
			else {
				return true;
			}
		}
		
	}

}