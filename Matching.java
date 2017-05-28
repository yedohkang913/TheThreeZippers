/*========================
class Matching
==========================*/

public class Matching {
 
	private Word[] wordDeck;
	private Word[] defDeck;
	private int numMatched;
	private String[][] display;
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
	for (Word x : deck) {
	    newDeck.add(x);
	}	
 
//sort the word column
      wordDeck = MergeSort.sort(deck);


	display = new String[deck.length][2];
	
}// end constructor
 
	// prints the word in one column and the def in another; 
// if there is a null pointer, skip this line
	public void printDisplay() {
		for ( int r = 0 ; r < display.length ; r++ ) {
			String output;
			for (int c = 0; c < 2; c++) {
				if (c == 0) {output += ALPHA.substring(r, r+1);}
				else {output += r+1;}
if ( display[r][c] != null ) {
for (int x = 0; x < (26 - display[r][c].getName.length); x++) {
	output += “ “;
}
output += display[r][c].getName();
} else { 
	for (int x = 0; x < 26; x++) {
		output += “ “;
	}
} 
System.out.println(output);
}
	}
 
	public play() {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
    		System.out.println("LET'S STUDY WITH MATCHING!");
    		System.out.println("Type in the letter corresponding to a word, then type in the number corresponding to its definition!");
    		for (int i = 0; i < deck.length; i++) {
    		//Print definition
    			System.out.println("\n" + deck[i].getDefinition());
    			String input = sc1.next().toLowerCase();
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
    		} // end for loop
System.out.println("Percent Correct: " + percentRight + "%");
}
}
