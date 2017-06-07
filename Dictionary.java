import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/*=============================
  class Dictionary
  Description: stores a Dictionary as an ArrayList of Words, searches a word and returns a definition
  ==============================*/
public class Dictionary {
	
    private String word;
    private static ArrayList<Word> _list;
    private static final String[] alpha = {"A","B","C","D","E","F","G",
					   "H","I","J","K","L","M","N","O",
					   "P","Q","R","S","T","U","V","W",
					   "X","Y","Z"};
	
    public static void create () throws FileNotFoundException {
		
	_list = new ArrayList<Word>();
		
	// go through the entire alphabet
	for (int i = 0; i < alpha.length; i++) {
			
	    String line = "";
	    int defPlace = 1;
		
	    File inputFile = new File("dictionaries/" +  alpha[i] + ".txt");
	    //File inputFile = new File("T.txt");
	    Scanner input = new Scanner(inputFile);
	    // while the text file has a next line
	    while (input.hasNextLine()) {
		line += input.nextLine() + "\n";
	    }
	    // parse into string, definition
	    String[] arr = line.split("\\s{2,}");

	    for (int j = 0; j < arr.length - 1; j += 2) {
		// instantiate a new Word object and add it to the arraylist
		_list.add(new Word(arr[j].toLowerCase(), arr[defPlace]));
		// update
		defPlace += 2;
	    }
	}
    }
	
    public static boolean checque() {
	for ( int x = 0; x < _list.size()-1 ; x++ ) {
	    System.out.println(_list.get(x));
	  
	    if ( _list.get(x).alphaCompare(_list.get(x+1) )>= 0 ){
		return false;
	    }
	}
	return true;
    }
    public static String search (String word) {
		
	Word yeah = new Word( word );
	    
	//binary search function  
	int hi = _list.size()-1;
	int lo = 0;
	int mid = (hi + lo )/2;
	int _index = -1;
	while ( lo <= hi ) {
	    //found the word!
	    if ( (_list.get(mid)).alphaCompare(yeah) == 0 ) {
		_index = mid;
		return _list.get(_index).getDefinition();
	    }
	    //word comes before arg
	    else if ( (_list.get(mid) ).alphaCompare(yeah) < 0 ) {
		lo = mid+1;
	    }
	    //word comes after arg
	    else {
		hi = mid-1;
	    }
	    mid = ( hi + lo ) /2;
	}
	return null;
    }

    //if the user entered a word that the dictionary couldnt find, try to find it!
    public static String checkWord1( String word ) {
	//append a number to the word, check if its in our dictionary
	//return all the definitions of that word
	String ret = "";
	int i =1;
        while ( search(word+i) != null ) {
	    ret += "\n" + search( word + i );
	    i++;
	}
	if ( ret.equals("")) return null;
	return ret;
    }
    
    public static String checkWord2( String word ) {
	//check if the word is actually two words (ex. lavenderwater --> lavender water, policeofficer --> police officer)
	for ( int x = 0 ; x < word.length() ; x++ ){
	    String wordA = word.substring(0,x);
	    String wordB = word.substring(x);
	    if ( search(wordA + " " + wordB ) != null ) {
		return search(wordA + " " + wordB);
	    }
	}
	return null;
    }

}
