public class Driver {

    public static void main (String[] arg) {
	System.out.println("Welcome to Dictionary Frenzy!");
	System.out.println("Here's an example set:");
	
	Set hello = new Set("coolSet");
	Word cool = new Word("cool");
	cool.setDefinition("not warm enough");
	Word great = new Word ("great");
	great.setDefinition("what you say to a mate");
	Word superb = new Word ("superb");
	superb.setDefinition("it's not just super, it's _____");
	Word fantastic = new Word ("fantastic");
	fantastic.setDefinition("_____ baby!");
	Word awesome = new Word ("awesome");
	awesome.setDefinition("awe inducing");
	hello.add(cool);
	hello.add(great);
	hello.add(superb);
	hello.add(fantastic);
	hello.add(awesome);
	hello.showAllWords();
	System.out.println();

	Flashcard hiF = new Flashcard (hello);
	hiF.play();
	System.out.println();
	Learn hiL = new Learn(hello);
	hiL.play();
	System.out.println();
	Matching hiM = new Matching (hello);
	hiM.play();
	System.out.println();

    }


}
