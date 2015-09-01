import javax.swing.*;
import javax.swing.*;
import java.awt.*;


public class PlayerController{
	private static Player[] players = new Player[1000];
	private static int playerCount=0;
	private static Player activePlayer1;
	private static Player activePlayer2;

	static JFrame loginWindow = new JFrame("Login to letters game");

	public PlayerController(){
		displayLogin();
	}

	private static void displayLogin(){
		loginWindow.getContentPane().removeAll();
		loginWindow.getContentPane().setLayout(new BorderLayout());
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.getContentPane().add(new LoginView());
        loginWindow.pack();
        loginWindow.setVisible(true);
	}

	public static void addPlayer(){
		String username = JOptionPane.showInputDialog(loginWindow, "Enter your desired username");
		String password = JOptionPane.showInputDialog(loginWindow, "Enter your desired password");
		if(findPlayer(username)==null){
			Player toSave = new Player(username, password);
			players[playerCount]=toSave;
			playerCount++;
			loginPlayer(toSave);
			JOptionPane.showMessageDialog(loginWindow, "Player created and logged in", "Info:", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(loginWindow, "A user with that username exists", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	public static Player findPlayer(String username){
		for(int i =0; i<playerCount; i++){
			if(username.equalsIgnoreCase(players[i].getUsername())){
				return players[i];
			}
		}
		return null;

	}

	public static void login(){ //handle errors
		String username = JOptionPane.showInputDialog(loginWindow, "Enter your username");
		String password = JOptionPane.showInputDialog(loginWindow, "Enter your password");
		Player playerToLogin = findPlayer(username);

		if(playerToLogin==null){
			JOptionPane.showMessageDialog(loginWindow, "No user with that username found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(!playerToLogin.getPassword().equals(password)){
			JOptionPane.showMessageDialog(loginWindow, "Invalid password, try again", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(loginWindow, "Welcome, "+username, "Info:", JOptionPane.INFORMATION_MESSAGE);
			loginPlayer(playerToLogin);
		}
	}

	public static void logout(){
		activePlayer1 = null;
		displayLogin();
	}


	private static void loginPlayer(Player player){
		activePlayer1 = player;
		loginWindow.dispose();
		MainController.showMenu();
	}
}