import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileClickHandler implements ActionListener{
	//class used to handle presses on tiles in board

  	private int id; //each instance stores the id of the button to which it is attached
  	public TileClickHandler(int id){
  		//create new tile click handler
  		super(); //contruct action listener
  		this.id = id; //store id given in instance var
  	}

  	public void actionPerformed(ActionEvent e){ 
  		//when button clicked, use game controller to process click for given tile
    	GameController.tileClick(id);
  	} 
}