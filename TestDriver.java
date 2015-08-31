import javax.swing.*;
import java.awt.*;


public class TestDriver{
	static BoardPanel board = new BoardPanel(6);
	static JFrame frame = new JFrame("Letters game");
	public static void main(String[] args){
		
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateBoard();
        frame.setVisible(true);
        updateBoard();
	}

	public static void updateBoard(){
		frame.getContentPane().add(board, BorderLayout.WEST);
	    frame.pack();
	}

	public static void click(int id){
		board.handleLetterClick(id);
	}
}