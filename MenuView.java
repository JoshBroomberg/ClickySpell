import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MenuView extends JPanel{
	//view for main menu

	private JButton playGameButton = new JButton("Play"); //play button
	private JButton viewHighscoresButton = new JButton("View high scores"); //high scores button
	private JButton logoutButton = new JButton("Log out"); //log out button

	public MenuView(){
		super(new FlowLayout()); //create JPanel with flow layout
		this.setBorder(new EmptyBorder(25, 50, 50, 50)); //set border for formatting

		//add action listeners
		playGameButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHOW_GAME));
		viewHighscoresButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHOW_HIGH_SCORES));
		logoutButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.LOG_OUT));

		//add buttons to view
		this.add(playGameButton);
		this.add(viewHighscoresButton);
		this.add(logoutButton);

	}
}