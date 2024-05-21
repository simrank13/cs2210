/**
 * This class implements an ordered dictionary using binary search tree
 * In the binary search tree, only internal nodes will store information, leaves
 * must be nodes storing null Record objects. Key for internal node will be Key object 
 * from Record stored in that node
 *
 */
public class BSTDictionary implements BSTDictionaryADT {
	/*
	 * BinarySearchTree object stores data in dictionary
	 */
	private BinarySearchTree bst;  
	
	/**
	 * Constructor which creates new BSTDictionary with empty Binary Search Tree
	 */
	public BSTDictionary() {
		this.bst = new BinarySearchTree();
		
	}

	/**
	 * get method returns Record with key k or null if Record is not in dictionary
	 * @param k key for Record that needs to be found
	 * @return Record with key k otherwise null if Record not in dictionary
	 */
	public Record get(Key k) {
		// declare and initialize currNode to get node with specified key from binary search tree
		BSTNode currNode = bst.get(bst.getRoot(), k);
	    
	    // if current node is null or is leaf node then key is not in dictionary so then return null
	    if(currNode == null || currNode.isLeaf()) {
	    	return null;
	    }
	    // otherwise
	    else {
	    	
	    	// return Record that is associated with node with the specified key
	    	return currNode.getRecord();
	    }
	}

	/**
	 * put method inserts record d into ordered dictionary and throw DictionaryException if Record with same key as d 
	 * is already in dictionary
	 * @param r Record that needs to be inserted 
	 * @throws DictionaryException if record with same key already exists in dictionary
	 */
	public void put(Record r) throws DictionaryException {
		// insert Record r into binary search tree
		bst.insert(bst.getRoot(), r);
		
	}
	
	/**
	 * remove method Record with Key k from dictionary. It throws DictionaryException if Record is not in dictionary
	 * @param k key of Record that needs to be removed
	 * @throws DictionaryException if key is not present in dictionary
	 */
	public void remove(Key k) throws DictionaryException {
		// if key is not present in dictionary, then throw DictionaryException
		if (this.get(k) == null) {
			throw new DictionaryException("Key is not in Dictionary");
		}
		// declare and initialize r to node containing specified key from binary search tree
		 BSTNode r = bst.get(bst.getRoot(), k);
		 // remove node with specified key from binary search tree
	     bst.remove(r, k);
		
	}
	
	/**
	 * successor method which returns successor of k and returns null if given key has no successor. 
	 * @param k key for which to get successor
	 * @return Record with successor key, otherwise null if successor doesnt exist 
	 */
	public Record successor(Key k) {
		// declare and initialize successor to node with successor key from binary search tree
		BSTNode successor = bst.successor(bst.getRoot(), k);
		
		// if successor node is null, successor doesnt exist so return null
		if( successor == null) {
			return null;
		}
		// otherwise
		else {
			// return Record with successor key
			return successor.getRecord();
		}
	}
	
	/**
	 * predecessor method which returns predecessor of k and returns null if given key has no predecessor
	 * @param k key for which to get predecessor
	 * @return Record with predecessor key, otherwise null if predecessor doesnt exist
	 */
	public Record predecessor(Key k) {
		// declare and initialize predecessor to node with predecessor key from binary search tree
		BSTNode predecessor = bst.predecessor(bst.getRoot(), k);
		
		// if predecessor node is null, predecessor doesnt exist so return null
		if( predecessor == null) {
			return null;
		}
		// otherwise
		else {
			// return Record with predecessor key
			return predecessor.getRecord();
		}
		
	}
	/**
	 * smallest method returns Record with smallest key in ordered dictionary
	 * Returns null if dictionary is empty
	 * @return Record with smallest key, otherwise null if dictionary is empty
	 */
	public Record smallest() {
		// declare and initialize currNode to node with smallest key from binary search tree
		BSTNode currNode = bst.smallest(bst.getRoot());
		
		// if node is null then dictionary is empty so then return null
		if(currNode == null) {
	    	return null;
	    }
		
		// otherwise
	    else {
	    	// return Record with node with smallest key 
	    	return currNode.getRecord();
	    }
	}
	
	/**
	 * largest method returns Record with largest key in ordered dictionary
	 * Returns null if dictionary is empty
	 * @return Record with largest key, otherwise null if dictionary is empty
	 */
	public Record largest() {
		// declare and initialize currNode to node with largest key from binary search tree
		BSTNode currNode = bst.largest(bst.getRoot());
		
		// if node is null then dictionary is empty so then return null
		if(currNode == null) {
	    	return null;
	    }
		// otherwise
	    else {
	    	// return Record with node with largest key 
	    	return currNode.getRecord();
	    }
		
	}
}