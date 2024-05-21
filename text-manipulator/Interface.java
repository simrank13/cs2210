// import java i/o classes for file reading and string tokenization
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 * This class implements user interface and contains main method
 *
 */
public class Interface {

	public static void main(String[] args) {
		// create BSTDictionary object dictionary to manage ordered dictionary
		BSTDictionary dictionary = new BSTDictionary();
		
		// if command-line arguments dont contain exactly 1 element then print message to screen that no input file specified
		if(args.length != 1) {
			System.out.println("No input file specified");
			// temrinate program with exit code 0 so that program successfully terminates
			System.exit(0);
		}
		// get input file path from command-line arguments
		String inputFile = args[0];
		
		// manages file reading and closes properly using try block
		try(BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile))) {
			
			// read lines from input file and store it in line variable
			String line = bufferReader.readLine();
			
			// keep reading lines until reach end of file
			while(line != null) {
				
				// store current line as label string
				String label = line;
				
				// read next line that contains type and data information
				String typeAndData = bufferReader.readLine();
				
				// if there is additional type and data information then
				if(typeAndData != null) {
					
					// declaration of type and data variable that will store the type information and data information (individually)
					int type;
					String data = "";
					
					// using switch-case, get the first character of typeAndData string 
					switch(typeAndData.charAt(0)) {
					
					// if the first character is '-' then set type as 3 
					// and update data to the first letter in typeAndData string
					// then exit switch statement
					case '-':
						type = 3;
						data = typeAndData.substring(1);
						break;
						
					// if the first character is '+' then set type as 4
					// and update data to the first letter in typeAndData string
					// then exit switch statement
					case '+':
						type = 4;
						data = typeAndData.substring(1);
						break;
						
					// if the first character is '*' then set type as 5
					// and update data to the first letter in typeAndData string
					// then exit switch statement
					case '*':
						type = 5;
						data = typeAndData.substring(1);
						break;
						
					// if the first character is '/' then set type as 2
					// and update data to the first letter in typeAndData string
					// then exit switch statement
					case '/':
						type = 2;
						data = typeAndData.substring(1);
						break;
					
						// otherwise if doesnt have any of these characters '+', '-', '*', '/' then
					default:
						
						// if typeAndData string ends with gif then set type to 7 
						//and update data string to equal typeAndData string
						if(typeAndData.endsWith("gif")) {
							type = 7;
							data = typeAndData;
						}
						
						// if typeAndData string ends with jpg then set type to 6
						//and update data string to equal typeAndData string
						else if(typeAndData.endsWith("jpg")) {
							type = 6;
							data = typeAndData;
						}
						
						// if typeAndData string ends with html then set type to 8
						//and update data string to equal typeAndData string
						else if(typeAndData.endsWith("html")) {
							type = 8;
							data = typeAndData;
						}
						
						// otherwise if typeAndData string dont end with "gif", "jpg", or "html" then set type to 1
						//and update data string to equal typeAndData string
						else {
							type = 1;
							data = typeAndData;
						}
						// exit switch statement
						break;
								
					}
					// create Record object based on updated label, type and data
					Record record = new Record(new Key(label, type), data);
					
					// using try catch add Record to dictionary and if throws DictionaryException
					// then catch the exception and print message to screen
					try {
						// convert label to lower case and then update label string 
						label = label.toLowerCase();
						// insert Record record into dictionary 
						dictionary.put(record);
					}
					catch (DictionaryException e) {
						System.err.println(e.getMessage());
					}
				}
				// read next line in input file and update line to that new line
				line = bufferReader.readLine();
			}
			
		// if FileNotException is thrown catch that exception and print its print message to screen
		} catch (FileNotFoundException e) {
			
			System.err.println(e.getMessage());
		
		// if IOException is thrown catch that exception and print its print message to screen
		} catch (IOException e) {
			
			System.err.println(e.getMessage());
		}
		// continue looping to process additional commands until user doesnt enter "exit" 
		while(true) {
			// create StringReader object to read user input
			StringReader keyboard = new StringReader();
			// prompt user to enter next command and convert to lowercase
			String commandEntered = keyboard.read("Enter next command: ");
			commandEntered = commandEntered.toLowerCase();
			
			// create StringTokenizer object to tokenize entered command
			StringTokenizer st = new StringTokenizer(commandEntered);
			String command = st.nextToken();
			
			// if user entered add then
			if (command.equals("add")) {
			    try {
			    	// set label as tokenized command
			        String label = st.nextToken();
			        
			     // declaration and initialization of type variable which will store type information
			        int type = 0;
			        
			        // convert tokenized command to an integer and set type to the integer tokenized command
			        type = Integer.parseInt(st.nextToken());
			        
			        // then split command into array and if type isnt set to 1 then check its length and if length isnt 4 them throw exception
			        String arr[] = commandEntered.split(" ");
			        if(type != 1) {
			        	if(arr.length != 4) {
			        		throw new Exception();
			        	}
			        }
			        
			        
			        // extract data part of command including spaces and set it as data string
			        String data = st.nextToken("");
			        
			        // create Record object r storing updated label, type, and data
			        Record r = new Record(new Key(label, type), data);
			        
			        try {
			        	// try to add Record r into dictionary
			            dictionary.put(r);
			        // if DictionaryException was thrown catch it an print to screen that the record is already in dictionary
			        } catch (DictionaryException e) {
			            System.err.println("A record with the given key (" + label + ", " + type + ") is already in the ordered dictionary.");
			        }
			    // if NumberFormatException was thrown then catch it and print to screen that type is to be integer
			    } catch (NumberFormatException e) {
			        System.err.println("We expect the type to be an integer");
			        
			    // if Exception was thrown then catch it and print to screen that it is invalid add command
			    } catch (Exception e) {
			        System.err.println("Invalid <add> command");
			    }
			}

			// if user entered "delete" then
			else if (command.equals("delete")) {
			    try {
			    	// set label as tokenized command
			        String label = st.nextToken();
			        
			        // split command into array and check its length and if length isnt 3 them throw exception
			        String arr[] = commandEntered.split(" ");
			    	if(arr.length != 3) {
			    		throw new Exception();
			    	}
			        // declaration and initialization of type variable which will store type information
			        int type = 0;
			        
			        // convert tokenized command to an integer and set type to the integer tokenized command
			        type = Integer.parseInt(st.nextToken());
			        
			        // create Key object key storing updated label and type
			        Key key = new Key(label, type);
			        
			        try {
			        	// try to remove Key key into dictionary
			            dictionary.remove(key);
			        } 
			        // if DictionaryException was thrown catch it an print to screen that the record is not in dictionary
			        catch (DictionaryException e) {
			        	
			            System.err.println("No record in the ordered dictionary has key (" + label + ", " + type + ").");
			        }
			    } 
			    // if NumberFormatException was thrown then catch it and print to screen that type is to be integer
			    catch (NumberFormatException e) {
			    	
			        System.err.println("We expect the type to be an integer");
			    } 
			    // if Exception was thrown then catch it and print to screen that it is invalid delete command
			    catch (Exception e) {
			    	
			        System.err.println("Invalid <delete> command");
			    }
			}
			// if user entered "list" then
			else if (command.equals("list")) {
			    try {
			    	// set label as tokenized command
			    	String prefix = st.nextToken();
			    	
			        // split command into array and check its length and if length isnt 2 them throw exception
			    	String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			    	// create Record object d and get the successor of given prefix from dictionary with type 0 and store it in the Record d object
			        Record d = dictionary.successor(new Key(prefix, 0));

			        // declaration and initialization of count variable which will store number of matching labels
			        int count = 0;
			        
			        // while there is valid record and label of record starts with specified prefix
			        while (d != null && d.getKey().getLabel().startsWith(prefix)) {
			        	// if there is more than 0 matching labels then print comma and space before next label
			        	if (count > 0) {
			        		System.out.print(", ");
			            }
			        	// print label of current record
			            System.out.print(d.getKey().getLabel());
			            
			            // increment count of matching labels by 1 each time
			            count++;

			            // update Record d to move with key greater than current record's key 
			            d = dictionary.successor(d.getKey());
			        }
			        // if there is more than 0 matching labels then print new line to screen
			        if (count > 0) {
			        	System.out.println();
			        	
			        // otherwise
			        } else {
			        	// print to screen that there are no labels found in dictionary
			            System.out.println("No labels in the ordered dictionary starting with prefix " + prefix);
			        }
			    // if Exception was thrown catch that exception and print to screen invalid list command
			    } catch (Exception e) {
			        System.err.println("Invalid <list> command");
			    }
			}


			// if user entered "define"
			else if (command.equals("define")) {
			    try {
			    	// set label as tokenized command
			        String label = st.nextToken();
			        
			        // split command into array and check its length and if length isnt 2 them throw exception
			        String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			        // create Key object key storing updated label and type as 1
			        Key key = new Key(label, 1);
			        
			        // get the Record associated with specified key from dictionary and store it in Record d object
			        Record d = dictionary.get(key);

			        // if Record is found for specific key then 
			        if (d != null) {
			        	// print data associated with label
			            System.out.println("Data for " + label + " is: " + d.getDataItem());
			        
			        // otherwise
			        } else {
			        	
			        	// print message that label is not in dictionary 
			            System.out.println("The word " + label + " is not in the ordered dictionary");
			        }
			    // if Exception is thrown then catch that exception and print to screen invalid define command
			    } catch (Exception e) {
			        System.err.println("Invalid <define> command");
			    }
			}



			// if user entered "translate" then
			else if(command.equals("translate")) {
				try { 
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			    	// create Key object key storing updated label and type as 2
					Key key = new Key(label, 2);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record is found for specific key then
					if(d != null) {
						// print data associated with label
						System.out.println("Data for " + label + " is: " + d.getDataItem());
					}
					
					// otherwise
					else {
						// print message that definition of label not in dictionary 
						System.out.println("There is no definition for the word " + label);
					}
					
				}
				// if Exception is thrown then catch that exception and print to screen invalid translate command
				catch (Exception e) {
			        System.err.println("Invalid <translate> command");
			    }
				
			}
			
			// if user entered "sound" then
			else if(command.equals("sound")) {
				try {
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
					
			    	// create Key object key storing updated label and type as 3
					Key key = new Key(label, 3);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record d is found for specific key then
					if(d != null) {
						try {
							// try to create Soundplayer object player and play sound (using play method from SoundPlayer) associated with Record d's data 
							SoundPlayer player = new SoundPlayer();
							player.play(d.getDataItem());
						}
						// if MultimediaException is thrown then catch the exception and print message to screen
						catch (MultimediaException e) {
							System.err.println(e.getMessage());
						}
					}
					// otherwise if Record d for specific key isnt found in dictionary then print to screen that there is no sound file for the label
					else {
						System.out.println("There is no sound file for " + label);
					}
				}
				// if Exception is thrown, catch it and print to screen invalid sound command
				catch (Exception e) {
			        System.err.println("Invalid <sound> command");
			    }
			}
			
			// if user entered "play"
			else if(command.equals("play")) {
				try {
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
					
			    	// create Key object key storing updated label and type as 4
					Key key = new Key(label, 4);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record d is found for specific key then
					if(d != null) {
						try {
							// try to create Soundplayer object player and play sound (using play method from SoundPlayer) associated with Record d's data 
							SoundPlayer player = new SoundPlayer();
							player.play(d.getDataItem());
						}
						// if MultimediaException is thrown then catch the exception and print message to screen
						catch (MultimediaException e) {
							System.err.println(e.getMessage());
						}
					}
					// otherwise if Record d for specific key isnt found in dictionary then print to screen that there is no music file for the label
					else {
						System.out.println("There is no music file for " + label);
					}
				}
				// if Exception is thrown, catch it and print to screen invalid play command
				catch (Exception e) {
			        System.err.println("Invalid <play> command");
			    }
			}
			
			// if user enter "say" then
			else if(command.equals("say")) {
				try {
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			    	// create Key object key storing updated label and type as 5
					Key key = new Key(label, 5);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record d is found for specific key then
					if(d != null) {
						try {
							// try to create Soundplayer object player and play sound (using play method from SoundPlayer) associated with Record d's data 
							SoundPlayer player = new SoundPlayer();
							player.play(d.getDataItem());
						}
						// if MultimediaException is thrown then catch the exception and print message to screen
						catch (MultimediaException e) {
							System.err.println(e.getMessage());
						}
					}
					// otherwise if Record d for specific key isnt found in dictionary then print to screen that there is no voice file for the label
					else {
						System.out.println("There is no voice file for " + label);
					}
				}
				// if Exception is thrown, catch it and print to screen invalid say command
				catch (Exception e) {
			        System.err.println("Invalid <say> command");
			    }
			}
			
			// if user entered "show" then 
			else if(command.equalsIgnoreCase("show")) {
				try {
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			    	
			    	// create Key object key storing updated label and type as 6
					Key key = new Key(label, 6);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record d is found for specific key then
					if(d != null) {
						try {
							// try to create PictureViewer object viewer and view image (using show method from PictureViewer) associated with Record d's data
							PictureViewer viewer = new PictureViewer();
							viewer.show(d.getDataItem());
						}
						// if MultimediaException is thrown then catch the exception and print message to screen
						catch (MultimediaException e) {
							System.err.println(e.getMessage());
						}
					}
					// otherwise if Record d for specific key isnt found in dictionary then print to screen that there is no image file for the label
					else {
						System.out.println("There is no image file for " + label);
					}
				}
				// if Exception is thrown, catch it and print to screen invalid show command
				catch (Exception e) {
			        System.err.println("Invalid <show> command");
			    }
			}
			
			// if user enters "animate" then 
			else if(command.equalsIgnoreCase("animate")) {
				try {
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			    	
			    	// create Key object key storing updated label and type as 7
					Key key = new Key(label, 7);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record d is found for specific key then
					if(d != null) {
						try {
							// try to create PictureViewer object viewer and view animated image (using show method from PictureViewer) associated with Record d's data
							PictureViewer viewer = new PictureViewer();
							viewer.show(d.getDataItem());
						}
						// if MultimediaException is thrown then catch the exception and print message to screen
						catch (MultimediaException e) {
							System.err.println(e.getMessage());
						}
					}
					// otherwise if Record d for specific key isnt found in dictionary then print to screen that there is no animated image file for the label
					else {
						System.out.println("There is no animated image file for " + label);
					}
				}
				// if Exception is thrown, catch it and print to screen invalid animate command
				catch (Exception e) {
			        System.err.println("Invalid <animate> command");
			    }
			}
			
			// if user enters "browse" then
			else if(command.equals("browse")) {
				try {
					// set label as tokenized command
					String label = st.nextToken();
					
					// split command into array and check its length and if length isnt 2 them throw exception
					String arr[] = commandEntered.split(" ");
			    	if(arr.length != 2) {
			    		throw new Exception();
			    	}
			    	
			    	// create Key object key storing updated label and type as 8
					Key key = new Key(label, 8);
					
					// get the Record associated with specified key from dictionary and store it in Record d object
					Record d = dictionary.get(key);
					
					// if Record d is found for specific key then
					if(d != null) {
						// create ShowHTML object browser and browse webpage (using show method from ShowHTML) associated with Record d's data
						ShowHTML browser = new ShowHTML();
						browser.show(d.getDataItem());
					}
					// otherwise if Record d for specific key isnt found in dictionary then print to screen that there is no webpage file for the label
					else {
						System.out.println("There is no webpage called " + label);
					}
				}
				// if Exception is thrown, catch it and print to screen invalid animate command
				catch (Exception e) {
			        System.err.println("Invalid <browse> command");
			    }
			}
			
			// if user entered "first" then
			else if (commandEntered.equals("first")) {
				
				// get the smallest record in dictionary and store it in Record object r
				Record r = dictionary.smallest();
				// print details of smallest record in format: "label, type, data"
				System.out.println(r.getKey().getLabel() + "," + r.getKey().getType() + "," + r.getDataItem());
				
			}
			
			// if user entered "last" then
			else if(commandEntered.equals("last")) {
				
				// get the largest record in dictionary and store it in Record object r
				Record r = dictionary.largest();
				// print details of largest record in format: "label, type, data"
				System.out.println(r.getKey().getLabel() + "," + r.getKey().getType() + "," + r.getDataItem());
				
			}
			
			// if user entered "exit"
			else if(commandEntered.equals("exit")) {
				// then end program
				break;
				
			}
			
			// otherwise if user did not enter any of the commands then print to the screen invalid command
			else {
				System.out.println("Invalid command.");
			}
			
			
		}
		
	}
}