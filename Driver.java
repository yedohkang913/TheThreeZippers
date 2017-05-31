
import java.util.Scanner;
import java.util.ArrayList;

public class Driver {

    //=======instance vars==========
    static ArrayList<Set> _all;
    //==============================
    
    public static void main (String[] arg) {

	//initialize vars
	_all = new ArrayList<Set>();
	boolean pass;
	Scanner sc = new Scanner(System.in);


	//continuous loop for users to return to menu; until they call quits
	while (true) {

	    //welcome msg + initial options
	    System.out.println("Welcome to Dictionary Frenzy!");
	    System.out.println("Here are your options" +
			       "\n1: Search a word up in our dictionary." +
			       "\n2: Create a set of vocabulary words" +
			       "\n3: Play a game with an existing set." +
			       "\n4: Exit");

	    //user can only enter an integer that is equal to or less than 4
	    pass = false;
	    int option = 0;
	    while (!pass) {
		try {
		    option = sc.nextInt(); //should be an int
		    if (option <= 4) { //should be less than or equal to 4
			pass = true;
		    } else {
			System.out.println("Bad input");
		    }
	     	} catch (Exception e) {
	     	    System.out.println("Enter a valid input.");
	     	}
	    }

	    //~~~~~~~~~~~~~~~SEARCHING UP A WORD~~~~~~~~~~~~
	    if (option == 1 ) {
	     	System.out.println("\nWelcome to the DICTIONARY." +
	     			   "\nEnter H at any time to return home" +
	     			   "\nOtherwise, enter a word for its definition.");
	     	String input = sc.next();
	     	if (input.equals("H")) {
	     	    //do nothing; loops again to main menu
	     	}
	     	else {
		    //should take in the word and search dictionary
	     	    System.out.println("Currently under construction, sorry.");

	     	}
	    }

	    //~~~~~~~~~~~~~~~~~~~CREATING A SET~~~~~~~~~~~~~~~~~
	    else if (option == 2) {
	     	System.out.println("A name for your new Set: ");
	     	_all.add(new Set (sc.next()));
	     	System.out.println("Enter words or S to save and exit.");

		//add words or leave
	     	String input = sc.next().toLowerCase();
	     	while (!input.equals("s")) {
	     	    System.out.println("Dictionary is still under construction.");
		    //add words to Set, which should also automatically search
		    //up words
	     	    //_all.get(_all.size()-1).add(input);
	     	    input = sc.next().toLowerCase();
	     	}
	    }

	    //~~~~~~~~~~~~~~~~~~PLAYING A GAME~~~~~~~~~~~~~~~~~
	    else if (option == 3) {
	     	System.out.println("Select a game:");
	     	System.out.println("\t1: Flashcards");
		System.out.println("\t2: Learn");
		System.out.println("\t3: Matching");

		//check for H or any of the above options
	     	pass = false;
	     	String option2 = sc.next();
	     	if (option2.equals("H")) {
	     	    //do nothing--end this round
	     	}
		else {
	     	    while (!pass) {
	     		try {
	     		    int opt = Integer.parseInt(option2);
	     		    pass = true;
	     		} catch (Exception e) {
	     		    System.out.println("Enter a valid input.");
	     		}
	     	    }

		    //choose an existing set in _all and print all
	     	    System.out.println("Select an existing Set.");
	     	    for (int x = 0; x < _all.size(); x++) {
	     		System.out.println("\t" +
	     				   (x+1) +
	     				   ": " +
	     				   _all.get(x).getName());
	     	    }

		    //SIKE--you've made a bad choice--rough terrain ahead
	     	    System.out.println("Currently under construction, sorry.");
	     	    System.out.println("Here's an example set:");

		    //*****************AN EXAMPLE***********************8
	     	    Set hello = new Set("Compliments");
	     	    Word cool = new Word("cool"
					 ,"not warm enough");
		    Word great = new Word ("great","what you say to a mate");
		    Word superb = new Word ("superb","it's not just super, it's _____");
		    Word fantastic = new Word ("fantastic","______ Beasts and where to find them.");
		    Word awesome = new Word ("awesome","awe inducing");
		    hello.add(cool);
		    hello.add(great);
		    hello.add(superb);
		    hello.add(fantastic);
		    hello.add(awesome);
		    hello.showAllWords();
		    System.out.println();

		    Flashcard hiF = new Flashcard (hello);
		    hiF.play();
		    System.out.println();
		    Learn hiL = new Learn(hello);
		    hiL.play();
		    System.out.println();
		    Matching hiM = new Matching (hello);
		    hiM.play();
		    System.out.println();
		    //*****************************************************
		    
		}
	    }

	    //~~~~~~~~~~~~~~~~~~~~CALL IT QUITS~~~~~~~~~~~~~~~~~~~~
	    else {
		System.out.println("See you soon!");
		break;
	    }

	}

    }
}
