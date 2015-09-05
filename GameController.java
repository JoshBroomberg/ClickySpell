import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameController{
	private static BoardController boardController;
	private static BoardView boardView;

	private static SidebarController sidebarController;
	private static SidebarView sidebarView;

	private static JFrame frame = new JFrame("Letters game");
	private static Score.GameType type;
	private static Timer timer;
	
	public static void main(String[] args){
		new GameController();
	}

	public GameController(){
		displayGameBoard();
	}

	private static void displayGameBoard(){
		int typeChoice =0; 
		int boardSize=0;
		boolean validType= false;
		boolean validSize =false;
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
		
		switch(typeChoice){
			case 1:
			type = Score.GameType.ARCADE;
			sidebarController = new SidebarController(false, PlayerController.getHighScore(type));
			break;

			case 2:
			type = Score.GameType.TIMED;
			timer = new Timer(1000, new TimerListener());
			sidebarController = new SidebarController(true, PlayerController.getHighScore(type));
			
			break;

			default:
			JOptionPane.showMessageDialog(frame, "Unexpected error", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}

		boardController = new BoardController(boardSize);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exit();
			}
		});
		frame.setResizable(false);
		updateBoard();
		frame.setVisible(true);

		if(typeChoice==2){
			JOptionPane.showMessageDialog(frame, "Click ok when you are ready to start", "info:", JOptionPane.INFORMATION_MESSAGE);
			timer.start();
		}
	}

	public static void updateBoard(){
		frame.getContentPane().removeAll();
		boardView = new BoardView(boardController.getBoardSize(), boardController.getBoard());
		sidebarView = new SidebarView(sidebarController.timed(),"XX", sidebarController.getScore(), sidebarController.getHighScore(), boardController.getRemainingLetters(), boardController.getWordCount(), boardController.getSequence());
		frame.getContentPane().add(boardView, BorderLayout.WEST);
		frame.getContentPane().add(sidebarView,BorderLayout.EAST);
		frame.pack();
	}

	public static void shuffle(){
		resetSelection();
		boardController.shuffle();
		updateBoard();
	}

	public static void tileClick(int id){
		boolean validClick = boardController.handleLetterClick(id);
		if(!validClick){
			JOptionPane.showMessageDialog(frame, "Invalid click", "Error", JOptionPane.ERROR_MESSAGE);
		}
		sidebarView.setSequence( boardController.getSequence());
		String word = boardController.getSequence().toLowerCase();
		if(word.length()>0){
			boolean validword = boardController.validateWord(word);
			if(validword){
				sidebarView.validWordColor();
			}else{
				sidebarView.invalidWordColor();
			}
		}
		else{
			sidebarView.resetColor();
		}
	}

	public static void tick(String secondsRemaining){
		sidebarView.setTime(secondsRemaining);
	}

	public static void checkWord(){
		String word = boardController.getSequence().toLowerCase();
		boolean validword = boardController.validateWord(word);
		if(validword){
			int score = boardController.scoreSequence();
			JOptionPane.showMessageDialog(frame, "Congrats, "+word+" is a word. \nYou scored: "+score+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
			sidebarController.incrementScore(score);
			boardController.clearUsedTiles();
			updateBoard();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Invalid word", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void resetSelection(){
		boardController.reset();
		sidebarView.setSequence("");
		sidebarView.resetColor();
	}

	private static boolean endGame(){
		int score = Integer.parseInt(sidebarController.getScore());
		if(score>0){
			PlayerController.registerNewScore(score, type);
			return true;
		}else{
			return false;
		}
	}

	public static void exit(){
		if(type==Score.GameType.TIMED){
			timer.stop();
		}
		if(endGame()){
			int oldHigh =Integer.parseInt(sidebarController.getHighScore());
			int newHigh = Integer.parseInt(sidebarController.getScore());
			if(oldHigh<newHigh){
				JOptionPane.showMessageDialog(frame, "Thats the end! You set a new highscore of "+sidebarController.getScore()+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(frame, "Thats the end! You final score was: "+sidebarController.getScore()+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(frame, "You created no words, no score recorded!", "info:", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.dispose();
		MainController.showMenu();
	}
}