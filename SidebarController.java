public class SidebarController{
	private int currentScore;
	
	public SidebarController(){
		currentScore = 0;
	}

	public void incrementScore(int scoreIncrement){
		currentScore+=scoreIncrement;
	}

	public String getScore(){
		Integer wrapperInt = currentScore;
		return wrapperInt.toString();
	}
}