import javax.swing.*;
import javax.swing.*;
import java.awt.*;


public class MenuController{
	static JFrame menuWindow = new JFrame("Letters game menu");

	public MenuController(){
		displayMenu();
	}

	private static void displayMenu(){
		menuWindow.getContentPane().removeAll();
		menuWindow.getContentPane().setLayout(new BorderLayout());
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.getContentPane().add(new MenuView());
        menuWindow.pack();
        menuWindow.setVisible(true);
	}
	public static void showGame(){
		menuWindow.dispose();
		MainController.showGame();
	}

	public static void showScores(){
		menuWindow.dispose();
		MainController.showHighscores();
	}

	public static void logout(){
		menuWindow.dispose();
		MainController.showLogin();
	}

	
}