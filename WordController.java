import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

public class WordController{
	//this class ised used to validate words

	private String[] words; //string array of all valid words
	public WordController(){
		try{
			//tries to user FileToArray to read file called EnglishWords.txt
			words=FileToArray.read("EnglishWords.txt");
		}
		catch(FileNotFoundException e){
			//if FileToArray throws a file not found exception program ends and displays prompt to supply file
			JOptionPane.showMessageDialog(null, "Word file not found, please put a text file called 'EnglishWords.txt' into the program folder", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}	
		catch(IOException e){
			//if error in readinf fiel, program ends and displays error
			JOptionPane.showMessageDialog(null, "Unexpected error reading word file", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public boolean isWord(String wordToCheck){
		//method search the array for a word ,matching that supplied and returns true if one is found
		for(String word:words){
			if(wordToCheck.equalsIgnoreCase(word)){
				return true;
			}
		}
		return false;
	}
}