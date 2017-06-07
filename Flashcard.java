/*=============================
  class Flaschard
  Description: Prints out a definition or a word, and the user presses any key to print its
  corresponding definition or word. 
  ==============================*/


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
	System.out.println("\n\u001B[33mWelcome to Flashcard!\u001B[0m");
	System.out.println("\nPlease select an option: ");
	System.out.println("1: RANDOM SET");
	System.out.println("2: PRIORITIZED SET (based on number of times the word was missed)");
	System.out.println("3: ALPHABETICAL SET");
	System.out.print("\nSelection: ");
        String option = sc.next();
	int removeType = 1;
	//if the user did not enter a valid number
	boolean done = false;
	while ( done == false ) {
	    try {
		removeType = Integer.parseInt(option);
		if ( removeType > 3 ) {
		    System.out.print("\nPlease enter 1, 2, or 3: ");
		    option = sc.next();
		}
		else{ done = true; }
	    }
	    catch ( Exception e ) {
		System.out.print("\nPlease enter 1, 2, or 3: ");
	        option = sc.next();
	    }
	}
	if (removeType == 1) {
	    System.out.println("\nYou have chosen a random set.");
	}
	else if (removeType == 2) {
	    System.out.println("\nYou have chosen a prioritized set.");
	}
	else if (removeType == 3) {
	    System.out.println("\nYou have chosen an alphabetical set.");
	}

	// scanner input, for choices: either word first or definition or random
	System.out.println("\nPlease select an option: ");
	System.out.println("1: WORD FIRST");
	System.out.println("2: DEFINITION FIRST");
	System.out.println("3: RANDOM");
	System.out.print("\nSelection: ");
	//order = sc.nextInt();
	//did the user enter a valid input?
	option = sc.next();
	order = 1;
	//if the user did not enter a number
	done = false;
	while ( done == false ) {
	    try {
		order = Integer.parseInt(option);
		if ( order > 3 ) {
		    System.out.print("\nPlease enter 1, 2, or 3: ");
		    option = sc.next();
		}
		else {done = true; }
	    }
	    catch ( Exception e ) {
		System.out.print("\nPlease enter 1, 2, or 3: ");
	        option = sc.next();
	    }
	}	
	if (order == 1) {
	    System.out.println("\nYou have chosen to start with words.");
	}
	else if (order == 2) {
	    System.out.println("\nYou have chosen to start with definitions.");
	}
	else if (order == 3) {
	    randomOrder = true;
	    System.out.println("\nYou have chosen to start with a random choice.");
	}

	//dequeue cards randomly or by priority or alpha into an ArrayList
	deck = new Word[newDeck.getSize()];
	int limit = newDeck.getSize();
	for (int x = 0; x < limit; x++) {
	    //put words of Set newDeck into AL deck randomly
	    if (removeType == 1) {
		Word temp = newDeck.removeR();
		deck[x] = new Word(temp.getName(), temp.getDefinition());
	    } //put words of Set newDeck into AL deck by priority
	    else if (removeType == 2) {
		Word temp = newDeck.removeP();
		deck[x] = new Word(temp.getName(), temp.getDefinition());
	    } //put words of Set newDeck into AL deck regularly; sorted later 
	    else {
		Word temp = newDeck.regRemove();
		deck[x] = new Word(temp.getName(), temp.getDefinition());
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
	System.out.println("\nLET'S STUDY WITH FLASHCARDS!");
	System.out.println("\nEnter any key to see the corresponding word/definition.");
	for (int i = 0; i < deck.length; i++) {
	    //If user has chosen to start with random choice
	    if (randomOrder == true) { 
		order = (int)(Math.random()*2+1);
	    }
	    //Print word, then print definition as prompted when user enters whatever key
	    if (order == 1) {
		System.out.println("\nWord: \n" + deck[i].getName());
		String opt = sc1.next();
		if (opt != null) {
		    // IN THE TERMINAL, WHEN the word is printed, and a random key is pressed,
		    // the definition is NOT SHOWN. it just prints null
		    System.out.println("\nDefinition: \n" + deck[i].getDefinition());
		}
	    }
	    //Print definition, then print word as prompted when user enters something
	    else if (order == 2) {
		System.out.println("\nDefinition: \n" + deck[i].getDefinition());
		String opt = sc1.next();
		if (opt != null) {
		    System.out.println("\nWord: \n" + deck[i].getName());
		}
	    }
	    //If the user did not know the correct word/definition, assign a higher priority to that word
	    System.out.println("\nDid you know the correct word/definition? \n\t1: no \n\tany other key EXCEPT for H: yes");
	    System.out.print("\nSelection: ");
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
    
} // end class flashcard

