import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class LoginView extends JPanel{
	private JButton loginButton = new JButton("Login");
	private JButton newPlayerButton = new JButton("New player");
	

	public LoginView(){
		super(new FlowLayout());
		//super();
		//BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		//this.setLayout(layout);
		this.setBorder(new EmptyBorder(50, 50, 50, 50));
		loginButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.LOGIN));
		newPlayerButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.ADD_PLAYER));
		this.add(loginButton);
		this.add(newPlayerButton);
	}
}