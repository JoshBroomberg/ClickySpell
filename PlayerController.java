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
	//class to control players, used to facilitate log in functions and display of scores
	private static Player[] players = new Player[1000]; //holds all stored players
	private static int playerCount=0; //counts players
	private static Player activePlayer1; //holds currently active player

	//create frames for log in and high scores
	private static JFrame loginWindow = new JFrame("Login to letters game");
	private static JFrame highscoreWindow = new JFrame("Your high scores");


	//on instantiation read players from file and display login
	public PlayerController(){
		readPlayers();
		displayLogin();
	}

	private static void displayLogin(){
		//sets up and display login frame for display

		//create and format panel for header text on frame
		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(new EmptyBorder(50, 100, 0, 0));
		JLabel gameTitle = new JLabel("<html><strong><i>Letters Game</i></strong></html>");
		header.add(gameTitle, BorderLayout.CENTER);

		//clear frmae and header and login view
		loginWindow.getContentPane().removeAll();
		loginWindow.getContentPane().setLayout(new BorderLayout());
		loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if closed, end program
		loginWindow.getContentPane().add(header, BorderLayout.NORTH);
		loginWindow.getContentPane().add(new LoginView(), BorderLayout.CENTER);

		//static view thus set and fix size
		loginWindow.setResizable(false);
		loginWindow.setSize(285,325);
		loginWindow.setVisible(true);
	}

	public static void displayScores(){
		//sets up and displays high score winow

		highscoreWindow.getContentPane().removeAll();
		highscoreWindow.getContentPane().setLayout(new BorderLayout());
		highscoreWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//if closed, display main menu
		highscoreWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				hideScores();
			}
		});
		//add scores panel
		highscoreWindow.getContentPane().add(new ScoreView(activePlayer1));
		highscoreWindow.pack();
		highscoreWindow.setVisible(true);
	}

	public static void hideScores(){
		//hide high scores panel and show menu via main controller
		highscoreWindow.dispose();
		MainController.showMenu();
	}

	public static void addPlayer(){
		//add a new player

		//vars to hold new login info
		String username=null;
		String password = null;

		//vars to control validity of user input
		boolean usernameSuccess = false;
		boolean passwordSuccess = false;
		boolean cancelled = false;

		while(!usernameSuccess){
			//until valid usenrmae given, ask for username
			username = JOptionPane.showInputDialog(loginWindow, "Enter your desired username");
			
			if(username!=null){
				//if cancel wasn't clicked continue

			if(username.length()!=0){
				//if username isn't left blank check if user with username exits
				if(findPlayer(username)==null){
					//if one doesn't, end loop. null indicates no user with username found
					usernameSuccess = true;
				}
				else{
					//if one does, alert user
					JOptionPane.showMessageDialog(loginWindow, "A user with that username exists", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				//if username blank, alert user
				JOptionPane.showMessageDialog(loginWindow, "Username can't be blank", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}else{
				//if cancelled, store that process cancelled and break loop
				cancelled = true;
				break;
			}
		}

		if(!cancelled){
			//if process wasn't cancelled. 
			while(!passwordSuccess){
				//while valid password not provided:

				//show password input dialog
				JPasswordField passfield = new JPasswordField(15);
				int okClicked = JOptionPane.showConfirmDialog(loginWindow, passfield, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
				
				if (okClicked == JOptionPane.OK_OPTION) {
					//if ok clicked, proceed
					password = new String(passfield.getPassword()); //get password entered
					if(password.length()!=0){
						//if length greater than zero, password valid
						passwordSuccess=true;
					}else{
						//if blank, alert user
						JOptionPane.showMessageDialog(loginWindow, "Password can't be blank", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					//if cancelled, store that process cancelled and break loop
					cancelled = true;
					break;
				}
			}
			if(!cancelled){
				//repeated because the value of cancelle dmay have changed since last if
				//if still not cancelled, save new user
				Player toSave = new Player(username, password); //create new player using partial contructor due to no scores being recorded yet
				players[playerCount]=toSave; //add to array
				playerCount++;
				loginPlayer(toSave); //log the player in
				writePlayers(); //store player in file
				//aert user to success
				JOptionPane.showMessageDialog(null, "Player created and logged in", "Info:", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public static void registerNewScore(int score, Score.GameType gameType){
		//store new score for player
		activePlayer1.addScore(score,gameType); //add score to player
		writePlayers(); //rewrite player file
	}

	public static Player findPlayer(String username){
		//find player with given username, return player if found, null if not
		for(int i =0; i<playerCount; i++){
			if(username.equalsIgnoreCase(players[i].getUsername())){
				return players[i];
			}
		}
		return null;

	}

	public static void login(){

		//vars for validating input info
		boolean usernameSuccess = false;
		boolean passwordSuccess = false;
		boolean cancelled = false;
		Player playerToLogin =null;
		
		//var for username 
		String username="";
		while(!usernameSuccess){
			//while username inputted invalid, prompt for username
			username = JOptionPane.showInputDialog(loginWindow, "Enter your username");
			if(username!=null){
				//if cancel not clicked, proceed

				if(username.length()!=0){
					//if username not left empty find player
					playerToLogin = findPlayer(username);
				}else{
					//if empty, alert user
					JOptionPane.showMessageDialog(loginWindow, "Username can't be blank", "Error", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				if(playerToLogin==null){
					//if no player found, alert user
					JOptionPane.showMessageDialog(loginWindow, "No user with that username found", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					//if player found, end loop
					usernameSuccess=true;
				}
			}else{
				//if cancel press store process cancelled and break loop
				cancelled = true;
				break;
			}
		}
		if(!cancelled){
			//if not cancelled

			while(!passwordSuccess){
				//loop while password invalid

				//display password field
				JPasswordField passfield = new JPasswordField(15);
				int okClicked = JOptionPane.showConfirmDialog(loginWindow, passfield, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
				String password = null;
				if (okClicked == JOptionPane.OK_OPTION) {
					//if cancel not clicked, get password
					password = new String(passfield.getPassword());
				}else{
					cancelled = true;
				    break;
				}

				//if password is not correct for given user, alert user and let them try again
				if(!playerToLogin.getPassword().equals(password)){
					JOptionPane.showMessageDialog(loginWindow, "Invalid password, try again", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					//else, if correct end loop
					passwordSuccess=true;

					//alert user to success
					JOptionPane.showMessageDialog(loginWindow, "Welcome, "+username, "Info:", JOptionPane.INFORMATION_MESSAGE);
			        loginPlayer(playerToLogin); //login player
				}
			}
			
		}

		
	}

	public static void logout(){
		//used to log out
		
		activePlayer1 = null;//clear active player
		displayLogin(); //redisplay log in
	}

	public static Score getHighScore(Score.GameType type){
		//returns high score of current player
		return activePlayer1.getMaxScore(type);
	}


	private static void loginPlayer(Player player){
		//makes player provided the active player
		activePlayer1 = player;
		loginWindow.dispose(); //closes login window
		MainController.showMenu(); //uses main controller to display main menu
	}

	private static void writePlayers(){
		//writes all players to player text file
		//if not file exists, this method will create one
		PrintWriter pr;
		try{
			pr = new PrintWriter(new FileWriter("players.txt"));
			for(int i = 0; i<playerCount; i++){
				pr.println(players[i].toString());
			}
			pr.close();
		}
		catch(IOException e){
			//if IO error occurs, display error and exit.
			JOptionPane.showMessageDialog(null, "Unexpected error while writing information to player file", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);

		}
	}

	private static void readPlayers(){
		//read all players currently stored in players file
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader("players.txt"));
			String line = br.readLine(); //get all info for one player
			while(line!=null){
				//loop through file until next line empty
				StringTokenizer st = new StringTokenizer(line, "#");
				//split line into major data components
				//store username and password
				String username = st.nextToken();
				String password = st.nextToken();

				//set up score variables
				Score[] scoresArr = null;
				int scoreCount=0;

				//if scores are stored for player, ie there is another major data block followed by a #
				if(st.hasMoreTokens()){
					//read all scores into one string
					String scores=st.nextToken();
					scoresArr = new Score[1000];

					StringTokenizer sv = new StringTokenizer(scores, "&"); //split string on &
					while(sv.hasMoreTokens()){
						//while there are more scores to store
						//read score value and game type and create new Score
						//then store in array of scores
						scoresArr[scoreCount] = new Score(Integer.parseInt(sv.nextToken()), Score.GameType.valueOf(sv.nextToken()));
						scoreCount++;
					}
				}
				if(scoreCount>0){
					//if scores where present, use full contructor to create plyer
					//save in array
					players[playerCount] = new Player(username, password, scoresArr, scoreCount);
					playerCount++;
				}else{
					//if only log in info found, use partial constructor
					players[playerCount] = new Player(username, password);
					playerCount++;

				}
				line = br.readLine(); //read next line
			}

		}catch(FileNotFoundException e){
			//if no file found, recover by using write method to create blank file
			writePlayers();
			//recover by creating file
		}
		catch(IOException e){
			//if IO error, end program and display alert
			JOptionPane.showMessageDialog(null, "Unexpected error while reading information to player file", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}