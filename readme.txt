Letters Game read me:

This is the user manual for the letters game. It covers 3 sections:
	1)Registrations/log in
	2)Main menu and game navigation
	3)Game play

1)Registrations/log in:

	To play the game you must be logged in. This is because the game stores previous scores in your profile and evaluates each new game to check if it is a new personal high score. 

	You have one of two options when starting the game, either log in or register a new player. 
		
	-If you choose to log in:

		You will be asked to provide a username. If no player with that username is found, you will be prompted to try again. You can escape this process by clicking cancel. If you provide a valid username, you will be prompted for your password. If the correct password is entered you will be logged in and taken to the main menu. If an invalid password (in terms of length or correctness) is provided, you will be prompted to retry and shown the password field again. 

		For a prepopulated account, log in with username: admin/password: password 

	-If you choose to create a user:

		You will be asked to provide a username. If a player with that username is already registered, you will be prompted to try again. You can escape this process by clicking cancel. If you provide a valid username, you will be prompted to enter your password. If a valid password is entered (must be longer than 0 letters) you will be logged in and taken to the main menu. If an invalid password (in terms of length) is provided, you will be prompted to retry and shown the password field again. 
	
2)Main menu and game navigation

	The main menu provides three options:

	-"Play":

		This option is used to start a new game. When this button is clicked the user is prompted to make two choice. Firstly, you will be asked to choose between arcade and timed mode. Arcade mode is untimed and ended when you click exit. Timed mode is a game lasting one minute. More detail on types later. To play arcade enter 1, to play timed enter 2. If you enter invalid choices you will be prompted to retry. Cancel buttons on the choice dialogs cancel the new game. 
		Secondly, once you choose your game type, you will be asked to choose a board size and thus your difficulty. Larger boards mean more letters and more possible combinations on the board at any given time. Conversely, smaller boards provide less options. Enter a size between 3 and 9. This represents the dimensions of the square board. from 3x3 tiles to 9x9. Again, invalid input will result in a retry dialog. Once this input is done, a new game will start. 

	-"view high scores":
		This option will open a window showing your ten highest scores for each of the two game modes. You can click back or close the highscore window to get back to the main menu. 

	-"Log out":
		This will log you out of the game and return you to the log in window.

3)Game play

	The game window has two main features. A board on the left and a game play panel on the right. 

	The board has the letters you will use to play and the game play panel has information and play buttons. 
		The info shown is: 
		"score" - this is your current score
		"high score" - this is your high score in the current mode
		"words made" - this is a count of the number of words made in your current game
		"letters in the bag" - this is the number of letters remaining 'in the bag'. That is letters not shown no the board that will be placed on the board when you use up letters. 
		"current sequence" - shows the word you have created via tile selection. The color of this field pre-emptively indicates the validity of a word. It is red if your selection makes for an invalid word and green if you have made a valid word.
		<optionally> "time remaing" - if you are in timed mode this shows the seconds left. 

		Option buttons:
		"submit word" - checks if current selection is valid word.
		"shuffle letters" - moves the letters on the board into new random positions
		"reset selection" - clears the current selection. 
		"exit and end game" - ends the game and records your score. 

	How to play:

	The game window is where the game is played. Click tiles in order to construct words. When a valid tile is clicked, it will turn red. If you click a tile that is not adjacent to the last tile in the sequence or click a tile in the sequence that is not the last tile you will be told you have made an invalid click.

	Once you have constructed a word, click "submit word" in the game play panel to check your word. If it is valid, you will be told so and told the points you received. A words score is the sum of the value of each tile as displayed on the tile plus 1 point for each tile used up until 5 letters and 2 points per tile for all tiles after the fifth one. When you click ok, or press enter, the game will continue. The points you got from the word will be added to the score display and the letters you used will be replaced by new letters from the bag. Reducing the "letters left in bag" count. If there are no letters in the bag, the letters used will be replaced with empty, unusable squares.  If the word ou created is not valid you will be told so and allowed to continue playing with the same sequence. 

	If you have made a mistake in yur sequence you can do one of two things. Either you can click on the last letter in the sequence to remove it from the sequence or you can click "reset sequence" which clears your selection. 

	If you are stuck, you can click shuffle letters which moves the letters on the board around to create new possible combinations. 

	When does a game end?

	In timed mode, one of two things end the game. Either you run out of time, or click end and exit. In either case, if you have cored more than zero, your score is record and the game is exited. 

	In arcade mode, the game only ends when you click exit. if you cannot see any words you could create with the board you can click shuffle, if you still cannot see words you end the game. There is an element of luck to the game because you cannot swap letetrs from the board into the bag and vice versa without making words. This means that, especially on smaller boards, you may get letters that do not contain viable words. This is a natural end to the game. 

	When a game is ended, you will be returned to the main menu. 











