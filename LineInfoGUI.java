import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LineInfoGUI extends Application {

	private Pane pane;
	private BorderPane borderPane;
	private Circle startPoint, endPoint;
	private Line line;
	private Button distanceButton, midpointButton, vertHorzButton, slopeButton;
	private Text distanceText, midpointText, vertHorxText, timeText, slopeText; // USE THESE!
	private LineInfoDisplayer lineInfoDisplayer; // USE THIS!

	private static final int CIRCLE_RADIUS = 5;

	public void start(Stage primaryStage) {
		borderPane = new BorderPane();

		pane = new Pane();
		pane.setOnMouseClicked(this::handleMouseClicks);
		borderPane.setCenter(pane);

		distanceText = new Text("");
		distanceButton = new Button("Calculate Distance");
		distanceButton.setOnAction((event) -> {
			lineInfoDisplayer = LineInfoDisplayer
					.createLineInfoDisplayer(LineInfoDisplayer.InfoType.DISTANCE);
			String distanceInfor = lineInfoDisplayer.getInfo(line);
			distanceText.setText(distanceInfor);
		});
		// YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON.
		// USE A LAMBDA TO ASSIGN A VALUE TO lineInfoDisplayer AND
		// USE OBJECT TO GET THE STRING DESCRIBING THE DISTANCE.
		// UPDATE THE TEXT OBJECT TO DISPLAY THAT INFO.

		midpointText = new Text("");
		midpointButton = new Button("Calculate Midpoint");
		midpointButton.setOnAction((event) -> {
			lineInfoDisplayer = LineInfoDisplayer
					.createLineInfoDisplayer(LineInfoDisplayer.InfoType.MIDPOINT);
			String midpointInfor = lineInfoDisplayer.getInfo(line);
			midpointText.setText(midpointInfor);
		});
		// YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON.
		// SAME AS ABOVE BUT FOR MIDPOINT!

		vertHorxText = new Text("");
		vertHorzButton = new Button("Determine Vertical/Horizontal");
		vertHorzButton.setOnAction((event) -> {
			lineInfoDisplayer = LineInfoDisplayer
					.createLineInfoDisplayer(LineInfoDisplayer.InfoType.VERTHORZ);
			String vertOrHorzInfor = lineInfoDisplayer.getInfo(line);
			vertHorxText.setText(vertOrHorzInfor);
		});
		// YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON.
		// SAME AS ABOVE BUT FOR VERTICAL/HORIZONTAL!

		slopeText = new Text("");
		slopeButton = new Button("Calculate Slope");
		slopeButton.setOnAction((event) -> {
			lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.SLOPE);
			String slopeInfor = lineInfoDisplayer.getInfo(line);
			slopeText.setText(slopeInfor);
		});

		timeText = new Text("Please Create a Line.");

		// SOMEWHERE IN THE CODE YOU WILL UPDATE THE TEXT OF THIS!

		TilePane distancePane = new TilePane(distanceButton, distanceText);
		distancePane.setAlignment(Pos.CENTER);
		TilePane midpointPane = new TilePane(midpointButton, midpointText);
		midpointPane.setAlignment(Pos.CENTER);
		TilePane vertHorzPane = new TilePane(vertHorzButton, vertHorxText);
		vertHorzPane.setAlignment(Pos.CENTER);
		TilePane slopePane = new TilePane(slopeButton, slopeText);
		slopePane.setAlignment(Pos.CENTER);
		TilePane timePane = new TilePane(timeText);
		timePane.setAlignment(Pos.CENTER);

		VBox controlBox = new VBox(distancePane, midpointPane, vertHorzPane, slopePane, timePane);
		controlBox.setAlignment(Pos.CENTER);
		controlBox.setSpacing(15);

		borderPane.setBottom(controlBox);

		Scene scene = new Scene(borderPane, 500, 500, Color.WHITE);
		primaryStage.setTitle("Line Information");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void handleMouseClicks(MouseEvent event) {
		double x = event.getX();
		double y = event.getY();

		if (startPoint == null) { // no start point yet, set the start point
			startPoint = new Circle(x, y, CIRCLE_RADIUS);
			pane.getChildren().add(startPoint);
			createCoordinates(x, y);
			line = null;
		} else if (endPoint == null) { // start point, but not end point yet
										// set the end point
			endPoint = new Circle(x, y, CIRCLE_RADIUS);
			pane.getChildren().add(endPoint);
			createCoordinates(x, y);

			line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(),
					endPoint.getCenterY());
			pane.getChildren().add(line);

			LocalDateTime lineTime = LocalDateTime.now();
			String lineCompleted = "User created a line at: " + lineTime.getMonth() + " " + lineTime.getDayOfMonth()
					+ ", " + lineTime.getHour() + ": " + lineTime.getMinute() + ": " + lineTime.getSecond();
			timeText.setText(lineCompleted);

		} else { // startPoint != null && endPoint !=null
			// both start and end are there, so this is starting a new line;
			// set a new start point
			pane.getChildren().clear();
			endPoint = null;
			line = null;
			distanceText.setText("");
			midpointText.setText("");
			vertHorxText.setText("");
			slopeText.setText("");

			startPoint = new Circle(x, y, CIRCLE_RADIUS);
			pane.getChildren().add(startPoint);
			createCoordinates(x, y);
		}
	}

	private void createCoordinates(double x, double y) {
		String coordinateString = "(" + x + ", " + y + ")";
		Text coordinates = new Text(x - CIRCLE_RADIUS, y - CIRCLE_RADIUS - 2, coordinateString);
		pane.getChildren().add(coordinates);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
