import java.awt.Color;
import java.util.NoSuchElementException;

public class BoardController{
	private int boardDimension;
	private TileCollection letters = new TileCollection();
	private TileGUI[][] tiles;
	private TileGUI[] sequence;
	private int sequenceCount = 0;
	private int wordCount =0;

	static WordController wordController = new WordController();

	public BoardController(int dimension){
		//super(new GridLayout(dimension,dimension)); //create JPanel with grid layout set to dimension spec 
		boardDimension = dimension;
		tiles = new TileGUI[dimension][dimension]; //create tile 2D array with size matching dimensions
		sequence = new TileGUI[(int)Math.pow(dimension,2)]; //create array to hold selected sequence with size = total tiles possible

		//fill tiles array with letters
		int count =0;
		for(int row =0; row<dimension; row++){
			for(int column = 0; column<dimension; column++){
				TileGUI tileToInsert = new TileGUI(letters.removeOne());
				tileToInsert.setForeground(Color.black);
				tileToInsert.addActionListener(new TileClickHandler(count));
				tileToInsert.putClientProperty("id", count);
				tiles[row][column] = tileToInsert;
				//this.add(tileToInsert);
				count++;
			}	
		}
	}

	public TileGUI[][] getBoard(){
		return tiles;
	}

	public int getBoardSize(){
		return boardDimension;
	}

	

	public boolean handleLetterClick(int id){
		TileGUI clickedButton = findTile(id);
		if(isAdjacent(clickedButton)&&(!inSequence(clickedButton))){
			clickedButton.setForeground(Color.red);
			sequence[sequenceCount] = clickedButton;
			sequenceCount++;
			return true;
		}
		else if(clickedButton.equals(sequence[sequenceCount-1])){
			clickedButton.setForeground(Color.black);
			sequence[sequenceCount-1] = null;
			sequenceCount--;
			return true;
		}
		else{
			return false;

		}
	}

	public boolean validateWord(String word){
		
		if(wordController.isWord(word)){
			wordCount++;
			return true;
			
		}
		else{
			return false;
		}
	}

	public String getWordCount(){
		Integer wordCount = this.wordCount;
		return wordCount.toString();
	}

	public String getSequence(){
		String word = "";
		for(int i = 0; i<sequenceCount; i++){
			word+=sequence[i].getTile().letter();
		}
		return word;
	}

	public void clearUsedTiles(){ //needs to account for running out of tiles
		for(int i =0; i<sequenceCount; i++){
			int tileID = (int)sequence[i].getClientProperty("id");
			try{
			TileGUI newTile = new TileGUI(letters.removeOne());
			newTile.setForeground(Color.black);
			newTile.putClientProperty("id", tileID);
			newTile.addActionListener(new TileClickHandler(tileID));
			Coordinate coordinate = tileLocation(tileID);
			tiles[coordinate.getRow()][coordinate.getColumn()] = newTile;
			}
			catch(NoSuchElementException e){
				sequence[i].setEnabled(false);
				sequence[i].setText("");
			}
			sequence[i]=null;
		}
		sequenceCount=0;
	}

	public int scoreSequence(){
		int score = 0;
		for(int i =0; i<sequenceCount; i++){
			score+=sequence[i].getTile().value();
		}
		return score;
	}

	public void reset(){
		for(int i =0; i<sequenceCount; i++){
			sequence[i].setForeground(Color.black);
			sequence[i] = null;
		}
		sequenceCount =0;

	}

	public String getRemainingLetters(){
		Integer lettersRemaining = letters.size();
		return lettersRemaining.toString();
	}

	private boolean inSequence(TileGUI tile){
		for(int i =0; i<sequenceCount; i++){
			if(tile.equals(sequence[i])){
				return true;
			} 
		}
		return false;

	}


	private boolean isAdjacent(TileGUI tile){
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

			// System.out.println("start row"+startRow);
			// System.out.println("start column"+startColumn);
			// System.out.println("end row"+endRow);
			// System.out.println("end col"+endColumn);

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
		for(int row =0; row<boardDimension; row++){
			for(int column = 0; column<boardDimension; column++){
				//if(tiles[row][column]!=null&&tiles[row][column].getClientProperty("id")!=null){ 
					if((int)tiles[row][column].getClientProperty("id") == id){
						return new Coordinate(row, column);
					}
				//}
			}
		}
		System.out.print("not found");
		return null;
		
	}
}