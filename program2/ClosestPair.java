/**
 * Class for closest pair of points.
 * Functionalities include reading input from a file, finding the closest pair of points, 
 * and printing the distance between the closest pair of points.
 * @author Sumit Hotchandani
 */
package program2;

import java.io.*;
import java.util.*;

public class ClosestPair {
	private PointSet pointSet;

	/**
	 * Constructor to assign a point set to the closest pair object.
	 * pre: none
	 * post: a point set is assigned to the closest pair object.
	 */
	ClosestPair(PointSet pointSet) {
		this.pointSet = pointSet;
	}

	/**
	 * function to find the closest pair of points.
	 * pre: point set is initialized.
	 * post: closest pair of points is found and printed.
	 */
	private double closest() {
		List<Point> sortedX = this.pointSet.sortByX();
		List<Point> sortedY = this.pointSet.sortByY();

		// update indices of points after sorting by x coordinate 
		// required for printing the output in the correct order
		for (int i = 0; i < sortedX.size(); i++) {
			sortedX.get(i).setIndex(i);
		}
		
		return closestPairRecursive(sortedX, sortedY, this.pointSet.size());
	}

	/**
	 * function to calculate the distance between the closest pair of points.
	 * called when the number of points is less than or equal to 3 (base case).
	 * pre: base case for recursive function is reached.
	 * post: distance between the closest pair of points is calculated and returned.
	 */
	private double getDistance(List<Point> P, int n) {
		double min = Double.MAX_VALUE;
		
		// compare each point with every other point
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (P.get(i).distance(P.get(j)) < min) {
					min = P.get(i).distance(P.get(j));
				}
			}
		}

		return min;
	}

	/**
	 * Recursive function to find the closest pair of points using divide and conquer strategy.
	 * pre: point set is initialized and sorted by x and y coordinates.
	 * post: closest pair of points is found and printed.
	 */
	private double closestPairRecursive(List<Point> Px, List<Point> Py, int n) {
		// base case when number of points is less than or equal to 3
		if (n <= 3) {
			double dist = getDistance (Px, n);
			System.out.printf("D[%d,%d]: %.4f\n", Px.get(0).getIndex(), Px.get(n-1).getIndex(), dist);
			return dist;
		}

		// divide the set into two halves
		int mid = n / 2;
		Point midPoint = Px.get(mid);

		// create lists of points sorted by y coordinates on the left and right of the mid point
		List<Point> Pyl = new ArrayList<>();
		List<Point> Pyr = new ArrayList<>();
		
		for (int i = 0; i < Py.size(); i++) {
			if (Py.get(i).getX() <= midPoint.getX()) {
				Pyl.add(Py.get(i));
			} else {
				Pyr.add(Py.get(i));
			}
		}

		// recursively find the closest pair of points in the left and right halves
		double dl = closestPairRecursive(Px.subList(0, mid), Pyl, mid);
		double dr = closestPairRecursive(Px.subList(mid, n), Pyr, n - mid);

		// find the minimum distance between the closest pair of points in the left and right halves
		double d = Math.min(dl, dr);

		// create a strip of points within d distance from the mid point
		List<Point> strip = new ArrayList<>();
		for (int i = 0; i < Py.size(); i++) {
			if (Math.abs(Py.get(i).getX() - midPoint.getX()) < d) {
				strip.add(Py.get(i));
			}
		}

		// find the closest pair of points in the strip
		double stripDistance = stripClosest(strip, d);

		System.out.printf("D[%d,%d]: %.4f\n", Px.get(0).getIndex(), Px.get(n-1).getIndex(), Math.min(d, stripDistance));
		
		// return the minimum distance between the closest pair of points in the left and right halves and the strip
		return Math.min(d, stripDistance);
	}

	/**
	 * function to calculate the distance between the closest pair of points in the strip.
	 * pre: strip of points is created, based on the distance from the mid point.
	 * post: distance between the closest pair of points in the strip is calculated and returned.
	 */
	private double stripClosest(List<Point> strip, double d) {
		double min = d;
		
		for (int i = 0; i < strip.size(); i++) {
			for (int j = i + 1; j < strip.size() && (strip.get(j).getY() - strip.get(i).getY()) < min; j++) {
				if (strip.get(i).distance(strip.get(j)) < min) {
					min = strip.get(i).distance(strip.get(j));
				}
			}
		}

		return min;
	}
 
	/**
	 * main function
	 * pre: none
	 * post: input is read from a file, closest pair of points is found and printed.
	 */
	public static void main(String args[]) throws IOException {
		PointSet pointSet = new PointSet();
		try (BufferedReader br = new BufferedReader(new FileReader("program2/program2data_50k.txt"))) {
			int n = Integer.parseInt(br.readLine().trim());
			for (int i = 0; i < n; i++) {
					String[] coordinates = br.readLine().split("\\s+");
					double x = Double.parseDouble(coordinates[0]);
					double y = Double.parseDouble(coordinates[1]);
					pointSet.addPoint(new Point(x, y, i));
			}
		} catch (IOException e) {
				e.printStackTrace();
		}

		ClosestPair cp = new ClosestPair(pointSet);
		cp.closest();
	}
}
