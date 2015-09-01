import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TimerListener implements ActionListener{
    Integer secondsRemaining = 60;

    public void actionPerformed(ActionEvent evt){
        secondsRemaining--;
        GameController.tick(secondsRemaining.toString());
        if(secondsRemaining <= 0){
            GameController.exit();
        }
    }

    



}