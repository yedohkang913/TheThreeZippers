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
	//Dictionary.create();


	//continuous loop for users to return to menu; until they call quits
	while (true) {

	    //welcome msg + initial options
	    System.out.println("\n\nWelcome to Dictionary Frenzy!");
	    System.out.println("Here are your options" +
			       "\n1: Search a word up in our dictionary." +
			       "\n2: Create a set of vocabulary words" +
			       "\n3: Play a game with an existing set." +
			       "\n4: Export a text file of an existing set." +
			       "\n5: Exit");

	    //user can only enter an integer that is equal to or less than 4
	    pass = false;
	    String option = sc.next();
	    while (!pass) {
		try {
		    choiceMain = Integer.parseInt(option); //should be an int
		    //should be less than or equal to 5
		    if (choiceMain <= 5) { pass = true; }
		    else {  
			System.out.println("Enter a valid input."); 
			option = sc.next();
		    }
	     	} catch (Exception e) {
	     	    System.out.println("Enter a valid input.");
		    option = sc.next();
	     	}
	    }

	    //~~~~~~~~~~~~~~~~~~~~~~~SEARCHING UP A WORD~~~~~~~~~~~~~~~~~~~~
	    if (choiceMain == 1 ) {
	     	System.out.println("\nWelcome to the DICTIONARY." +
	     			   "\nEnter H at any time to return home" +
	     			   "\nOtherwise, enter a word for its definition.");
	     	String input1 = sc.next().toLowerCase();
		while (!input1.equals("h")) {
		    //should take in the word and search dictionary
	     	    System.out.println("Currently under construction, sorry.");
		    /*
		      Word answer = Dictionary.search(input);
		      System.out.println( answer + "\nDefn: " +
		      answer.getDefinition());
		    */			
		    System.out.println("Type in another word to search," +
				       "or type \"H\" to return to main menu.");
		    input1 = sc.next().toLowerCase();
	     	}
	    }

	    //~~~~~~~~~~~~~~~~~~~~~~~~~~~CREATING A SET~~~~~~~~~~~~~~~~~~~~~~~
	    else if (choiceMain == 2) {
	     	System.out.println("A name for your new Set: ");
	     	_all.add(new Set (sc.next()));
	     	System.out.println("Enter word or \"H\" to save and exit.");

		//add words or leave
	     	String inputAdd = sc.next().toLowerCase();
	     	while (!inputAdd.equals("h")) {
	     	    System.out.println("Dictionary is still under construction.");

		    /*
		    //search dictionary for user's inputted definition
		    Word newWord = new Word (input);
		    String possDef = Dictionary.search(input).getDefinition();

		    if (possDef != null) {
		    System.out.println("Here is the defn: \n" +
		    possDef +
		    "Would you like to change it? Y/N");
		    String inputYN = sc.next().toLowerCase();
		    pass = false;
		    while (pass == false) {
		    if (inputYN.equals("y")) {
		    System.out.println("What is your definition?");
		    newWord.setDefinition(sc.next());
		    pass = true;
		    }
		    else if (inputYN.equals("n")) {
		    //do nothing
		    }
		    else { System.out.println("Enter a valid input.");}
		    }
		    } else {
		    System.out.println("Your definition: ");
		    newWord.setDefinition(sc.next());
		    }
		    
		    _all.get(_all.size()-1).add(input);
	     	    inputAdd = sc.next().toLowerCase();

		    */
	     	}
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
	    }

	    //~~~~~~~~~~~~~~~~~~~~~~~~~~PLAYING A GAME~~~~~~~~~~~~~~~~~~~~~~
	    else if (choiceMain == 3) {
	     	System.out.println("Select a game or enter \"H\" for main menu:");
	     	System.out.println("\t1: Flashcards");
		System.out.println("\t2: Learn");
		System.out.println("\t3: Matching");

		//game and set
		int game = 0;
		int setChoice = 0;

		
		//check for H or any of the above options
		pass = false;
	     	String option3 = sc.next().toLowerCase();
		while (!option3.equals("h") && !pass) {
		    try {
			game = Integer.parseInt(option3);
			if ( game <= 3 ) {
			    pass = true;
			}
			else {
			    System.out.println("Enter a valid input");
			    option3 = sc.next().toLowerCase();
			}
		    } catch (Exception e) {
			System.out.println("Enter a valid input.");
			option3 = sc.next().toLowerCase();
		    }
		}

		//select a set
		if (!option3.equals("h")) {
		    //print all existing sets
		    System.out.println("Select an existing Set.");
		    for (int x = 0; x < _all.size(); x++) {
			System.out.println("\t" +
					   (x+1) +
					   ": " +
					   _all.get(x).getName());
		    }

		    //ask users for a set
		    option3 = sc.next().toLowerCase();
		    pass = false;
		    while (!option3.equals("h") && !pass) {
			try {
			    setChoice = Integer.parseInt(option3);
			    if (setChoice <= 3) {pass = true;}
			    else {System.out.println("Enter a valid input."); option3 = sc.next().toLowerCase();}
			} catch (Exception e) {
			    System.out.println("Enter a valid input.");
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
		System.out.println("Which set would you like to print?");
		for (int x = 0; x < _all.size(); x++) {
		    System.out.println("\t" +
				       (x+1) +
				       ": " +
				       _all.get(x).getName());
		}

		//ask users for a set
		String sett = sc.next().toLowerCase();
		pass = false;
		int setC = 1;
		while (!sett.equals("h") && !pass) {
		    try {
			setC = Integer.parseInt(sett);
			if (setC <= 3) {pass = true;}
			else {
			    System.out.println("Enter a valid input."); 
			    sett = sc.next().toLowerCase();}
		    } catch (Exception e) {
			System.out.println("Enter a valid input.");
			sett = sc.next().toLowerCase();
		    }
		}
		if ( !sett.equals("h") ) {
		    try { 
			PrintWriter filee = new PrintWriter("out.txt"); 
			filee.println(_all.get(setC-1).showAllWords() );
		    }
		    catch ( Exception e ) { 
			System.out.println("yah");
		    }
		    		    
		}
		
	    }
	
	    //~~~~~~~~~~~~~~~~~~~~CALL IT QUITS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	    else {
		System.out.println("See you soon!");
		break;
	    }


	}
    }
}
