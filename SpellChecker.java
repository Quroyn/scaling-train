import java.util.Scanner;
import java.util.File;

public class SpellChecker{
// A class to do spell checking. This version only marks misspelled words with
// asterisks as in **mispeling**.  It serves as a parent class for other spell
// checkers to inherit functionality to add features by overriding methods.

 	protected String [] dictWords;
 	// Array of words considered correct by spell checker

	protected boolean ignoreCase;
	// If true, ignore case when checking the spelling of words; otherwise
	// capitalization differences will be counted as misspellings

	public static String [] readAllLines(String filename){
 	// Utility which reads all lines from a file and returns them as an array of
	// strings. If problems are encountered during reading, return a string array
	// of length 0 (empty).  See implementation notes for dicussion of how to
	// handle exceptions and use two-pass scanning to allocate an appropriately
	// sized array.
		File file = new File(filename);
  		String[] words = new String[countWords(file)];
  		try{
  			Scanner in = new Scanner(file);
  		} catch(Exception e){ // find what to return if there is an error
  			return new String[0]
  		}
  		int index = 0;

  		while(in.hasNextWord()){
  			words[index] = in.nextWord();
  			index++;
  		}
  		return words;
	}

	private static int countWords(File file){
	// Helper method that counts the number of words in a provided file.
		Scanner in = new Scanner(file);
		int wordCount = 0;
		while(in.hasNextWord()){
			in.nextWord();
			wordCount++;
		}
		return wordCount;
	}

	public SpellChecker(String dictFilename, boolean ignoreCase){
	// Construct a spellchecker. dictFilename is the name of a file containing all
	// words that are considered correct, one on each line; english-dict.txt is
	// commonly used.  ignoreCase indicates whether case should be ignored or used
	// when checking for word correctness against dictionary words.
		this.dictWords = readAllLines(dictFilename);
		this.ignoreCase = ignoreCase;
	}

	private 

	public int dictSize(){
	// Return the size of the dictionary used by this spellchecker which is the
	// number of words read from the dictionary file and stored in the
	// dictWords array.
		return dictWords.length;
	}

	public boolean isCorrect(String word){
	// Return true if the provided word is considered correct by the spell checker
	// and false otherwise. A word is correct if it is equal to a word in the
	// dictionaryWord array. It is also correct if case is being ignored and is
	// equal ignoring case to some word in the dictWords array.

		if(ignoreCase){ // don't require same case
			for(int i = 0; i < dictWords.length; i++){
				if(word.equalsIgnoreCase(dictWords[i]) {return true;}
			}
			return false;
		}
		// require same case
		for(int i = 0; i < dictWords.length; i++){
			if(word.equals(dictWords[i]) {return true;}
		}
		return false;
	}

	public String correctWord(String word){
	// Create a correction for the given word.  Return the word surrounded by
	// asterisks which mark it as incorrect as in the word "misunderestimated"
	// should become "**misunderestimated**".  This method produces a correction
	// for the given word even if it is in the dictionary: it is to be used in
	// conjunction with isCorrect(word) to transform only words not in the
	// dictionary. That means correctWord("apple") returns "**apple**".

		return new String("**" + word + "**");
	}

	public void correctDocument(Document doc){
	// From the beginning of the document, apply corrections to all words in the
	// document. Each misspelled word will be marked with asterisks according to
	// the correctWord() method.  Methods of Document such as nextWord(),
	// hasNextWord(), and replaceLastWord(w) are used to modify the provided
	// document.

		String word = new String(doc.nextWord());
		if(!word.isCorrect()){
			replaceLastWord(correctWord(word));
		}
}

}