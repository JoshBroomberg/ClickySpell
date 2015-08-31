import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickHandler implements ActionListener{ 
	int actionID;
  	public ButtonClickHandler(int actionID){
  		super();
  		this.actionID = actionID;
  	}

  	public void actionPerformed(ActionEvent e){ 
  		switch(actionID){
  			case 1:
  				GameController.checkWord();
  			break;

  			case 2:
  			break;

  			case 3:
  			break;

  			case 4:
  			break;

  			case 5:
  			break;

  		}
  	} 
}