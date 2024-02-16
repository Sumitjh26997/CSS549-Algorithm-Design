/**
 * Class to represent a point
 * Functionality includes calculating the distance between two points and getting the coordinates of a point.
 * @author Sumit Hotchandani
 */
package program2;

/**
 * Class to represent a point
 * A point is represented by its x and y coordinates.
 * Functionality includes calculating the distance between two points and getting the coordinates of a point.
 */
public class Point {
	private double x;
	private double y;
	private int index;

	/**
	 * Constructor to create a point with given coordinates.
	 * pre: none
	 * post: a point is created with given coordinates.
	 */
	public Point(double x, double y, int index) {
		this.x = x;
		this.y = y;
		this.index = index;
	}

	/**
	 * function to calculate the Euclidean distance between two points.
	 * pre: none
	 * post: Euclidean distance between two points is calculated and returned.
	 */
	public double distance(Point p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
	}

	/**
	 * function to get the x coordinate of a point.
	 * pre: none
	 * post: x coordinate of a point is returned.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * function to get the y coordinate of a point.
	 * pre: none
	 * post: y coordinate of a point is returned.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * function to get the index of a point.
	 * pre: none
	 * post: index of a point is returned.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * function to set the index of a point.
	 * pre: none
	 * post: index of a point is set.
	 */
	public void setIndex(int index) {
    this.index = index;
	}
}
