/*=======================================
  class Set

  Description: Creates a collection of Word instances as a linked list.
  Attributes: name, _first, _last, size, reviewed
  Methods: add, removeR, removeP, getReviewed, addReviewed, getSize, 
  showAllWords
 =======================================*/

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
	size++;
	if (_first == null) {
	    _first = newWord;
	    _last = newWord;
	    return;
	}
	newWord.setLast(_last);
	_last.setNext(newWord);
	_last = newWord;
    }

    //reg remove at head 
    public Word regRemove() {
	Word output;
	//if it's the only one, set all to null
	if (size == 1) {
	    output = _first;
	    _first = null;
	    _last = null;
	} else { //otherwise just point first to the next node
	    output = _first;
	    _first = _first.getNext();
	    _first.setLast(null);
	}
	size--;
	return output;
    }
    
    // remove randomly
    public Word removeR() {
	Word output;
	if (_last == _first) {
	    output = _first;
	    _first = null;
	    _last = null;
	    size--;
	} else {
	    //choose a random number of times
	    int r = (int) (Math.random() * size);
	    //add the removed first node that random number of times
	    for (int x = 0; x < r; x++) {
		add(regRemove());
	    }
	    //return the first node, which is now a random node in the list
	    output = regRemove();
	}
	return output;
    }

    //remove by priority
    public Word removeP() {
	Word highest = _first;
	Word temp = _first;
	//walk to the highest priority node by traversing the list and comparing priorities 
	while (temp != null) {
	    if (temp.compareTo(highest) > 0) {
		highest = temp;
	    }
	    temp = temp.getNext();
	}
	//remove that highest priority node
	if (highest == _first) {
	    regRemove();
	}
	else if (highest == _last) {
	    _last = highest.getLast();
	    _last.setNext(null); size--;
	}
	else {
	    highest.getLast().setNext(highest.getNext());
	    highest.getNext().setLast(highest.getLast());
	    size--;	    
	}
	return highest;
    }

    public Word findRemove(String input) {
	input = input.toLowerCase();
        Word temp = _first;
	while (temp != null) {
	    if (temp.getName().toLowerCase().equals(input)) {
		size--;
		if (temp == _first) {
		    if (temp == _last) {
			_last = null;
			_first = null;
			return null;
		    } else {
			_first = _first.getNext();
			_first.setLast(null);
		    }
		}
		else if (temp == _last) {
		    _last = _last.getLast();
		    _last.setNext(null);
		}
		else {
		    temp.getLast().setNext(temp.getNext());
		    temp.getNext().setLast(temp.getLast()); 
		}
		return temp;
	    }
	    temp = temp.getNext();
	}
	return null;
    }

    public String getName() {
	return name;
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

    public void setName(String newName) {
	name = newName;
    }

    //print all the words in the set
    public String showAllWords() {
	String ret = name + ": A Complete List\n";
	int numberPlace = 1;
	Word temp = _first;
	while (temp != null) {
	    ret += numberPlace + " " + temp + ": " + temp.getDefinition() + "\n";
	    numberPlace += 1;
	    temp = temp.getNext();
	}
	return ret;
    }
}

