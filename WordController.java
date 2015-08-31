import java.io.FileNotFoundException;
import java.io.IOException;

public class WordController{
	String[] words;
	public WordController(){
		try{
			words=FileToArray.read("EnglishWords.txt");
		}
		catch(FileNotFoundException e){

		}	
		catch(IOException e){

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