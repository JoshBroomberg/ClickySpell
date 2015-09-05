public class Score{
	//used to store information for a recorded score

	//enum to store game type
	public static enum GameType{ARCADE, TIMED}
	
	//instance vars to store score value and associated game type 
	private int score;
	private GameType gameType;
	
	
	public Score(int score, GameType gameType){
		//create new score instance and populate instance vars
		this.score = score;
		this.gameType = gameType;
	}

	public int getScore(){
		//return value of score
		return score;
	}
	public GameType getType(){
		//return game type of score
		return gameType;
	}
}