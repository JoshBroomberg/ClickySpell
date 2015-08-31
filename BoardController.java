import java.awt.Color;

public class BoardController{
	private int boardDimension;
	private TileCollection letters = new TileCollection();
	private TileGUI[][] tiles;
	private TileGUI[] sequence;
	private int sequenceCount = 0;


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
				tileToInsert.addActionListener(new ClickHandler(count));
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

	public String getSequence(){
		String word = "";
		for(TileGUI tile:sequence){
			word+=tile.getTile().letter();
		}
		return word;
	}

	public void handleLetterClick(int id){
		TileGUI clickedButton = findTile(id);
		if(isAdjacent(clickedButton)){
			clickedButton.setForeground(Color.red);
			sequence[sequenceCount] = clickedButton;
			sequenceCount++;
			System.out.print(getSequence());
		}
		else{
			System.out.print("ERROR");

		}
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
				if((int)tiles[row][column].getClientProperty("id") == id){
					return new Coordinate(row, column);
				}
			}
		}
	
		return null;
		
	}
}