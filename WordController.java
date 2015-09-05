import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

public class WordController{
	private String[] words;
	public WordController(){
		try{
			words=FileToArray.read("EnglishWords.txt");
		}
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Word file not found, please put a text file called 'EnglishWords.txt' into the program folder", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}	
		catch(IOException e){
			JOptionPane.showMessageDialog(null, "Unexpected error reading word file", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public boolean isWord(String wordToCheck){
		for(String word:words){
			if(wordToCheck.equalsIgnoreCase(word)){
				return true;
			}
		}
		return false;
	}
}