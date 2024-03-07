package program3;

/**
 * Class to represent a path in a flow network.
 * The path is represented using an array of parents, source and sink.
 * @author Sumit Hotchandani	
 */
public class FlowPath {
	int[] parent;
	int source;
	int sink;
	int flow;

	/**
	 * constructor to create a path with given parent, source, sink and flow.
	 * pre: none
	 * post: a path is created with given parent, source, sink and flow.
	 */
	public FlowPath(int[] parent, int source, int sink, int flow) {
			this.parent = parent;
			this.source = source;
			this.sink = sink;
			this.flow = flow;
	}
}
