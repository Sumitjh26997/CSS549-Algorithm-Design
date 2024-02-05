package program2;

/**
 * Class to represent a point
 * A point is represented by its x and y coordinates.
 * Functionality includes calculating the distance between two points and getting the coordinates of a point.
 */
public class Point {
	private int x;
	private int y;

	/**
	 * Constructor to create a point with given coordinates.
	 * pre: none
	 * post: a point is created with given coordinates.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * function to calculate the distance between two points.
	 * pre: none
	 * post: distance between two points is calculated and returned.
	 */
	public double distance(Point p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
	}

	/**
	 * function to get the coordinates of a point.
	 * pre: none
	 * post: coordinates of a point are returned.
	 */
	public int[] getCoodinates() {
		return new int[] {this.x, this.y};
	}
}
