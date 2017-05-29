/*========================
  class Matching
  ==========================*/

import java.util.Scanner;

public class Matching {
 
    private Word[] wordDeck;
    private Word[] defDeck;
    private int numMatched;
    private Word[][] display;
    private final String ALPHA = "abcdefghijklmnopqrtuvwxyz";
 
    public Matching (Set newDeck) {
	System.out.println("Welcome to Matching!");

	//dequeue cards randomly or by priority or alpha into an ArrayList
	wordDeck = new Word[newDeck.getSize()];
	defDeck = new Word[newDeck.getSize()];
	int limit = newDeck.getSize();
	for (int x = 0; x < limit; x++) {
	    //put words of Set newDeck into AL deck randomly
	    Word temp = newDeck.removeR();
	    wordDeck[x] = temp;
	    defDeck[x] = temp; //your definitions are random
	} 

	//put words of Set newDeck back into newDeck
	for (Word x : wordDeck) {
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
		output1 = ALPHA.substring(r, r+1) + ": " + display[r][0].getName();
	    if (display[r][1] != null) 
		output2 += r+1 + ": "  + display[r][1].getDefinition();
	    //System.out.printf("%26d", output1);
	    //System.out.printf("%-26d", output2);

	    //FOR TESTING
	    System.out.print( output1);
	    System.out.print( output2);
	    System.out.println();
	    /*String output;
	    for (int c = 0; c < 2; c++) {
		if (c == 0) {output += ALPHA.substring(r, r+1);}
		else {output += r+1;}
		if ( display[r][c] != null ) {
		    for (int x = 0; x < (26 - display[r][c].getName.length); x++) {
			output += " " ;
		    }
		    output += display[r][c].getName();
		} else { 
		    for (int x = 0; x < 26; x++) {
			output += " ";
		    }
		} 
		System.out.println(output);
	    */
	}
    }
 
    public void play() {
	Scanner sc1 = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	System.out.println("LET'S STUDY WITH MATCHING!");
	System.out.println("Type in the letter corresponding to a word, then type in the number corresponding to its definition!");
	while (numMatched != wordDeck.length) {
	    printDisplay();
	    String input = sc1.next().toLowerCase();
	    //make the input readable
	    String  wordPlace = ""; String defPlace = "";
	    for (int x = 0; x < input.length(); x++) {
		if (ALPHA.indexOf(input.substring(x, x+1)) != -1) {
		    wordPlace += ALPHA.indexOf(input.substring(x, x+1));
		} else {
		    defPlace += input.substring(x, x+1);
		}
	    }
	    int indexOfWord = Integer.parseInt(wordPlace);
	    int indexOfDef = Integer.parseInt(defPlace);
	    //is it a valid input?
	    if (indexOfWord < 0 || indexOfWord > wordDeck.length ||
		indexOfDef < 0 || indexOfDef > wordDeck.length ||
		display[indexOfWord][1] == null ||
		display[indexOfDef][2] == null) {
		System.out.println("Input does not follow instructions.");
	    }
	    //is the input correct?
	    else if (display[indexOfWord][1].getDefinition().equals(
		    display[indexOfDef][2].getDefinition())) {
		System.out.println("You got it!");
		numMatched++;
		display[indexOfWord][1] = null;
		display[indexOfDef][2] = null;
	    }
	    else { //input is wrong
		System.out.println("Sorry, buddy.");
	    }
	    System.out.println("End of Game");
	}

	/*
	  if (!input.equals(deck[i].getName())) {
	  System.out.println("Sorry, but that's incorrect.");
	  deck[i].setTimesMissed();
	  wrong++;
	  while (!input.equals(deck[i].getName())) {
	  System.out.println("Please enter the CORRECT answer: " + deck[i].getName() ) ;
	  input = sc1.next();
	  }
	  } else {
	  System.out.println("True and correct!");
	  correct++;
	  }

	  wordsLeft--;
	  System.out.println("# of Words Incorrect: " + wrong);
	  System.out.println("# of Words Correct: " + correct);
	  System.out.println("# of Words Left : " + wordsLeft);
	  percentRight = (float) correct / (correct + wrong) * 100;
	*/

    }

    public static void main(String[] args) {
	Set hello = new Set("coolSet");
	hello.add( new Word ( "cool" ) );
	hello.add( new Word ( "great" ) );
	hello.add( new Word ( "super" ) );
	hello.add( new Word ( "fantastic" ) );
	hello.add( new Word ( "awesome" ) );
	Matching hi = new Matching (hello);
	hi.play();
    }
}
