package mine.graphs;

import java.util.Arrays;
import java.util.Set;

import mine.graphs.Algorythms.Phase;
import mine.graphs.Algorythms.Visitor;

public class Demo {

	public static void search(Graph g) {

		Visitor printer = new Visitor() {
			@Override
			public boolean apply(int v, Phase phase) {
				if (phase == Phase.IN)
					System.out.println("Visiting " + v);
				return true;
			}
		};

		System.out.println(" -- DFS");
		Set<Integer> visitedDfs = Algorythms.DFS(g, 0, printer);

		System.out.println(" -- BFS");
		Set<Integer> visitedBfs = Algorythms.BFS(g, 0, printer);

		if (!visitedBfs.equals(visitedDfs))
			System.err.println(" -- DFS & BFS did NOT visited same set of vertexes");
	}

	public static void dijkstra(Graph g) {
		double[] distances = Algorythms.Dijkstra(g, 0);
		System.out.println(" -- Dijkstra found distances from 0:\n"
				+ Arrays.toString(distances));
	}

	public static void main(String[] args) {

		Graph g = Graph.createRandomish(true);
		System.out.println(g);

		search(g);
		dijkstra(g);

		System.out.println(" -- Topo sort");
		Graph dag = Graph.createRandomDAG();
		System.out.println(dag);
		System.out.println(Algorythms.topologicalOrder(dag));
	}

}
