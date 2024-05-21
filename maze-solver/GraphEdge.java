/**
 * This class represents edge of graph 
 *
 */

public class GraphEdge {
	/**
	 * instance variable start stores the first endpoint
	 */
	private GraphNode start;
	/**
	 * instance variable end stores the second endpoint
	 */
	private GraphNode end;
	/**
	 * instance variable type stores the associated type of the edge
	 */
	private int type;
	/**
	 * instance variable label stores the associated label of the edge
	 */
	private String label;
	
	/**
	 * constructor method creates edge with specified first endpoint, second endpoint, type and label
	 * @param u first endpoint 
	 * @param v second endpoint
	 * @param type type of edge
	 * @param label label of edge
	 */
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		// set specified first endpoint to first endpoint of the edge
		this.start = u;
		// set specified second endpoint to second endpoint of the edge
		this.end = v;
		// set specified type to type of edge
		this.type = type;
		// set specified label to label of edge
		this.label = label; 
		
	}
	/**
	 * firstEndpoint method returns first endpoint of edge
	 * @return start first endpoint
	 */
	public GraphNode firstEndpoint() {
		// return first endpoint
		return this.start;
	}
	
	/**
	 * secondEndpoint method returns second endpoint of edge
	 * @return end second endpoint
	 */
	public GraphNode secondEndpoint() {
		// return second endpoint
		return this.end;
	}
	/**
	 * getter getType method returns type of edge
	 * @return type type of edge
	 */
	public int getType() {
		// return type of edge
		return this.type;
	}
	
	/**
	 * setter setType method sets specified type as type of edge
	 * @param newType specified type
	 */
	public void setType(int newType) {
		// set specified type to type of edge
		this.type = newType;
	}
	
	/**
	 * getter getLabel method returns label of edge
	 * @return label label of edge
	 */
	public String getLabel() {
		// return label of edge
		return this.label;
	}
	
	/**
	 * setter setLabel method sets specified label as label of edge
	 * @param newLabel specified label
	 */
	public void setLabel(String newLabel) {
		// set specified label to label of edge 
		this.label = newLabel;
	}
	
}