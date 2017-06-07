/*========================
  class Matching
  Prints words and definitions in two columns and requires users to input the 
  corresponding letters and numbers to match the words to their definitions.
  ==========================*/

import java.util.Scanner;

public class Matching {

    //~~~~~~~~~instance vars~~~~~~~~~~~~~
    private Word[] wordDeck;
    private Word[] defDeck;
    private int numMatched;
    private Word[][] display;
    private final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //constructor
    public Matching (Set newDeck) {
	System.out.println("\n\u001B[33mWelcome to Matching!\u001B[0m");

	//dequeue cards randomly or by priority or alpha into an ArrayList
	wordDeck = new Word[newDeck.getSize()];
	defDeck = new Word[newDeck.getSize()];
	int limit = newDeck.getSize();
	for (int x = 0; x < limit; x++) {
	    //put words of Set newDeck into AL deck randomly
	    Word temp = newDeck.removeR();
	    //we'll be setting pointers to null, so deep copy
	    Word newTemp = new Word (temp.getName(), temp.getDefinition());
	    wordDeck[x] = newTemp;
	    defDeck[x] = newTemp; //your definitions are random
	} 

	//put words of Set newDeck back into newDeck
	for (Word x : defDeck) {
	    newDeck.add(x);
	}	

	//sort the word column; defDeck is still random
	wordDeck = MergeSort.sort(wordDeck);


	display = new Word[wordDeck.length][2];
	//populate the display
	for (int r = 0; r < display.length; r++) {
	    display[r][0] = wordDeck[r];
	    display[r][1] = defDeck[r];
	}

	numMatched = 0;
	
    }// end constructor

    
    // prints the word in one column and the def in another; 
    // if there is a null pointer, skip this line
    public void printDisplay() {
	for ( int r = 0 ; r < display.length ; r++ ) {
	    String output1 = ""; String output2 = "";
	    if (display[r][0] != null)
		output1 += ALPHA.substring(r, r+1) + ": " + display[r][0].getName();
	    if (display[r][1] != null) 
		output2 += + (r+1) + ": "  + display[r][1].getDefinition();
	    System.out.print("\t");
	    //make these outputs each a minimum of 26 spaces
	    System.out.printf("%-26s", output1);
	    System.out.print("\t");
	    System.out.printf("%-26s", output2);
	    System.out.println();
	}
    }
    
 
    public void play() {
	Scanner sc1 = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	System.out.println("\nLET'S STUDY WITH MATCHING!");
	System.out.println("Type in the letter corresponding to a word, then type in the number corresponding to its definition!\n");

	//numMatched increases until all matches are made
	while (numMatched != wordDeck.length) {
	    
	    printDisplay(); //print the columns
	    System.out.print("\nInput (letter THEN number, ex. a3): ");
	    String input = sc1.next().toLowerCase();
	    
	    //make the input readable--separate letters and numbers
	    String  wordPlace = ""; String defPlace = "";
	    for (int x = 0; x < input.length(); x++) {
		if (ALPHA.indexOf(input.substring(x, x+1)) != -1) {
		    wordPlace += ALPHA.indexOf(input.substring(x, x+1));
		} else {
		    defPlace += input.substring(x, x+1);
		}
	    }
	    
	    //is it a valid input?
	    if ( wordPlace == "" || defPlace == "") {
		System.out.println("\nInput does not follow instructions.");
	    }
	    else {
		int indexOfWord = Integer.parseInt(wordPlace);
		int indexOfDef = Integer.parseInt(defPlace) - 1;

		//is the input within indices and does the word exist?
		if (indexOfWord < 0 || indexOfWord >= wordDeck.length ||
		    indexOfDef < 0 || indexOfDef >= wordDeck.length ||
		    display[indexOfWord][0] == null ||
		    display[indexOfDef][1] == null) {
		    System.out.println("\nInput does not follow instructions.");
		}
		
		//is the input correct?
		else if (display[indexOfWord][0].getDefinition().equals(
									display[indexOfDef][1].getDefinition())) {
		    System.out.println("\n\u001B[35mYou got it!\u001B[0m");
		    numMatched++;
		    display[indexOfWord][0] = null;
		    display[indexOfDef][1] = null;
		}
		
		else { //input is wrong & numMatched does not change
		    System.out.println("\n\u001B[31mSorry, buddy.\u001B[0m");
		}
	    }
	}
	System.out.println("\nEnd of Game!!");

    }
}
