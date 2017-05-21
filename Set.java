public class Set {

    private String name;
    private Word _first; 
    private Word _last;
    private int size;
    private int reviewed;

    public Set(String newName) {
	name = newName;
	_first = null; _last = null;
	size = 0;
	reviewed = 0;
    }

    public void add ( Word newWord ) {
	newWord.setLast(_last);
	_last.setNext(newWord);
	_last = newWord;
	size++;
    }

    // remove randomly
    public Word removeR() {
	//choose a random number of times
	int r = (int) (Math.random() * size);
	//add the removed first node that random number of times
	for (int x = 0; x < r; x++) {
	    Word temp = _first;
	    _first = _first.getNext();
	    _first.setLast(null);
	    add(temp); size--;
	}
	//return the first node, which is now a random node in the list
	Word output = _first;
	_first = _first.getNext();
	_first.setLast(null);
	size--;
	return output;
    }

    //remove by priority
    public Word removeP() {
	Word highest = _first;
	Word temp = _first;
	//walk to the highest priority node by traversing the list and comparing priorities 
	while (temp.getNext() != null) {
	    if (temp.compareTo(highest) > 0) {
		highest = temp;
	    }
	    temp = temp.getNext();
	}
	//remove that highest priority node
	highest.getLast().setNext(highest.getNext());
	highest.getNext().setLast(highest.getLast());
	size--;
	return highest;
    }

    public int getReviewed() {
	return reviewed;
    }

    public void addReviewed() {
	reviewed += 1;	
    }

    public int getSize() {
	return size;
    }

    //print all the words in the set
    public void showAllWords() {
	int numberPlace = 1;
	Word temp = _first;
	while (temp.getNext() != null) {
	    System.out.println(numberPlace + " " + temp.getName());
	    numberPlace += 1;
	    temp = temp.getNext();
	}

    }

}

