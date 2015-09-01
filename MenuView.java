import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel{
	JButton playGameButton = new JButton("Play");
	JButton viewHighscoresButton = new JButton("View high scores");
	JButton logoutButton = new JButton("Log out");

	public MenuView(){
		super(new FlowLayout());
		playGameButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHOW_GAME));
		viewHighscoresButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.HIGH_SCORES));
		logoutButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.LOG_OUT));

		this.add(playGameButton);
		this.add(viewHighscoresButton);
		this.add(logoutButton);

	}
}