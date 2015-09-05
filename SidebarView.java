import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarView extends JPanel{
	
	JLabel statsPanelLabel = new JLabel("Game statistics:");
	JLabel scoreLabel = new JLabel("Score:");
	JTextField scoreField = new JTextField();
	JLabel highScoreLabel = new JLabel("High score:");
	JTextField highScoreField = new JTextField();
	JLabel wordsLabel = new JLabel("Words made:");
	JTextField wordsField = new JTextField();
	JLabel lettersLabel = new JLabel("Letters in bag:");
	JTextField lettersField = new JTextField();
	JLabel timeLabel = new JLabel("Time remaining");
	JTextField timeField = new JTextField();
	JLabel sequenceLabel = new JLabel("Current sequence");
	JTextField sequenceField = new JTextField();


	JPanel statsPanel = new JPanel(new FlowLayout());

	JButton checkWord = new JButton("Check word");
	JButton resetSelection = new JButton("Reset selection");
	JButton shuffle = new JButton("Shuffle letters");
	JButton endGame = new JButton("Exit and end game");
	JPanel gameplayPanel = new JPanel(new FlowLayout());

	public SidebarView(boolean timed, String timeRemaining, String score, String highScore, String lettersRemaining, String wordsMade, String sequence){
		super();
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200, 400));
		this.add(Box.createRigidArea(new Dimension(200,10)));
		gameplayPanel.setPreferredSize(new Dimension(200, 400));
		checkWord.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.CHECK_WORD));
		resetSelection.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.RESET_SELECTION));
		endGame.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.EXIT_GAME));
		shuffle.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHUFFLE));
		gameplayPanel.add(checkWord);
		gameplayPanel.add(shuffle);
		gameplayPanel.add(resetSelection);
		gameplayPanel.add(endGame);

		
		this.add(gameplayPanel, BorderLayout.CENTER);

		scoreField.setText(score);
		lettersField.setText(lettersRemaining);
		wordsField.setText(wordsMade);
		highScoreField.setText(highScore);
		sequenceField.setText(sequence);

		statsPanel.setPreferredSize(new Dimension(200, 230));
		statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
		statsPanel.add(scoreLabel);
		statsPanel.add(scoreField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
		statsPanel.add(highScoreLabel);
		statsPanel.add(highScoreField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
		statsPanel.add(wordsLabel);
		statsPanel.add(wordsField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
		statsPanel.add(lettersLabel);
		statsPanel.add(lettersField);
		statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
		statsPanel.add(sequenceLabel);
		statsPanel.add(sequenceField);
		if(timed){
			timeField.setText(timeRemaining);
			statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
			statsPanel.add(timeLabel);
			statsPanel.add(timeField);
		}
		statsPanel.add(Box.createRigidArea(new Dimension(200,10)));
		this.add(statsPanel, BorderLayout.NORTH);

		
		
	}

	public void setTime(String secondsRemaining){
		timeField.setText(secondsRemaining);
	}
}