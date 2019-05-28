package mine.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import mine.graphs.Graph.Edge;

public class Algorythms {

	public static enum Phase {
		IN, OUT, TOUCHED;
	}

	public static interface Visitor {
		public boolean apply(int v, Phase when);
	}

	private static Set<Integer> visited;

	public static Set<Integer> DFS(Graph g, int root, Visitor fn) {
		visited = new HashSet<Integer>();
		dfs(g, root, fn);
		return new TreeSet<Integer>(visited);
	}

	private static void dfs(Graph g, int root, Visitor fn) {
		if (!fn.apply(root, Phase.IN))
			return;
		visited.add(root);
		for (Edge e : g.edgesFrom(root)) {
			fn.apply(e.to, Phase.TOUCHED);
			if (!visited.contains(e.to)) {
				dfs(g, e.to, fn);
			}
		}
		fn.apply(root, Phase.OUT);
	}

	public static Set<Integer> BFS(Graph g, int root, Visitor fn) {
		visited = new HashSet<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(root);
		visited.add(root);
		while (!q.isEmpty()) {
			int v = q.poll();
			if (!fn.apply(v, Phase.IN))
				break;
			for (Edge e : g.edgesFrom(v)) {
				fn.apply(e.to, Phase.TOUCHED);
				if (!visited.contains(e.to)) {
					q.add(e.to);
					visited.add(e.to);
				}
			}
			fn.apply(v, Phase.OUT);
		}

		return new TreeSet<Integer>(visited);
	}

	static TreeSet<Integer> queue;
	static double[] distances;

	public static double[] Dijkstra(Graph g, int root) {

		distances = new double[g.getV()];
		for (int i = 0; i < distances.length; i++)
			distances[i] = Double.POSITIVE_INFINITY;

		queue = new TreeSet<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer v1, Integer v2) {
				return Double.compare(distances[v1], distances[v2]);
			}
		});

		distances[root] = 0;
		queue.add(root);

		while (!queue.isEmpty()) {
			int v = queue.first();

			// unreachable nodes
			if (distances[v] == Double.POSITIVE_INFINITY)
				break;

			for (Edge e : g.edgesFrom(v)) {
				if (distances[e.to] > distances[v] + e.weight) {
					queue.remove(e.to);
					distances[e.to] = distances[v] + e.weight;
					queue.add(e.to);
				}
			}
		}

		return distances;
	}

	public static List<Integer> topologicalOrder(Graph g) {

		final ArrayList<Integer> stack = new ArrayList<Integer>();
		final Set<Integer> notVisited = new HashSet<Integer>();

		Visitor lateVisitor = new Visitor() {
			@Override
			public boolean apply(int v, Phase when) {
				if (!notVisited.contains(v))
					return false;
				if (when == Phase.OUT)
					stack.add(v);
				return true;
			}
		};

		for (int i = 0; i < g.getV(); i++)
			notVisited.add(i);

		while (!notVisited.isEmpty()) {
			Set<Integer> visited = DFS(g, notVisited.iterator().next(),
					lateVisitor);
			notVisited.removeAll(visited);
		}

		Collections.reverse(stack);
		return stack;
	}
}
