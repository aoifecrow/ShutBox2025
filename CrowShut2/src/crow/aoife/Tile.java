package crow.aoife;

/**
 * Class to represent a single Tile
 * @author A.Crow
 * @version 1.0
 */
public class Tile {
	private int value;
	private boolean isDown;
	private boolean selected;
	
	/**
	 * Constructor for a Tile - default to down and not selected
	 * @param v
	 */
	public Tile(int v) {
		value = v;
		isDown = false;
		selected = false;
	}
	
	/**
	 * Sets the Tile as shut
	 */
	public void shut() {
		isDown=true;
	}
	
	/**
	 * Sets the Tile as open
	 */
	public void open() {
		isDown = false;
	}
	
	/**
	 * Sets the Tile as down
	 * @return
	 */
	public boolean isDown() {
		return isDown;
	}
	
	/**
	 * Sets the Tile as selected
	 */
	public void select() {
		selected = true;
	}
	
	/**
	 * Shows wether the state of selection 
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Sets the Tile as unselected
	 */
	public void deselect() {
		selected = false;
	}
	
	/**
	 * Shows the value of the Tile
	 * @return
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Create a string representation of the object Tile
	 */
	@Override 
	public String toString() {
		String state ="";
		
		//Checks the state of isDown
		if (isDown) {
			state ="D";
		}
		else {
			state="U";
		}
			return String.format("%d: %s",value, state);
		
	}
}
