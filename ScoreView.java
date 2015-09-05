import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel{
	//class used to show high scores
	private JButton backButton = new JButton("Back"); //back button
	
	public ScoreView(Player player){
		//constructor recives player as param
		super(new BorderLayout()); //create new JPanel wil border layout

		//add action listener to back button
		backButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.HIDE_HIGH_SCORES));

		//create panel for arcade scores
		JPanel arcadePanel = new JPanel();
		BoxLayout arcadeLayout = new BoxLayout(arcadePanel, BoxLayout.Y_AXIS);
		arcadePanel.setLayout(arcadeLayout);
		JLabel arcadeLabel = new JLabel("Arcade mode highscores");

		//get 10 top arcade scores for player and store in array
		Score[] arcadescores = player.getMaxScores(10, Score.GameType.ARCADE);

		//create new table using create table method and add to a scroll pane
		JTable arcadetable = createTable(arcadescores);
		JScrollPane arcadescrollPane = new JScrollPane(arcadetable);

		//add label and table to panel
		arcadePanel.add(arcadeLabel);
		arcadePanel.add(arcadescrollPane);

		//add arcade panel to main panel
		this.add(arcadePanel, BorderLayout.WEST);

		//create panel for timed scores
		JPanel timedPanel = new JPanel();
		BoxLayout timedLayout = new BoxLayout(timedPanel, BoxLayout.Y_AXIS);
		timedPanel.setLayout(timedLayout);
		JLabel timedLabel = new JLabel("Timed mode highscores");

		//get 10 top timed scores for player and store in array
		Score[] timedscores = player.getMaxScores(10, Score.GameType.TIMED);
		
		//create new table using create table method and add to a scroll pane
		JTable timedtable = createTable(timedscores);
		JScrollPane timedscrollPane = new JScrollPane(timedtable);

		//add label and table to panel
		timedPanel.add(timedLabel);
		timedPanel.add(timedscrollPane);

		//add timed panel to main panel
		this.add(timedPanel,BorderLayout.EAST);

		//add back button to main panel
		this.add(backButton, BorderLayout.SOUTH);
	}

	public JTable createTable(Score[] scores){
		//uses array of scores to return new JTable
		String[] columnNames = {"Rank",
		"Score"}; //column names array

		Object[][] data = new Object[scores.length][2]; //create a generic 2D object array with height of score array and two columns
		for(int i=0; i<scores.length; i++){
			//populate column 1 with score index ie rank
			data[i][0]=i+1;
			if(scores[i]!=null){
				//if there is a score for that rank, add it to column 2
				data[i][1]=scores[i].getScore();
			}else{
				//if there is no score for that rank yet because there are fewer than ten scores in the category
				//add a dash
				data[i][1]= "-";
			}
		}
		//create a table using the titles and data arrays
		JTable table = new JTable(data, columnNames);
		//return the table
		return table;

	}
}