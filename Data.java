/**
 * This class represents records that will be stored in HashDictionary
 * Each record is stored in dictionary and will consist of a configuration and integer score
 */
public class Data {
	/**
	 * stores configuration as string
	 */
	private String config; 
	/**
	 * stores integer score
	 */
	private int score; 
	
	/**
	 * Constructor method which initializes new data object with specified configuration and score
	 * config string used as key attribute for every data object
	 * @param config configuration string
 	 * @param score integer score  
	 */
	public Data(String config, int score) {
		
		// set config and score to specified config string and integer score
		this.config = config;
		this.score = score;
	}
	
	/**
	 * Getter method that returns configuration  stored in this Data object
	 * @return config configuration of this Data object
	 */
	public String getConfiguration() {
		
		// return configuration of this Data object
		return this.config;
	}
	
	/**
	 * Getter method that returns the score stored in this Data object
	 * @return score integer score of this Data object
	 */
	public int getScore() {
		
		// return score of this Data object
		return this.score;
	}

}