/*=============================
  class Learn
  Description: prints the definition of a set, and checks if the user's input (the word) is correct
  ==============================*/

import java.util.Scanner;

public class Learn {

    private Word[] deck;
    private float percentRight;
    private int numCorrect;
    private int numWrong;
    private int numWordsLeft;
    private int removeType;

    public Learn ( Set newDeck ) {
        
        percentRight = 0;
        numCorrect = 0;
        numWrong = 0;    
    
	// scanner input, for choices: either random, prioritized, or alphabetical
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome to Learn!");
        System.out.println("\nPlease select an option: ");
        System.out.println("1: RANDOM SET");
        System.out.println("2: PRIORITIZED SET (based on number of times a word was missed)");
        System.out.println("3: ALPHABETICAL SET");
        System.out.print("\nSelection: ");
	//did the user enter a valid input?
	String option = sc.next();
	int removeType = 1;
	boolean done = false;
	while ( done == false ) {
	    try {
		removeType = Integer.parseInt(option);
		if ( removeType > 3 ) {
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
        if (removeType == 1) {
            System.out.println("\nYou have chosen a random set.");
	}
	else if (removeType == 2) {
	    System.out.println("\nYou have chosen a prioritized set.");
	}
	else if (removeType == 3) {
	    System.out.println("\nYou have chosen an alphabetical set.");
	}

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

	numWordsLeft = deck.length;    
    }

    public float getPercent() {
        return percentRight;
    }


    public void play() {
	Scanner sc1 = new Scanner(System.in);
	System.out.println("\nLET'S STUDY WITH LEARN!");
	System.out.println("\nType in the word and press enter, after the definition has been printed!");
	for (int i = 0; i < deck.length; i++) {
	    //Print definition
	    System.out.println("\nDefinition: \n" + deck[i].getDefinition());
	    System.out.println();
	    System.out.print("Word: ");
	    String input = sc1.next().toLowerCase();
	    //if the user enters the incorrect word: either display the answer or try again
	    if (!input.equals(deck[i].getName())) {
		deck[i].setTimesMissed(); 
		numWrong++;
		while (!input.equals(deck[i].getName())) {
		    System.out.println("\nSorry, but that's incorrect. \nTry again, or type in 'kellyiscool' for the correct answer!");
		    System.out.print("Word/kellyiscool: ");
		    input = sc1.next();
		    if ( input.equals("kellyiscool") ) {
			while ( !input.equals(deck[i].getName() ) ) {
			    System.out.println("\nPlease enter the CORRECT answer: " + deck[i].getName() ) ;
			    System.out.print("Input: ");
			    input = sc1.next();
			}
		    }
		}
	    } else {
		System.out.println("\nTrue and correct!");
		numCorrect++;
	    }
	    //print stats for this game!
	    numWordsLeft--;
	    System.out.println("\n# of Words Incorrect: " + numWrong);
	    System.out.println("# of Words Correct: " + numCorrect);
	    System.out.println("# of Words Left : " + numWordsLeft);
	    percentRight = (float) numCorrect / (numCorrect + numWrong) * 100;
	} // end for loop
	System.out.println("\nPercent Correct: " + percentRight + "%");
    } // end play        

} // end class Learn
