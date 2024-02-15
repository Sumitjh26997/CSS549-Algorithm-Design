/**
 * Class to represent set of points
 * Functionality includes adding a point to the set, getting the size of the set, 
 * getting a point from the set, sorting the set by x and y coordinates.
 * @author Sumit Hotchandani
 */
package program2;

import java.util.*;

public class PointSet {
	private List<Point> points;

	/**
	 * Constructor to create a point set.
	 * pre: none
	 * post: a point set is created.
	 */
	public PointSet() {
		this.points = new ArrayList<>();
	}

	/**
	 * function to add a point to the set.
	 * pre: point set is initialized.
	 * post: a point is added to the set.
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	/**
	 * function to get the size of the set.
	 * pre: none
	 * post: size of the set is returned.
	 */
	public int size() {
		return points.size();
	}

	/**
	 * function to get a point from the set.
	 * pre: point set is initialized.
	 * post: a point from the set is returned.
	 */
	public Point get(int index) {
			return points.get(index);
	}

	/**
	 * function to sort the set by x coordinates.
	 * pre: point set has values added.
	 * post: set is sorted by x coordinates and returned.
	 */
	public List<Point> sortByX() {
		List<Point> sortedX = new ArrayList<>(this.points);
		Collections.sort(sortedX, Comparator.comparingDouble(p -> p.getX()));
		return sortedX;
	}

	/**
	 * function to sort the set by y coordinates.
	 * pre: point set has values added.
	 * post: set is sorted by y coordinates and returned.
	 */
	public List<Point> sortByY() {
		List<Point> sortedY = new ArrayList<>(this.points);
		Collections.sort(sortedY, Comparator.comparingDouble(p -> p.getY()));
		return sortedY;
	}
}
