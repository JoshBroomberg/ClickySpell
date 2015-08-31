import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileClickHandler implements ActionListener{ 
  	private int id;
  	public TileClickHandler(int id){
  		super();
  		this.id = id;
  	}

  	public void actionPerformed(ActionEvent e){ 
    	GameController.tileClick(id);
  	} 
}