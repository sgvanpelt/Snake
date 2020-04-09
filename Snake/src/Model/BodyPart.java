package Model;

public class BodyPart {
	
	// instance variables
	private int positionX;
	private int positionY;
	
	// constructor
	public BodyPart(int x, int y) {
		this.setBodyPartX(x);
		this.setBodyPartY(y);
	}

	public int getBodyPartX() {
		return positionX;
	}

	public void setBodyPartX(int positionX) {
		this.positionX = positionX;
	}

	public int getBodyPartY() {
		return positionY;
	}

	public void setBodyPartY(int positionY) {
		this.positionY = positionY;
	}
	
}
