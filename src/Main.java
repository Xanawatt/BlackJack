import java.util.*;


/*52 cards
				7 piles
				x x x x x x x
				  x x x x x x
				    x x x x x
				      x x x x
				        x x x
				          x x
				            x

				28 in play, 28 in deck
				int[ ][ ] aryNumbers = new int[7][7]; 7 rows, 7 columns
 */
/**ChangeLog:
 * 9/28/16: 
 * 		started work on BlackJack.
 * 		Imported previously worked on solitare game to do the deck management
 * 9/29/16:
 *    Added a lot of utility methods for reading arrays
 * 10/3/16:
 *    Investigated vectors
 */
public class Main {
	public static final String[] playingCards = new String[52];
	public static Scanner reader;
	static String response;
	public static int CC = 0;
	public static Hand playerOneHand = new Hand();
	public static Hand playerTwoHand = new Hand();
	public static Hand dealerHand = new Hand();
	public static double playerOneBalance = 500;
	public static double playerTwoBalance = 500;
	public static double playerOneBet;
	public static double playerTwoBet;
	public static double playerOnePayout;
	public static double playerTwoPayout;
	public static double playerOneBlackJackPayout;
	public static double playerTwoBlackJackPayout;
	public static boolean playerOneKeepGoing = true;
	public static boolean playerTwoKeepGoing = true;
	public static boolean dealerKeepGoing = true;
	public static boolean playAgain = true;
	/*public static String[] playerOneHand = new String[10];
  public static String[] playerTwoHand = new String[10];
  public static String[] dealerHand = new String[10];
	 */
	public static void main(String[] args) {
		playingCards[0] = "Ace of Clubs";			playingCards[26] = "Ace of Diamonds";
		playingCards[1] = "2 of Clubs";				playingCards[27] = "2 of Diamonds";
		playingCards[2] = "3 of Clubs";				playingCards[28] = "3 of Diamonds";
		playingCards[3] = "4 of Clubs";				playingCards[29] = "4 of Diamonds";
		playingCards[4] = "5 of Clubs";				playingCards[30] = "5 of Diamonds";
		playingCards[5] = "6 of Clubs";				playingCards[31] = "6 of Diamonds";
		playingCards[6] = "7 of Clubs";				playingCards[32] = "7 of Diamonds";
		playingCards[7] = "8 of Clubs";				playingCards[33] = "8 of Diamonds";
		playingCards[8] = "9 of Clubs";				playingCards[34] = "9 of Diamonds";
		playingCards[9] = "10 of Clubs";			playingCards[35] = "10 of Diamonds";
		playingCards[10] = "Jack of Clubs";			playingCards[36] = "Jack of Diamonds";
		playingCards[11] = "Queen of Clubs";		playingCards[37] = "Queen of Diamonds";
		playingCards[12] = "King of Clubs";			playingCards[38] = "King of Diamonds";

		playingCards[13] = "Ace of Hearts";			playingCards[39] = "Ace of Spades";
		playingCards[14] = "2 of Hearts";			playingCards[40] = "2 of Spades";
		playingCards[15] = "3 of Hearts";			playingCards[41] = "3 of Spades";
		playingCards[16] = "4 of Hearts";			playingCards[42] = "4 of Spades";
		playingCards[17] = "5 of Hearts";			playingCards[43] = "5 of Spades";
		playingCards[18] = "6 of Hearts";			playingCards[44] = "6 of Spades";
		playingCards[19] = "7 of Hearts";			playingCards[45] = "7 of Spades";
		playingCards[20] = "8 of Hearts";			playingCards[46] = "8 of Spades";
		playingCards[21] = "9 of Hearts";			playingCards[47] = "9 of Spades";
		playingCards[22] = "10 of Hearts";			playingCards[48] = "10 of Spades";
		playingCards[23] = "Jack of Hearts";		playingCards[49] = "Jack of Spades";
		playingCards[24] = "Queen of Hearts";		playingCards[50] = "Queen of Spades";
		playingCards[25] = "King of Hearts";		playingCards[51] = "King of Spades";
		int i;
		int j; /*
		for(i = 0; i < 4; i++){
			for(j = 0; j < 13; j++){
				System.out.println(playingCards[i][j] + "");
			}
		} */
		while(playAgain == true){
			startOfGame();
			shuffleArray(playingCards);
			shuffleArray(playingCards);
			shuffleArray(playingCards);
			shuffleArray(playingCards);
			dealCardsAtStart();
			checkFirstTurnBlackJack();

			while((playerOneKeepGoing == true || playerTwoKeepGoing == true) && dealerKeepGoing == true){
				turn();
			}
			handOutMoney();
			endOfGame();
		}
		System.out.println("Thank you for playing!");
	}




	public static void shuffleArray(String[] array) {
		Random random = new Random();
		for(int i = array.length - 1; i > 0; i--) {
			int m = random.nextInt(i + 1);
			String temp = array[i];
			array[i] = array[m];
			array[m] = temp;
		}
	}

	/**
	 * Prints out an array of ints
	 * @param  array  int[]
	 */
	public static void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i] + "");
		}
	}

	/**
	 * Prints out an array of Strings
	 * @param  array  int[][]
	 */
	public static void printArray(int[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				System.out.println(array[i][j] + "");
			}
		}
	}
	/**
	 * Prints out an array of Strings
	 * @param  array  String[]
	 */
	public static void printArray(String[] array){
		for(int i = 0; i < array.length && array[i] != null; i++){
			System.out.println(array[i] + "");
		}
	}
	/**
	 * Prints out a 2D array of Strings
	 * @param  array  String[][]
	 */
	public static void printArray(String[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				System.out.println(array[i][j] + "");
			}
		}
	}
	public static void startOfGame(){
		reader = new Scanner(System.in);
		//intro
		System.out.println("Blackjack pays 3:2");
		System.out.println("Each player will get $500 to start.");
		System.out.println("Minimum bet is $5, Maximum is $100");
		gottaGoSlow();

		//player one stuff
		System.out.println("Player One: How much would you like to bet?");
		playerOneBet = reader.nextInt();
		while(playerOneBet < 5 || playerOneBet > 100){
			System.out.println("Please enter an Integer from 5-100");
			playerOneBet = reader.nextInt();
		}
		System.out.println("You have bet $" + playerOneBet + ".");
		playerOneBlackJackPayout = playerOneBet * 1.5;
		playerOnePayout = playerOneBet;
		System.out.println("If you win, you will get $" + playerOnePayout + ". " + 
				"If you BlackJack, then you will get $" + playerOneBlackJackPayout + ".");
		gottaGoSlow();

		//player two stuff
		System.out.println("Player Two: How much would you like to bet?");
		playerTwoBet = reader.nextInt();
		while(playerTwoBet < 5 || playerTwoBet > 100){
			System.out.println("Please enter an Integer from 5-100");
			playerTwoBet = reader.nextInt();
		}
		System.out.println("You have bet $" + playerTwoBet + ".");
		playerTwoPayout = playerTwoBet;
		playerTwoBlackJackPayout = playerTwoBet * 1.5;
		System.out.println("If you win, you will get $" + playerTwoPayout + ". " + 
				"If you BlackJack, then you will get $" + playerTwoBlackJackPayout + ".");
		gottaGoSlow();
	}



	public static void dealCardsAtStart(){

		playerOneHand.addCardToHand();
		playerOneHand.addCardToHand();


		//System.out.println(playerOneHand.Value() + "");
		/*System.out.println("The status of Player One's hand is: ");
	  System.out.println(calcStatusOfHand(playerOneHand) + ""); */

		playerTwoHand.addCardToHand();
		playerTwoHand.addCardToHand();


		//System.out.println(playerTwoHand.Value() + "");
		/*System.out.println("The status of Player Two's hand is: ");
	  System.out.println(calcStatusOfHand(playerTwoHand) + ""); */
		System.out.println("The dealer's first card is: ");
		dealerHand.addCardToHand();

		dealerHand.print();
		gottaGoSlow();
	}
	public static void checkFirstTurnBlackJack(){
		playerOneKeepGoing = true;
		if(playerOneHand.Value() == 21){
			System.out.println("Player One has BlackJack!");
			gottaGoSlow();
			System.out.println("Here were your cards: ");
			playerOneHand.print();
			gottaGoSlow();
			playerOneKeepGoing = false;
		}



		playerTwoKeepGoing = true;
		if(playerTwoHand.Value() == 21){
			System.out.println("Player Two has BlackJack!");
			gottaGoSlow();
			System.out.println("Here were your cards: ");
			playerTwoHand.print();
			gottaGoSlow();
			playerTwoKeepGoing = false;
		}
	}
	public static void turn(){
		reader = new Scanner(System.in);
		while(playerOneKeepGoing == true && playerOneHand.Value() < 21){
			System.out.println("Player One: Here are your cards: ");
			playerOneHand.print();
			gottaGoSlow();
			while(playerOneKeepGoing == true && playerOneHand.Value() < 21){
				System.out.println("Player One: Would you like to 'stand' or 'hit'?");
				response = reader.next().toLowerCase();

				if(response.equals("hit")){
					playerOneHand.addCardToHand();
					System.out.println("Player One: Here are your cards: ");
					playerOneHand.print();
					gottaGoSlow();
					//System.out.println(playerOneHand.Value() + "");
				}
				else if(response.equals("stand")){
					playerOneKeepGoing = false;
				}
				if(playerOneHand.Value() == 21){
					System.out.println("Player One has a Normal 21!");
					Timer.delay(2000);
					playerOneKeepGoing = false;
				}
				else if(playerOneHand.Value() > 21){
					System.out.println("Player One has BUSTED!!!");
					gottaGoSlow();
					playerOneKeepGoing = false;
				}
			}
		}

		while(playerTwoKeepGoing == true && playerTwoHand.Value() < 21){
			System.out.println("Player Two: Here are your cards: ");
			playerTwoHand.print();
			gottaGoSlow();
			while(playerTwoKeepGoing == true && playerTwoHand.Value() < 21){
				System.out.println("Player Two: Would you like to 'stand' or 'hit'?");
				response = reader.next().toLowerCase();

				if(response.equals("hit")){
					playerTwoHand.addCardToHand();
					System.out.println("Player Two: Here are your cards: ");
					playerTwoHand.print();
					gottaGoSlow();
					//System.out.println(playerTwoHand.Value() + "");}	
				}
				else if(response.equals("stand")){
					playerTwoKeepGoing = false;
				}
				if(playerTwoHand.Value() == 21){
					System.out.println("Player Two has a Normal 21!");
					gottaGoSlow();
					playerTwoKeepGoing = false;
				}
				else if(playerTwoHand.Value() > 21){		
					System.out.println("Player Two has BUSTED!!!");
					gottaGoSlow();
					playerTwoKeepGoing = false;
				}
			}
		}
		while(dealerKeepGoing == true && dealerHand.Value() < 21 && 
			!(playerOneHand.Value() > 21 && playerTwoHand.Value() > 21)){
			while(dealerHand.Value() <= 16){
				dealerHand.addCardToHand();
				System.out.println("The Dealer has hit.");
				System.out.println("Here are the dealer's cards: ");
				dealerHand.print();
				gottaGoSlow();

			}
			if(dealerHand.Value() >= 17 && dealerHand.Value() < 21){ 
				System.out.println("The Dealer has stood.");
				gottaGoSlow();
				dealerKeepGoing = false;
			}
			else if(dealerHand.Value() > 21){
				System.out.println("The dealer has BUSTED!!!");
				gottaGoSlow();
				dealerKeepGoing = false;
			}
			else if(dealerHand.Value() == 21){
				System.out.println("The dealer has 21!");
				gottaGoSlow();
				dealerKeepGoing = false;
			}
		}
	}
	public static void handOutMoney(){
		if(dealerHand.Value() > 21){
			if(playerOneHand.Value() < 21){
				System.out.println("Player One has won! They have recieved $" + playerOnePayout + ".");
				playerOneBalance = playerOneBalance + playerOnePayout;
				gottaGoSlow();
			}
			else if(playerOneHand.Value() == 21){
				System.out.println("Player One has won! They have recieved $" + playerOneBlackJackPayout + ".");
				playerOneBalance = playerOneBalance + playerOneBlackJackPayout;
				gottaGoSlow();
			}else{
				System.out.println("Player One has lost! They have lost $" + playerOnePayout + ".");
				playerOneBalance = playerOneBalance - playerOnePayout;
				gottaGoSlow();
			}



			if(playerTwoHand.Value() < 21){
				System.out.println("Player Two has Won! They have recieved $" + playerTwoPayout + ".");
				playerTwoBalance = playerTwoBalance + playerTwoPayout;
				gottaGoSlow();
			}
			else if(playerTwoHand.Value() == 21){
				System.out.println("Player Two has won! They have recieved $" + playerTwoBlackJackPayout + ".");
				playerTwoBalance = playerTwoBalance + playerTwoBlackJackPayout;
				gottaGoSlow();
			}else{
				System.out.println("Player Two has lost! They have lost $" + playerTwoPayout + ".");
				playerTwoBalance = playerTwoBalance - playerTwoPayout;
				gottaGoSlow();
			}
		}
		else if(dealerHand.Value() < 21){
			//player one stuff
			if(playerOneHand.Value() == dealerHand.Value()){
				System.out.println("Player one has tied with the dealer. They will get back $" + playerOneBet + ".");
			}
			else if(playerOneHand.Value() == 21){
				System.out.println("Player One has won! They have recieved $" + playerOneBlackJackPayout + ".");
				playerOneBalance = playerOneBalance + playerOneBlackJackPayout;
				gottaGoSlow();
			}
			else if(playerOneHand.Value() < 21){
				if(playerOneHand.Value() > dealerHand.Value()){
					System.out.println("Player One has won! They have recieved $" + playerOnePayout + ".");
					playerOneBalance = playerOneBalance + playerOnePayout;
					gottaGoSlow();
				}
				else if(playerOneHand.Value() < dealerHand.Value()){
					System.out.println("Player One has lost! They have lost $" + playerOnePayout + ".");
					playerOneBalance = playerOneBalance - playerOnePayout;
					gottaGoSlow();
				}
			}else{
				System.out.println("Player One has lost! They have lost $" + playerOnePayout + ".");
				playerOneBalance = playerOneBalance - playerOnePayout;
				gottaGoSlow();
			}



			//player two stuff
			if(playerTwoHand.Value() == dealerHand.Value()){
				System.out.println("Player Two has tied with the dealer. They will get back $" + playerTwoBet + ".");
			}
			else if(playerTwoHand.Value() == 21){
				System.out.println("Player Two has won! They have recieved $" + playerTwoBlackJackPayout + ".");
				playerTwoBalance = playerTwoBalance + playerTwoBlackJackPayout;
				gottaGoSlow();
			}
			else if(playerTwoHand.Value() < 21){
				if(playerTwoHand.Value() > dealerHand.Value()){
					System.out.println("Player Two has won! They have recieved $" + playerTwoPayout + ".");
					playerTwoBalance = playerTwoBalance + playerTwoPayout;
					gottaGoSlow();
				}
				else if(playerTwoHand.Value() < dealerHand.Value()){
					System.out.println("Player Two has lost! They have lost $" + playerTwoPayout + ".");
					playerTwoBalance = playerTwoBalance - playerTwoPayout;
					gottaGoSlow();
				}
			}else{
				System.out.println("Player Two has lost! They have lost $" + playerTwoPayout + ".");
				playerTwoBalance = playerTwoBalance - playerTwoPayout;
				gottaGoSlow();
			}
		}
	}
	public static void endOfGame(){
		reader = new Scanner(System.in);
		System.out.println("Balances: ");
		gottaGoSlow();
		System.out.println("Player One: $" + playerOneBalance);
		System.out.println("Player Two: $" + playerTwoBalance);
		gottaGoSlow();
		System.out.println("Would you like to play again? 'y' or 'n'");
		if(reader.next().toLowerCase().equals("y")){
			playAgain = true;
			playerOneHand.clear();
			playerTwoHand.clear();
			dealerHand.clear();
			playerOneKeepGoing = true;
			playerTwoKeepGoing = true;
			dealerKeepGoing = true;
		}else{
			playAgain = false;
		}
	}
	public static void printDashLine(){
		System.out.println("------------------------------------------------------");
	}
	public static void gottaGoSlow(){
		printDashLine();
		Timer.delay(1000);
	}
}