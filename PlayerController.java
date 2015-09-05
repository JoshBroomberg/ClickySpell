import javax.swing.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;


public class PlayerController{
	private static Player[] players = new Player[1000];
	private static int playerCount=0;
	private static Player activePlayer1;
	//private static Player activePlayer2;

	static JFrame loginWindow = new JFrame("Login to letters game");
	static JFrame highscoreWindow = new JFrame("Your high scores");


	public PlayerController(){
		readPlayers();
		displayLogin();
	}

	private static void displayLogin(){
		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(new EmptyBorder(50, 100, 0, 0));
		JLabel gameTitle = new JLabel("<html><strong><i>Letters Game</i></strong></html>");
		header.add(gameTitle, BorderLayout.CENTER);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("2.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		loginWindow.setContentPane(new JLabel(imageIcon));
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
		highscoreWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highscoreWindow.getContentPane().add(new ScoreView(activePlayer1));
		highscoreWindow.pack();
		highscoreWindow.setVisible(true);
	}

	public static void hideScores(){
		highscoreWindow.dispose();
	}

	public static void addPlayer(){
		String username = JOptionPane.showInputDialog(loginWindow, "Enter your desired username");
		String password = JOptionPane.showInputDialog(loginWindow, "Enter your desired password");
		if(findPlayer(username)==null){
			Player toSave = new Player(username, password);
			players[playerCount]=toSave;
			playerCount++;
			loginPlayer(toSave);
			writePlayers();
			JOptionPane.showMessageDialog(null, "Player created and logged in", "Info:", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(loginWindow, "A user with that username exists", "Error", JOptionPane.ERROR_MESSAGE);

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

		}catch(IOException e){

		}
	}
}