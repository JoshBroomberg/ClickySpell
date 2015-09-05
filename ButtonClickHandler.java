import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickHandler implements ActionListener{ 
  //class implements an action listen
  //each instance holds the action of the button it is associated with. If it clicked, it acts on this action

  //this enum stores all action by name for easy usage
	public static enum Actions{CHECK_WORD, RESET_SELECTION, SHUFFLE, LOGIN, LOG_OUT,ADD_PLAYER, SHOW_GAME, EXIT_GAME, SHOW_HIGH_SCORES, HIDE_HIGH_SCORES}
	private Actions actionID; //stores action of button attached to


  	public ButtonClickHandler(Actions actionID){
  		super(); //invoke action lister constructor
  		this.actionID = actionID; //set action to action provided
  	}

  	public void actionPerformed(ActionEvent e){ 
      //if actionperformed, check actionID and perform associated action
  		switch(actionID){
  			case LOGIN:
  				PlayerController.login();
  			break;

  			case ADD_PLAYER:
  				PlayerController.addPlayer();
  			break;

  			case SHOW_GAME:
  				MenuController.showGame();
  			break;

  			case SHOW_HIGH_SCORES:
  				MenuController.showScores();
  			break;

  			case HIDE_HIGH_SCORES:
  				PlayerController.hideScores();
  			break;

  			case LOG_OUT:
  			    MenuController.logout();
  			break;

  			case EXIT_GAME:
  				GameController.exit();
  			break;

  			case CHECK_WORD:
  				GameController.checkWord();
  			break;

  			case RESET_SELECTION:
  				GameController.resetSelection();
  			break;

  			case SHUFFLE:
  				GameController.shuffle();
  			break;

  			
  			
  		}
  	} 
}