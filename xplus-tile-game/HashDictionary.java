/**
 * this class implements Dictionary ADT using hashtable using hash table with seperating chaining to minimize collisions
 * it stores records with configurations and integer scores and provides methods to manipulate dictionary
 * collisions are tracked to evaluate quality of hash function
 *
 */
public class HashDictionary implements DictionaryADT{
	/**
	 * represents hash table with seperate linking using linked list
	 */
	private LinearNode[] table;  // stores hash table with separate linking using linked list
	/**
	 * number of collisions 
	 */
	private int collision; 
	
	/**
	 * Constructor method which initializes new HashDictionary with specified size
	 * @param size specified size of HashDictionary
	 */
	public HashDictionary(int size) {
		
		// set hash table to point to linear node storing specified size and set collision to 0
		table = new LinearNode[size];
		this.collision = 0;
	}
	
	/**
	 * hashFunction method calculates hash index for given key using polynomial hash function
	 * @param key configuration string to hash
	 * @return calculated hash index
	 */
	private int hashFunction(String key) {
		
		// initialization of variable storing hash index
		int index = 0;
		
		// loop through index 0 to until the length of the configuration string key length 
		//and update index using polynomial hash function
		for(int i = 0; i < key.length(); i++) {
			
			// then multiply current index by 39 and add ASCII value of character at position i 
			//and then calculate the modulo that fits in the table's size
			index = (11 * index + key.charAt(i)) % table.length;
		}
		
		// then return the calculated hash index
		return index;
	}
	
	/**
	 * put Method adds Data record to dictionary and throws DictionaryException exception 
	 * if configuration string of record already exists in the dictionary
	 * @param record Data record 
	 * @return 1 if adding record produce collision, otherwise 0
	 * @throws DictionaryException if configuration already exists in dictionary
	 */
	public int put(Data record) throws DictionaryException {
		
		// initialize index variable which stores hash index calculated for given configuration string of Data object record
		int index = hashFunction(record.getConfiguration());
		
		// create new LinearNode node newNode with record
		LinearNode newNode = new LinearNode(record);
		
		// if hash table at calculated index is empty then store new node in hash table (by setting the table at calculated index as newNode)
		if(table[index] == null) {
			table[index] = newNode;
		}
		
		// otherwise if hash table at calculated index is not empty and has node in at calculated index then
		else {
			
			// create new node curr which represents current node that stores the hash table at calculated index
			LinearNode curr = table[index];
			
			// check if configuration already exists in hash table by checking if the current node's element's configuration string is same as Data object record's configuration string 
			//and if so throw DictionaryException exception
			if(curr.getElement().getConfiguration().equals(record.getConfiguration())) {
				throw new DictionaryException();
			}
			
			// loop until the next node of the current node is null and if the current node's next node is null 
			//then check if the configuration already exists in hash table by checking if current node's element's configuration string is same as Data object record's configuration string 
			//and if so throw DictionaryException exception
			while(curr.getNext() != null) {
				if(curr.getElement().getConfiguration().equals(record.getConfiguration())) {
					throw new DictionaryException();
				}
				
				// move to next node in linked list (by setting curr node as next node of curr node)
				curr = curr.getNext();
			}
			
			// set the next node of the current node as the new node, this adds the new node to the end of chain
			curr.setNext(newNode);
		}
		
		// create new node curr which represents current node that stores hash table at calculated index
		LinearNode curr = table[index];
		
		// initialize counter variable which will store number of nodes in hash table
		int counter = 0;
		
		// iterate while current node is not null then increment counter variable by 1 (since that means more than one node with Data object record) 
		//if so and then get the next node and update it to be the current node as this will allow to check nodes in dictionary 
		//and iterate through dictionary until next node of this current node is null
		while(curr != null) {
			counter++;
			curr = curr.getNext();
		}
		
		// if counter variable is greater than 1 meaning if more than one node (with that record) then it will be a collision hence set collision to 0
		if(counter > 1) {
			collision = 1;
		}
		
		// otherwise set collision to 0 (meaning no collision) 
		else {
			collision = 0;
		}
		
		// return number of collisions, either 1 for collision or 0 for no collision
		return collision;
	}
	
	/**
	 * remove method removes record with given configuration from dictionary and throws DictionaryException exception 
	 * if there is no record in hash table that stores config (configuration string, config)
	 * @param config configuration to remove
	 * @throws DictionaryException if no record with given configuration exists 
	 */
	public void remove(String config) throws DictionaryException {
		
		// initialize index variable which stores hash index calculated for given configuration string config
		int index = hashFunction(config);
		
		// if hash table at calculated index is empty then throw DictionaryException exception 
		if(table[index] == null) {
			throw new DictionaryException();
		}
		
		// otherwise if hash table at calculated index is not empty and has node in at calculated index then
		else {
			
			// create new node curr node which represents current node that stores the hash table at calculated index
			// create new node prev node which represents previous node (representing that current node is first node)
			LinearNode curr = table[index];
			LinearNode prev = null;
			
			// if current node is null meaning if first node in hash table is null the configuration does not exist so throw DictionaryException exception
			if(curr == null) {
				throw new DictionaryException();
			}
			
			// if prev node is null meaning if configuration is in first node, 
			//then removes node by setting table at calculated index to the next node of the current node (this skips over current node and points directly to next node)
			if(prev == null) {
				table[index] = curr.getNext();
			}
			
			//if prev is not null meaning if configuration is not in first node but is in chained node, 
			//then remove it by setting prev node's next node as curr node's next node 
			//(updates reference of prev node's next node for curr node and point directly to node coming after curr node)
			else {
				prev.setNext(curr.getNext());
			}
			
			// iterate while curr node is not null and curr node's element does not equal config string and if so then 
			while(curr != null & !curr.getElement().getConfiguration().equals(config)) {
				
				// set prev node to curr node this updates prev node's reference to curr node then move to next node in linked list (by setting curr node as next node of curr node)
				prev = curr;
				curr = curr.getNext();
			}
		}
	}
	
	/**
	 * get method returns score stored in record of dictionary with specified configuration key or -1 if configuration string config is not in dicitonary
	 * @param config configuration to get score for
	 * @return score of configuration otherwise -1 if not found
	 */
	public int get(String config) {
		
		// initialize index variable which stores hash index calculated for given configuration string config
		int index = hashFunction(config);
		
		// create new node curr which represents current node that stores the hash table at calculated index
		LinearNode curr = table[index];
		
		// iterate while curr node is not null then if curr node's element matches the configuration string 
		// then create temporary Data object which stores the curr nodes element and then returns associated score with temporary Data object
		while(curr != null) {
			
			if(curr.getElement().getConfiguration().equals(config)) {
				Data tempDataObj = curr.getElement();
				return tempDataObj.getScore();
			}
			
			// move to next node in linked list (by setting curr node as next node of curr node)
			curr = curr.getNext();
		}
		
		// if configuration is not found, return -1 showing that it does not exist in hash table
		return -1;
	}
	
	/**
	 * numRecords return number of Data object stored in dictionary 
	 * @return counter number of records in dictionary
	 */
	public int numRecords() {
		
		// initialize counter variable storing number of records
		int counter = 0;
		
		// iterator from first node though the hash table's last node then
		for(int i = 0; i < table.length; i++) {
			// initialize the currNode to create node for each element of each index at hash table
			LinearNode currNode = table[i];
			
			// iterate while curr node is not null then increment counter variable by one to keep track of number of records in table then move to next node in linked list (by setting curr node as next node of curr node)
			while(currNode != null) {
				counter++;
				currNode = currNode.getNext();
			}
			
		}
		
		// return number of records
		return counter;
	}
	
	
	/**
	 * A helper class that stores Data as a linked structure, this class is used for implementing linked list structure to store Data objects
	 * Instance variables of LinearNode contains reference to next LinearNode and Data element, it is used within HashDictionary class for chaining elements in the hash table.
	 * @author skullar5
	 */
	private class LinearNode {
		/**
		 * reference to next LinearNode in linked structure
		 */
	    private LinearNode next;
	    /**
	     * data element stored in this node
	     */
	    private Data element;
	    
	    /**
	     * Constructor to create LinearNode with specified Data element
	     * @param elem Data element to store in this node
	     */
	    LinearNode (Data elem){
	    	// initialize next reference to null
	        next = null;
	        // store provided Data element
	        element = elem;
	    }
	    
	    /**
	     * Gets next LinearNode in the linked structure
	     * @return next LinearNode in linked list
	     */
	    LinearNode getNext(){
	    	// return next node
	        return next;
	    }
	    
	    /**
	     * Sets next LinearNode in linked structure
	     * @param node LinearNode to set as the next node
	     */
	    void setNext (LinearNode node){
	    	// set next node to specified node
	        next = node;
	    }
	    
	    /**
	     * Get Data element stored in this LinearNode
	     * @return Data element stored in this node
	     */
	    Data getElement(){
	    	// return Data element stored in node
	        return element;
	    }

	}

}
