package Model;

public class Spot {

	// instance variables
	private int positionX;
	private int positionY;
	private Marker marker;

	// constructor
	public Spot(int x, int y, Marker marker) {
		this.setSpotX(x);
		this.setSpotY(y);
		this.setMarker(marker);
	}

	public int getSpotX() {
		return positionX;
	}

	public void setSpotX(int positionX) {
		this.positionX = positionX;
	}

	public int getSpotY() {
		return positionY;
	}

	public void setSpotY(int positionY) {
		this.positionY = positionY;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}
}
