package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DashBoard extends HBox {

	// constants
	private final int dashboardheight = 50;
	private final int speedsliderwidth = 250;
	private final int labelwidth = 100;
	private final int labelheight = 40;

	// instance variables
	private HBox sliderPane;
	private BorderPane timePane;
	private Controller controller;
	private Slider speedSlider;

	// constructor
	public DashBoard(Controller controller) {
		this.controller = controller;
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		
		setPrefHeight(dashboardheight);
		setMinHeight(dashboardheight);
		setMaxHeight(dashboardheight);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));

		ToggleButton pauseButton = new ToggleButton("start");
		pauseButton.setOnAction(event -> {
			if (pauseButton.isSelected()) {
				pauseButton.setText("pause");
				controller.gameTimer();
				controller.startTimer();
			} else {
				pauseButton.setText("start");
				controller.pauseTimer();
			}
		});

		Button exitButton = new Button();
		exitButton.setText("exit");
		exitButton.setOnAction(e -> controller.exit());

		createSliderPane();
		createTimerPane();

		this.getChildren().addAll(pauseButton, exitButton, sliderPane, timePane);

	}

	public void createSliderPane() {
		speedSlider = new Slider(1, 12, 1);
		speedSlider.setMinorTickCount(0);
		speedSlider.setMajorTickUnit(1);
		speedSlider.setPrefWidth(speedsliderwidth);
		speedSlider.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		speedSlider.setShowTickLabels(true);
		speedSlider.setShowTickMarks(true);
		speedSlider.setDisable(true);
		speedSlider.setOpacity(255);
		sliderPane = new HBox(speedSlider);
		sliderPane.setAlignment(Pos.CENTER);
	}

	public void setSpeedSlider() {
		speedSlider.setValue(controller.getSpeed());
	}

	public void createTimerPane() {
		timePane = new BorderPane();
		Label playtimeLabel = new Label();
		playtimeLabel.textProperty().bind(controller.getTime());
		playtimeLabel.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		playtimeLabel.setPrefWidth(labelwidth);
		playtimeLabel.setPrefHeight(labelheight);
		playtimeLabel.setAlignment(Pos.CENTER);
		timePane.setCenter(playtimeLabel);
	}

}
