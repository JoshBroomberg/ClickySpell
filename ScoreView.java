import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel{
	JButton loginButton = new JButton("Back");
	
	public ScoreView(Player player){
		super(new BorderLayout());
		JTable table = createTable();
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	public JTable createTable(){
		String[] columnNames = {"First Name",
		"Last Name",
		"Sport",
		"# of Years",
		"Vegetarian"};

		Object[][] data = {
			{"Kathy", "Smith",
			"Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe",
			"Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black",
			"Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White",
			"Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown",
			"Pool", new Integer(10), new Boolean(false)}
		};

		JTable table = new JTable(data, columnNames);

		return table;

	}
}