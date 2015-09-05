public class SidebarController{
	//controller for the side bar

	//stores 3 pieces of info
	private int currentScore; //current score
	private int highScore; //high score for player in this mode
	private boolean timed; //whether the game is a timed game
	
	public SidebarController(boolean timed, Score highScore){
		//instantiate new controller
		currentScore = 0; //set current score to zero
		this.timed = timed; //timed to represent param given
		if(highScore!=null){
			//if highscore provided, set highscore to value of score obj
			this.highScore = highScore.getScore();
		}
		else{
			//else set high score to zero
			this.highScore=0; 
		}
	}

	public void incrementScore(int scoreIncrement){
		//increase current score by ammount provided
		currentScore+=scoreIncrement;
	}

	public String getScore(){
		//return current score as a string
		Integer wrapperInt = currentScore;
		return wrapperInt.toString();
	}

	public String getHighScore(){
		//return the current high score as a string
		Integer wrapperInt = highScore;
		return wrapperInt.toString();
	}

	public boolean timed(){
		//return whether the game is timed
		return timed;
	}
}