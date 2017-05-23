import java.util.Scanner;
import java.util.ArrayList;

public class Flashcard {

    //private String name;
    private ArrayList<Word> deck;
    private int removeType; //1 = random & 2 = priority & 3 = alphabetical         
    private int order; //1 = name & 2 = definition    
    private boolean randomOrder; //either display words first or definition first

    public Flashcard(Set newDeck) {

	// scanner input, for choices: either random, prioritized, or alphabetical
	Scanner sc = new Scanner(System.in);
	System.out.println("Welcome to Flashcard!");
	System.out.println("Please select an option: ");
	System.out.println("1 for RANDOM SET");
	System.out.println("2 for PRIORITIZED SET (based on number of word)");
	System.out.println("3 for ALPHABETICAL SET");
	System.out.print("Selection: ");
	removeType = sc.nextInt();
	while (removeType != 1 && removeType != 2 && removeType != 3) {
	    System.out.println("Invalid number selected. Please enter a valid number for selection.");
	    System.out.print("Selection: ");
	    removeType = sc.nextInt();
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
	order = sc.nextInt();
	while (order != 1 && order != 2 && order != 3) {
	    System.out.println("Invalid number selected. Please enter a valid number for selection.");
	    System.out.print("Selection: ");
	    order = sc.nextInt();
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
	deck = new ArrayList<Word>();           
	while (newDeck.getSize() != 0) {
	    //put words of Set newDeck into AL deck randomly
	    if (removeType == 1) { 
		deck.add(newDeck.removeR());
	    } //put words of Set newDeck into AL deck by priority
	    else if (removeType == 2) {
		deck.add(newDeck.removeP());
	    } //put words of Set newDeck into AL deck regularly; sorted later 
	    else {
		deck.add(newDeck.regRemove());
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
	System.out.println("LET’S STUDY WITH FLASHCARDS!");
	System.out.println("Press 1 to see the corresponding word/definition.");
	for (int i = 0; i < deck.size(); i++) {
	    //If user has chosen to start with random choice
	    if (randomOrder == true) { 
		order = (int)(Math.random()*2+1);
	    }
	    //Print word, then print definition as prompted when user enters 1
	    if (order == 1) {
		System.out.println(deck.get(i).getName());
		int option = sc1.nextInt();
		while (option != 1) {
		    System.out.println("Please enter a valid number.");
		    option = sc1.nextInt();
		}
		if (option == 1) {
		    System.out.println(deck.get(i).getDefinition());
		}
	    }
	    //Print definition, then print word as prompted when user enters 1
	    else if (order == 2) {
		System.out.println(deck.get(i).getDefinition());
		int option = sc1.nextInt();
		while (option != 1) {
		    System.out.println("Please enter a valid number.") ;
		    option = sc1.nextInt();
		}
		if (option == 1) {
		    System.out.println(deck.get(i).getName());
		}
	    }
	} // end for loop
    } // end play		

} // end class flashcard

