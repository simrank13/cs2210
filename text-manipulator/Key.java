/**
 * This class represents key of data items stored in internal nodes of binary search tree implementing ordered dictionary
 * The key object consists of a label and a type, label is stored in lowercase to for case-insensitive comparisons
 *
 */
public class Key{
	/**
	 * stores label as string
	 */
    private String label;
    /**
     * stores integer type
     */
    private int type;

    /**
     * Constructor method initializes new Key object with specified label and type, 
     * Label is converted to lowercase for case-insensitive comparision
     * @param theLabel label string
     * @param theType type variable
     */
    public Key(String theLabel, int theType) {
    	// set label to specified label in lower case and type to specified type
        this.label = theLabel.toLowerCase();
        this.type = theType;
    }
    /**
     * Getter method that returns label stored in this Key object
     * @return label label of this Key object
     */
    public String getLabel() {
    	// return label of this Key object
        return this.label;
    }

    /**
     * Getter method returns type stored in this Key object
     * @return type type of this Key object
     */
    public int getType() {
    	// return type of this Key object
        return this.type;
    }

    /**
     * compareTo method compares two Key objects and returns 0 if this Key object is same as k and -1 if Key object is smaller than k and 1 if larger
     * @param k specified k object
     * @return 0 if this Key object equals to Key k and -1 if this Key object is smaller than Key k  and 1 if larger 
     */
    public int compareTo(Key k) {
    	// initialization of thisLetter and kLetter variables representing letters in this Key object and Key k
        char thisLetter, kLetter;

        // initialization of precedence string 
        String precedence = "";

        // iterate through label of this Key object and Key k
        for (int i = 0; i < this.label.length() && i < k.getLabel().length(); i++) {
        	// then set letter of this Key as this object's label's first letter and letter of Key k as k object's label's first letter
            thisLetter = this.label.charAt(i);
            kLetter = k.getLabel().charAt(i);
            
            //if the this labels first letter is smaller than k labels first letter then give precedence to this object's label
            if (thisLetter < kLetter) {
                precedence = this.label;
                break;
             // otherwise if this labels first letter is greater than k labels first letter then give precedence to k object's label
            } else if (thisLetter > kLetter) {
                precedence = k.getLabel();
                break;
            }
        }
        // if this object's label matches Key k's label and this object's type matches Key k's type then return 0
        if (this.label.equals(k.getLabel()) && this.type == k.getType()) {
            return 0;
        // other wise if this object's label has precedence or this object's label matches Key k's label and this object's type is less than Key k's type 
        // then return -1
        } else if (precedence == this.label || this.label.equals(k.getLabel()) && this.type < k.getType()) {
            return -1;
        // otherwise return 1
        } else {
            return 1;
        }
    }
 
}