public class Coordinate{
	//specialised object to storel ocation info of tile on board
	private int row; //stores row
	private int column; //stores column

	public Coordinate(int row, int column){
		this.row = row;
		this.column = column;
	}

	public int getRow(){
		//returns row
		return row;
	}

	public int getColumn(){
		//returns column
		return column;
	}
}