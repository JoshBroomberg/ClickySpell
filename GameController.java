import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameController{
	static BoardController boardController;
	static BoardView boardView;

	static SidebarController sidebarController;
	static SidebarView sidebarView;

	static JFrame frame = new JFrame("Letters game");
	static Score.GameType type;
	static Timer timer;
	
	public static void main(String[] args){
		new GameController();
	}

	public GameController(){
		displayGameBoard();
	}

	private static void displayGameBoard(){

		int typeChoice = Integer.parseInt(JOptionPane.showInputDialog(
			"What type of game would you like to play?\n"+
			"Enter a number from the list below:\n"+
			"1. Arcade (untimed)\n"+
			"2. 1 minute timed game "));

		int boardSize = Integer.parseInt(JOptionPane.showInputDialog(
			"How big would you like the board to be?\n"+
			"Enter a number between 2 and 9\n"+
			"2 will yield a 2x2 grid, the hardest\n"+
			"9 will yield a 9x9 grid, the easiest"));
		
		switch(typeChoice){
			case 1:
			type = Score.GameType.ARCADE;
			sidebarController = new SidebarController(false, PlayerController.getHighScore(type));
			break;

			case 2:
			type = Score.GameType.TIMED;
			timer = new Timer(1000, new TimerListener());
			sidebarController = new SidebarController(true, PlayerController.getHighScore(type));
			timer.start();
			break;
		}

		boardController = new BoardController(boardSize);
		frame.getContentPane().setLayout(new BorderLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}

	public static void updateBoard(){
		frame.getContentPane().removeAll();
		boardView = new BoardView(boardController.getBoardSize(), boardController.getBoard());
		sidebarView = new SidebarView(sidebarController.timed(),"XX", sidebarController.getScore(), sidebarController.getHighScore(), boardController.getRemainingLetters(), boardController.getWordCount());
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
			JOptionPane.showMessageDialog(frame, "Thats the end! You final score was: "+sidebarController.getScore()+" points", "info:", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(frame, "You created no words, no score recorded!", "info:", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.dispose();
		MainController.showMenu();
	}
}