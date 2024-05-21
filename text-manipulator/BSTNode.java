/**
 * This class represents node for binary search tree
 *
 */
public class BSTNode {
	/**
	 * stores Record object
	 */
    private Record item;
    /**
     * stores left child node
     */
    private BSTNode leftChild;
    /**
     * stores right child node
     */
    private BSTNode rightChild;
    /**
     * stores parent node
     */
    private BSTNode parent;

    /**
     * Constructor initializes new BSTNode object with specified Record object
     * @param item Record object 
     */
    public BSTNode(Record item) {
    	// initialize Record object item to specified Record object item
    	// set left child, right child and parent node to null
        this.item = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }
    
    /**
     * Getter method which returns Record object stored in this node
     * @return item Record object
     */
    public Record getRecord() {
    	// return Record object item
        return this.item;
    }
    
    /**
     * Setter method which stores given record in this node
     * @param d Record object d
     */
    public void setRecord(Record d) {
    	// set Record object item to specified Record object d 
        this.item = d;
    }
    
    /**
     * Getter method which returns left child
     * @return leftChild left child of this node
     */
    public BSTNode getLeftChild() {
    	// return left child of this node 
        return this.leftChild;
    }

    /**
     * Getter method which returns right child
     * @return rightChild right child of this node
     */
    public BSTNode getRightChild() {
    	// return right child of this node
        return this.rightChild;
    }

    /**
     * Getter method which returns parent node
     * @return parent parent node of this node
     */
    public BSTNode getParent() {
    	// return parent node of this node
        return this.parent;
    }
    
    /**
     * Setter method which sets left child to specified value
     * @param u specified value u used to initialize left child
     */
    public void setLeftChild(BSTNode u) {
    	// set left child to specified value u
        this.leftChild = u;
    }
    
    /**
     * Setter method which sets right child to specified value
     * @param u specified value u used to initialize right child
     */
    public void setRightChild(BSTNode u) {
    	// set right child to specified value u
        this.rightChild = u;
    }
    
    /**
     * Setter method which sets parent to specified value
     * @param u specified value u used to initialize parent
     */
    public void setParent(BSTNode u) {
    	// set parent to specified value u
        this.parent = u;
    }
    /**
     * isLeaf method returns true if this node is a leaf and false otherwise
     * @return true if leaf otherwise false
     */
    public boolean isLeaf() {
    	// if left child and right child (children) are null then return true else false
        return this.leftChild == null && this.rightChild == null;
    }
}