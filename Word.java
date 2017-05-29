/*=======================================
  class Word

  Description: A basic building block that can function as a node
  Attributes: name, definition, timesMissed, nextWord, lastWord
  Methods: compareTo, getName, setTimesMissed, getTimesMissed, setNext, getNext,
  setLast, getLast
 =======================================*/

public class Word implements Comparable{

    private String name;
    private String definition;
    private int timesMissed;
    private Word nextWord; private Word lastWord;
    private final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    
    public Word(String nam) {
        name = nam;
        definition = null;
        timesMissed = 0;
        nextWord = null;
	lastWord = null;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~COMPARABLE METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
    // compareTo - looks at timesMissed
    public int compareTo( Object w ){
        return this.getTimesMissed() - ((Word) w).getTimesMissed();
    }

    /* alphaCompare - for alphabetizing
      returns - if this word comes before the argument in the dictionary
      returns 0 if they are the same word
      returns + if this word comes after the argument in the dictionary
     */
    public int alphaCompare(Object that) {
	if (name.equals(((Word) that).getName())) {return 0;}
	int a = 0; int b = 0;
	// while there are still letters to compare
	while (a != name.length() && b != ((Word) that).getName().length()) {
	    // if their letter is the same, move on to next letter
	    if (ALPHA.indexOf(name.substring(a,a+1)) ==
		ALPHA.indexOf(((Word) that).getName().substring(b, b+1))) {
		a++; b++; 
	    } // otherwise compare where those letters stand in the alphabet
	    else {
		return ALPHA.indexOf(name.substring(a,a+1)) -
		    ALPHA.indexOf(((Word) that).getName().substring(b, b+1));
	    }
	} 
	// one word must exist in the other; shorter letter comes first
	return name.length() - ((Word) that).getName().length();
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
//~~~~~~~~~~~~~~~~~~~~~~~ ACCESSORS & MODIFIERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
    public String getName() {
        return name;
    }

    public void setTimesMissed() {
	timesMissed += 1;
    }

    public int getTimesMissed() {
	return timesMissed;
    }

    public String getDefinition() {
	return definition;
    }
	
    public void setDefinition(String input) {
	definition = input;	    
    }

    public void setNext(Word next) {
	nextWord = next;
    }

    public Word getNext() {
	return nextWord;
    }

    public void setLast(Word last) {
	lastWord = last;
    }

    public Word getLast() {
	return lastWord;
    }

    public String toString() {
	return name;
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //tester for alphaCompare
    public static void main(String[] args) {
	Word bob = new Word ("dog");
	Word cob = new Word ("dog");
	Word rob = new Word ("doghouse");
	Word fob = new Word ("driver");
	Word job = new Word ("angle");

	System.out.println(bob.alphaCompare(job)); //angle is greater
	System.out.println(bob.alphaCompare(fob)); //dog is greater
	System.out.println(bob.alphaCompare(rob)); //dog is greater
	System.out.println(bob.alphaCompare(cob)); //same
    }

}//end class word
