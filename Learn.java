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
        System.out.println("\n\u001B[33mWelcome to Learn!\u001B[0m");
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

	numWordsLeft = deck.length;    
    }

    public float getPercent() {
        return percentRight;
    }


    public void play() {
	Scanner sc1 = new Scanner(System.in);
	Scanner lines = new Scanner(System.in);

	System.out.println("\nLET'S STUDY WITH LEARN!");
	System.out.println("\nType in the word and press enter, after the definition has been printed!");
	for (int i = 0; i < deck.length; i++) {
	    //Print definition
	    System.out.println("\nDefinition: \n" + deck[i].getDefinition());
	    System.out.println();
	    System.out.print("Word: ");
	    String input = lines.nextLine().toLowerCase();
	    //if the user enters the incorrect word: either display the answer or try again
	    if (!input.equals(deck[i].getName())) {
		deck[i].setTimesMissed(); 
		numWrong++;
		while (!input.equals(deck[i].getName())) {
		    System.out.println("\n\u001B[31mSorry, but that's incorrect. \nTry again, or type in 'kellyiscool' for the correct answer!\u001B[0m");
		    System.out.print("Word/kellyiscool: ");
		    input = sc1.next();
		    if ( input.equals("kellyiscool") ) {
			while ( !input.equals(deck[i].getName() ) ) {
			    System.out.println("\nPlease enter the CORRECT answer: " + deck[i].getName() ) ;
			    System.out.print("Input: ");
			    input = lines.nextLine();
			}
		    }
		}
	    } else {
		System.out.println("\n\u001B[35mTrue and correct!\u001B[0m");
		numCorrect++;
	    }
	    //print stats for this game!
	    numWordsLeft--;
	    System.out.println("\u001B[33m\n# of Words Incorrect: " + numWrong);
	    System.out.println("# of Words Correct: " + numCorrect);
	    System.out.println("# of Words Left : " + numWordsLeft + "\u001B[0m");
	    percentRight = (float) numCorrect / (numCorrect + numWrong) * 100;
	} // end for loop
	System.out.println("\nPercent Correct: " + percentRight);
    } // end play        

} // end class Learn
