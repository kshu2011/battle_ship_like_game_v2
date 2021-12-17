import java.util.ArrayList;

public class MyDot {
	/** this is the actual dot com object
	 * it knows it's name and location and whether it's
	 * a hit/miss or kill */
	
	ArrayList<String> locations = new ArrayList<String>(); //record its location
	String name = new String(); //save name
	int numOfHits = 0; //record hits
	
	public String checkYourself(String userGuess) {
		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).equals(userGuess)) {
				numOfHits++; //it's a hit so increment
				locations.remove(i); //go ahead remove the cell that's been hit
				if (locations.isEmpty()) { //if no more cell's left, it's killed
					return "kill";
				}
				return "hit";
			}
		}
		return "miss";
	}
	
	void setLocationCells(ArrayList<String> cellLocations) {
		/** this set function sets the location of this dot com */
		locations = cellLocations;
	}
	
	void setName(String givenName) {
		/** this method sets the name of this dot com*/
		name = givenName;
	}
}
