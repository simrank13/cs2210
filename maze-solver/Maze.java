// import Iterator, FileReader, BufferedReader, and Stack from java library
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class represents the mass, in which object of class Graph is used to store maze and solution is found for it
 *
 */
public class Maze {
	/**
	 * instance variable graph stores maze
	 */
	private Graph graph;
	/**
	 * instance variable startRoom and exitRoom store the room where maze starts and room where maze exists
	 */
	private GraphNode startRoom, exitRoom;
	/**
	 * instance variable numOfCoins stores the available number of coins that can be used in maze
	 */
	private int numOfCoins; 

	/**
	 * Constructor method reads input file and builds graph representing maze and if 
	 * input file does not exist or format of input file is not correct then throw MazeException
	 * @param inputFile input file of maze
	 * @throws MazeException if input file does not exist or format of file is not correct
	 */
	public Maze(String inputFile) throws Exception {
		// using try catch to handle exception while reading input file
		try {
			// create buffered reader to read from input file
			BufferedReader fileInput = new BufferedReader(new FileReader(inputFile));
				
			// read first four line of input file and store first line as the scaleFactor, 
			//second line as width, third line as length, and four line as available number of coins to use
			int scaleFactor = Integer.parseInt(fileInput.readLine());
			int width = Integer.parseInt(fileInput.readLine());    
			int length = Integer.parseInt(fileInput.readLine());   
			this.numOfCoins = Integer.parseInt(fileInput.readLine());
			
			// create graph representing maze with specified width and length
			this.graph = new Graph(width * length);
				
			// create matrix to store maze layout
			char[][] mazeMatrix = new char[length * 2 - 1][width * 2 - 1];
			
			// declare and initialize line variable which will store the line read from the input file 
			// and lineCount counter variable will record number of lines in input file
			String line = "";
			int lineCount = 0;
			
			// read each line from input file until no more lines in input file and reached end of file
			while((line = fileInput.readLine()) != null) {
				
				// iterate through each character in current line then get the character at current position in line
				for(int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					
					// then assign character to corresponding position in matrix 
					mazeMatrix[lineCount][i] = c;
				}
				// move to next row in mazeMatrix array as new line is retrieved 
				lineCount++;
			}
			// declare and initialize room variable to keep track of room number
			int room = 0;
			
			// iteration through every other (even) row of mazeMatrix matrix 
			for(int i = 0; i < mazeMatrix.length; i+=2) {
				// iteration through every other (odd) column of mazeMatrix matrix 
				for (int j = 1; j < mazeMatrix[0].length; j+=2) {
					
					// if current position to left of current position c is 's' then assign start room node to current room in graph
					if(mazeMatrix[i][j-1] == 's') {
						this.startRoom = this.graph.getNode(room);	
					}
					
					// if current position to right of current position c is 's' then assign start room node to next room in graph
					else if (mazeMatrix[i][j+1] == 's'){
						this.startRoom = this.graph.getNode(room+1);
					}
					
					// if current position to left of current position c is 'x' then assign exit room node to current room in graph
					if(mazeMatrix[i][j-1] == 'x') {
						this.exitRoom = this.graph.getNode(room);	
					}
					
					// if current position to right of current position c is 'x' then assign exit room node to next room in graph
					else if(mazeMatrix[i][j+1] == 'x') {
						this.exitRoom = this.graph.getNode(room+1);
					}
					
					// create start and end nodes which represents starting room for current position c and ending room for current position c
					// position c connects two room in graph, ending room is retrieved by adding 1 to starting room index 
					GraphNode start = this.graph.getNode(room);
					GraphNode end = this.graph.getNode(room+1);
					
					
					// store current position c in maze into c variable
					char c = mazeMatrix[i][j];
					
					// declare and initialize type and label variable to store type and label of edge
					int type = 0;
					String label = "";
					
					// if current position c is not 'w' (wall) then
					if (c != 'w') {
						// if current position c is 'c' (corridor) then set label of edge as "corridor"
						if (c == 'c') {
							label = "corridor";
						}
						
						// if current position c is 'o' (room) then set label of edge as "room"
						else if(c == 'o') {
							label = "room";
						}
						
						// otherwise, if current position c is a digit and is between 0 and 9 then set label of edge to "door" and type to numeric value of character c 
						else if(Character.isDigit(c) && Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9){ 
							
							label = "door";
							type = Character.getNumericValue(c);
						}
						
						//insert edge between current start and end nodes with updated type and label
						this.graph.insertEdge(start, end, type, label);
					}
					// increment room counter to keep track of rooms processed
					room++;
					
				}
				// increment room counter for skipped (even) rows 
				room++;
				
			}
			
			// iterate through every other (even) column of mazeMatrix matrix 
			for (int i = 0; i < mazeMatrix[0].length; i+=2) {
				// declaration of rowCounter variable which will keep track of row index in mazeMatrix
				int rowCounter = 0;
				
				// iterate through every other (odd) row of mazeMatrix matrix
				for (int j = 1; j < mazeMatrix.length; j+=2) {
					
					// get starting node for current position c in maze and ending nodes associated with next row in maze for current position c
					GraphNode start = this.graph.getNode(i/2 + (rowCounter * width));
					GraphNode end = this.graph.getNode(i/2 + (rowCounter + 1) * width);
					
					// retrieve character at current position c in mazeMatrix and store it in c variable
					char c = mazeMatrix[j][i];
					
					// declare and initialize type and label variable to store type and label of edge
					String label = "";
					int type = 0;
					
					// if current position c is not 'w' (wall) then
					if(c != 'w') {
						// if current position c is 'c' (corridor) then set label of edge as "corridor"
						if(c == 'c') {
							label = "corridor";
						}
						
						// otherwise, if current position c is a digit and is between 0 and 9 then set label of edge to "door" and type to numeric value of character c 
						else if(Character.isDigit(c) && Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9){
							label = "door";
							type = Character.getNumericValue(c);
						}
						//insert edge between current start and end nodes with updated type and label
						this.graph.insertEdge(start,  end,  type,  label);
					}
					// increment row counter for next iteration
					rowCounter++; 
				}
				
			}
			
		}
		// if Exception is thrown then catch that exception and throw MazeException with message that input file does not exist or the format of the file is incorrect
		catch(Exception e) {
			throw new MazeException("Input file does not exist or format of input file is not correct.");
		}
	}
	/**
	 * getGraph method returns reference to Graph object representing maze and throws
	 * MazeException if graph is null
	 * @return reference to Graph object representing maze 
	 * @throws MazeException if graph is null meaning graph does not exist
	 */
	public Graph getGraph() throws MazeException {
		// if graph does not exist, meaning graph is null then throw MazeException with message that graph does not exist
		if(graph == null) {
			throw new MazeException("Graph Does Not Exist.");
		}
		// otherwise return graph representing maze
		else {
			return graph;
		}
	}
	
	/**
	 * solve method returns java Iterator containing nodes of path from entrance to exit of maze if path exists 
	 * and if path does not exist, returns null
	 * @return java Iterator of nodes of path from start to exist if path exist otherwise return null
	 * @throws GraphException if no incident edge for specified node in graph
	 */
	public Iterator solve() throws GraphException {
		// create stack to keep track of path of nodes 
		Stack<GraphNode> stack = new Stack<GraphNode>();
		
		// get the starting room node and store in startNode graph node 
		GraphNode startNode = this.startRoom;
		
		// get the exit room node and store in exitNode graph node
		GraphNode exitNode = this.exitRoom;
		
		// if there is a path then return iterator containing nodes of path
		if(path(startNode, exitNode, stack)) {
			return  stack.iterator();
		}
		// otherwise if there is no path return null
		else {
			return null;
		}
	}
	
	/**
	 * path helper method finds solution path in maze using depth-first search 
	 * @param start current node in path
	 * @param end target node to reach
	 * @param stack stack to store path
	 * @return true if solution path found, otherwise false
	 * @throws GraphException if no incident edge for specified node in graph
	 */
	private boolean path(GraphNode start, GraphNode end,  Stack<GraphNode> stack) throws GraphException {
		// previousEdge graph edge object stores edge leading to current node
		GraphEdge previousEdge = null;
		
		// add current node to path
		stack.push(start);
		
		// if current node is target node then return true to indicate solution path is found
		if (start == end) {
            return true;
            
        // otherwise
        } else {
        	// mark current node as visited 
        	start.mark(true);

        	// until the stack is empty, 
            while (!stack.isEmpty()) {
            	
            	// get current node in path and store as current graph node
            	GraphNode current = stack.peek(); 
            	
            	// if current node is exit room then return true to indicate solution path is found
            	if (current == this.exitRoom) {
            		return true;
            	}
            	
            	// get iterator over incident edges of current node
            	Iterator<GraphEdge> incidentEdge = this.graph.incidentEdges(current);
            	
            	// next graph node object stores next node to visit 
	            GraphNode next = null;
	            
	            // iterate through incident edges to get neighbouring nodes
            	while (incidentEdge.hasNext()) {
            		
            		// get next incident edge and store in edge graph edge object
	                GraphEdge edge = incidentEdge.next();
	                
	                // get neighbour node connected by current edge
	                GraphNode neighbour = edge.secondEndpoint();
	                
	                // if type of edge is less than or equal to available number of coins that can be used then
	                if (edge.getType() <= this.numOfCoins ) {
	                	// if neighbour has not been visited then 
	                	if (neighbour.isMarked() == false) { 
	                		//mark neighbour as visited
	                		neighbour.mark(true);
	                		
	                		// decrement coins based on type of edge and then add neighbour to path
	                		this.numOfCoins -= edge.getType();
	                		stack.push(neighbour);
	                		
	                		// update previous edge to current edge and next edge to neighbour edge
	                		previousEdge = edge;
	                		next = neighbour;
	                		
	                		// break to visit next node
	                		break;
	                	}
	                }            	
	            }
	            
            	// backtrack if no valid neighbour is found and remove current node form path
	            if (next == null) { 
	            	stack.pop();
	            	
	            	// backtrack by getting previous edge and updating coin counter by incrementing back the type of previous edge 
	            	previousEdge = this.graph.getEdge(previousEdge.secondEndpoint(), previousEdge.firstEndpoint());
	            	this.numOfCoins += previousEdge.getType();
	            	
	            }
	            
            }
            // if no solution path found then return false
        	return false;
        }
	}
	
}