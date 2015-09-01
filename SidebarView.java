import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarView extends JPanel{
	JTextField scoreField = new JTextField();
	JButton checkWord = new JButton("Check word");
	JButton resetSelection = new JButton("Reset selection");
	public SidebarView(String score){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(200, 40));
		scoreField.setText(score);
		scoreField.setMaximumSize(new Dimension(200, 40));
		this.add(scoreField);
		this.add(Box.createRigidArea(new Dimension(200,40)));
		checkWord.addActionListener(new ButtonClickHandler(1));
		this.add(checkWord);
		this.add(Box.createRigidArea(new Dimension(200,40)));
		resetSelection.addActionListener(new ButtonClickHandler(2));
		this.add(resetSelection);
	}
}