package program2;

import java.util.*;

public class PointSet {
	private List<Point> points;

	public PointSet() {
		this.points = new ArrayList<>();
	}

	public void addPoint(Point point) {
		this.points.add(point);
		Collections.sort(this.points, Comparator.comparingDouble(p -> p.getX()));
	}
	
	public int size() {
		return points.size();
	}

	public Point get(int index) {
			return points.get(index);
	}
}
