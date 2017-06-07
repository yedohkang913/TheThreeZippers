*The Three Zippers*           
*APCS2 Final Project*           
*Yedoh Kang, Kristin Lin, Kelly Wang*          

# DICTIONARY FRENZY!

### For Users:
Welcome to Dictionary Frenzy!! Dictonary Frenzy! includes a dictionary to search up words and some interactive learning tools that will help you study your vocabulary words. You will also be able to create and edit multiple sets, and create a text file of the words for later use! The program features not one, not two, but THREE (!!!) educational tools: 

**Flashcards:** Display a word then show its definition, or display a definition then show its word!     
**Learn:** Given a definition, type in the corresponding word.              
**Matching:** Match each word with its correct definition.                      

### How it works:

Dictionary Frenzy! uses a class called Word, which has a name and a definition, as well as timesMissed, nextWord, and lastWord for the purpose of the Set class, which creates queues of Words for users to create their vocabulary sets. Using the regular queue, priority queue, and random queue methods of removing an element, Set allows the Flashcard, Learn, and Matching classes to give users an option of having their words presented in alphabetical order, order of priority (Words with a higher number of timesMissed will be of higher priority), or random order. To present the words in alphabetical order, Flashcard, Learn, and Matching remove the Words from a given Set regularly and sort it into alphabetical order using MergeSort, O(nlogn).

Dictionary Frenzy! has a Driver file that presents the user with the options of searching a Word, which refers to the ArrayList of all instances of Words in the static class Dictionary, creating a new Set of Words, editing a Set of Words, exporting a text file of words or using an educational tool--Flashcard, Learn, or Matching. There also other options within these options, such as changing the given definition to a definition of one's liking.

### Launch Instructions: 
1. Open up the terminal.
2. Follow these instructions:
```txt
$ git clone https://github.com/yedohkang913/TheThreeZippers.git
$ cd TheThreeZippers
$ javac Driver.java
$ java Driver
```
3. Have fun!

