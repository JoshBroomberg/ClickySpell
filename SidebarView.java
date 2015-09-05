import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarView extends JPanel{
	
	//create panel for the stats and information
	private JPanel statsPanel = new JPanel(new FlowLayout());

	//create fields and labels for all information to be displayed
	private JLabel statsPanelLabel = new JLabel("Game statistics:");
	private JLabel scoreLabel = new JLabel("Score:");
	private JTextField scoreField = new JTextField();
	private JLabel highScoreLabel = new JLabel("High score:");
	private JTextField highScoreField = new JTextField();
	private JLabel wordsLabel = new JLabel("Words made:");
	private JTextField wordsField = new JTextField();
	private JLabel lettersLabel = new JLabel("Letters in bag:");
	private JTextField lettersField = new JTextField();
	private JLabel timeLabel = new JLabel("Time remaining");
	private JTextField timeField = new JTextField();
	private JLabel sequenceLabel = new JLabel("Current sequence:");
	private JTextField sequenceField = new JTextField();
	
	//create panel for game play buttons
    private JPanel gameplayPanel = new JPanel(new FlowLayout());

    //create all cgameplay buttons
	private JButton checkWord = new JButton("submit word");
	private JButton resetSelection = new JButton("Reset selection");
	private JButton shuffle = new JButton("Shuffle letters");
	private JButton endGame = new JButton("Exit and end game");
	

	public SidebarView(boolean timed, String timeRemaining, String score, String highScore, String lettersRemaining, String wordsMade, String sequence){
		super(); //insatntiate new Jpanel
		//set formatting options
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200, 500));

		//add box for spacing
		this.add(Box.createRigidArea(new Dimension(200,10)));

		gameplayPanel.setPreferredSize(new Dimension(200, 300)); //set formatting for gameplayPanel

		//add action listeners to all buttons
		checkWord.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.CHECK_WORD));
		resetSelection.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.RESET_SELECTION));
		endGame.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.EXIT_GAME));
		shuffle.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.SHUFFLE));

		//add all buttons to gameplay panel
		gameplayPanel.add(checkWord);
		gameplayPanel.add(shuffle);
		gameplayPanel.add(resetSelection);
		gameplayPanel.add(endGame);

		//add game play panel to side bar panel
		this.add(gameplayPanel, BorderLayout.CENTER);

		statsPanel.setPreferredSize(new Dimension(200, 350)); //format stats/info panel

		//populate all info fields with data provided in params
		scoreField.setText(score);
		lettersField.setText(lettersRemaining);
		wordsField.setText(wordsMade);
		highScoreField.setText(highScore);
		sequenceField.setText(sequence);

		//add all info common fields and labels to the stats panel with boxes for spacing
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
		statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
		statsPanel.add(sequenceField);
		if(timed){
			//if the game is timed, then add the fields related to timed games
			timeField.setText(timeRemaining); //first set the txt in the time remaining field
			statsPanel.add(Box.createRigidArea(new Dimension(200,5)));
			statsPanel.add(timeLabel);
			statsPanel.add(timeField);
		}
		statsPanel.add(Box.createRigidArea(new Dimension(200,10)));

		//add stats panel to side bar panel
		this.add(statsPanel, BorderLayout.NORTH);
		
	}

	public void setTime(String secondsRemaining){
		//allow the time field to be set to a given value
		timeField.setText(secondsRemaining);
	}
	public void setSequence(String sequence){
		//allows sequence field to be set to given sequence
		sequenceField.setText(sequence);
	}
	public void validWordColor(){
		//sets sequence field to valid color ie green
		sequenceField.setBackground(Color.green);
	}
	public void invalidWordColor(){
		//sets sequence field to invalid color ie red
		sequenceField.setBackground(Color.red);
	}
	public void resetColor(){
		//sets sequence field to original color ie white
		sequenceField.setBackground(Color.white);
	}
}