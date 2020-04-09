package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverScene extends Scene {
	
	// constants
	private final int fontsize = 60;

	// instance variables
	private Controller controller;
	private VBox labelpane;
	private BorderPane root;

	// constructor
	public GameOverScene(Controller controller, int paneWidth, int paneHeight) {
		super(new Pane());
		this.controller = controller;
		
		root = new BorderPane();
		root.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		root.setPrefSize(paneWidth, paneHeight);
		setRoot(root);

		createLabels();
		labelpane.setAlignment(Pos.CENTER);
		labelpane.setSpacing(20);
		root.setCenter(labelpane);
	}

	public void createLabels() {
		labelpane = new VBox();

		Label gameover = new Label();
		gameover.setText("Game Over");
		gameover.setTextFill(Color.BLACK);
		gameover.setStyle("-fx-font-weight: bold");
		gameover.setFont(Font.font("Arial", fontsize));
		gameover.setAlignment(Pos.CENTER);

		Label timer = new Label();
		timer.setTextFill(Color.WHITE);
		timer.setFont(Font.font("Arial", fontsize));
		timer.textProperty().bind(controller.getTime());
		timer.setAlignment(Pos.CENTER);

		labelpane.getChildren().addAll(gameover, timer);

	}

}
