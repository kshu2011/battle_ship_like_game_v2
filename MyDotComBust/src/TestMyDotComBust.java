import java.util.ArrayList;
import java.util.Random;

// This class will be used to test my code as I go along
// it's not part of the game. just testing class.
public class TestMyDotComBust {
	public static void main(String[] args) {
		System.out.println("Test 1: Check MyDot, make sure it can be killed");
		MyDot one = new MyDot();
		ArrayList<String> theLocation = new ArrayList<String>();
		theLocation.add("A1");
		theLocation.add("A2");
		theLocation.add("A3");
		one.setLocationCells(theLocation);
		one.setName("Pets.com");
		one.checkYourself("A1");
		one.checkYourself("A2");
		String result = one.checkYourself("A3");
		if (result.equals("kill")) {
			System.out.println("Pass!");
		} else {
			System.out.println("Fail!");
		}

		System.out.println("Test 2: See if miss and hit are working");
		MyDot two = new MyDot();
		ArrayList<String> theLocationTwo = new ArrayList<String>();
		theLocationTwo.add("B3");
		theLocationTwo.add("C3");
		theLocationTwo.add("D3");
		two.setLocationCells(theLocationTwo);
		two.setName("hello.com");
		if (two.checkYourself("A1").equals("miss")) {
			System.out.println("Pass: miss");
		} else {
			System.out.println("Fail: miss");
		}
		if (two.checkYourself("B3").equals("hit")) {
			System.out.println("Pass: hit");
		} else {
			System.out.println("Fail: hit");
		}
		if (two.checkYourself("C5").equals("miss")) {
			System.out.println("Pass: miss");
		} else {
			System.out.println("Fail: miss");
		}
		two.checkYourself("C3");
		if (two.checkYourself("D3").equals("kill")) {
			System.out.println("Pass: kill");
		} else {
			System.out.println("Fail: kill");
		}
		
		System.out.println("Test 3: Test out MyHelper to see if it can generate random grid locations");
		MyGameHelper aHelper = new MyGameHelper();
		for (int i = 0; i < 10; i++) { //should generate several different locations
			System.out.println(aHelper.makeLocation());
		}
		
		
	}
}
