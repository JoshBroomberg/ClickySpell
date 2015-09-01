import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel{
	JButton loginButton = new JButton("Login");
	JButton newPlayerButton = new JButton("New player");

	public LoginView(){
		super(new FlowLayout());
		loginButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.LOGIN));
		newPlayerButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.ADD_PLAYER));
		this.add(loginButton);
		this.add(newPlayerButton);
	}
}