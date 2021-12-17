import java.util.ArrayList;

//This is same as MyDotComGame, just a fancier version of it
//instead of just 1 dot com and an array, it has 3 dot coms and
//uses a vritual 7 x 7 grid instead

public class MyDotComBust {
	/** This is the main game, so it has the main method in here. This class will 
	 * instantiate a new MyDotComBust game and starts the game so user can play.*/
	
	private MyGameHelper helper = new MyGameHelper();
	private ArrayList<MyDot> theDotList = new ArrayList<MyDot>();
	private int numOfGuesses = 0;
	
	public void setUpGame() {
	/** This method will setup the game by creating
	 * three dot com objects and place them randomly on the
	 * grid*/
		
		for (int i = 0; i < 3; i++) { //create 3 dot objects and set a random location for each
			theDotList.add(new MyDot());
			theDotList.get(i).setLocationCells(helper.makeLocation());//set random location
		}
		//assign names to each
		theDotList.get(0).setName("Pets.com");
		theDotList.get(1).setName("GoDaddy.com");
		theDotList.get(2).setName("Wiki.com");
	}
	
	public void startPlaying() {
	/** asks player for guesses and calls 'checkUserGuess' until
	 * all the dot com objects are killed. */
		MyDot currentDot = null;
		String dotResult = null;
		while (theDotList.isEmpty() == false) {
			String userGuess = helper.getUserInput("enter a number");
			checkUserGuess(userGuess);
		}
	}
	
	public void checkUserGuess(String userGuess) {
	/** Loops through the dot com objects and calls each objects
	 * checkYourself() method to see if it's hit/miss or killed.
	 * Since there are 3 dot com objects, so long as one has been killed
	 * this will print the one killed. If there is one that is hit, it will print
	 * 'hit' otherwise it prints 'miss'. For example if there is 1 kill, 1 hit and 1 miss 
	 * then this will just print the 'kill'. Priority is kill then hit then miss.*/
		
		numOfGuesses++;
		MyDot currentDot = null;
		String dotResult = null;
		ArrayList<String> results = new ArrayList<String>(); //save all the results that we get from dot com
		MyDot dotKilled = null;
		
		for (int i = 0; i < theDotList.size(); i++) {// check to see if it hit any of the dots
			currentDot = theDotList.get(i);
			dotResult = currentDot.checkYourself(userGuess);
			results.add(dotResult);
			if (dotResult.equals("kill")) { //then it's sunk, remove it
				dotKilled = theDotList.get(i); //save it first
				theDotList.remove(i); //then remove it
			}
		}
		if (dotKilled != null) {
			System.out.println("Ouch! You sunk " + dotKilled.name + "  : (");
		} else if (results.contains("hit")) {
			System.out.println("hit");
		} else {
			System.out.println("miss");
		}
		
	}
	
	public void finishGame() {
	/** This method will print out message about how well the player did*/	
		System.out.println("Number of guesses " + numOfGuesses);
	}
	
	public ArrayList<MyDot> getDotList() {
		/** not a necassary method, i am using it so I can "see" where
		 * the dot coms are to test the code */
		return theDotList;
	}
	
	public static void main(String[] args) {
		MyDotComBust game = new MyDotComBust();
		game.setUpGame();
		
		System.out.println("cheat sheet");
		for (int i = 0; i < 3; i++) {
			System.out.println(game.getDotList().get(i).locations.toString());
		}
		
		game.startPlaying();
		game.finishGame();
	}
}
