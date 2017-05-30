import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/*=============================
class Dictionary
Description: stores a Dictionary as an ArrayList of Words.
==============================*/
public class Dictionary {
	
	private String word;
	private static ArrayList<Word> _list;
	private static final String[] alpha = {"A","B","C","D","E","F","G","H","I","J","K",
		"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	
	public Dictionary(String wor) {
		
		word = wor;
		
	}
	
	public static void create () throws FileNotFoundException {
		
		_list = new ArrayList<Word>();
		
		//for (int i = 0; i < alpha.length; i++) {
			
			String line = "";
			int defPlace = 1;
		
			//File inputFile = new File( alpha[i] + ".txt");
			File inputFile = new File("A.txt");
			Scanner input = new Scanner(inputFile);
			while (input.hasNextLine()) {
				line += input.nextLine() + "\n";
			}
			String[] arr = line.split("\\s{2,}");

			for (int j = 0; j < arr.length - 1; j += 2) {
				_list.add(new Word(arr[j].toLowerCase(), arr[defPlace]));
				defPlace += 2;
			}
			
			//}
	}
	
	public void search () {
		
		try {
			create();
		}
		catch (FileNotFoundException inputFile) {
			System.out.println("another");
		}
		
		// will write binary search function later
	}
	
	public static void main (String[] args) {
		try {
			create();
		}
		catch (FileNotFoundException inputFile) {
			System.out.println("another");
		}
		System.out.println(_list);
	}
}