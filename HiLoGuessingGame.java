import java.util.Optional;
import java.util.Random;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class HiLoGuessingGame extends Application {
	private int correctNumber;
	private int userNumber;
	private Text instruction;
	private Text gameTitle;
	private TextField numberInputField;
	private VBox centerDisplay;
	private Text returnResultText;
	private Button submitButton;
	private Button againButton;
	private Text newGameNotice;
	private Text errorMessage;

	public void start(Stage primaryStage) {
		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: null;");
		Random ran = new Random();
		correctNumber = ran.nextInt(100);

		gameTitle = new Text("Welcome to Hi-Lo Guessing Game!");
		gameTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		gameTitle.setFill(Color.CRIMSON);

		instruction = new Text(
				"Please enter a number between 1-100 below, \nand press Enter or Click ¡®Submit¡¯ button to \ncheck if this number is correct.");
		instruction.setFont(Font.font("Times New Roman", 15));
		instruction.setFill(Color.BLACK);

		returnResultText = new Text("Result will display after you enter a number.");
		returnResultText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		returnResultText.setFill(Color.BLACK);

		newGameNotice = new Text("Game Starts!");
		newGameNotice.setFont(Font.font("Segoe Script", FontWeight.BOLD, 25));
		newGameNotice.setFill(Color.BLACK);

		errorMessage = new Text(" ");

		numberInputField = new TextField();
		numberInputField.setOnAction(this::processTextField);

		submitButton = new Button("Submit Number");
		submitButton.setOnAction(this::processTextField);

		againButton = new Button("Play Again!");
		againButton.setOnAction(this::handleAgainButton);

		VBox topDisplay = new VBox();
		topDisplay.setSpacing(10);
		topDisplay.setAlignment(Pos.CENTER);
		topDisplay.getChildren().add(gameTitle);
		topDisplay.getChildren().add(instruction);
		borderPane.setTop(topDisplay);

		centerDisplay = new VBox();
		centerDisplay.setSpacing(10);
		centerDisplay.setAlignment(Pos.CENTER);
		centerDisplay.getChildren().add(newGameNotice);
		centerDisplay.getChildren().add(numberInputField);
		centerDisplay.getChildren().add(submitButton);
		centerDisplay.getChildren().add(returnResultText);
		centerDisplay.getChildren().add(errorMessage);
		borderPane.setCenter(centerDisplay);

		Scene scene = new Scene(borderPane, 500, 300, Color.ALICEBLUE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hi-Lo Guessing Game");
		primaryStage.show();
	}

	private void processTextField(ActionEvent event) {
		newGameNotice.setText(" ");
		String userInputText = numberInputField.getText();

		if (isInteger(userInputText)) {
			userNumber = Integer.parseInt(userInputText);
			errorMessage.setText(" ");
			if (userNumber != correctNumber) {
				if (userNumber < correctNumber) {
					returnResultText.setText("Result: The number you entered is lower than the correct number.");
					returnResultText.setFont(Font.font("Ink Free", 15));
					returnResultText.setFill(Color.BLACK);
				} else if (userNumber > correctNumber) {
					returnResultText.setText("Result: The number you entered is higher than the correct number.");
					returnResultText.setFont(Font.font("Ink Free", 15));
					returnResultText.setFill(Color.BLACK);
				}
				numberInputField.clear();
			} else if (userNumber == correctNumber) {
				returnResultText.setText("Result: You entered " + userNumber + " It is the correct number!");
				returnResultText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
				returnResultText.setFill(Color.RED);
				numberInputField.clear();
				centerDisplay.getChildren().add(againButton);
			}
		} else {
			numberInputField.clear();
			errorMessage.setText("Error! That is not a number! ");
			errorMessage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 28));
			errorMessage.setFill(Color.RED);
		}
	}

	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private void handleAgainButton(ActionEvent event) {
		Random ran = new Random();
		correctNumber = ran.nextInt(100);
		newGameNotice.setText("You Start a New Game!");
		returnResultText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		returnResultText.setFill(Color.PLUM);

		returnResultText.setText("Result will be displayed after you enter a number.");
		returnResultText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		returnResultText.setFill(Color.BLACK);
		centerDisplay.getChildren().remove(againButton);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
