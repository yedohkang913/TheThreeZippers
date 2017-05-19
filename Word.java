public class Word implements Comparable{

    private String name;
    private String definition;
    private int timesMissed;
    Word nextWord;     
    
    public Word(String nam) {
        name = nam;
        definition = null;
        timesMissed = 0;
        nextWord = null;
    }

    public int compareTo( Object w ){
        return this.getTimesMissed() - w.getTimesMissed();
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

}//end class word
