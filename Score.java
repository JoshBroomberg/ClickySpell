public class Score{
	public static enum GameType{ARCADE, TIMED}
	
	private int score;
	private GameType gameType;
	
	
	public Score(int score, GameType gameType){
		this.score = score;
		this.gameType = gameType;
	}

	public int getScore(){
		return score;
	}
	public GameType getType(){
		return gameType;
	}
}