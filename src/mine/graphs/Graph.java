package mine.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Graph {

	public static class Edge {
		public final int to;
		public final double weight;

		Edge(int w, double ww) {
			to = w;
			weight = ww;
		}

		@Override
		public String toString() {
			return to + " (" + weight + ")";
		}
	}

	private ArrayList<LinkedList<Edge>> graph;
	private int V;
	private int E;
	private boolean directed;

	private Graph(int V) {
		this.V = V;
		graph = new ArrayList<LinkedList<Edge>>(V);
		for (int i = 0; i < V; i++) {
			graph.add(new LinkedList<Edge>());
		}
	}

	public static Graph createDirected(int V) {
		Graph g = new Graph(V);
		g.directed = true;
		return g;
	}

	public static Graph createUndirected(int V) {
		Graph g = new Graph(V);
		g.directed = false;
		return g;
	}

	public boolean addEdge(int x, int y, double w) {
		if (hasEdge(x, y))
			return false;
		graph.get(x).add(new Edge(y, w));
		if (!directed)
			graph.get(y).add(new Edge(x, w));
		E++;
		return true;
	}

	public double getEdge(int x, int y) {
		for (Edge e : graph.get(x))
			if (e.to == y)
				return e.weight;
		throw new IllegalArgumentException();
	}

	public List<Edge> edgesFrom(int v) {
		return graph.get(v);
	}

	public boolean hasEdge(int x, int y) {
		for (Edge e : graph.get(x))
			if (e.to == y)
				return true;
		return false;
	}

	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	public boolean isDirected() {
		return directed;
	}

	public String toString() {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for (LinkedList<Edge> l : graph) {
			sb.append(i++ + ": ");
			for (Edge e : l) {
				sb.append(e + " ");
			}
			sb.append("\n");
		}
		return sb.toString();

	}

	private static final int MAX_VERTEXES = 10;
	private static final int MIN_VERTEXES = 5;
	private static final float DENSITY = 0.2f;

	public static Graph createRandomish(boolean directed) {
		Random r = new Random();
		int V = MIN_VERTEXES + r.nextInt(MAX_VERTEXES - MIN_VERTEXES);
		int E = (int) (DENSITY * V * V);
		Graph g;
		if (directed)
			g = createDirected(V);
		else
			g = createUndirected(V);

		// ensure connectivity
		int i = 0;
		while (i < V - 1) {
			double w = Math.round(Math.random() * 100.0) / 10.0;
			g.addEdge(i, i + 1, w);
			i++;
		}
		// add some random edges
		while (i++ < E) {
			int from = r.nextInt(V);
			int to = r.nextInt(V);
			double w = Math.round(Math.random() * 100.0) / 10.0;
			g.addEdge(from, to, w);
		}
		return g;
	}

	public static Graph createRandomDAG() {
		Random r = new Random();
		int V = MIN_VERTEXES + r.nextInt(MAX_VERTEXES - MIN_VERTEXES);
		int E = (int) (DENSITY * V * V);
		Graph g = createDirected(V);
		int i = 0;
		// add some random edges
		while (i++ < E - V - 1) {
			int from = r.nextInt(V - 1);
			int to = from + r.nextInt(V - from - 1) + 1;
			double w = Math.round(Math.random() * 100.0) / 10.0;
			g.addEdge(from, to, w);
		}
		// ensure connectivity
		i = 0;
		while (i < V - 1) {
			double w = Math.round(Math.random() * 100.0) / 10.0;
			g.addEdge(i, i + 1, w);
			i++;
		}
		return g;
	}
}
