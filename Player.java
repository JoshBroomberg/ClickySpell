public class Player{
	//object used to store info and methods for a player

	//log in info
	private String username;
	private String password;

	//score array and counter
	private Score[] scores;
	private int scoreCount;

	//create new player instance populating all instance vars
	public Player(String username, String password, Score[] scores, int scoreCount){
		this.username = username;
		this.password = password;
		this.scoreCount = scoreCount;
		this.scores = new Score[1000];
		//copying scores from array given into new rray with 1000 slots. 
		//this is used because read from file returns an array of scores only as long as number o scores stored
		//this process copies those scores into a new, bigger array
		for(int i =0; i<scoreCount; i++){
			this.scores[i] = scores[i];
		}
	}

	public Player(String username, String password){
		//if player is new, only get username and password. 
		//reference full constructor and send through empty score array
		this(username, password, new Score[1000], 0);
	}

	//sort all players scores max to min
	private void sortScores(){
		for(int i =0; i<scoreCount-1; i++){
			for(int j = i+1; j<scoreCount; j++){
				if(scores[j].getScore()>scores[i].getScore()){
					Score temp = scores[i];
					scores[i] = scores[j];
					scores[j] = temp;
				}
			}
		}
	}

	
	public Score[] getMaxScores(int number, Score.GameType mode){
		//get given number of top scores achieved in given game type
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

	public Score getMaxScore(Score.GameType mode){
		//get max high score in given game tupe
		sortScores();
		for(int i = 0; i<scoreCount; i++){
				if(scores[i].getType()==mode){
					return scores[i];
				}
		}
		return null;
	}

	public void addScore(int score, Score.GameType gameType){
		//add a new score to player
		scores[scoreCount]= new Score(score, gameType);
		scoreCount++;
	}

	public String getUsername(){
		//return username of player
		return username;
	}
	public String getPassword(){
		//return password of player
		return password;
	}

	public String toString(){
		//return string represnetaion of player for printing to file. Separate major data pieces with #
		//ie username, password and array of scores
		//separate each piece of score infomation with &
		String toReturn = "";
		toReturn = username+"#"+password+"#";
		for(int i =0; i<scoreCount; i++){
			//separate each scores type and value with a &
			toReturn+=scores[i].getScore()+"&"+scores[i].getType().name()+"&";
		}
		if(scoreCount>0){
			//if there were scores added, add a final # to group them all together for reading
			toReturn+="#";
		}
		return toReturn;
	}
}