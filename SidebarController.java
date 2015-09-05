public class SidebarController{
	private int currentScore;
	private int highScore;
	private boolean timed;
	
	public SidebarController(boolean timed, Score highScore){
		currentScore = 0;
		this.timed = timed;
		this.highScore = highScore.getScore();
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