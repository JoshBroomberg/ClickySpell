import java.awt.Color;
import java.util.NoSuchElementException;
import java.util.*;
import javax.swing.*;

public class BoardController{
	private int boardDimension; //stores dimension of board
	private TileCollection letters = new TileCollection(); //'bag of letters', the source of new tiles
	private TileGUI[][] tiles; //the 2D array holding active tiles
	private TileGUI[] sequence; //the array holding the currently selected sequence
	private int sequenceCount = 0; //number of tile sin sequence
	private int wordCount =0; //number of words made

	static WordController wordController = new WordController(); //instance of word controller for validation

	public BoardController(int dimension){
		 //constructor that instantiates board controller

		boardDimension = dimension;
		tiles = new TileGUI[dimension][dimension]; //intialise the tile 2D array with size matching dimensions
		sequence = new TileGUI[(int)Math.pow(dimension,2)]; //create array to hold selected sequence with size = total tiles possible

		//fill tiles array with letters
		int count =0;
		for(int row =0; row<dimension; row++){
			for(int column = 0; column<dimension; column++){
				//instantiate and set up the tile to add
				TileGUI tileToInsert = new TileGUI(letters.removeOne());
				tileToInsert.setForeground(Color.black);
				tileToInsert.addActionListener(new TileClickHandler(count)); //add an action listener which stores the buttons id/postion
				tileToInsert.putClientProperty("id", count); //attached the id to the button
				tiles[row][column] = tileToInsert; //add tile to array
				count++;
			}	
		}
	}

	public TileGUI[][] getBoard(){
		//return board tiles
		return tiles;
	}

	public int getBoardSize(){
		//return board size
		return boardDimension;
	}

	public void shuffle(){
		//shuffle the board's tiles
		Collections.shuffle(Arrays.asList(tiles));
	}
	

	public boolean handleLetterClick(int id){
		//method to hand a button click

		TileGUI clickedButton = findTile(id); //find button click based on id provided
		if(isAdjacent(clickedButton)&&(!inSequence(clickedButton))){
			//check for valid click based on button adjacent to previous tile and not in sequence
			//if valid, format and add to sequence
			clickedButton.setForeground(Color.red);
			sequence[sequenceCount] = clickedButton; //add to sequence
			sequenceCount++;
			if(sequenceCount>1){
				//disable the 2nd last button ini sequence if it exists
				sequence[sequenceCount-2].setEnabled(false);
			}
			//return true if click valid
			return true;
		}
		else if(clickedButton.equals(sequence[sequenceCount-1])){
			//check for valid click if button clicked was most recent one selected
			clickedButton.setForeground(Color.black);
			sequence[sequenceCount-1] = null; //remove from sequence
			sequenceCount--;
			if(sequenceCount>=1){
				//re enable button selected prior to the one being unselected
				sequence[sequenceCount-1].setEnabled(true);
			}
			return true; //return true for valid click
		}
		else{
			return false; //return false because click invalid

		}
	}

	public boolean validateWord(String word){
		//called when user submits word
		//return true if word is valid
		if(wordController.isWord(word)){
			wordCount++; //increment count of words successfully created
			return true; //return true
			
		}
		else{
			return false;
		}
	}

	public String getWordCount(){
		//return word count as string
		Integer wordCount = this.wordCount;
		return wordCount.toString();
	}

	public String getSequence(){
		//return selected sequence as word
		String word = "";
		for(int i = 0; i<sequenceCount; i++){
			word+=sequence[i].getTile().letter();
		}
		return word;
	}

	public void clearUsedTiles(){
		//clears tiles currently in sequence if they have been used to make word
		for(int i =0; i<sequenceCount; i++){
			int tileID = (int)sequence[i].getClientProperty("id"); //get id of tile being removed
			try{
				//tries to get new tile from bag of letters
			TileGUI newTile = new TileGUI(letters.removeOne()); 
			newTile.setForeground(Color.black); //format tile
			newTile.putClientProperty("id", tileID); //store id from old button in new button
			newTile.addActionListener(new TileClickHandler(tileID)); //add action listener which stores id of button
			Coordinate coordinate = tileLocation(tileID); //find co-ordinate of tile being cleared
			tiles[coordinate.getRow()][coordinate.getColumn()] = newTile; //put new tile in co ords
			}
			catch(NoSuchElementException e){
				//if no tiles left in bag disabled and clear tile
				sequence[i].setEnabled(false);
				sequence[i].setText("");
			}
			sequence[i]=null; //remove tile from sequence
		}
		sequenceCount=0;
	}

	public int scoreSequence(){
		//get score for sequence currently selected. 
		int score = 0;
		for(int i =0; i<sequenceCount; i++){
			score+=sequence[i].getTile().value(); //add tile value
			if(i<=4){
				//if tile is tile 1-5 in word, add 1. 
				score+=1;
			}else{
				//if tile is greater than 5th, add 2
				score+=2;
			}
		}
		return score;
	}

	public void reset(){
		//empties selection and reformats all buttons previously selected. 
		for(int i =0; i<sequenceCount; i++){
			sequence[i].setForeground(Color.black);
			sequence[i].setEnabled(true);
			sequence[i] = null;
		}
		sequenceCount =0;

	}

	public String getRemainingLetters(){
		//return umber of letters left in bag
		Integer lettersRemaining = letters.size();
		return lettersRemaining.toString();
	}

	private boolean inSequence(TileGUI tile){
		//returns true if button is already in selected sequence
		for(int i =0; i<sequenceCount; i++){
			if(tile.equals(sequence[i])){
				return true;
			} 
		}
		return false;

	}


	private boolean isAdjacent(TileGUI tile){
		//returns true if tile selected is first tile or is adjacent to most recently selected tile. 
		if(sequenceCount == 0){
			return true;
		}
		else{
			int id = (int)sequence[sequenceCount-1].getClientProperty("id");
			Coordinate tileLocation = tileLocation(id);

			int startRow = tileLocation.getRow()-1>0?tileLocation.getRow()-1:0;
			int startColumn = tileLocation.getColumn()-1>0?tileLocation.getColumn()-1:0;

			int endRow = tileLocation.getRow()+1<boardDimension?tileLocation.getRow()+1:boardDimension-1;
			int endColumn = tileLocation.getColumn()+1<boardDimension?tileLocation.getColumn()+1:boardDimension-1;

			for(int row = startRow; row<=endRow; row++){
				for(int column = startColumn; column<=endColumn; column++){
					if(tile.equals(tiles[row][column])){
						return true;
					}
				}	
			}
			return false;
		}

	}

	private TileGUI findTile(int id){
		//returns tile from board based on id
		for(int row =0; row<boardDimension; row++){
			for(int column = 0; column<boardDimension; column++){
				if((int)tiles[row][column].getClientProperty("id") == id){
					return tiles[row][column];
				}
			}
		}
	
		return null;
	}

	private Coordinate tileLocation(int id){

		//returns tile location based on id
		for(int row =0; row<boardDimension; row++){
			for(int column = 0; column<boardDimension; column++){
					if((int)tiles[row][column].getClientProperty("id") == id){
						return new Coordinate(row, column);
					}
			}
		}
		//program ends if tile can't be found
		JOptionPane.showMessageDialog(null, "Unexpected error", "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
		return null; //could use null to handle this elsewhere

		
	}
}