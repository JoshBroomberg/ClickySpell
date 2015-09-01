public class MainController{
	
	public static void main(String[] args){
		new MainController();
	}


	public MainController(){
		new PlayerController();
	}

	public static void showMenu(){
		new MenuController();
	}

	public static void showLogin(){
		PlayerController.logout();

	}

	public static void showGame(){
		new GameController();
	}

}