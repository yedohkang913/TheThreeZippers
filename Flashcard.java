import java.util.Scanner;
import java.util.ArrayList;

public class Flashcard {

    //private String name;
    private Word[] deck;
    private int removeType; //1 = random & 2 = priority & 3 = alphabetical         
    private int order; //1 = name & 2 = definition    
    private boolean randomOrder; //either display words first or definition first

    public Flashcard(Set newDeck) {

	// scanner input, for choices: either random, prioritized, or alphabetical
	Scanner sc = new Scanner(System.in);
	System.out.println("Welcome to Flashcard!");
	System.out.println("Please select an option: ");
	System.out.println("1 for RANDOM SET");
	System.out.println("2 for PRIORITIZED SET (based on number of times the word was missed)");
	System.out.println("3 for ALPHABETICAL SET");
	System.out.print("Selection: ");
        String option = sc.next();
	int removeType = 1;
	//if the user did not enter a number
	boolean done = false;
	while ( done == false ) {
	    try {
		removeType = Integer.parseInt(option);
		done = true; }
	    catch ( Exception e ) {
		System.out.println("Please enter 1, 2, or 3");
	        option = sc.next();
	    }
	}
	//if the user entered an invalid number
	while (removeType != 1 && removeType != 2 && removeType != 3) {
	    System.out.println("Invalid number selected. Please enter a valid number for selection.");
	    System.out.print("Selection: ");
	    option = sc.next();
	    done = false;
	    //if a user is a fool and once again decides not to enter a number 
	    while ( done == false ) {
		try {
		    removeType = Integer.parseInt(option);
		    done = true; }
		catch ( Exception e ) {
		    System.out.println("Please enter 1, 2, or 3");
		    option = sc.next();
		}
	    }
	}
	if (removeType == 1) {
	    System.out.println("You have chosen a random set.");
	}
	else if (removeType == 2) {
	    System.out.println("You have chosen a prioritized set.");
	}
	else if (removeType == 3) {
	    System.out.println("You have chosen an alphabetical set.");
	}

	// scanner input, for choices: either word first or definition or random
	System.out.println("Please select an option: ");
	System.out.println("1 for WORD FIRST");
	System.out.println("2 for DEFINITION FIRST");
	System.out.println("3 for RANDOM");
	System.out.print("Selection: ");
	//order = sc.nextInt();
	//did the user enter a valid input?
	option = sc.next();
	order = 1;
	//if the user did not enter a number
	done = false;
	while ( done == false ) {
	    try {
		order = Integer.parseInt(option);
		done = true; }
	    catch ( Exception e ) {
		System.out.println("Please enter 1, 2, or 3");
	        option = sc.next();
	    }
	}
	//if the user entered an invalid number
	while (order != 1 && order != 2 && order != 3) {
	    System.out.println("Invalid number selected. Please enter a valid number for selection.");
	    System.out.print("Selection: ");
	    option = sc.next();
	    done = false;
	    //if a user is a fool and once again decides not to enter a number 
	    while ( done == false ) {
		try {
		    order = Integer.parseInt(option);
		    done = true; }
		catch ( Exception e ) {
		    System.out.println("Please enter 1, 2, or 3");
		    option = sc.next();
		}
	    }
	}
	
	if (order == 1) {
	    System.out.println("You have chosen to start with words.");
	}
	else if (order == 2) {
	    System.out.println("You have chosen to start with definitions.");
	}
	else if (order == 3) {
	    randomOrder = true;
	    System.out.println("You have chosen to start with a random choice.");
	}

	//dequeue cards randomly or by priority or alpha into an ArrayList
	deck = new Word[newDeck.getSize()];
	int limit = newDeck.getSize();
	for (int x = 0; x < limit; x++) {
	    //put words of Set newDeck into AL deck randomly
	    if (removeType == 1) {
		deck[x] = newDeck.removeR();
	    } //put words of Set newDeck into AL deck by priority
	    else if (removeType == 2) {
		deck[x] = newDeck.removeP();
	    } //put words of Set newDeck into AL deck regularly; sorted later 
	    else {
		deck[x] = newDeck.regRemove();
	    }
	}

	//put words of Set newDeck back into newDeck
	for (Word x : deck) {
	    newDeck.add(x);
	}	

	//if user asked for alphabetically, time to sort
	if (removeType == 3) {
	    deck = MergeSort.sort(deck);
	}
    }// end constructor

    public void play() {
	Scanner sc1 = new Scanner(System.in);
	System.out.println("LET'S STUDY WITH FLASHCARDS!");
	System.out.println("Press 1 to see the corresponding word/definition.");
	for (int i = 0; i < deck.length; i++) {
	    //If user has chosen to start with random choice
	    if (randomOrder == true) { 
		order = (int)(Math.random()*2+1);
	    }
	    //Print word, then print definition as prompted when user enters 1
	    if (order == 1) {
		System.out.println("\n" + deck[i].getName());
		String opt = sc1.next();
		int option = 0;
		boolean done = false;
		//if user enters something other than 1
		while ( done == false ) {
		    try {
			option = Integer.parseInt(opt);
			done = true; }
		    catch (Exception e) {
			System.out.println("Please enter 1 to see the definition.");
			opt = sc1.next();
		    }
		}
		
		while (option != 1) {
		    System.out.println("Please enter 1 to see the definition.");
		    opt = sc1.next();
		    done = false;
		    while ( done == false ) {
			try {
			    option = Integer.parseInt(opt);
			    done = true; }
			catch (Exception e) {
			    System.out.println("Please enter 1 to see the definition.");
			    opt = sc1.next();
			}
		    }
		}
		if (option == 1) {
		    System.out.println(deck[i].getDefinition());
		}
	    }
	    //Print definition, then print word as prompted when user enters 1
	    else if (order == 2) {
		System.out.println("\n" + deck[i].getDefinition());
		String opt = sc1.next();
		int option = 0;
		boolean done = false;
		//if user enters something other than 1
		while ( done == false ) {
		    try {
			option = Integer.parseInt(opt);
			done = true; }
		    catch (Exception e) {
			System.out.println("Please enter 1 to see the word.");
			opt = sc1.next();
		    }
		}
		
		while (option != 1) {
		    System.out.println("Please enter 1 to see the word.");
		    opt = sc1.next();
		    done = false;
		    while ( done == false ) {
			try {
			    option = Integer.parseInt(opt);
			    done = true; }
			catch (Exception e) {
			    System.out.println("Please enter 1 to see the word.");
			    opt = sc1.next();
			}
		    }
		}
		if (option == 1) {
		    System.out.println(deck[i].getName());
		}
	    }
	    //If the user did not know the correct word/definition, assign a higher priority to that word
	    System.out.println("Did you know the correct word/definition? Enter 1 for no, any key for yes");
	    String rite = sc1.next();
	    int q;
	    try { q = Integer.parseInt(rite);
		if( Integer.parseInt(rite) == 1 ) {
		    deck[i].setTimesMissed();
		}
	    }
	    catch( Exception e ){};
	    
	} // end for loop
    } // end play		

    //tester
    public static void main(String[] args) {
	Set geo = new Set("Geometry Terms");
	geo.add(new Word ("hypotenuse", "issa leg"));
	geo.add(new Word ("isosceles", "issa triangle"));
	geo.add(new Word ("median", "issa middle"));
	geo.add(new Word ("quadrilateral", "issa four"));
	geo.add(new Word ("triangle","threes"));
	Flashcard tester = new Flashcard(geo);
	tester.play();

    }
    
} // end class flashcard

