import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TimerListener implements ActionListener{
	//This class is a special action listen attached to timer object, when timer ticks, it handles
	//necessary actions

    private Integer secondsRemaining = 60; //listener stores time remaining, starting at 60 seconds

    public void actionPerformed(ActionEvent evt){
    	//when time ticks
        secondsRemaining--; //seconds remaining is decrmented by one
        GameController.tick(secondsRemaining.toString()); //Game controller tick method is called and supplied with seconds remaining
        //this updates the game view

        //if time runs out, run the exit method of game controller to end the game
        if(secondsRemaining <= 0){
            GameController.exit();
        }
    }

    



}