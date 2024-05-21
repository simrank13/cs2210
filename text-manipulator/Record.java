/**
 * This class represents records to be stored in internal nodes of the binary search tree. 
 * Each record object consists of a key and data
 *
 */
public class Record {
	/**
	 * stores Key object in Record object
	 */
    private Key theKey;
    /**
     * stores data as string in Record object
     */
    private String data;

    /**
     * Constructor which initializes new Record object with specified key and data
     * @param k Key object used to initialize theKey 
     * @param theData theData string used to initialize data string
     */
    public Record(Key k, String theData) {
    	// set instance variable theKey and data to specified values k and theData
        this.theKey = k;
        this.data = theData;
    }
    
    /**
     * Getter method which returns key object stored in Record object
     * @return theKey Key theKey object
     */
    public Key getKey() {
    	// return Key object stored in Record object
        return this.theKey;
    }
    
    /**
     * Getter method which returns data string stored in Record object
     * @return data data string
     */
    public String getDataItem() {
    	// return data string stored in Record object
        return this.data;
    }
}