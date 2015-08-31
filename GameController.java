import javax.swing.*;
import java.awt.*;


public class GameController{
	static BoardController boardController = new BoardController(6);
	static BoardView boardView = new BoardView(6, boardController.getBoard());
	static JFrame frame = new JFrame("Letters game");
	public static void main(String[] args){
		
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateBoard();
        frame.setVisible(true);
        updateBoard();
	}

	public static void updateBoard(){
		frame.getContentPane().add(boardView, BorderLayout.WEST);
	    frame.pack();
	}

	public static void click(int id){
		boardController.handleLetterClick(id);
	}
}