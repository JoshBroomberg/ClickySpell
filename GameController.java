import javax.swing.*;
import java.awt.*;



public class GameController{
	static BoardController boardController;
	static BoardView boardView;

	static SidebarController sidebarController;
	static SidebarView sidebarView;

	//static PlayerController playerController = new PlayerController();


	static JFrame frame = new JFrame("Letters game");
	
	public static void main(String[] args){
		new GameController();
	}

	public GameController(){
		 boardController = new BoardController(9);
		 sidebarController = new SidebarController();
		 displayGameBoard();
	}

	private static void displayGameBoard(){
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateBoard();
        frame.setVisible(true);
	}

	public static void updateBoard(){
		frame.getContentPane().removeAll();
		boardView = new BoardView(9, boardController.getBoard());
		sidebarView = new SidebarView(sidebarController.getScore(), boardController.getRemainingLetters(), boardController.getWordCount());
		frame.getContentPane().add(boardView, BorderLayout.WEST);
		frame.getContentPane().add(sidebarView,BorderLayout.EAST);
	    frame.pack();
	}

	public static void tileClick(int id){
		boolean validClick = boardController.handleLetterClick(id);
		if(!validClick){
			JOptionPane.showMessageDialog(frame, "Invalid click", "Error", JOptionPane.ERROR_MESSAGE);
		}
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

	private static void endGame(){
		int score = Integer.parseInt(sidebarController.getScore());
		PlayerController.registerNewScore(score, Score.GameType.ARCADE);
	}

	public static void exit(){
		endGame();
		frame.dispose();
		MainController.showMenu();
	}
}