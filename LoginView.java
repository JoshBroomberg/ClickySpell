import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class LoginView extends JPanel{
	//panel used for login menu
	private JButton loginButton = new JButton("Login"); //button to log in
	private JButton newPlayerButton = new JButton("New player"); //button to reg new player
	

	public LoginView(){
		super(new FlowLayout()); //create new flow layout panel
		this.setBorder(new EmptyBorder(50, 50, 50, 50)); //set border for formatting
		//add action listeners to buttons
		loginButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.LOGIN));
		newPlayerButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.ADD_PLAYER));

		//add buttons to panel
		this.add(loginButton);
		this.add(newPlayerButton);
	}
}