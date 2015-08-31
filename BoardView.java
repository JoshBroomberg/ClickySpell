import javax.swing.JPanel;
import java.awt.*;

public class BoardView extends JPanel{
	public BoardView(int dimension, TileGUI[][] tiles){
		super(new GridLayout(dimension,dimension));
		for(int row =0; row<dimension; row++){
			for(int column = 0; column<dimension; column++){		
				this.add(tiles[row][column]);
			}	
		}
	}
}