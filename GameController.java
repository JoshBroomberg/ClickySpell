import javax.swing.*;
import java.awt.*;



public class GameController{
	static BoardController boardController = new BoardController(6);
	static BoardView boardView;

	static SidebarController sidebarController= new SidebarController();
	static SidebarView sidebarView= new SidebarView();


	static JFrame frame = new JFrame("Letters game");
	public static void main(String[] args){
		new GameController();
		
	}

	public GameController(){
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateBoard();
        frame.setVisible(true);
	}

	public static void updateBoard(){
		frame.getContentPane().removeAll();
		boardView = new BoardView(6, boardController.getBoard());
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
			JOptionPane.showMessageDialog(frame, "Congrats, "+word+" is a word", "info:", JOptionPane.INFORMATION_MESSAGE);
			updateBoard();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Invalid word", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}