import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class MenuController{
	private static JFrame menuWindow = new JFrame("Letters game menu");

	public MenuController(){
		displayMenu();
	}

	private static void displayMenu(){
		JPanel header = new JPanel(new BorderLayout());
	    header.setBorder(new EmptyBorder(50, 100, 0, 0));
		JLabel gameTitle = new JLabel("<html><strong><i>Main Menu</i></strong></html>");
		header.add(gameTitle, BorderLayout.CENTER);

		menuWindow.getContentPane().removeAll();
		menuWindow.getContentPane().setLayout(new BorderLayout());
        //menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		menuWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				logout();
			}
		});
        menuWindow.getContentPane().add(header,BorderLayout.NORTH);
        menuWindow.getContentPane().add(new MenuView(),BorderLayout.CENTER);
        menuWindow.setSize(290, 275);
        menuWindow.setResizable(false);
        menuWindow.setVisible(true);
	}
	public static void showGame(){
		
		MainController.showGame();
		menuWindow.dispose();
	}

	public static void showScores(){
		menuWindow.dispose();
		MainController.showHighscores();
	}

	public static void hideScores(){
		displayMenu();
		MainController.hideHighscores();
	}

	public static void logout(){
		menuWindow.dispose();
		MainController.showLogin();
		JOptionPane.showMessageDialog(null, "You have logged out", "info:", JOptionPane.INFORMATION_MESSAGE);
	}
}