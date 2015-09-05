import javax.swing.JPanel;
import java.awt.*;

public class BoardView extends JPanel{
	//create an extended JPanel
	public BoardView(int dimension, TileGUI[][] tiles){
		super(new GridLayout(dimension,dimension)); //set layout to be grid set to dimension
		for(int row =0; row<dimension; row++){
			for(int column = 0; column<dimension; column++){
			//add tiles to grid		
				this.add(tiles[row][column]);
			}	
		}
	}

	
}