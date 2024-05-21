/**
 * This class represents binary search tree
 *
 */
public class BinarySearchTree {
	/**
	 * stores the root node
	 */
	private BSTNode root; 
	
	/**
	 * Constructor which creates leaf node as root of tree
	 */
	public BinarySearchTree() {
		// initialize root node with null data
		this.root = new BSTNode(null);
	}
	
	/**
	 * getRoot method returns root node of this binary search tree
	 * @return root node
	 */
	public BSTNode getRoot() {
		// return root node of this binary search tree
		return this.root;
	}
	
	/**
	 * getPerLecture helper method finds the node that preceds or matches given key in binary search tree
	 * @param r root node of current sub tree 
	 * @param k key to search for 
	 * @return node containing specified key or leaf node 
	 */
	private BSTNode getPerLecture(BSTNode r,Key k) {
		// if current node is leaf node return the leaf node
		if(r.isLeaf()) {
			return r;
		}
		// otherwise
		else {
			// compare current node's key with specified key 
			// and if they are the same return current node
			if(r.getRecord().getKey().compareTo(k) == 0) {
				return r;
			}
			// otherwise if specified key is greater than current node's key 
			// then make recursive call to search in right subtree
			else if(r.getRecord().getKey().compareTo(k) < 0) {
				return getPerLecture(r.getRightChild(), k);
			}
			// otherwise if specified key is smaller than current node's key 
			// then make recursive call to search in left subtree
			else {
				return getPerLecture(r.getLeftChild(), k);
			}
		}
	}
	/**
	 * get method returns node storing given key
	 * @param r root node
	 * @param k key to search for
	 * @return null if key is not stored in tree with root r otherwise return node if storing given key
	 */
	public BSTNode get(BSTNode r, Key k) {
		// declaration and initialization of currNode which stores node with Record with given key k is stored
		BSTNode currNode = getPerLecture(r,k);
		
		// if current node is leaf then key is not in tree so return null
		if(currNode.isLeaf()) {
			return null;
		}
		// otherwise return node storing given key
		else {
			return currNode;
		}
	}
	
	/**
	 * insert method adds record to binary search tree with root r
	 * throws DictionaryException if tree already stores record with same key as d
	 * @param r root node
	 * @param d Record which is to be inserted
	 * @throws DictionaryException if record with same key already exists in tree
	 */
	public void insert (BSTNode r, Record d) throws DictionaryException{
		// declaration and initialization of currNode which stores node with Record d  
		BSTNode currNode = getPerLecture(r,d.getKey());
		
		// if current node with same key already exists then throw DictionaryException
		if(get(r,d.getKey()) != null) {

			throw new DictionaryException("Node " + d + " is already in tree");
		}
		
		// otherwise 
		else {
			// set Record d for the node found
			currNode.setRecord(d);
			// declaration and initialization of left child and right child
			BSTNode leftVal = new BSTNode(null);
			BSTNode rightVal = new BSTNode(null);
			
			// set current node's left child with leftVal and current node's right child with rightVal
			currNode.setLeftChild(leftVal);
			currNode.setRightChild(rightVal);
			
			// set leftVal and rightVal parents as currNode connecting leaf nodes to current node
			leftVal.setParent(currNode);
			rightVal.setParent(currNode);
		}
	}
	
	/**
	 * smallest method returns node with smallest key in tree with root r
	 * @param r root node
	 * @return node with smallest key and null if tree is empty
	 */
	public BSTNode smallest(BSTNode r) {
		// if tree is empty return null
		if(r == null) {
			return null;
		}
		
		// otherwise
		else {
			
			// declare temporary node and initialize it to root
			BSTNode temp = r;
			
			// while the temporary node isnt leaf node traverse the left child of each node (until reaches leaf node)
			while(!temp.isLeaf()) {
				temp = temp.getLeftChild();
			}
			// return parent of smallest node (smallest node itself)
			return temp.getParent();
		}
	}
	
	/**
	 * largest method returns node with largest key in tree with root r
	 * @param r root node
	 * @return node with largest key and null if tree is empty
	 */
	public BSTNode largest(BSTNode r) {
		// if tree is empty return null
		if(r == null) {
			return null;
		}
		
		// otherwise
		else {
			
			// declare temporary node and initialize it to root
			BSTNode tempNode = r;
			
			// while temporary node isnt leaf node traverse the right child to each node (until reaches leaf node)
			while(!tempNode.isLeaf()) {
				tempNode = tempNode.getRightChild();
			}
			// return parent of largest node (largest node itself)
			return tempNode.getParent();
		}
	}
	
	/**
	 * successor method returns node storing successor of given key in tree with root r and null if successor doesnt exist
	 * @param r root node of binary search tree
	 * @param k key for which successor needs to be found
	 * @return node with successor otherwise null if successor doesnt exist
	 */
	public BSTNode successor(BSTNode r, Key k) {
		// declare and initialize currNode to node storing given key in tree
		BSTNode currNode = getPerLecture(r,k);
		
		// if key is not in tree then get parent of current node, 
		if(get(r,k) == null) {
			// get parent of current node and move up tree to find node with smallest key greater than given key
			currNode = currNode.getParent();
			// then keep moving up tree until reached root of key found is greater than given key
			while(currNode != null && currNode.getRecord().getKey().compareTo(k) < 0) {
				currNode = currNode.getParent();
			}
			// return node with smallest key greater than given key which is the successor of given key 
			return currNode;
		} 
		// if current node's right child is not leaf node, then make recursive call to check right subtree 
		if(!currNode.getRightChild().isLeaf()) {
			return smallest(currNode.getRightChild());
		}
		else {
			// get parent of current node and move up tree to find node with smallest key greater than given key
			currNode = currNode.getParent();
			// then keep moving up tree until reached root of key found is greater than given key
			while(currNode != null && currNode.getRecord().getKey().compareTo(k) < 0) {
				currNode = currNode.getParent();
			}
			// return node with smallest key greater than given key which is the successor of given key 
			return currNode;
		}
	}
	
	/**
	 * predecessor method returns node storing predecessor of given key in tree with root r and null if predecessor doesnt exist
	 * @param r root node of binary search tree
	 * @param k key for which predecessor needs to be found
	 * @return node which predecessor otherwise null if predecessor doesnt exist
	 */
	public BSTNode predecessor(BSTNode r, Key k) {
		// declare and initialize currNode to node storing given key in tree
		BSTNode currNode = getPerLecture(r,k);
		
		// if key is not in tree then get parent of current node, 
		if(get(r,k) == null) {
			// get parent of current node and move up tree to find node with largest key smaller than given key
			currNode = currNode.getParent();
			while(currNode != null && currNode.getRecord().getKey().compareTo(k) > 0) {
				currNode = currNode.getParent();
			}
			// return node with largest key smaller than given key which is the predecessor of given key 
			return currNode;
			
		}
		// if current node's left child is not leaf node, then make recursive call to check left subtree 
		if(!currNode.getLeftChild().isLeaf()) {
			return largest(currNode.getLeftChild());
		}
		else {
			// get parent of current node and move up tree to find node with largest key smaller than given key
			currNode = currNode.getParent();
			while(currNode != null && currNode.getRecord().getKey().compareTo(k) > 0) {
				currNode = currNode.getParent();
			}
			// return node with largest key smaller than given key which is the predecessor of given key 
			return currNode;
		}
	}
	
	/**
	 * remove method deletes node with given key from tree with root r
	 * throws DictionaryException if tree does not store record with given key
	 * @param r root node of binary search tree 
	 * @param k key of node to be removed 
	 * @throws DictionaryException if node with given key is not in present in tree
	 */
	public void remove(BSTNode r, Key k) throws DictionaryException {
		// declare and initialize currNode to node storing given key in tree
		BSTNode currNode = getPerLecture(r,k);
		
		// if key is not in tree then throw DictionaryException
		if (get(r,k) == null) {
			throw new DictionaryException("Node " + k + " is not in tree"); 
		}
		
		// otherwise
		else {
			
			//declare and initialize parentNode to current nodes parent node
			BSTNode parentNode = currNode.getParent();
			
			// if current node is leaf node meaning both left and right child are leaves then 
			if(currNode.getLeftChild().isLeaf() && currNode.getRightChild().isLeaf()) {
				// create new leaf node to replace current node
				BSTNode newNode = new BSTNode(null);
				
				// if parent node isnt null and current node is left child of parent node then 
				if (parentNode != null){
					if(parentNode.getLeftChild() == currNode) {
						//set new node as left child of parent node
						newNode.setParent(parentNode);
						parentNode.setLeftChild(newNode);
					}
					// if current node is right child of parent node then set new node as right child of parent node
					else if(parentNode.getRightChild() == currNode){
						newNode.setParent(parentNode);
						parentNode.setRightChild(newNode);
					}
				}
				else {
					// if current node is root then replace current node with new leaf node and assign root's record to null (null data)
					root.setRecord(null);
					root.setLeftChild(null);
					root.setRightChild(null);
				}
			}
			// otherwise if current nodes children are not both leaf nodes then
			else {
				// current node's left child and right child arent leaf nodes then
				if(!(currNode.getLeftChild().isLeaf()) && !(currNode.getRightChild().isLeaf())) {
					
					// if current node's right child isnt null then 
					if(currNode.getRightChild() != null) {
						// declare and initialize smallest to smallest node in right subtree
						BSTNode smallest = smallest(currNode.getRightChild());
						// declare and initialize data to get record of smallest node in right subtree
						Record data = smallest.getRecord();
						
						// remove smallest node from tree
						remove(root, data.getKey());
						// set current nodes record to the smallest nodes record replacing current node's record with smallest node's record
						currNode.setRecord(data);
					}
					// otherwise if current node's left child isnt null then
					else if(currNode.getLeftChild() != null) {
						// declare and initialize largest to largest node in left subtree
						BSTNode largest = largest(currNode.getLeftChild());
						// declare and initialize data to get record of largest node in left subtree
						Record data = largest.getRecord();
						
						// remove largest node from tree
						remove(root, data.getKey());
						// set current nodes record to the largest nodes record replacing current node's record with largest node's record
						currNode.setRecord(data);
					}
					
				}
				// otherwise if current node's left child isnt leaf node meaning current node has only left child then
				else if (!(currNode.getLeftChild().isLeaf())) {
					
					// if parent node isnt null then
					if(parentNode != null) {
						// if current node is left child of parent node then replace current node with current node's left child
						if (parentNode.getLeftChild() == currNode) {
							parentNode.setLeftChild(currNode.getLeftChild());
							currNode.getLeftChild().setParent(parentNode);
						}
						// otherwise if current node is right child of parent node then replace current node with current node's left child
						else if (parentNode.getRightChild() == currNode) {
							parentNode.setRightChild(currNode.getLeftChild());
							currNode.getLeftChild().setParent(parentNode);
					    }
					}
					// otherwise if parent node is null meaning current node is root, then
					else {
						// set record of current node to record of current node's left child
						Record value = currNode.getLeftChild().getRecord();
						currNode.setRecord(value);
						
						// remove current node's left child
						remove(currNode.getLeftChild(), value.getKey());

					}
				}
				// otherwise if current node's right child isnt leaf meaning current node current node's right child then
				else if (!(currNode.getRightChild().isLeaf())) {
					
					// if parent node isnt null then
					if(parentNode != null) {
						// if current node is left child of parent node then replace current node with current node's right child
						if (parentNode.getLeftChild() == currNode) {
							parentNode.setLeftChild(currNode.getRightChild());
							currNode.getRightChild().setParent(parentNode);
						} 
						// otherwise if current node is right child of parent node then replace current node with current node's right child 
						else if (parentNode.getRightChild() == currNode) {
							parentNode.setRightChild(currNode.getRightChild());
							currNode.getRightChild().setParent(parentNode);
						}
					}
					// otherwise if parent node is null meaning current node is root, then
					else {
						// set record of current node to record of current node's right child
						Record val = currNode.getRightChild().getRecord();
						currNode.setRecord(val);
						
						// remove current node's right child
						remove(currNode.getRightChild(), val.getKey());
					}

				}

				
			}
		}
		
		
	}
}