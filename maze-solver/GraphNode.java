/**
 * This class represents node of the graph
 *
 */
public class GraphNode {
	/**
	 * instance variables name stores the associated name of the node 
	 */
	private int name;
	/**
	 * instance variables mark stores the mark of the node
	 */
	private boolean mark;
	
	/**
	 * constructor which creates node with given name
	 * @param name name of node
	 */
	public GraphNode(int name) {
		// set the specified name to name of the node
		this.name = name; 
	}
	
	/**
	 * mark method sets mark of node with specified value
	 * @param mark mark of node
	 */
	public void mark(boolean mark) {
		// set the specified mark to mark of the node
		this.mark = mark;
	}
	
	/**
	 * isMarked method returns value of the node's mark
	 * @return mark value of mark of node
	 */
	public boolean isMarked() {
		// return the value of mark of node
		return this.mark;
	}
	
	/**
	 * getter getName method returns name of the node
	 * @return name name of node
	 */
	public int getName() {
		// return name of node
		return this.name;
	}
}