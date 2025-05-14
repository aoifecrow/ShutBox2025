package crow.aoife;


public class Tile {
	private int value;
	private boolean isDown;
	private boolean selected;
	public Tile(int v) {
		value = v;
		isDown = false;
		selected = false;
	}
	
	public void shut() {
		isDown=true;
	}
	public void open() {
		isDown = false;
	}
	
	public boolean isDown() {
		return isDown;
	}
	public void select() {
		selected = true;
	}
	public boolean isSelected() {
		return selected;
	}
	public void deselect() {
		selected = false;
	}
	public int getValue() {
		return value;
	}
	@Override 
	public String toString() {
		String state ="";
		if (isDown) {
			state ="D";
		}
		else {
			state="U";
		}
			return String.format("%d: %s",value, state);
		
	}
}
