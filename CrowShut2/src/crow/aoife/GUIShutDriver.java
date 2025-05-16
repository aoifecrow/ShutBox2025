package crow.aoife;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Shut the Box parlor game Interface
 * 
 * @author A.Crow
 * @version 1.0
 */
public class GUIShutDriver extends Application {

	//Global Variables 
	Die d1 = new Die();
	Die d2 = new Die();
	int player1Score = 0;
	int player2Score = 0;
	int currentPlayer = 1;
	int currentRound = 1;
	boolean first789 = true;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//set up main scene
		VBox root = new VBox(10); //plane for everything	
		root.setAlignment(Pos.CENTER); //centered
		Button[] tileBtns = new Button[9]; //creates array for buttons
		Label gameName = new Label("Shut The Box"); //create title
		gameName.setStyle("-fx-font-size: 40px"); //set style
		Label player = new Label("Player "+currentPlayer); //title for current player
		player.setStyle("-fx-font-size: 24px");//set style
		Label round = new Label("Current Round:"+currentRound); //title for current round
		round.setStyle("-fx-font-size: 18px"); //set style
		Label p1ScoreLabel = new Label("Player 1 Score : " + player1Score);// title for player 1 score
		Label p2ScoreLabel = new Label("Player 2 Score : " + player2Score);// title for player 2 score
		//set styles
		p1ScoreLabel.setStyle("-fx-font-size: 16px");
		p2ScoreLabel.setStyle("-fx-font-size: 16px");
		
		//set up game over scene
		VBox over = new VBox(10);
		Label messageOver = new Label("OVER");
		over.setAlignment(Pos.CENTER);
		over.getChildren().add(messageOver);
		
		//create buttons for each tile and put in array 
		for (int i=0; i<tileBtns.length; i++) {
			Button button= new Button (String.valueOf(i+1));
			button.setStyle("-fx-background-color:#FFFFFF"); //set unselected colour
			tileBtns[i] = button;
		}
		
		Tile[] markers = new Tile[9];//array list for tiles
		//create tiles for each number and put in array
		for (int i=0; i<tileBtns.length; i++) {
			markers[i] = new Tile(i+1);
		}
		
		HBox btnBox = new HBox(10); //horizontal plane for buttons to be sideways
		btnBox.setAlignment(Pos.CENTER); //centered
		
		// change state of each button
		for (Button b: tileBtns) {
			btnBox.getChildren().add(b); //add each button to plane
			
			//change state of button when pressed
			b.setOnAction(e-> {
				int number = Integer.valueOf(b.getText());
				
				//checks if the tile is not already down
				if (!markers[(number-1)].isDown()) {
					
					//checks if tile is not already selected
					if(!markers[(number-1)].isSelected()) {
						//set the color for selection
						b.setStyle("-fx-background-color: #90EE90");
						markers[(number-1)].select(); //select tile
					}
					//if buttons are selected then deselect
					else {
						//return colour back to deselected
						b.setStyle("-fx-background-color: #FFFFFF");
						markers[(number-1)].deselect(); //deselect tile
					}
				}
		}); // end of button click
			
		}// end of event handler for tile selection buttons
		
		// Create button that finalizes selected tiles 
		Button lockIn = new Button("Lock IN");
		lockIn.setDisable(true); //disable lockIn
		
		//set up for roll result
		HBox result = new HBox(2);
		Label rollStatement = new Label("You rolled:");
		Label dieResult = new Label("");
		rollStatement.setStyle("-fx-font-size: 16 px");
		dieResult.setStyle("-fx-font-size: 16 px");
		Button btnRoll = new Button("Roll 2 Die");
	
		Button roundOver = new Button("Done"); //finished button
		
		
		Scene scene2 = new Scene(over,500,500); //set up game over scene
		
		//Round over
		roundOver.setOnAction(e->{
			int roundSum =0; //sum for the round
			
			//counts the sum of tiles that are still up
			for(Tile t: markers) {
				if(!t.isDown()) {
				roundSum+=t.getValue();
				}
			}
			
			//adds score to player 1
			if (currentPlayer == 1) {
				player1Score += roundSum;
				
			}
			//adds score to player 2
			else {
				player2Score +=roundSum;
			}
			
			//reset tiles
			for (int i =0; i<tileBtns.length; i++ ) {
				tileBtns[i].setStyle("-fx-background-color: #FFFFFF");
				markers[i].open();
				tileBtns[i].setDisable(false);
			} 
			
			// if the currentRound is under 3
			if (currentRound<3) {
				currentRound++; //adds to current round
				
			}
			// if the last round was player 1 round 3 then switch to player 2
			else if (currentPlayer==1){
				currentRound = 1;
				currentPlayer =2;
			}
			// if it's the last round, find winner
			else if (currentPlayer==2) {
				
				//finds lowest score and winner
				if (player1Score <player2Score) {
					messageOver.setText("Player 1 Won! Score: "+player1Score);//player 1 win message
				}
				else if (player2Score <player1Score) {
					messageOver.setText("Player 2 Won! Score: "+player2Score);//player 2 win message
				}
				else {
					messageOver.setText("Tie! Scores: "+player1Score); //tie message
				}
				stage.setScene(scene2); //game over screen
			}
			
			//reset all text labels and buttons
			player.setText("Player "+currentPlayer);
			round.setText("Current Round:"+currentRound);
			p1ScoreLabel.setText("Player 1 Score:" + player1Score);
			p2ScoreLabel.setText("Player 2 Score:" + player2Score);
			first789 = true;
			btnRoll.setText("Roll 2 Die");
			dieResult.setText("");
			btnRoll.setDisable(false);
			lockIn.setDisable(true);
			});
		
		//roll die
		btnRoll.setOnAction(e-> {
			int sumDie = 0; //sum of die
			
			//if 7,8,9 tiles are down only roll 1 die
			if (markers[6].isDown() && markers[7].isDown() && markers[8].isDown()) {
				sumDie = d1.roll(); //roll 1 die
			}
			else {
				sumDie = d1.roll() +d2.roll(); //roll 2 die
			}
			dieResult.setText(String.valueOf(sumDie)); //display sum of die
			btnRoll.setDisable(true); //turn off roll ability
			
			//enable potential lock in 
			lockIn.setDisable(false);
		});
		
		
		lockIn.setOnAction( e-> {
			//determine the sum of the selected tiles
			int sum = 0;
			int downCount = 0;
			for(Tile t: markers) {
				if (t.isSelected()) {
					sum +=t.getValue();
				}
				if (t.isDown()) {
					downCount++;
				}
				}
			
			if (sum == Integer.valueOf(dieResult.getText())) {
				System.out.println("Good Match");
				//diable the buttons that are selected and change colour 
				for(int i=0; i<markers.length; i++) {
					if (markers[i].isSelected()) {
						markers[i].deselect();
						markers[i].shut();
						tileBtns[i].setStyle("-fx-background-color:#ff99ff");
						tileBtns[i].setDisable(true);
					}
					}
				//renable roll 
				dieResult.setText("");
				btnRoll.setDisable(false);
				lockIn.setDisable(true);
			}
			else {
				//Create alert
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please Select Valid Numbers");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			//If 789 are down for the first time and all of the tiles haven't been put down then Alert that one die is being rolled is shown.
			if (markers[6].isDown() && markers[7].isDown() && markers[8].isDown() && downCount != 9) {
				if (first789 == true) {
					//Create alert
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("You are now rolling one die!");
					alert.showAndWait();
					first789 = false;
					btnRoll.setText("Roll Die");
				}
			}
		});
		HBox controlButtons = new HBox(3);
		controlButtons.setAlignment(Pos.CENTER);
		controlButtons.getChildren().addAll(btnRoll, lockIn, roundOver);
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(rollStatement, dieResult);
		root.getChildren().add(gameName);
		root.getChildren().add(player);
		root.getChildren().add(round);
		root.getChildren().addAll(p1ScoreLabel,p2ScoreLabel);
		root.getChildren().add(btnBox);
		root.getChildren().add(result);
		root.getChildren().add(controlButtons);
		
		Scene scene1 = new Scene(root,500,500);
			stage.setScene(scene1);
	
	
		

		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
