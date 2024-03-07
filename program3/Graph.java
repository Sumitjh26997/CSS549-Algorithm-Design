package program3;
import java.util.*;

/**
 * Class to represent a Graph.
 * The graph is represented using an adjacency list.
 * The graph also contains a list of nodes and a level array to store the level of
 * each node in the graph.
 * The graph also contains a list of edges for each node in the graph.
 * @author Sumit Hotchandani
 **/

public class Graph {
	private int V;
	private List<String> nodes;
	List<Edge>[] adj;
	int[] level;

	/**
	 * Constructor to create a graph with given number of vertices.
	 * pre: none
	 * post: a graph is created with given number of vertices.
	 */
	Graph(int V) {
		this.V = V;
		this.nodes = new ArrayList<>();
		this.level = new int[V];
		this.adj = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			this.adj[i] = new ArrayList<>();
			this.level[i] = -1;
		}
	}

	Graph(Graph lg) {
		this.V = lg.V;
		this.nodes = new ArrayList<>(lg.nodes);
		this.level = new int[V];
		this.adj = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			this.adj[i] = new ArrayList<>();
			this.level[i] = lg.level[i];
		}

		for (int i = 0; i < V; i++) {
			for (Edge e : lg.adj[i]) {
				this.adj[i].add(e);
			}
		}
	}

	/**
	 * function to get the number of vertices in the graph.
	 * pre: none
	 * post: number of vertices in the graph is returned.
	 */
	public int size() {
		return this.V;
	}

	/**
	 * function to add a node to the graph.
	 * pre: none
	 * post: node is added to the graph.
	 */
	public void addNode(String name) {
		nodes.add(name);
	}

	/**
	 * function to add an edge to the graph.
	 * pre: none
	 * post: edge is added to the graph.
	 */
	public void addEdge(int from, int to, int capacity) {
		Edge e = new Edge(from, to, capacity);
		adj[from].add(e);
	}

	/**
	 * function to get the edges of a node in the graph.
	 * pre: none
	 * post: edges of the node are returned.
	 */
	public List<Edge> getEdges(int u) {
		return adj[u];
	}

	/**
	 * function to get the name of the node of the graph.
	 * pre: none
	 * post: name of the node of the graph is returned.
	 */
	public String getNodeName(int u) {
		return nodes.get(u);
	}

	public void printGraph() {
		for (int i = 0; i < V; i++) {
			System.out.print("level : " + this.level[i] + " " + nodes.get(i) + " -> ");
			for (Edge e : adj[i]) {
				System.out.print(nodes.get(e.to) + " ");
			}
			System.out.println();
		}
	}

	/**
	 * function to set level of a node in the graph.
	 * pre: none
	 * post: level of a node is set in the graph.
	 */
	public void setLevel(int u, int level) {
		this.level[u] = level;
	}

	/**
	 * function to get the level of a node in the graph.
	 * pre: none
	 * post: level of a node in the graph is returned.
	 */
	public int getLevel(int u) {
		return this.level[u];
	}

	public void removeEdge(int from, int to) {
		for (int i = 0; i < this.adj[from].size(); i++) {
			if (this.adj[from].get(i).to == to) {
				this.adj[from].remove(i);
				break;
			}
		}
	}
}
