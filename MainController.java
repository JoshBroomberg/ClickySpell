public class MainController{
    //this class is used to boot the program and acts as the bridge between all other controllers

    //main program structure is as follows.
    //Any button click calls relevant method in controller for the frame containing the button
    //That method takes any actions need. If the actions are facilitated by another controller
    //the MainController is used as a bridge

    //main void to sart the program by creating new instance of MainController 
	public static void main(String[] args){
		new MainController();
	}


	public MainController(){
		//constructor for MainController creates new PLayController which displays login
		new PlayerController();
	}

	public static void showMenu(){
		//shows main menu controller
		new MenuController();
	}

	public static void showLogin(){
		//reshows the login screen suing player controller
		PlayerController.logout();

	}

	public static void showGame(){
		//shows game view
		new GameController();
	}

	public static void showHighscores(){
		//uses player controller to show high scores
		PlayerController.displayScores();
	}

	public static void hideHighscores(){
		//hides high scores
		PlayerController.hideScores();
	}

}