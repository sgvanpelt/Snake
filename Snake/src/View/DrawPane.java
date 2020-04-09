package View;

import java.util.ArrayList;

import Model.Marker;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class DrawPane extends StackPane {

	// constants
	private final int squaresize = 40;

	// instance variables
	private int rows = 15;
	private int cols = 19;
	private int paneWidth;
	private int paneHeight;
	private BorderPane squarespot;
	private GridPane gridpane;
	private Canvas canvas;
	private GraphicsContext g;
	private Image mouse;
	private Image bear;
	private Image fire;

	// constructor
	public DrawPane(int paneWidth, int paneHeight) {
		this.paneWidth = paneWidth;
		this.paneHeight = paneHeight;
		
		setPrefSize(paneWidth, paneHeight);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		mouse = new Image(getClass().getResource("/images/mouse.png").toString());
		bear = new Image(getClass().getResource("/images/bear.png").toString());
		fire = new Image(getClass().getResource("/images/fire.png").toString());

		canvas = new Canvas(paneWidth, paneHeight);
		g = canvas.getGraphicsContext2D();
		gridpane = new GridPane();
		// createGrid();
		this.getChildren().addAll(gridpane, canvas);
		createSquares();
	}

	public void clearCanvas() {
		g.clearRect(0, 0, paneWidth, paneHeight);
	}

	// draw the snake and bodyparts
	public void draw(ArrayList<Integer> x, ArrayList<Integer> y, int snakeX, int snakeY) {
		g.setFill(Color.RED);
		g.fillOval(snakeX, snakeY, squaresize, squaresize);

		g.setFill(Color.ORANGE);
		for (int i = 0; i < x.size(); i++) {
			g.fillOval(x.get(i), y.get(i), squaresize, squaresize);
		}

	}

	// create the grid of the game
	public void createGrid() {
		gridpane = new GridPane();
		for (int i = 0; i < cols; i++) {
			ColumnConstraints columnConstraint = new ColumnConstraints();
			columnConstraint.setPrefWidth(squaresize);
			gridpane.getColumnConstraints().add(columnConstraint);
		}
		for (int j = 0; j < rows; j++) {
			RowConstraints rowConstraint = new RowConstraints();
			rowConstraint.setPrefHeight(squaresize);
			gridpane.getRowConstraints().add(rowConstraint);
		}
		this.getChildren().add(gridpane);
	}

	// create the squares on the grid
	public void createSquares() {

		for (int xspot = 0; xspot < cols; xspot++) {

			for (int yspot = 0; yspot < rows; yspot++) {

				squarespot = new BorderPane();
				squarespot.setPrefWidth(squaresize);
				squarespot.setPrefHeight(squaresize);

				if ((xspot & 1) == 0) {
					if ((yspot & 1) == 0) {
						squarespot.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
					} 
					else {
						squarespot.setBackground(new Background(new BackgroundFill(Color.valueOf("#1a1a1a"), null, null)));
					}
				} 
				else {
					if ((yspot & 1) == 0) {
						squarespot.setBackground(new Background(new BackgroundFill(Color.valueOf("#1a1a1a"), null, null)));
					} 
					else {
						squarespot.setBackground(new Background(new BackgroundFill(Color.valueOf("#333333"), null, null)));
					}
				}
				gridpane.add(squarespot, xspot, yspot);
			}
		}

	}

	// give an image to a generated spot
	public void createSpots(ArrayList<Integer> x, ArrayList<Integer> y, ArrayList<Marker> m) {
		for (int i = 0; i < x.size(); i++) {
			if (m.get(i) == Marker.MOUSE) {
				g.drawImage(mouse, x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
			} else if (m.get(i) == Marker.BEAR) {
				g.drawImage(bear, x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
			} else if (m.get(i) == Marker.FIRE) {
				g.drawImage(fire, x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
			}
		}
	}

}
