import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarView extends JPanel{
	
	JLabel statsPanelLabel = new JLabel("Game statistics:");
	JLabel scoreLabel = new JLabel("Score:");
	JTextField scoreField = new JTextField();
	JLabel wordsLabel = new JLabel("Words made:");
	JTextField wordsField = new JTextField();
	JLabel lettersLabel = new JLabel("Letters in bag:");
	JTextField lettersField = new JTextField();


	JPanel statsPanel = new JPanel(new FlowLayout());

	JButton checkWord = new JButton("Check word");
	JButton resetSelection = new JButton("Reset selection");
	JPanel gameplayPanel = new JPanel(new FlowLayout());
	public SidebarView(String score, String lettersRemaining, String wordsMade){
		super();
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200, 400));
		this.add(Box.createRigidArea(new Dimension(200,40)));


		scoreField.setText(score);
		lettersField.setText(lettersRemaining);
		wordsField.setText(wordsMade);

		statsPanel.setPreferredSize(new Dimension(200, 175));
		statsPanel.add(Box.createRigidArea(new Dimension(200,20)));
		statsPanel.add(scoreLabel);
		statsPanel.add(scoreField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,10)));
		statsPanel.add(wordsLabel);
		statsPanel.add(wordsField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,10)));
		statsPanel.add(lettersLabel);
		statsPanel.add(lettersField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,60)));

		this.add(statsPanel, BorderLayout.NORTH);

		
		gameplayPanel.setPreferredSize(new Dimension(200, 150));
		checkWord.addActionListener(new ButtonClickHandler(1));
		resetSelection.addActionListener(new ButtonClickHandler(2));
		gameplayPanel.add(checkWord);
		gameplayPanel.add(resetSelection);
		
		this.add(gameplayPanel, BorderLayout.CENTER);
	}
}