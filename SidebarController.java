public class SidebarController{
	private int currentScore;
	private boolean timed;
	
	public SidebarController(boolean timed){
		currentScore = 0;
		this.timed = timed;
	}

	public void incrementScore(int scoreIncrement){
		currentScore+=scoreIncrement;
	}

	public String getScore(){
		Integer wrapperInt = currentScore;
		return wrapperInt.toString();
	}

	public boolean timed(){
		return timed;
	}
}