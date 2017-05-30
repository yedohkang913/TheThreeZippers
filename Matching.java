/*========================
  class Matching
  ==========================*/

import java.util.Scanner;

public class Matching {
 
    private Word[] wordDeck;
    private Word[] defDeck;
    private int numMatched;
    private Word[][] display;
    private final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
 
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
		output1 += ALPHA.substring(r, r+1) + ": " + display[r][0].getName();
	    if (display[r][1] != null) 
		output2 += + (r+1) + ": "  + display[r][1].getDefinition();
	    System.out.print("\t");
	    System.out.printf("%-26s", output1);
	    System.out.print("\t");
	    System.out.printf("%-26s", output2);
	    System.out.println();
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
	    if ( wordPlace == "" || defPlace == "") {
		System.out.println("Input does not follow instructions.");
	    }
	    else {
		int indexOfWord = Integer.parseInt(wordPlace);
		int indexOfDef = Integer.parseInt(defPlace) - 1;
		//is it a valid input?
		if (indexOfWord < 0 || indexOfWord >= wordDeck.length ||
		    indexOfDef < 0 || indexOfDef >= wordDeck.length ||
		    display[indexOfWord][0] == null ||
		    display[indexOfDef][1] == null) {
		    System.out.println("Input does not follow instructions.");
		}
		//is the input correct?
		else if (display[indexOfWord][0].getDefinition().equals(
			 display[indexOfDef][1].getDefinition())) {
		    System.out.println("You got it!");
		    numMatched++;
		    display[indexOfWord][0] = null;
		    display[indexOfDef][1] = null;
		}
		else { //input is wrong
		    System.out.println("Sorry, buddy.");
		}
	    }
	}
	System.out.println("End of Game");

    }

    public static void main(String[] args) {
	Set hello = new Set("coolSet");
	Word cool = new Word("cool");
	cool.setDefinition("not warm enough");
	Word great = new Word ("great");
	great.setDefinition("what you say to a mate");
	Word superb = new Word ("superb");
	superb.setDefinition("it's not just super, it's _____");
	Word fantastic = new Word ("fantastic");
	fantastic.setDefinition("_____ baby!");
	Word awesome = new Word ("awesome");
	awesome.setDefinition("awe inducing");
	hello.add(cool);
	hello.add(great);
	hello.add(superb);
	hello.add(fantastic);
	hello.add(awesome);
	Matching hi = new Matching (hello);
	hi.play();
    }
}
