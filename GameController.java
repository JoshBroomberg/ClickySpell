import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameController{
	private static BoardController boardController; //variable will hold board control
	private static BoardView boardView; //variable will hold board panel

	private static SidebarController sidebarController; //var will hold sidebar controller
	private static SidebarView sidebarView; //var will hold side bar view

	private static JFrame frame = new JFrame("Letters game"); //create game play frame
	private static Score.GameType type; //var will hold type of game arcade/timed
	private static Timer timer; //will hold timer object
	
	// public static void main(String[] args){
	// 	new GameController(); //
	// }

	public GameController(){
		//instantiate a new game controller calling the display board action
		displayGameBoard();
	}

	private static void displayGameBoard(){
		//show new game board

		//set up vars to get user input on game setup
		int typeChoice =0; 
		int boardSize=0;
		boolean validType= false;
		boolean validSize =false;

		//ask user for game type
		while(!validType){
			String type = JOptionPane.showInputDialog(
				"What type of game would you like to play?\n"+
				"Enter a number from the list below:\n"+
				"1. Arcade (untimed)\n"+
				"2. 1 minute timed game ");
			if(Character.isDigit(type.charAt(0))&&type.length()==1){
				typeChoice = Integer.parseInt(type);
				if(typeChoice==1||typeChoice==2){
					validType=true;
				}
				else{
					JOptionPane.showMessageDialog(frame, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(frame, "Invalid input, not a valid number", "Error", JOptionPane.ERROR_MESSAGE);
			}

			
			
		}

		//ask user for baord dimension
		while(!validSize){
			String size = JOptionPane.showInputDialog(
				"How big would you like the board to be?\n"+
				"Enter a number between 3 and 9\n"+
				"3 will yield a 3x3 grid, the hardest\n"+
				"9 will yield a 9x9 grid, the easiest");
			if(Character.isDigit(size.charAt(0))&&size.length()==1){
				boardSize = Integer.parseInt(size);
				if(boardSize>=3&&boardSize<=9){
					validSize=true;
				}
				else{
					JOptionPane.showMessageDialog(frame, "Invalid size", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(frame, "Invalid input, not a valid number", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}

		//STRUCTURAL NOTE:
		//The game controller provides the panel controllers the relavent info once, from then on, they handle the passing of
		//this information to the views
		
		//set up sidebar controller depending on game type choice
		switch(typeChoice){
			case 1:
			type = Score.GameType.ARCADE; //store type chosen
			sidebarController = new SidebarController(false, PlayerController.getHighScore(type)); //create sidebar controller to match
			break;

			case 2:
			type = Score.GameType.TIMED; //store type

			//create new timer object
			timer = new Timer(1000, new TimerListener()); //new timer listener added to timer, see class for details

			sidebarController = new SidebarController(true, PlayerController.getHighScore(type)); //setup sidebar controller to match
			break;

			default:
			JOptionPane.showMessageDialog(frame, "Unexpected error", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}

		boardController = new BoardController(boardSize); //create new board controller based on size provided
		
		//setup display fram
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//take specific exit action if closed
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exit();
			}
		});
		frame.setResizable(false);
		updateBoard(); //run update board method to populate view panels
		frame.setVisible(true);

		if(typeChoice==2){
			//for timed game, ask if user ready before starting timer
			JOptionPane.showMessageDialog(frame, "Click ok when you are ready to start", "info:", JOptionPane.INFORMATION_MESSAGE);
			timer.start();
		}
	}

	public static void updateBoard(){
		//used to create/populate view panels. Called when new data must be displayed
		frame.getContentPane().removeAll();
		boardView = boardController.getBoardView(); //get board view from controller

		//get side bar view from controller supplying params that need to be access from baordController
		sidebarView = sidebarController.getSidebarView(boardController.getRemainingLetters(), boardController.getWordCount(), boardController.getSequence());
		frame.getContentPane().add(boardView, BorderLayout.WEST);
		frame.getContentPane().add(sidebarView,BorderLayout.EAST);
		frame.pack();
	}

	public static void shuffle(){
		//method used to shuffle letters
		resetSelection(); //resets any active selection
		boardController.shuffle(); //shuffles actual tiles
		updateBoard(); //updates board
	}

	public static void tileClick(int id){
		//method run when tile click detected
		boolean validClick = boardController.handleLetterClick(id); //check if valid click
		if(!validClick){
			//display error if invalid
			JOptionPane.showMessageDialog(frame, "Invalid click", "Error", JOptionPane.ERROR_MESSAGE);
		}

		
		String word = boardController.getSequence().toLowerCase(); //gets current selection
		sidebarView.setSequence(word); //set sequence display to reflect selection
		if(word.length()>0){
			//if selection has letters check if word is valid and set sequence display to reflect validity
			WordController wordController = new WordController();
			boolean validword = wordController.isWord(word);
			if(validword){
				sidebarView.validWordColor();
			}else{
				sidebarView.invalidWordColor();
			}
		}
		else{
			//if no letters, reset colour indicator to white
			sidebarView.resetColor();
		}
	}

	public static void tick(String secondsRemaining){
		//method used to decrement time left when timer ticks
		sidebarView.setTime(secondsRemaining);
	}

	public static void checkWord(){
		//method used when word is submitted
		String word = boardController.getSequence().toLowerCase();
		boolean validword = boardController.validateWord(word);
		if(validword){
			//if word valid
			int score = boardController.scoreSequence(); //score word
			//alert user to success
			JOptionPane.showMessageDialog(frame, "Congrats, "+word+" is a word. \nYou scored: "+score+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
			sidebarController.incrementScore(score); //increment score
			boardController.clearUsedTiles(); //clear used tiles
			updateBoard(); //update baord display
		}
		else{
			//show error for invalid word
			JOptionPane.showMessageDialog(frame, "Invalid word", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void resetSelection(){
		//clear selection sequence
		boardController.reset(); //reset actual sequence
		sidebarView.setSequence(""); //reset sequence display
		sidebarView.resetColor();
	}

	private static boolean endGame(){
		//ends game recording score if any achieved, return true if score recorded, false if not
		//get current score
		int score = Integer.parseInt(sidebarController.getScore());
		if(score>0){
			//if any words actually created, record new score for active player
			PlayerController.registerNewScore(score, type);
			return true;
		}else{
			return false;
		}
	}

	public static void exit(){
		//method used when game is quit
		if(type==Score.GameType.TIMED){
			timer.stop(); //stop timer
		}
		if(endGame()){
			//if score was recorded, ie endGame return true check if it was a highscore
			int oldHigh =Integer.parseInt(sidebarController.getHighScore());
			int newHigh = Integer.parseInt(sidebarController.getScore());
			if(oldHigh<newHigh){
				//alert user to achievement of new high score
				JOptionPane.showMessageDialog(frame, "Thats the end! You set a new highscore of "+sidebarController.getScore()+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
			}else{
				//alert user to end of game
				JOptionPane.showMessageDialog(frame, "Thats the end! You final score was: "+sidebarController.getScore()+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
			}
		}else{
			//alert user that score of 0 wasnt saved
			JOptionPane.showMessageDialog(frame, "You created no words, no score recorded!", "info:", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.dispose(); //close frame
		MainController.showMenu(); //shwo main menu
	}
}