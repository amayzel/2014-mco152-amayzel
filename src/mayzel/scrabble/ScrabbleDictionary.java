package mayzel.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleDictionary {
	private Set<String> dictionary;  
	
	public ScrabbleDictionary(){
		try{
			Scanner file = new Scanner (new File("./OWL.txt")); 
			dictionary = new HashSet<String>();
			while(file.hasNext()){
				dictionary.add(file.next());
				file.nextLine();
			}
			file.close();
		}
		catch(FileNotFoundException notFound){
			System.out.println("ERROR! The file you have requested can not be found!");
		}	
	
	}	
	public boolean contains(String word){
		if(word==null){
			return false;
		}
		String upperCaseWord = word.toUpperCase();
		return dictionary.contains(upperCaseWord);
		}
	
}

