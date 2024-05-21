/**
 * This class represents methods used by aglorithm computerPlay where board_size specifies size of the board, 
 * lengthToWin specifies length of X shape or + shape needed to win game and max_levels is maximum level of game tree =
 *
 */
public class Configurations {
	/**
	 * 2d character array representing gameboard
	 */
	private char[][] board;
	/**
	 * size of game board
	 */
	private int board_size; /**
	 * number of consecutive symbols required to win
	 */
	private int lengthToWin;
	/**
	 * maximum depth for exploring game tree
	 */
	private int max_levels; 
	
	
	/**
	 * Constructor method initialized gameboard with empty spaces, ' ', and sets board_size, lengthToWin, max_level parameters
	 * @param board_size size of game board
	 * @param lengthToWin number of consecutive symbols required to win 
	 * @param max_levels maximum depth for exploring game tree
	 */
	public Configurations(int board_size, int lengthToWin, int max_levels) {
		
		// initializing instance variables (board_size, lengthToWin, and max_levels) to specified board size, required length to win and maximum levels in game tree 
		this.board_size = board_size;
		this.lengthToWin = lengthToWin;
		this.max_levels = max_levels;
		
		// initialize the board to have specified size and iterate through each row and column of the board and set each position with empty spaces
		board = new char[board_size][board_size];
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				board[i][j] = ' ';
			}
		}
		
	}
	
	/**
	 * createDictionary() method creates empty HashDictionary with appropriate size 
	 * @return empty HashDictionary with prime-sized hash table
	 */
	public HashDictionary createDictionary() {
		
		// initialize a HashDictionary with a prime number size of 9973 and then returns that HashDictionary
		HashDictionary hashDict = new HashDictionary(9973);
		return hashDict;
	}
	
	/**
	 * repeatedConfiguration method first stores characters of board in String then checks  if String representing board is in hashtable
	 * @param hashTable HashDictionary checks for game board configuration 
	 * @return associated score if configuration is found, otherwise, -1
	 */
	public int repeatedConfiguration(HashDictionary hashTable) {
		
		// initialize empty string
		String s = "";
		
		// iterate through each row and column of board then  
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				// add character at position ith row and jth column to configuration string
				s += board[i][j];
			}
		}
		
		// if configuration is not found in dictionary then return -1 
		if(hashTable.get(s) == -1) {
			return -1;
		}
		
		// otherwise if configuration is found return associated score
		else {
			return hashTable.get(s);
		}
	}
	
	/**
	 * addConfiguration method first represents content of board as String then it inserts this String and score in hashDictionary
	 * adds current game board configuration to given hashDictionary with specified score
	 * @param hashDictionary HashDictionary to add game board configuration to
	 * @param score score associated with game board configuration
	 */
	public void addConfiguration(HashDictionary hashDictionary, int score) {
		
		// initialize empty string
		String s = "";
		
		// iterate through each row and column of board then 
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				// add character at position ith row and jth column to configuration string
				s += board[i][j];
			}
		}
		
		// create Data object with configuration string s and score then add it in the hash dictionary using put method
		Data data = new Data(s, score);
		hashDictionary.put(data);
		
	}
	
	/**
	 * savePlay method stores symbol in board[row][col] with specified row, column and symbol
	 * saves player's move on game board
	 * @param row row where player's symbol is placed
	 * @param col column where player's symbol is placed
	 * @param symbol player's symbol ('X'(human) or 'O'(computer)
	 */
	public void savePlay(int row, int col, char symbol) {
		
		// set specified square on board to players symbol ('X' or 'O')
		board[row][col] = symbol;
	}
	
	/**
	 * squareIsEmpty method returns true if board[row][col] is ' ', otherwise return false
	 * checks if specific square on board is empty
	 * @param row row of square to check
	 * @param col column of square to check
	 * @return true if the square is empty (' '), otherwise false
	 */
	public boolean squareIsEmpty(int row, int col) {
		
		// return true if square contains empty space
		if(board[row][col] == ' ') {
			return true;
		}
		
		// otherwise return false
		else {
			return false;
		}
	}
	
	/**
	 * wins method returns true if X shape or + shape of length at least k formed by tiles of kind symbol on board where k is length of X shape or +shape needed to win game
	 * checks if given symbol ('X' or 'O') has formed X shaped or + shape on board
	 * @param symbol symbol to check for ('X' or 'O')
	 * @return true if specified symbol has formed winning shape, false otherwise
	 */
	public boolean wins(char symbol) {
		
		// iterate through each tile in board to check for + shape, starting at 1 and going up to board_size - 1 so that all adjacent tiles are checked including center tile in each direction
		for(int i = 1; i < board_size-1; i++) {
			for(int j = 1; j < board_size-1; j++) {
				
				// if the tiles form + and are marked by specified symbol then start counting length of shape from there
				if (board[i][j] == symbol && board[i+1][j] == symbol && board[i-1][j] == symbol && board[i][j+1] == symbol && board[i][j-1] == symbol) {
					int count = 1;
					
					// iterate from i - 1 until the top row is reached or tile does not match the symbol
					// check adjacent tiles in north direction and if there are tiles in north direction for each tile the count variable increments by 1 
					for (int k = i - 1; k >= 0 && board[k][j] == symbol; k--) {
						count++;
					}
					
					// iterate from i + 1 until the bottom row is reached or until the tile doesn't match the symbol
					// check adjacent tiles in south  direction and if there are tiles in south direction for each tile the count variable increments by 1  
					for(int k = i + 1; k <= board_size - 1 && board[k][j] == symbol; k++) {
						count++;
					}
					
					// iterate from i - 1 until the west-most row is reached or tile does not match the symbol
					// check adjacent tiles in west direction and if there are tiles in west direction for each tile the count variable increments by 1 
					for(int k = j - 1; k >= 0 && board[i][k] == symbol; k--) {
						count++;
					}
					
					// iterate from i - 1 until the east-most row is reached or tile does not match the symbol
					// check adjacent tiles in east direction and if there are tiles in east direction for each tile the count variable increments by 1 
					for(int k = j + 1; k<= board_size - 1 && board[i][k] == symbol; k++) {
						count++;
					}
					
					// if the count of symbol tiles in + shape is greater than or equal to to the required winning length (for +) then return true
					if(count >= lengthToWin) {
						return true;
					} 
				}
			}
		}
		// iterate through each tile in board to check for X shape, starting at 1 and going up to board_size - 1 so that all adjacent tiles are checked including center tile in each direction
		for(int i = 1; i < board_size - 1; i++) {
			for(int j = 1; j < board_size - 1; j++) {
				
				// if the tiles form X and are marked by specified symbol then start counting length of shape from there
				if(board[i][j] == symbol && board[i+1][j+1] == symbol && board[i-1][j-1] == symbol && board[i+1][j-1] == symbol && board[i-1][j+1] == symbol) {
					int count = 1;
					
					// iterate from i - 1 and j - 1 until the top-left to bottom-right diagonal is reached or tile does not match the symbol
					// check adjacent tiles in top-left to bottom-right direction and if there are tiles in top-left to bottom-right direction for each tile the count variable increments by 1 
					for (int x = i-1, y = j-1; x >= 0 && y >= 0 && board[x][y] == symbol; x--, y--) {
						count++;
					}
					
					// iterate from i - 1 and j + 1 until the top-right to bottom-left diagonal is reached or tile does not match the symbol
					// check adjacent tiles in top-right to bottom-left direction and if there are tiles in top-right to bottom-left direction for each tile the count variable increments by 1 
					for(int x = i - 1, y = j + 1; x >= 0 && y <= board_size - 1 && board[x][y] == symbol; x--, y++) {
						count++;
					}
					
					// iterate from i + 1 and j - 1 until the bottom-left to top-right diagonal is reached or tile does not match the symbol
					// check adjacent tiles in bottom-left to top-right direction and if there are tiles in bottom-left to top-right direction for each tile the count variable increments by 1 
					for(int x = i + 1, y = j - 1; x <= board_size - 1 && y >= 0 && board[x][y] == symbol; x++, y--) {
						count++;
					}
					
					// iterate from i + 1 and j + 1 until the bottom-right to top-left diagonal is reached or tile does not match the symbol
					// check adjacent tiles in bottom-right to top-left direction and if there are tiles in bottom-right to top-left direction for each tile the count variable increments by 1 
					for(int x = i + 1, y = j + 1; x <= board_size - 1 && y <= board_size - 1 && board[x][y] == symbol; x++, y++) {
						count++;
					}
					
					// if the count of symbol tiles in X shape is greater than or equal to to the required winning length then return true
					if(count >= lengthToWin) {
						return true;
					}
					
				}
			}
		}
		
		// otherwise if no winning patterns are found then return false
		return false;
		
	}
	
	/**
	 * isDraw method returns true if board has no empty positions left and no player has won game
	 * checks if game is draw
	 * @return true if game is draw, otherwise false
	 */
	public boolean isDraw() {
		
		// checks if game is not draw and if either player human ('X') or computer ('O') has already won and if this is the case then return false
		if (this.wins('X') || this.wins('O'))
			return false;
			
		// iterate through each square (position with ith row and jth column) and if that square does not match symbol 'X' and 'O', checking if there are any empty left on board 
		// and if that is the case return false since game is not a draw
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				
				if(board[i][j] != 'X' && board[i][j] != 'O') {
					return false;
				}
			}
		}
		
		// return true since game is draw
		return true;
		
	}
	
	/**
	 * evalBoard method returns 3 if computer won, 0 if human won, 2 if game is draw and 1 if game is undecided
	 * evaluates state of game board and returns value based on outcome
	 * @return integer representing game outcome: 3 for computer win, 0 for human win, 2 for a draw, or 1 for an undecided game
	 */
	public int evalBoard() {
		
		// checks if computer ('O') has won and if so return 3
		if(wins('O')) {
			return 3;
		}
		
		// checks if human ('X') has won and if so return 0
		else if(wins('X')) {
			return 0;
		}
		
		// checks if game is a draw and if so return 1
		else if(isDraw()) {
			return 2;
		}
		
		// if either of these condidtions have not been met this means hame is still undecided and hence will return 1
		else {
			return 1;
		}
	}

}
