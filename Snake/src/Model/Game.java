package Model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	// constants
	private final int squaresize = 40;

	// instance variables
	private Snake snake;
	private ArrayList<Spot> spots;

	// constructor
	public Game() {
		snake = new Snake();
		spots = new ArrayList<Spot>();
	}
	
	public Snake getSnake() {
		return snake;
	}

	// create the correct spot placement for a new spot
	public int[] createSpotsPlacement(int width, int height) {
		Random r = new Random();
		boolean coordscheck = false;
		int[] coordinates = new int[2];
		
		while (!coordscheck) {
			// positionize the spot and make it the right size
			int x = r.nextInt(width / squaresize);
			int y = r.nextInt(height / squaresize);
			x = x * squaresize;
			y = y * squaresize;
			
			// x en y create a random spot
			if (snake.checkSnake(x, y) && checkSpots(x, y)) {
				coordscheck = true;
				coordinates[0] = x;
				coordinates[1] = y;
			}
		}
		return coordinates;
	}

	// check if a spot already exists on the square it wants to place
	public boolean checkSpots(int x, int y) {
		for (Spot sp : spots) {
			if (x == sp.getSpotX() && y == sp.getSpotY()) {
				return false;
			}
		}
		return true;
	}

	// create the spots with a random marker
	public void createSpots(int width, int height) {
		int[] coordinates = createSpotsPlacement(width, height);
		spots.add(new Spot(coordinates[0], coordinates[1], Marker.generateRandomMarker()));
	}

	// create the checks for each marker and remove them after they are hit
	public boolean makeCheckSpots() {
		for (int i = 0; i < spots.size(); i++) {
			if (snake.getSnakeX() == spots.get(i).getSpotX() && snake.getSnakeY() == spots.get(i).getSpotY()) {
				if (Marker.MOUSE == spots.get(i).getMarker()) {
					snake.addBodyParts(3);
					spots.remove(i);
					return false;
				} else if (Marker.BEAR == spots.get(i).getMarker()) {
					snake.removeHalfBody();
					spots.remove(i);
					if (snake.checkShortSize()) {
						return true;
					}
					return false;
				} else if (Marker.FIRE == spots.get(i).getMarker()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Marker> getSpotMarker() {
		ArrayList<Marker> marks = new ArrayList<Marker>();
		for (int i = 0; i < spots.size(); i++) {
			marks.add(spots.get(i).getMarker());
		}
		return marks;
	}
	
	public ArrayList<Integer> getSpotX() {
		ArrayList<Integer> spotX = new ArrayList<Integer>();
		for (int i = 0; i < spots.size(); i++) {
			spotX.add(spots.get(i).getSpotX());
		}
		return spotX;
	}

	public ArrayList<Integer> getSpotY() {
		ArrayList<Integer> spotY = new ArrayList<Integer>();
		for (int i = 0; i < spots.size(); i++) {
			spotY.add(spots.get(i).getSpotY());
		}
		return spotY;
	}

	public ArrayList<Integer> getSpotsX() {
		return getSpotX();
	}

	public ArrayList<Integer> getSpotsY() {
		return getSpotY();
	}

	public boolean checkBodyDead() {
		return snake.checkBodyDead();
	}

	public void setDirection(Direction direction) {
		snake.setDirection(direction);
	}

	public void addBodyParts(int value) {
		snake.addBodyParts(value);
	}
	
	public int getHeadX() {
		return snake.getSnakeX();
	}

	public int getHeadY() {
		return snake.getSnakeY();
	}

	public ArrayList<Integer> getBodyX() {
		return snake.getBodyX();
	}

	public ArrayList<Integer> getBodyY() {
		return snake.getBodyY();
	}

}
