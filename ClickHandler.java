import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickHandler implements ActionListener{ 
  	private int id;
  	public ClickHandler(int id){
  		super();
  		this.id = id;
  	}

  	public void actionPerformed(ActionEvent e){ 
    	TestDriver.click(id);
  	} 
}