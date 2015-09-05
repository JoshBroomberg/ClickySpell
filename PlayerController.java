import javax.swing.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;


public class PlayerController{
	private static Player[] players = new Player[1000];
	private static int playerCount=0;
	private static Player activePlayer1;
	//private static Player activePlayer2;

	private static JFrame loginWindow = new JFrame("Login to letters game");
	private static JFrame highscoreWindow = new JFrame("Your high scores");


	public PlayerController(){
		readPlayers();
		displayLogin();
	}

	private static void displayLogin(){
		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(new EmptyBorder(50, 100, 0, 0));
		JLabel gameTitle = new JLabel("<html><strong><i>Letters Game</i></strong></html>");
		header.add(gameTitle, BorderLayout.CENTER);

		// BufferedImage img = null;
		// try {
		// 	img = ImageIO.read(new File("2.jpeg"));
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// }
		// Image dimg = img.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
		// ImageIcon imageIcon = new ImageIcon(dimg);
		// loginWindow.setContentPane(new JLabel(imageIcon));
		loginWindow.getContentPane().removeAll();
		loginWindow.getContentPane().setLayout(new BorderLayout());
		loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginWindow.getContentPane().add(header, BorderLayout.NORTH);
		loginWindow.getContentPane().add(new LoginView(), BorderLayout.CENTER);
		loginWindow.setResizable(false);
		loginWindow.setSize(285,325);
		loginWindow.setVisible(true);
	}

	public static void displayScores(){
		highscoreWindow.getContentPane().removeAll();
		highscoreWindow.getContentPane().setLayout(new BorderLayout());
		//highscoreWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highscoreWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		highscoreWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				MenuController.hideScores();
			}
		});
		highscoreWindow.getContentPane().add(new ScoreView(activePlayer1));
		highscoreWindow.pack();
		highscoreWindow.setVisible(true);
	}

	public static void hideScores(){
		highscoreWindow.dispose();
	}

	public static void addPlayer(){
		String username=null;
		String password = null;
		boolean usernameSuccess = false;
		boolean passwordSuccess = false;
		boolean cancelled = false;

		while(!usernameSuccess){
			username = JOptionPane.showInputDialog(loginWindow, "Enter your desired username");
			if(username!=null){
			if(username.length()!=0){
				if(findPlayer(username)==null){
					usernameSuccess = true;
				}
				else{
					JOptionPane.showMessageDialog(loginWindow, "A user with that username exists", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(loginWindow, "Username can't be blank", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}else{
				cancelled = true;
				break;
			}
		}

		if(!cancelled){
			while(!passwordSuccess){
				JPasswordField passfield = new JPasswordField(15);
				int okClicked = JOptionPane.showConfirmDialog(loginWindow, passfield, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
				
				if (okClicked == JOptionPane.OK_OPTION) {
					password = new String(passfield.getPassword());
					if(password.length()!=0){
						passwordSuccess=true;
					}else{
						JOptionPane.showMessageDialog(loginWindow, "Password can't be blank", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			Player toSave = new Player(username, password);
			players[playerCount]=toSave;
			playerCount++;
			loginPlayer(toSave);
			writePlayers();
			JOptionPane.showMessageDialog(null, "Player created and logged in", "Info:", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void registerNewScore(int score, Score.GameType gameType){
		activePlayer1.addScore(score,gameType);
		writePlayers();
	}

	public static Player findPlayer(String username){
		for(int i =0; i<playerCount; i++){
			if(username.equalsIgnoreCase(players[i].getUsername())){
				return players[i];
			}
		}
		return null;

	}

	public static void login(){
		boolean usernameSuccess = false;
		boolean passwordSuccess = false;
		boolean cancelled = false;
		Player playerToLogin =null; 
		String username="";//handle errors
		while(!usernameSuccess){
			username = JOptionPane.showInputDialog(loginWindow, "Enter your username");
			if(username!=null){
				if(username.length()!=0){
					playerToLogin = findPlayer(username);
				}else{
					JOptionPane.showMessageDialog(loginWindow, "Username can't be blank", "Error", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				if(playerToLogin==null){
					JOptionPane.showMessageDialog(loginWindow, "No user with that username found", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					usernameSuccess=true;
				}
			}else{
				cancelled = true;
				break;
			}
		}
		if(!cancelled){
			while(!passwordSuccess){
				JPasswordField passfield = new JPasswordField(15);
				int okClicked = JOptionPane.showConfirmDialog(loginWindow, passfield, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
				String password = null;
				if (okClicked == JOptionPane.OK_OPTION) {
					password = new String(passfield.getPassword());
				}

				if(!playerToLogin.getPassword().equals(password)){
					JOptionPane.showMessageDialog(loginWindow, "Invalid password, try again", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					passwordSuccess=true;
				}
			}
			JOptionPane.showMessageDialog(loginWindow, "Welcome, "+username, "Info:", JOptionPane.INFORMATION_MESSAGE);
			loginPlayer(playerToLogin);
		}

		
	}

	public static void logout(){
		activePlayer1 = null;
		displayLogin();
	}

	public static Score getHighScore(Score.GameType type){
		return activePlayer1.getMaxScore(type);
	}


	private static void loginPlayer(Player player){
		activePlayer1 = player;
		loginWindow.dispose();
		MainController.showMenu();
	}

	private static void writePlayers(){
		PrintWriter pr;
		try{
			pr = new PrintWriter(new FileWriter("players.txt"));
			for(int i = 0; i<playerCount; i++){
				pr.println(players[i].toString());
			}
			pr.close();
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null, "Unexpected error while writing information to player file", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);

		}
	}

	private static void readPlayers(){
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader("players.txt"));
			String line = br.readLine();
			while(line!=null){
				StringTokenizer st = new StringTokenizer(line, "#");
				String username = st.nextToken();
				String password = st.nextToken();
				Score[] scoresArr = null;
				int scoreCount=0;
				if(st.hasMoreTokens()){
					String scores=st.nextToken();
					scoresArr = new Score[1000];
					StringTokenizer sv = new StringTokenizer(scores, "&");
					while(sv.hasMoreTokens()){
						scoresArr[scoreCount] = new Score(Integer.parseInt(sv.nextToken()), Score.GameType.valueOf(sv.nextToken()));
						scoreCount++;
					}
				}
				if(scoreCount>0){
					players[playerCount] = new Player(username, password, scoresArr, scoreCount);
					playerCount++;
				}else{
					players[playerCount] = new Player(username, password);
					playerCount++;

				}
				line = br.readLine();
			}

		}catch(FileNotFoundException e){
			writePlayers();
			//recover by creating file
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null, "Unexpected error while reading information to player file", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}