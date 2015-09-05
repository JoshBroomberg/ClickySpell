import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MenuView extends JPanel{
	JButton playGameButton = new JButton("Play");
	JButton viewHighscoresButton = new JButton("View high scores");
	JButton logoutButton = new JButton("Log out");

	public MenuView(){
		super(new FlowLayout());
		this.setBorder(new EmptyBorder(25, 50, 50, 50));

		playGameButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHOW_GAME));
		viewHighscoresButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHOW_HIGH_SCORES));
		logoutButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.LOG_OUT));

		this.add(playGameButton);
		this.add(viewHighscoresButton);
		this.add(logoutButton);

	}
}