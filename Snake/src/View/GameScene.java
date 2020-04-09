package View;

import Controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {

	// instance variables
	private BorderPane root;
	private DrawPane drawpane;
	private Controller controller;
	private DashBoard dashboard;

	// constructor
	public GameScene(Controller controller, int paneWidth, int paneHeight) {
		super(new Pane());
		this.controller = controller;
		
		root = new BorderPane();
		root.setPrefSize(paneWidth, paneHeight);
		this.setRoot(root);

		drawpane = new DrawPane(paneWidth, paneHeight);
		dashboard = new DashBoard(controller);

		root.setCenter(drawpane);
		root.setBottom(dashboard);

	}

	public DrawPane getDrawPane() {
		return drawpane;
	}

	public DashBoard getDashBoard() {
		return dashboard;
	}

}
