package google.trainingOnSite.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import mine.graphs.Graph;

public class HamiltonianEuler {

	public static boolean hamilton(Graph g, int start,
			LinkedHashSet<Integer> current, List<List<Integer>> paths,
			boolean findAll) {

		current.add(start);

		boolean found = false;

		if (current.size() == g.getV()) {
			paths.add(new ArrayList<Integer>(current));
			found = true;
		} else {
			for (Graph.Edge e : g.edgesFrom(start)) {
				if (!current.contains(e.to)) {
					found |= hamilton(g, e.to, current, paths, findAll);
					if (found && !findAll)
						break;
				}
			}
		}

		current.remove(start);

		return found;
	}

	static HashSet<String> visited = new HashSet<String>();

	static String toKey(Graph g, int x, int y) {
		if (g.isDirected())
			return x + "." + y;
		else
			return (x > y ? x + "." + y : y + "." + x);
	}

	public static boolean euler(Graph g, int start,
			LinkedList<Integer> current, List<List<Integer>> results,
			boolean findAll) {

		current.add(start);

		if (g.getE() + 1 == current.size()) {
			results.add(new ArrayList<Integer>(current));
			current.removeLast();
			return true;
		}

		boolean found = false;

		for (Graph.Edge e : g.edgesFrom(start)) {

			final String k = toKey(g, start, e.to);
			if (!visited.contains(k)) {
				visited.add(k);
				found |= euler(g, e.to, current, results, findAll);
				visited.remove(k);
				if (found && !findAll)
					break;
			}
		}

		current.removeLast();

		return found;
	}

	public static void main(String[] args) {

		Graph g = Graph.createUndirected(7);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 10);
		g.addEdge(2, 3, 100);
		g.addEdge(3, 4, 1000);
		g.addEdge(4, 5, 10000);
		g.addEdge(5, 0, 100000);
		g.addEdge(0, 6, 1000000);

		List<List<Integer>> paths = new ArrayList<List<Integer>>();
		for (int v = 0; v < g.getV(); v++)
			hamilton(g, v, new LinkedHashSet<Integer>(), paths, true);

		System.out.println("Hamiltonian");
		for (List<Integer> p : paths)
			System.out.println(p);

		paths.clear();

		g = Graph.createUndirected(5);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 10);
		g.addEdge(2, 3, 100);
		g.addEdge(3, 4, 1000);
		g.addEdge(4, 0, 10000);
		g.addEdge(2, 4, 100000);

		euler(g, 2, new LinkedList<Integer>(), paths, true);

		System.out.println("Euler");
		for (List<Integer> p : paths)
			System.out.println(p);
	}
}
