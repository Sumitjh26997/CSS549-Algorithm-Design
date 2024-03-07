package program3;

import java.io.*;
import java.util.*;

/**
 * Class to find a maximum matching in a bipartite graph.
 * The class uses the Dinitz algorithm to find the maximum matching in the graph.
 * The class also contains a main method to read the graph from a file and print the maximum matching.
 * @author Sumit Hotchandani
 */

public class BipartiteMatching {
	/**
	 * function to obtain the level graph of the given graph using breadth-first search.
	 * pre: Residual graph G is initialized.
	 * post: Level graph of G is obtained using breadth-first search.
	 */
	public static boolean bfsLevelGraph(Graph G) {
		int source = 0;
		int sink = G.size() - 1;
		
		// visited array to keep track of visited nodes
		boolean[] visited = new boolean[G.size()];
		// queue to keep track of nodes to visit
		Queue<Integer> q = new LinkedList<>();
		
		// set level of source to 0 and add it to the queue
		G.setLevel(0, 0);
		q.add(source);
		visited[source] = true;

		// while queue is not empty, keep visiting nodes and set level of unvisited nodes to level of current node + 1
		while (!q.isEmpty()) {
			// get the current node from the queue
			int u = q.poll();
			// visit all the neighbors of the current node
			for (Edge e : G.getEdges(u)) {
				int v = e.to;
				if (G.getLevel(v) == -1 && !visited[v]) {
					G.setLevel(v, G.getLevel(u) + 1);
					q.add(v);
				}
			}
		}

		// return true if the sink is visited, else return false
		return G.getLevel(sink) != -1;
	}

	/**
	 * function to check if the given path is a path from source to sink is stuck on the source.
	 * pre: flow path is initialized.
	 * post: true is returned if the given path is stuck at the source, else false is returned.
	 */
	public static boolean isStuckAtSource(FlowPath path) {
		for(int i = 1; i < path.parent.length; i++) {
			if(path.parent[i] != -1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * function to get the last visited node in the given path.
	 * pre: flow path is initialized.
	 * post: the last node in the given path is returned.
	 */
	public static int getLastNode(FlowPath path) {
		for(int i = path.sink; i >= path.source; i--) {
			if(path.parent[i] != -1) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * function to construct a graph from the given file.
	 * pre: none
	 * post: a graph is constructed from the given file and returned.
	 */
	public static Graph constructGraph(List<Integer>	leftNodes, List<Integer> rightNodes) {	
			try {
					BufferedReader br = new BufferedReader(new FileReader("program3/program3data.txt"));
					int n = Integer.parseInt(br.readLine().trim());
					Graph graph = new Graph(n+2);

					graph.addNode("source");
					for (int i = 1; i <= n; i++) {
							String name = br.readLine().trim(); 
							graph.addNode(name);;
							if (i <= n/2) {
								leftNodes.add(i);
							} else {
								rightNodes.add(i);
							}
					}
					graph.addNode("sink");

					int numEdges = Integer.parseInt(br.readLine().trim());
					for (int i = 0; i < numEdges; i++) {
							String[] edge = br.readLine().trim().split("\\s+");
							int u = Integer.parseInt(edge[0]);
							int v = Integer.parseInt(edge[1]);
							graph.addEdge(u, v, 1); // Each edge and reverse edge has capacity 1
					}
					br.close();

					//add edges from source to left nodes
					for (int i = 0; i < leftNodes.size(); i++) {
						graph.addEdge(0, leftNodes.get(i), 1);		
					}

					//add edges from source to left nodes
					for (int i = 0; i < rightNodes.size(); i++) {
						graph.addEdge(rightNodes.get(i), n + 1 , 1);		
					}

					return graph;
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
    }

		/**
		 * function to find a path from source to sink in the given graph using BFS
		 * pre: level graph LG is initialized.
		 * post: a path from source to sink is returned if it exists, else partial path is returned.
		 */
		public static FlowPath bfs(Graph graph, int source, int sink) { 
			int[] parent = new int[graph.size()];
			Arrays.fill(parent, -1);
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(source);
			parent[source] = source;

			while (!queue.isEmpty()) {
					int u = queue.poll();
					for (Edge e : graph.adj[u]) {
							int v = e.to;
							if (parent[v] == -1 && graph.getLevel(v) == graph.getLevel(u) + 1){
									queue.offer(v);
									parent[v] = u;
									if (v == sink) {
										return new FlowPath(parent, source, sink, 0);
									}
							}
					}
			}
			
			return new FlowPath(parent, source, sink, 0);
		}

		/**
		 * function to remove back edges from the given graph.
		 * pre: level graph LG is initialized.
		 * post: back edges are removed from the given graph.
		 */
		public static void removeBackEdges(Graph G) {
			for(int i = 0; i < G.size(); i++) {
				for(Iterator<Edge> itr = G.getEdges(i).iterator(); itr.hasNext();)
				{ 
					Edge e = itr.next();
					if(G.getLevel(e.to) < G.getLevel(e.from)) {
						itr.remove();
					}
				}
			}
		}

		/**
		 * dinitz algorithm to find the maximum matching in the given graph.
		 * pre: residual graph G is initialized.
		 * post: maximum matching in the given graph is found.
		 */
		public static void dinitz(Graph G, int source, int sink) {	
			while(true) {
				Graph LG = new Graph(G);
				bfsLevelGraph(LG);
				removeBackEdges(LG);

				FlowPath path = bfs(LG, source, sink);
				if(path.parent[path.sink] == -1) {
					return;
				}

				while(true) {
					if(path.parent[path.sink] != -1) {
						for(int v = path.sink; v != path.source; v = path.parent[v]) {
							int u = path.parent[v];
							LG.removeEdge(u, v);
							G.removeEdge(u, v);
							G.addEdge(v, u, 0);
						}
					} else if(isStuckAtSource(path)) {
						break;
					} else {
						int lastNode = getLastNode(path);
						LG.removeEdge(path.parent[lastNode], lastNode);
					}

					path = bfs(LG, source, sink);
				}
			}
		}


	/**
	 * function to print the matching in the given graph.
	 * pre: dinits algorithm is run on the graph.
	 * post: matching in the given graph is printed.
	 */
	public static void printMatching(Graph G, List<Integer> leftNodes, List<Integer> rightNodes) {
		HashSet<Integer> left = new HashSet<>(leftNodes);
		int count = 0;
		for(int rightNode : rightNodes) {
			for(Edge e : G.getEdges(rightNode)) {
				if(left.contains(e.to)) {
					System.out.println(G.getNodeName(e.to) + " / " + G.getNodeName(e.from));
					count++;
				}
			}
		}
		System.out.println(count + " total matches");
	}

	/**
	 * main method to read the graph from a file and print the maximum matching.
	 * pre: valid file is present.
	 * post: maximum matching in the graph is printed.
	 */
	public static void main(String[] args) {
		List<Integer> leftNodes = new ArrayList<>();
		List<Integer> rightNodes = new ArrayList<>();
		Graph G = constructGraph(leftNodes, rightNodes);
		dinitz(G, 0, G.size() - 1);
		printMatching(G, leftNodes, rightNodes);
	}
}
