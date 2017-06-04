import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver {

    //=======instance vars==========
    static ArrayList<Set> _all;
    /*
      public static final String ANSI_RESET = "\u001B[0m";
      public static final String ANSI_RED = "\u001B[31m";
      public static final String ANSI_GREEN = "\u001B[32m";
      public static final String ANSI_YELLOW = "\u001B[33m";
      public static final String ANSI_BLUE = "\u001B[34m";
      public static final String ANSI_PURPLE = "\u001B[35m";
    */
    //==============================
    
    public static void main (String[] arg) {

	//initialize vars
	_all = new ArrayList<Set>();
	int choiceMain = 0;
	boolean pass = false; 
	Scanner sc = new Scanner(System.in);
	try {
	    Dictionary.create();
	}
	catch (FileNotFoundException inputFile) {
	    System.out.println("Please contact the makers of this program");
	}

	//continuous loop for users to return to menu; until they call quits
	while (true) {

	    //welcome msg + initial options
	    System.out.println("\n\nWelcome to Dictionary Frenzy!\n");
	    System.out.println("Here are your options: " +
			       "\n1: Search a word up in our dictionary." +
			       "\n2: Create a set of vocabulary words" +
			       "\n3: Play a game with an existing set." +
			       "\n4: Export a text file of an existing set." +
			       "\n5: Exit");

	    //user can only enter an integer that is equal to or less than 4
	    pass = false;
	    System.out.print("\nSelection: ");
	    String option = sc.next();
	    while (!pass) {
		try {
		    choiceMain = Integer.parseInt(option); //should be an int
		    //should be less than or equal to 5
		    if (choiceMain <= 5) { 
			pass = true; 
		    }
		    else {  
			System.out.print("\n\nPlease enter a valid input: "); 
			option = sc.next();
		    }
		} 
		catch (Exception e) {
		    System.out.print("\nPlease enter a valid input: ");
		    option = sc.next();
		}
	    }

	    //~~~~~~~~~~~~~~~~~~~~~~~SEARCHING UP A WORD~~~~~~~~~~~~~~~~~~~~
	    if (choiceMain == 1 ) {
	     	System.out.println("\nWelcome to the DICTIONARY!!\n" +
	     			   "\nEnter H at any time to return home!" +
	     			   "\nOtherwise, enter a word for its definition.\n");
	     	System.out.print("Word/H: \n");
		String input1 = sc.next().toLowerCase();
		System.out.println();
		while (!input1.equals("h")) {
		    //should take in the word and search dictionary
		    System.out.print("Definition:");
		    String answer = Dictionary.search(input1);
		    System.out.println("\n" + answer+ "\n\n");
		    System.out.println("Type in another word to search," +
				       " or type \"H\" to return to main menu.\n");
		    System.out.print("Word/H: \n");
		    input1 = sc.next().toLowerCase();
		    System.out.println();
	     	}
	    }

	    //~~~~~~~~~~~~~~~~~~~~~~~~~~~CREATING A SET~~~~~~~~~~~~~~~~~~~~~~~
	    else if (choiceMain == 2) {
		System.out.println("\nWelcome to MAKING YOUR OWN SET!!");
		System.out.println("\nA name for your new Set: ");
		System.out.print("Name: ");
	     	_all.add(new Set (sc.next()));
		System.out.println();
	     	System.out.println("Enter word or \"H\" to save and exit.\n");

		//add words or leave
		System.out.println("Word/H: ");
	     	String inputAdd = sc.next().toLowerCase();
	     	while (!inputAdd.equals("h")) {      
		    //search dictionary for user's inputted definition
		    Word newWord = new Word (inputAdd);
		    String possDef = Dictionary.search(inputAdd);

		    if (possDef != null) {
			System.out.println("\nDefinition: \n" +
					   possDef +
					   "\n\nWould you like to change it? Y/N");
			System.out.print("Y/N: ");
			String inputYN = sc.next().toLowerCase();
			pass = false;
			while (pass == false) {
			    if (inputYN.equals("y")) {
				System.out.println("\nWhat is your definition?");
				System.out.println("\nNew Definition: ");
				newWord.setDefinition(sc.next());
				pass = true;
			    }
			    else if (inputYN.equals("n")) {
				pass = true;
			    }
			    else {
				System.out.println("Enter a valid input.");
			    }
			}
		    }
		    else {
			System.out.println("\nYour definition: ");
			newWord.setDefinition(sc.next());
		    }
		    
		    _all.get(_all.size()-1).add(newWord);

		    /* fix BUG where someone changes the definition and inputs it
		     * and the Next Word to Input prints
		     * BUT instead of letting the user put in the next word, the Definition
		     * for the changed definition from the previous word shows up.
		     */
		    
		    System.out.print("\nNext Word to input/H: \n");
	     	    inputAdd = sc.next().toLowerCase();

	     	}
		/*
		  Set geo = new Set("Geometry Terms");
		  geo.add(new Word ("hypotenuse", "issa leg"));
		  geo.add(new Word ("isosceles", "issa triangle"));
		  geo.add(new Word ("median", "issa middle"));
		  geo.add(new Word ("quadrilateral", "issa four"));
		  geo.add(new Word ("triangle","threes"));
		  _all.add(geo);
		  //Flashcard tester = new Flashcard(geo);
		  //tester.play();
		  //System.out.println(_all.get(_all.size()-1).showAllWords());
		  }*/
	    }
	    //~~~~~~~~~~~~~~~~~~~~~~~~~~PLAYING A GAME~~~~~~~~~~~~~~~~~~~~~~
	    else if (choiceMain == 3) {
		System.out.println("\n Welcome to PLAYING A GAME!!\n");
	     	System.out.println("Select a game or enter \"H\" for main menu:");
	     	System.out.println("\t1: Flashcards");
		System.out.println("\t2: Learn");
		System.out.println("\t3: Matching");

		//game and set
		int game = 0;
		int setChoice = 0;

		
		//check for H or any of the above options
		pass = false;
		System.out.print("\nSelection: ");
	     	String option3 = sc.next().toLowerCase();
		while (!option3.equals("h") && !pass) {
		    try {
			game = Integer.parseInt(option3);
			if ( game <= 3 ) {
			    pass = true;
			}
			else {
			    System.out.print("\nPlease enter a valid input: ");
			    option3 = sc.next().toLowerCase();
			}
		    } catch (Exception e) {
			System.out.print("\nPlease enter a valid input: ");
			option3 = sc.next().toLowerCase();
		    }
		}

		//select a set
		if (!option3.equals("h")) {
		    //print all existing sets
		    System.out.println("\nSelect an existing Set: ");
		    for (int x = 0; x < _all.size(); x++) {
			System.out.println("\t" +
					   (x+1) +
					   ": " +
					   _all.get(x).getName());
		    }

		    //ask users for a set
		    System.out.print("\nSelection: ");
		    option3 = sc.next().toLowerCase();
		    pass = false;
		    while (!option3.equals("h") && !pass) {
			try {
			    setChoice = Integer.parseInt(option3);
			    if (setChoice <= 3) {
				pass = true;
			    }
			    else {
				System.out.print("\n Please enter a valid input: ");
				option3 = sc.next().toLowerCase();
			    }
			}
			catch (Exception e) {
			    System.out.print("\n Please enter a valid input: ");
			    option3 = sc.next().toLowerCase();
			}
		    }

		    //play the game
		    if (!option3.equals("h")) {
			Set theSet = _all.get(setChoice - 1);
			if (game == 1) {
			    Flashcard one = new Flashcard(theSet);
			    one.play();
			}
			else if (game == 2) {
			    Learn two = new Learn(theSet);
			    two.play();
			}
			else {
			    Matching three = new Matching(theSet);
			    three.play();
			}
		    }
		    
		}
	    }

	    //~~~~~~~~~~~~~~~~~~~~CREATE A TEXT FILE~~~~~~~~~~~~~~~~~~~~~~~~~
	    else if (choiceMain == 4) {
		System.out.println("Let's CREATE A TEXT FILE!");
		System.out.println("\nWhich set would you like to print?");
		for (int x = 0; x < _all.size(); x++) {
		    System.out.println("\t" +
				       (x+1) +
				       ": " +
				       _all.get(x).getName());
		}

		//ask users for a set
		System.out.print("\nSelection: ");
		String sett = sc.next().toLowerCase();
		pass = false;
		int setC = 1;
		while (!sett.equals("h") && !pass) {
		    try {
			setC = Integer.parseInt(sett);
			if (setC <= 3) {pass = true;}
			else {
			    System.out.print("\nPlease enter a valid input: "); 
			    sett = sc.next().toLowerCase();}
		    } catch (Exception e) {
			System.out.print("\nPlease enter a valid input: ");
			sett = sc.next().toLowerCase();
		    }
		}
		if ( !sett.equals("h") ) {
		    try { 
			PrintWriter filee = new PrintWriter(sett+".txt"); 
			filee.println(_all.get(setC-1).showAllWords() );
			filee.close();
			System.out.println("A text file has been created!");
		    }
		    catch ( Exception e ) { 
			System.out.println("yah");
		    }
		    		    
		}
		
	    }
	
	    //~~~~~~~~~~~~~~~~~~~~CALL IT QUITS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	    else {
		System.out.println("\nSee you soon!");
		break;
	    }


	}
    }
}
