import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* This class represents the ScrabblerHelper that executes the whole program. It prints out how many words have been found.
* @ author  Rahat Hossan
* @ version 03/03/2017
*/	
public class ScrabbleHelper {
	/**
	 * Prints all words found in dictionary
	 * @param args [0] - takes file path
	 * @param args [1] - takes letters
	 */
	public static void main(String[] args) {
		
		String File = null;
		String input = null;
		File dictionary = null;
		Scanner scan = null;
		
		/*
		 * Throws four exceptions depending on error.
		 * Main method runs all calls to other class methods and prints all words found. 
		 */
		try {
			
			File = args[0]; 
			input = args[1].toLowerCase();
			dictionary = new File(File);
			scan = new Scanner(dictionary);
			Dictionary words = new Dictionary(dictionary);
			Permutations perms = new Permutations(input);
			ArrayList<String> abc = new ArrayList<String>(perms.getAllWords(words));
			
			System.out.println("Found " + abc.size() + " word(s). They are: ");
			for(int i = 0; i < abc.size(); i++) {
				System.out.println(abc.get(i));
			}
			
		}catch (ArrayIndexOutOfBoundsException e) {
			System.err.print("Error: Arguments entered incorrectly");
			System.exit(1);
			
		}catch (IllegalArgumentException e) {
			System.err.print(e.getMessage());
			System.exit(1);
			
		}catch (FileNotFoundException e) {
			System.err.print("Error: File " + args[0] + " cannot be found");
			System.exit(1);
			
		}catch (OutOfMemoryError e) {
			System.err.print(e.getMessage());
			System.exit(1);
		}
		scan.close();

	}
}
