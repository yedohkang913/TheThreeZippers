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
	System.out.println("\n" + name + ": A Complete List");
	int numberPlace = 1;
	Word temp = _first;
	while (temp != null) {
	    System.out.println(numberPlace + " " + temp);
	    numberPlace += 1;
	    temp = temp.getNext();
	}

    }

    //testing
    public static void main (String[] args) {
	/* ~~~~~~~~ A TEST OF FUNCTIONALITY ~~~~~~~~
	Set geo = new Set("Geometry Terms");
	Word orthocenter = new Word("Orthocenter");
	Word hypotenuse = new Word("hypotenuse");
	Word isosceles = new Word("isoceles");
	orthocenter.setTimesMissed(); //p = 1
	hypotenuse.setTimesMissed(); hypotenuse.setTimesMissed(); // p = 2
	//p for isoceles = 0
	geo.add(orthocenter);
	geo.add(hypotenuse);
	geo.add(isosceles);
	geo.showAllWords();
	//should be hypo, ortho, iso
	System.out.println("\nRemoving by priority: ");
	System.out.println("size: " + geo.getSize());
	System.out.println(geo.removeP()); 
	System.out.println(geo.removeP()); 
	System.out.println(geo.removeP());
	System.out.println("size: " + geo.getSize());
	//readd
	geo.add(orthocenter);
	geo.add(hypotenuse);
	geo.add(isosceles);
	//should be random
	System.out.println("\nRemoving randomly: ");
	System.out.println("size: " + geo.getSize());
	System.out.println(geo.removeR()); 
	System.out.println(geo.removeR());
	System.out.println(geo.removeR());
	System.out.println("size: " + geo.getSize());

	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }

}

