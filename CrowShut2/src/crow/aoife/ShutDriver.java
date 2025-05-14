package crow.aoife;


import java.util.ArrayList;
import java.util.Scanner;

public class ShutDriver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int NUM_TILES =9;
		ArrayList<Tile> tiles = new ArrayList<>();
		Die die1 = new Die();
		Die die2 = new Die();
		
		for (int i=0; i<NUM_TILES; i++) {
			tiles.add(new Tile(i+1));
		}
		
		boolean done = false;
		while(!done) {
			for (Tile t: tiles) {
				System.out.println(t);
			}
			System.out.println("Rolling dice");
			die1.roll();
			die2.roll();
			int total = die1.getValue() + die2.getValue();
			System.out.printf("Select numbers that sum to %d, seperate numbers with spaces",total);
			String selected =in.nextLine();
			String[] selectedNumbers = selected.split(" ");
			boolean correct = false;
			//make a loop that runs continually if the selected numbers arent valid
			//check if the numbers they selected are down 
			//check if the numbers they selected add to the total
			
			while (correct!=true) {
				for (int i=0; i<selectedNumbers.length -1 ; i++) {
					for (int j=0; i<tiles.size() -1; i++) {
						if (Integer.valueOf(selectedNumbers[j]) == tiles.get(i).getValue() && tiles.get(i).isDown() == false) {
							
						}
					}
				}
			}
			done = true;
		}
		
		in.close();
	}

}
