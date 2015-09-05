import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class MenuController{
	//class used to control main menu

	private static JFrame menuWindow = new JFrame("Letters game menu"); //create frame

	public MenuController(){
		//on instantiate, display menu
		displayMenu();
	}

	private static void displayMenu(){
		//sets up menu frame for display

		//create panel to hold main menu title
		JPanel header = new JPanel(new BorderLayout());
	    header.setBorder(new EmptyBorder(50, 100, 0, 0));
		JLabel gameTitle = new JLabel("<html><strong><i>Main Menu</i></strong></html>");
		header.add(gameTitle, BorderLayout.CENTER);

		menuWindow.getContentPane().removeAll();
		menuWindow.getContentPane().setLayout(new BorderLayout());
        menuWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //if close clicked, log out instead of exiting
		menuWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				logout();
			}
		});

		//add header and menu view to frame
        menuWindow.getContentPane().add(header,BorderLayout.NORTH);
        menuWindow.getContentPane().add(new MenuView(),BorderLayout.CENTER);
        //set fixed size and set resizable false

        //this is a static window and doesn't need dynmaic sizing
        menuWindow.setSize(290, 275);
        menuWindow.setResizable(false);
        menuWindow.setVisible(true);
	}

	public static void showGame(){
		//if play is pressed, main controlelr used to start new game in game controller and menu closed
		MainController.showGame();
		menuWindow.dispose();
	}

	public static void showScores(){
		//if show high scores is clicked, menu closes and main controller used to show scores via player controller
		menuWindow.dispose();
		MainController.showHighscores();
	}

	// public static void hideScores(){
	// 	//if back button on high scors window is clicked, menu closes and main controller used to hide scores via player controller
	// 	displayMenu();
	// 	MainController.hideHighscores();
	// }

	public static void logout(){
		//if logout clicked, menu disposes and main controller used to log out
		menuWindow.dispose();
		MainController.showLogin();
		//user notified
		JOptionPane.showMessageDialog(null, "You have logged out", "info:", JOptionPane.INFORMATION_MESSAGE);
	}
}