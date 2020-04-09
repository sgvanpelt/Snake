package Model;

import java.util.ArrayList;

public class Snake {

	// constants
	private final int squaresize = 40;
	private final int startPositionX = 280;
	private final int startPositionY = 120;

	// instance variables
	private int positionX;
	private int positionY;
	private ArrayList<BodyPart> bp;
	private Direction direction;

	// constructor
	public Snake() {
		this.positionX = startPositionX;
		this.positionY = startPositionY;
		direction = Direction.RIGHT;
		bp = new ArrayList<BodyPart>();
		createBodyParts();
	}

	// start moving the snake in the correct direction
	public void startMove() {
		BodyPart bodypart = bp.get(bp.size() - 1);
		bodypart.setBodyPartX(positionX);
		bodypart.setBodyPartY(positionY);
		bp.remove(bodypart);
		orderArrayList(bodypart);

		if (direction == Direction.RIGHT) {
			positionX += squaresize;
		} 
		else if (direction == Direction.LEFT) {
			positionX -= squaresize;
		} 
		else if (direction == Direction.UP) {
			positionY -= squaresize;
		} 
		else if (direction == Direction.DOWN) {
			positionY += squaresize;
		}
	}

	public ArrayList<BodyPart> getSnake() {
		return bp;
	}

	// check if the snake and bodyparts are on specific x and y coordinates
	public boolean checkSnake(int x, int y) {
		if (x == getSnakeX() && y == getSnakeY()) {
			return false;
		}
		for (BodyPart bps : bp) {
			if (x == bps.getBodyPartX() && y == bps.getBodyPartY()) {
				return false;
			}
		}
		return true;
	}

	// create and add 4 bodyparts to the snake for the start
	public void createBodyParts() {
		int x = getSnakeX() - squaresize;
		for (int i = 0; i < 4; i++) {
			bp.add(new BodyPart(x, getSnakeY()));
			x = x - squaresize;
		}
	}

	// create and add new bodyparts when needed
	public void addBodyParts(int value) {
		int x = bp.get(bp.size() - 1).getBodyPartX();
		int y = bp.get(bp.size() - 1).getBodyPartY();
		for (int i = 0; i < value; i++) {
			bp.add(new BodyPart(x, y));
		}
	}

	// get the x coordinate of a bodypart
	public ArrayList<Integer> getBodyX() {
		ArrayList<Integer> bodyX = new ArrayList<Integer>();
		for (int i = 0; i < bp.size(); i++) {
			bodyX.add(bp.get(i).getBodyPartX());
		}
		return bodyX;
	}

	// get the y coordinate of a bodypart
	public ArrayList<Integer> getBodyY() {
		ArrayList<Integer> bodyY = new ArrayList<Integer>();
		for (int i = 0; i < bp.size(); i++) {
			bodyY.add(bp.get(i).getBodyPartY());
		}
		return bodyY;
	}

	// check if the head of the snake hits a bodypart
	public boolean checkBodyDead() {
		for (int i = 0; i < bp.size(); i++) {
			if (getSnakeX() == bp.get(i).getBodyPartX() && getSnakeY() == bp.get(i).getBodyPartY()) {
				return true;
			}
		}
		return false;
	}

	// remove half of a body when the snake hits a bear spot
	public void removeHalfBody() {
		// afronden naar beneden
		int amount = Math.floorDiv((bp.size() + 1), 2);

		for (int i = 0; i < amount; i++) {
			bp.remove(bp.size() - 1);
		}
	}

	// check if the snake is too short (<= 4)
	public boolean checkShortSize() {
		if (bp.size() < 4) {
			return true;
		}
		return false;
	}

	// order the bodypart arraylist so the spots are displayed correctly
	// if the snake moves the last bodypart gets set on the position of the head,
	// this method orders the array so its correct
	private void orderArrayList(BodyPart bodyp) {
		ArrayList<BodyPart> oldList = bp;
		bp = new ArrayList<BodyPart>();
		bp.add(bodyp);
		bp.addAll(oldList);
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getSnakeX() {
		return positionX;
	}

	public void setSnakeX(int positionX) {
		this.positionX = positionX;
	}

	public int getSnakeY() {
		return positionY;
	}

	public void setSnakeY(int positionY) {
		this.positionY = positionY;
	}

}
