package program3;
/**
 * Class to represent an Edge in a graph.
 * Initially, the capacity of the edge is set to the given capacity and the flow is set to 0.
 * @author Sumit Hotchandani
 */
class Edge {
	int from;
	int to;
	int capacity;
	int flow;

	/**
	 * Constructor to create an edge with given from, to and capacity.
	 * pre: none
	 * post: an edge is created with given from, to and capacity.
	 */
	public Edge(int from, int to, int capacity) {
			this.from = from;
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
	}
}
