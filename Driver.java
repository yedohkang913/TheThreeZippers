
import java.util.Scanner;
import java.util.ArrayList;

public class Driver {

    static ArrayList<Set> _all;
    
    public static void main (String[] arg) {

	_all = new ArrayList<Set>();
	boolean pass;
	Scanner sc = new Scanner(System.in);
	
	while (true) {

	    System.out.println("Welcome to Dictionary Frenzy!");
	    System.out.println("Here are your options" +
			       "\n1: Search a word up in our dictionary." +
			       "\n2: Create a set of vocabulary words" +
			       "\n3: Play a game with an existing set." +
			       "\n4: Exit");
	    pass = false;
	    int option = 0;
	    while (!pass) {
		try {
		    option = sc.nextInt();
		    if (option <= 4) {
			pass = true;
		    } else {
			System.out.println("Bad input");
		    }
	     	} catch (Exception e) {
	     	    System.out.println("Enter a valid input.");
	     	}
	    }
	    if (option == 1 ) {
	     	System.out.println("\nWelcome to the DICTIONARY." +
	     			   "\nEnter H at any time to return home" +
	     			   "\nOtherwise, enter a word for its definition.");
	     	String input = sc.next();
	     	if (input.equals("H")) {
	     	    //do nothing; loops again to main menu
	     	}
	     	else {
	     	    System.out.println("Currently under construction, sorry.");

	     	}
	    }
	    else if (option == 2) {
	     	System.out.println("A name for your new Set: ");
	     	_all.add(new Set (sc.next()));
	     	System.out.println("Enter words or S to save and exit.");
	     	String input = sc.next().toLowerCase();
	     	while (!input.equals("s")) {
	     	    System.out.println("Dictionary is still under construction.");
	     	    //_all[_all.size()-1].add(input);
	     	    input = sc.next().toLowerCase();
	     	}
	    }
	    else if (option == 3) {
	     	System.out.println("Select a game:");
	     	System.out.println("\t1: Flashcards");
		System.out.println("\t2: Learn");
		System.out.println("\t3: Matching");

	     	pass = false;
	     	String option2 = sc.next();
	     	if (option2.equals("H")) {
	     	    //do nothing--end this round
	     	} else {
	     	    while (!pass) {
	     		try {
	     		    int opt = Integer.parseInt(option2);
	     		    pass = true;
	     		} catch (Exception e) {
	     		    System.out.println("Enter a valid input.");
	     		}
	     	    }
	     	    System.out.println("Select an existing Set.");
	     	    for (int x = 0; x < _all.size(); x++) {
	     		System.out.println("\t" +
	     				   (x+1) +
	     				   ": " +
	     				   _all.get(x).getName());
	     	    }
	     	    System.out.println("Currently under construction, sorry.");
	     	    System.out.println("Here's an example set:");
	
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
		    
		}
	    }
				  
	    else {
		System.out.println("See you soon!");
		break;
	    }

	}

    }
}
