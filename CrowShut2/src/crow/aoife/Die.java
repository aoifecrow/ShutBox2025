package crow.aoife;


import java.util.Random;


/**
 * Class to represent a single Die
 * @author A.Crow
 * @version 1.0
 */
public class Die {
	Random rand = new Random(); //Class wide variable
	private int value;
	private int numSides;
	
	/**
	 * Constructor for a Die - default to 6 sides
	 */
	Die(){
		numSides = 6;
		value = roll();
	}
	/**
	 * Constructor for a die - any size of die
	 * @param n number of sides
	 */
	Die(int n) {
		numSides = n;
		value = roll();
	}
	/**
	 * Randomly assigns a new value to the die
	 * @return value on top of the die
	 */
	public int roll() {
		value = rand.nextInt(numSides)+1;
		return value;
	}
	/**
	 * Shows the value rolled
	 * @return
	 */
	public int getValue() {
		return value;
	}
	public void setSides(int n) {
		numSides = n;
	}
	/**
	 * Create a string representation of the object Die
	 */
	@Override
	public String toString() {
		return ("Die has " +numSides +" and the current value is " +value);
	}
}
