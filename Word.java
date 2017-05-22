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
    
    public Word(String nam) {
        name = nam;
        definition = null;
        timesMissed = 0;
        nextWord = null;
	lastWord = null;
    }

    public int compareTo( Object w ){
        return this.getTimesMissed() - ((Word) w).getTimesMissed();
    }

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

}//end class word
