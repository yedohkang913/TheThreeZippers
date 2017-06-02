import java.util.Scanner;

public class Learn {

    private Word[] deck;
    private float percentRight;
    private int correct;
    private int wrong;
    private int wordsLeft;
    private int removeType;

    public Learn ( Set newDeck ) {
        
        percentRight = 0;
        correct = 0;
        wrong = 0;    
    
	// scanner input, for choices: either random, prioritized, or alphabetical
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Learn!");
        System.out.println("Please select an option: ");
        System.out.println("1 for RANDOM SET");
        System.out.println("2 for PRIORITIZED SET (based on number of word)");
        System.out.println("3 for ALPHABETICAL SET");
        System.out.print("Selection: ");
	//did the user enter a valid input?
	String option = sc.next();
	int removeType = 1;
	boolean done = false;
	while ( done == false ) {
	    try {
		removeType = Integer.parseInt(option);
		if ( removeType > 3 ) {
		    System.out.println("Please enter 1, 2, or 3");
		    option = sc.next();
		}
		else {done = true; }
	    }
	    catch ( Exception e ) {
		System.out.println("Please enter 1, 2, or 3");
	        option = sc.next();
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

	wordsLeft = deck.length;    
    }

    public float getPercent() {
        return percentRight;
    }


    public void play() {
	Scanner sc1 = new Scanner(System.in);
	System.out.println("LET'S STUDY WITH LEARN!");
	System.out.println("Type in the word and press enter, after the definition has been printed!");
	for (int i = 0; i < deck.length; i++) {
	    //Print definition
	    System.out.println("\n" + deck[i].getDefinition());
	    String input = sc1.next().toLowerCase();
	    //if the user enters the incorrect word: either display the answer or try again
	    if (!input.equals(deck[i].getName())) {
		deck[i].setTimesMissed(); 
		wrong++;
		while (!input.equals(deck[i].getName())) {
		    System.out.println("Sorry, but that's incorrect. \nTry again, or type in 'kellyiscool' for the correct answer");
		    input = sc1.next();
		    if ( input.equals("kellyiscool") ) {
			while ( !input.equals(deck[i].getName() ) ) {
			    System.out.println("Please enter the CORRECT answer: " + deck[i].getName() ) ;
			    input = sc1.next();
			}
		    }
		}
	    } else {
		System.out.println("True and correct!");
		correct++;
	    }
	    //print stats for this game!
	    wordsLeft--;
	    System.out.println("# of Words Incorrect: " + wrong);
	    System.out.println("# of Words Correct: " + correct);
	    System.out.println("# of Words Left : " + wordsLeft);
	    percentRight = (float) correct / (correct + wrong) * 100;
	} // end for loop
	System.out.println("Percent Correct: " + percentRight + "%");
    } // end play        

    //tester
    public static void main(String[] args) {
	Set geo = new Set("Geometry Terms");
	geo.add(new Word ("hypotenuse", "issa leg"));
	geo.add(new Word ("isosceles", "issa triangle"));
	geo.add(new Word ("median", "issa middle"));
	geo.add(new Word ("quadrilateral", "issa four"));
	geo.add(new Word ("triangle","threes"));
	Learn tester = new Learn(geo);
	tester.play();
    }

} // end class Learn
