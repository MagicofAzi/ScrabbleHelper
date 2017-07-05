import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* This class represents the Dictionary of the ScrabbleHelper. It takes a parameter of a file
* and inputs all words from the file. It has a prefix searcher and a word searcher.
* @ author  Rahat Hossan
* @ version 03/03/2017
*/	
public class Dictionary {
	
	protected ArrayList<String> dictionary = new ArrayList<String>();
	/**
	 * A Dictionary constructor and takes letters from args as a parameter.
	 * 
	 * @param f - passed by args[0], file location.
	 * @throws IllegalArgumentException - returns an error message if given file is not a proper arg.
	 * Also checks whether the file is found.
	 */
	public Dictionary( File f ) throws IllegalArgumentException {
		
		Scanner scan = null;
		try {
			File file = f;
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.print("Error: File cannot be found.");
		} catch (IllegalArgumentException e) {
			System.err.print("Error: Illegal argument entered.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.print("Error: Missing name of input file.");
		}
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			dictionary.add(line);
		}
		
		scan.close();
	}
	/*
	 * This public method calls on a helper method and asts as a wrapper method. It returns a boolean of 
	 * whether or not a word is present in the dictionary
	 * @param str - given string 
	 * @return wordSearch, helper method.
	 */
	public boolean isWord(String str) {
		return wordSearch(str, 0, dictionary.size());
	}
	/*
	 * This public method acts as a helper method. It is a recursive binary search and returns a boolean of 
	 * whether or not a word is present in the dictionary
	 * @param str - given string 
	 * @param first - location of beginning of array
	 * @param last - location of beginning of array
	 * @return boolean wether or not a word is found.
	 */
	public boolean wordSearch(String str, int first, int last) {
		int middle = (first + last)/2;
		
		if ( first >= last ) {
	    	  return false;
	      }
		
		if ( dictionary.get(middle).compareTo(str) == 0 ) {
			return true;
		}
		else if ( dictionary.get(middle).compareTo(str) < 0 ) {
			return wordSearch(str, middle + 1, last);
		}
		else {
			return wordSearch(str, first, middle - 1);    
		}
	   
	  }
	/*
	 * This public method acts as a helper method. It is a recursive binary search and returns a boolean of 
	 * whether or not a word is present in the dictionary
	 * @param str - given string 
	 * @param first - location of beginning of array
	 * @param last - location of beginning of array
	 * @return boolean wether or not a word is found.
	 */
	public boolean isPrefix(String str) {
		return prefixSearch(str, 0, dictionary.size());
	}
	/*
	 * This public method calls on a helper method and asts as a wrapper method. It returns a boolean of 
	 * whether or not a word is present in the dictionary
	 * @param str - given string 
	 * @return prefixSearch, helper method.
	 */
	public boolean prefixSearch(String str, int first, int last) {
		int middle = (first + last)/2;
		
		if ( first >= last ) {
	    	  return false;
	      }
		
		if ( dictionary.get(middle).startsWith(str)) {
			return true;
		}
		else if (!dictionary.get(middle).startsWith(str)) {
			if (dictionary.get(middle).compareTo(str) < 0 ) {
				return prefixSearch(str, middle + 1, last);
			}
			else {
				return prefixSearch(str, first, middle - 1);    
			}
		}
		return false;
	  }
	
	

}
