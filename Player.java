public class Player{
	private String username;
	private String password;
	private Score[] scores;
	private int scoreCount;

	public Player(String username, String password){
		this.username = username;
		this.password = password;
		this.scores = new Score[1000];
		this.scoreCount = scoreCount;
	}

	private void sortScores(){
		for(int i =0; i<scoreCount-1; i++){
			for(int j = i+1; j<scoreCount; j++){
				if(scores[j].getScore()<scores[i].getScore()){
					Score temp = scores[i];
					scores[i] = scores[j];
					scores[j] = temp;
				}
			}
		}
	}

	public Score[] getMaxScores(int number, Score.GameType mode){
		sortScores();

		Score [] highScores = new Score[number];
		int highScoreCount = 0;

		for(int i = 0; i<scoreCount; i++){
			if(highScoreCount<number){
				if(scores[i].getType()==mode){
					highScores[highScoreCount] = scores[i];
					highScoreCount++;
				}
			}
			else{
				break;
			}
		}
		return highScores;
	}

	public void addScore(int score, Score.GameType gameType){
		scores[scoreCount]= new Score(score, gameType);
		scoreCount++;
	}

	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
}