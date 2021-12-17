import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MyGameHelper {
	/** This class knows how to get user input and 
	 * make the dot com locations*/
		
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
		 * head first Java book) and the 'x-axis' is 0 to 6
		 * */
		
		ArrayList<String> randomLocations = new ArrayList<String>();
		
		int randAscii = ThreadLocalRandom.current().nextInt(65, 70); //for generating a random Letter
		String randLetter = Character.toString(randAscii); //turn ascii value into character between A-G
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5); //for generating random number 'x' axis of the grid
				
		randomLocations.add(randLetter + Integer.toString(randomNum));//the 'first' cell on grid for the location
		
		int verticalOrHorizontal = ThreadLocalRandom.current().nextInt(0,2); // 0 be horizontal placement, 1 be vertical placement in grid
		if (verticalOrHorizontal == 0) { //horizontal placement
			for (int i = randomNum + 1; i < randomNum + 3; i++) {
				randomLocations.add(randLetter + Integer.toString(i));
			}
		} else { //this is for 'vertical' placement on virtual grid
			for (int i = randAscii + 1; i < randAscii + 3; i++) {
				randomLocations.add(Character.toString(i) + Integer.toString(randomNum));
			}
		}		
		return randomLocations;
	}
		
}
