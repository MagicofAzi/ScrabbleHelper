import java.util.ArrayList;
import java.util.Collections;
/**
* This class represents the scrabble solver of the ScrabbleHelper. It takes a parameter of letters
* and returns all possible permutations of letters. It returns an arraylist of words that are present in 
* the given dictionary. Additional wrapper methods are used to implement recursive binary searches.
* @ author  Rahat Hossan
* @ version 03/03/2017
*/	

public class Permutations {
	String letters;
	ArrayList<String> foundWords = new ArrayList<String>();
	ArrayList<String> permutations = new ArrayList<String>();
	
	/**
	 * A Permutations constructor and takes letters from args as a parameter.
	 * 
	 * @param letters - passed by args[1], sequence of letters that will be used to find possible words
	 * @throws IllegalArgumentException - returns an error message if given letters is not a letter.
	 * Also checks whether the second argument is given.
	 */
	public Permutations(String letters) throws IllegalArgumentException{
		this.letters = letters;
		if (this.letters.isEmpty()){
			throw new IllegalArgumentException("Error: No second command line argument detected");
		}
		for(int i = 0; i < letters.length(); i++) {
			if( (letters.charAt(i) > 90 && letters.charAt(i) < 97) ||
					letters.charAt(i) > 122 || letters.charAt(i) < 65 ) {
				throw new IllegalArgumentException("Error: Only letters can be valid inputs");
			}
			
		}
		if(letters.length() >= 10) {
			throw new OutOfMemoryError("Error: There are more than 10 letters in this String, too much computation required.");
		}
	}

	
	/*
	 * This public method calls on a helper method and asks as a wrapper method. It returns an arraylist of 
	 * all possible permutations.
	 * @param no parameters
	 * @return permutations, all possible permutations.
	 */
	public ArrayList<String> getAllPermutations() {
		permutations = getPermutations("", letters, permutations);
		return permutations;
	}
	
	
	/*
	 * This private static method is a helper method for getAllPermutations. 
	 * @param prefix - Original string which is set to empty
	 * @param letters - Second command line argument
	 * @return perms, all possible permutations and is set to permutations.
	 */
	private static ArrayList<String> getPermutations(String prefix, String letters, ArrayList<String> perms) {
		int charLength = letters.length();
	    if (charLength == 0) {
	    	perms.add(prefix);
	    }
	    else {
	        for (int i = 0; i < charLength; i++)
	            getPermutations(prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i+1, charLength), perms);
	    }
		return perms;
	}
	/*
	 * This public method calls a helper method findWords. It returns all words that match words in the dictionary 
	 * @param dictionary - Dictionary object to check words
	 * @return foundWords, array list of all words.
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary ) {
		findWords("", letters, dictionary);
		Collections.sort(foundWords);
		return foundWords;
	}
	/*
	 * This public method calls is a helper method for findWords.
	 * @param dictionary - Dictionary object to check words.
	 * @param prefix - empty string.
	 * @param letters - given letters
	 * @return void.
	 */
	public void findWords(String prefix, String letters, Dictionary dictionary) {
		int charLength = letters.length();
	    if (charLength == 0 && dictionary.isWord(prefix) && !foundWords.contains(prefix)) {   
	    	foundWords.add(prefix);
	    }
	    else {
	        for (int i = 0; i < charLength; i++)
	        	if(dictionary.isPrefix(prefix)) {
	        		findWords(prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i+1, charLength), dictionary);
	        	}     
	    }
	}	
}
