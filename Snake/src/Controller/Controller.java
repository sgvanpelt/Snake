package Controller;

import java.util.Random;

import Model.Direction;
import Model.Game;
import View.DrawPane;
import View.GameOverScene;
import View.GameScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	// constants
	private final int paneWidth = 760;
	private final int paneHeight = 600;

	// instance variables
	private Timeline timeline;
	private Timeline timer;
	private Timeline speedtimer;
	private SimpleStringProperty time;
	private Game game;
	private GameScene gamescene;
	private Stage stage;
	private GameOverScene gameoverscene;
	private DrawPane drawpane;
	private int speed = 1;

	// constructor
	public Controller(Stage stage) {
		this.stage = stage;
		game = new Game();

		time = new SimpleStringProperty("00:00.000");
		createTimer();
		speedTimer();

		gamescene = new GameScene(this, paneWidth, paneHeight);
		gameoverscene = new GameOverScene(this, paneWidth, paneHeight);
		drawpane = gamescene.getDrawPane();

		updateDrawPane();
		directionSnake();

		stage.setScene(gamescene);
		stage.setTitle("PROG4 ASS Snake - Bas van Pelt");
		stage.setResizable(false);
		stage.show();
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

	}

	// primary timer than runs the moving of the snake and the functionality of the spots,
	// when the snake dies the screen switches to the Game Over scene
	public void gameTimer() {
		timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
			game.makeCheckSpots();
			game.getSnake().startMove();
			updateDrawPane();
			if (snakeDies()) {
				pauseTimer();
				stage.setScene(gameoverscene);
			}
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setRate(speed);

	}

	// timer that handles the speed of the snake and the functionality of the slider
	public void speedTimer() {
		speedtimer = new Timeline(new KeyFrame(Duration.millis(5000), event -> {
			if (speed < 12) {
				setSpeed(speed + 1);
				timeline.setRate(speed);
				getGameScene().getDashBoard().setSpeedSlider();
				;
			}
		}));
		speedtimer.setCycleCount(Animation.INDEFINITE);
	}

	// timer that handles the timer of the game, the creation of spots and adding bodyparts
	public void createTimer() {
		timer = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			private int minutes = 0;
			private int seconds = 0;
			private int milliseconds = 0;

			private String mnts;
			private String scnds;
			private String milis;

			@Override
			public void handle(ActionEvent event) {
				milliseconds++;
				if (milliseconds == 1000) {
					milliseconds = 0;
					seconds++;
					if (seconds == 60) {
						seconds = 0;
						minutes++;
					}
				}
				String newTime;
				if (minutes < 10) {
					mnts = "" + 0 + minutes;
				} else {
					mnts = "" + minutes;
				}
				if (seconds < 10) {
					scnds = "" + 0 + seconds;
				} else {
					scnds = "" + seconds;
				}
				if (milliseconds < 100) {
					milis = "" + 0 + milliseconds;
				} else {
					milis = "" + milliseconds;
				}
				if (milliseconds < 10) {
					milis = "" + 0 + 0 + milliseconds;
				}
				newTime = "" + mnts + ":" + scnds + "." + milis;
				time.set(newTime);

				if (seconds % 2 == 0 && milliseconds == 0) {
					Random r = new Random();
					if (r.nextInt(1) < 4) {
						game.createSpots(paneWidth, paneHeight);
					}
				}
				if (seconds % 6 == 0 && milliseconds == 0) {
					game.addBodyParts(2);
				}

			}

		}));

		timer.setCycleCount(Timeline.INDEFINITE);

	}
	
	// start each timer
	public void startTimer() {
		timeline.play();
		timer.play();
		speedtimer.play();
	}

	// pause each timer
	public void pauseTimer() {
		timeline.pause();
		timer.pause();
		speedtimer.pause();
	}

	public SimpleStringProperty getTime() {
		return time;
	}

	// check if the snake dies when it goes outside the borders, the head hits one of its own bodyparts and if it it dies because of a spot
	private boolean snakeDies() {
		if (game.getSnake().getSnakeX() == getGameScene().getDrawPane().getWidth() || game.getSnake().getSnakeX() < 0) {
			return true;
		}
		if (game.getSnake().getSnakeY() == getGameScene().getDrawPane().getHeight() || game.getSnake().getSnakeY() < 0) {
			return true;
		}
		if (game.checkBodyDead()) {
			return true;
		}
		if (game.makeCheckSpots()) {
			return true;
		}
		return false;
	}

	// set the direction corresponding to your keyboard presses
	public void directionSnake() {
		stage.addEventFilter(KeyEvent.KEY_RELEASED, KeyEvent -> {
			switch (KeyEvent.getCode()) {
			case LEFT:
				game.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				game.setDirection(Direction.RIGHT);
				break;
			case UP:
				game.setDirection(Direction.UP);
				break;
			case DOWN:
				game.setDirection(Direction.DOWN);
				break;
			default:
				break;
			}
		});
	}

	// update the drawpane so it matches the changes that happened
	public void updateDrawPane() {
		drawpane.clearCanvas();
		drawpane.createSpots(game.getSpotX(), game.getSpotY(), game.getSpotMarker());
		drawpane.draw(game.getBodyX(), game.getBodyY(), game.getHeadX(), game.getHeadY());
	}

	public void exit() {
		Platform.exit();
	}

	public GameScene getGameScene() {
		return gamescene;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
