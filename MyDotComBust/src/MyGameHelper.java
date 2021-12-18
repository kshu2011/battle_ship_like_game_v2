import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MyGameHelper {
	/** This class knows how to get user input and 
	 * make the dot com locations*/
		
	static ArrayList<String> usedLocations = new ArrayList<String>(); //to keep track of what locations have been used
	
	public String getUserInput(String prompt) {
		/** this input method was from head first java*/
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0) {
				return null;
			} 
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}
	
	public ArrayList<String> makeLocation() {
		/** this method will generate a random placement for the dot com on a 
		 * 7 x 7 grid. Where the 'y-axis' is A to G (G is considered 0 or see page 140 of 
		 * head first Java book) and the 'x-axis' is 0 to 6. Each dot com will have 3 cells, the 
		 * ArrayList this method returns is 3 cells long and it will double check to make sure
		 * the new randomLocation created does not overlap with any previous locations generated.
		 * Note: grid is only 49 cells, and each dot com takes up 3 cells so MAX is 16 dot coms possible
		 * can be placed on the board, if try to create too many this function will fail. It will
		 * end up getting stuck in while loop.
		 * */
		
		ArrayList<String> randomLocations = new ArrayList<String>();
		
		int randAscii = ThreadLocalRandom.current().nextInt(65, 70); //for generating a random Letter
		String randLetter = Character.toString(randAscii); //turn ascii value into character between A-G
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5); //for generating random number 'x' axis of the grid
		
		String tryThisLocation = randLetter + Integer.toString(randomNum);//the 'first' cell on grid for the location
		boolean locationCreated = false;
		while (!locationCreated) { //keep going until we have created an acceptable location that does not overlap
			if (locationAcceptable(tryThisLocation)) { //try adding the first location if it is acceptable
				randomLocations.add(tryThisLocation); //add it to the randomLocation too
				usedLocations.add(tryThisLocation); //add it to used pile
				
				//below adds the 2nd and 3rd cell locations
				
				int verticalOrHorizontal = ThreadLocalRandom.current().nextInt(0,2); // 0 be horizontal placement, 1 be vertical placement on grid
				if (verticalOrHorizontal == 0) { //horizontal placement
					for (int i = randomNum + 1; i < randomNum + 3; i++) {
						if (locationAcceptable(randLetter + Integer.toString(i))) { //if okay add it
							randomLocations.add(randLetter + Integer.toString(i));		
							usedLocations.add(randLetter + Integer.toString(i)); //add it to used pile
						} else { //clear the randomLocations and remove them from the usedLocations list as well and start over, since placement overlaps with other dot com
							for (int index = 0; index < randomLocations.size(); index++) {
								String value = randomLocations.get(index);
								randomLocations.remove(index); //remove from randomLocations
								usedLocations.remove(value); //remove it from usedLocations as well
							} //start over, try another location
							randAscii = ThreadLocalRandom.current().nextInt(65, 70); 
							randLetter = Character.toString(randAscii); 
							randomNum = ThreadLocalRandom.current().nextInt(0, 5); 
							tryThisLocation = randLetter + Integer.toString(randomNum);
						}
					}
				} else { //this is for 'vertical' placement on virtual grid
					for (int i = randAscii + 1; i < randAscii + 3; i++) {
						if (locationAcceptable(Character.toString(i) + Integer.toString(randomNum))) { //if okay add it
							randomLocations.add(Character.toString(i) + Integer.toString(randomNum));		
							usedLocations.add(Character.toString(i) + Integer.toString(randomNum)); //add it to used pile
						} else {
							//this for loop will clear all the added cells from randomLocation and usedLocation, then start over
							for (int index = 0; index < randomLocations.size(); index++) {
								String value = randomLocations.get(index);
								randomLocations.remove(index);
								usedLocations.remove(value);
							}
							randAscii = ThreadLocalRandom.current().nextInt(65, 70); 
							randLetter = Character.toString(randAscii); 
							randomNum = ThreadLocalRandom.current().nextInt(0, 5); 
							tryThisLocation = randLetter + Integer.toString(randomNum);
						}
					}
				}
			} else { //try another location
				randAscii = ThreadLocalRandom.current().nextInt(65, 70); 
				randLetter = Character.toString(randAscii); 
				randomNum = ThreadLocalRandom.current().nextInt(0, 5); 
				tryThisLocation = randLetter + Integer.toString(randomNum);
			}
			if (randomLocations.size() == 3) { //successful in creating random location
				locationCreated = true;
			}
		}
		return randomLocations;
	}
	
	private boolean locationAcceptable(String cellLocation) {
		/** this function checks to see if chosen location is okay */
		if (usedLocations.contains(cellLocation) == true) { //location already picked so not okay
			return false;
		} else {
			return true; //acceptable, not used yet.
		}
	}
}
