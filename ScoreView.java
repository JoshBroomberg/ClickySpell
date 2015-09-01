import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel{
	JButton backButton = new JButton("Back");
	
	public ScoreView(Player player){
		super(new BorderLayout());
		backButton.addActionListener(new ButtonClickHandler(ButtonClickHandler.Actions.HIDE_HIGH_SCORES));
		

		JPanel arcadePanel = new JPanel();
		BoxLayout arcadeLayout = new BoxLayout(arcadePanel, BoxLayout.Y_AXIS);
		arcadePanel.setLayout(arcadeLayout);
		JLabel arcadeLabel = new JLabel("Arcade mode highscores");

		Score[] arcadescores = player.getMaxScores(10, Score.GameType.ARCADE);
		JTable arcadetable = createTable(arcadescores);
		JScrollPane arcadescrollPane = new JScrollPane(arcadetable);
		arcadePanel.add(arcadeLabel);
		arcadePanel.add(arcadescrollPane);
		this.add(arcadePanel, BorderLayout.WEST);

		JPanel timedPanel = new JPanel();
		BoxLayout timedLayout = new BoxLayout(timedPanel, BoxLayout.Y_AXIS);
		timedPanel.setLayout(timedLayout);
		JLabel timedLabel = new JLabel("Timed mode highscores");

		Score[] timedscores = player.getMaxScores(10, Score.GameType.TIMED);
		JTable timedtable = createTable(timedscores);
		JScrollPane timedscrollPane = new JScrollPane(timedtable);
		timedPanel.add(timedLabel);
		timedPanel.add(timedscrollPane);
		this.add(timedPanel,BorderLayout.EAST);

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