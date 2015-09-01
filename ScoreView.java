import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel{
	JButton backButton = new JButton("Back");
	
	public ScoreView(Player player){
		super(new BorderLayout());
		backButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.HIDE_HIGH_SCORES));
		Score[] scores = player.getMaxScores(10, Score.GameType.ARCADE);
		JTable table = createTable(scores);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
		this.add(backButton, BorderLayout.SOUTH);
	}

	public JTable createTable(Score[] scores){
		String[] columnNames = {"Rank",
		"Score"};

		Object[][] data = new Object[scores.length][2];
		for(int i=0; i<scores.length; i++){
			data[i][0]=i+1;
			if(scores[i]!=null){
			data[i][1]=scores[i].getScore();
			}else{
				data[i][1]= "-";
			}
		}

		JTable table = new JTable(data, columnNames);

		return table;

	}
}