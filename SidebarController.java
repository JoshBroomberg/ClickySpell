public class SidebarController{
	private int currentScore;
	private int highScore;
	private boolean timed;
	
	public SidebarController(boolean timed, Score highScore){
		currentScore = 0;
		this.timed = timed;
		if(highScore!=null){
			this.highScore = highScore.getScore();
		}
		else{
			this.highScore=0;
		}
	}

	public void incrementScore(int scoreIncrement){
		currentScore+=scoreIncrement;
	}

	public String getScore(){
		Integer wrapperInt = currentScore;
		return wrapperInt.toString();
	}

	public String getHighScore(){
		Integer wrapperInt = highScore;
		return wrapperInt.toString();
	}

	public boolean timed(){
		return timed;
	}
}