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

	/**
	 * Constructor to create a point with given coordinates.
	 * pre: none
	 * post: a point is created with given coordinates.
	 */
	public Point(double x, double y) {
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
	public double[] getCoodinates() {
		return new double[] {this.x, this.y};
	}

	public double getX() {
		return this.x;
	}
}
