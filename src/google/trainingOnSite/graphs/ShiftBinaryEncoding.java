package google.trainingOnSite.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import mine.graphs.Graph;

import com.google.common.base.Strings;

/**
 * generate sequence of N-bits binary numbers such that each element is a prefix
 * of (n-1) bits of next element
 * 
 * Algorythm: generate a graph of possible transitions (which elements can
 * follow each el.), then find a Hamiltonian path and voila :) Also could be
 * Euler path for N+1 bits
 */
public class ShiftBinaryEncoding {

	static HashMap<Integer, String> labels = new HashMap<Integer, String>();

	static String print(int i, int bits) {
		return Strings.padStart(Integer.toBinaryString(i), bits, '0');
	}

	public static Graph generate(int bits) {

		labels.clear();

		final int maxV = (1 << (bits));
		System.out.println("maxv = " + maxV + " so " + print(maxV - 1, bits));
		Graph g = Graph.createDirected(maxV);

		for (int i = 0; i < maxV; i++) {
			labels.put(i, print(i, bits));
			int next = (i << 1 & (maxV - 1));
			if (i != next) {
				System.out.println("edge 0: " + print(i, bits) + " to "
						+ print(next, bits));
				g.addEdge(i, next, 0);
			}
			next = (i << 1 & (maxV - 1)) + 1;
			if (i != next) {
				System.out.println("edge 1: " + print(i, bits) + " to "
						+ print(next, bits));
				g.addEdge(i, next, 1);
			}
		}

		return g;
	}

	public static void main(String[] args) {

		final int BITS = 4;
		final boolean FIND_ALL = false;

		// USING HAMILTONIAN (slower)

		Graph g = generate(BITS);

		System.out.println(g.getV());

		List<List<Integer>> paths = new ArrayList<List<Integer>>();
		for (int v = 0; v < g.getV(); v++) {

			if (!FIND_ALL
					& HamiltonianEuler.hamilton(g, v,
							new LinkedHashSet<Integer>(), paths, FIND_ALL))
				break;
		}

		for (List<Integer> p : paths) {
			System.out.println(p);
			for (int i : p)
				System.out.print(labels.get(i) + " ");
			System.out.println();
		}

		// ////////////////////////////////////////////

		// USING EULER

		Graph g2 = generate(BITS - 1);

		List<List<Integer>> paths2 = new ArrayList<List<Integer>>();
		for (int v = 0; v < g2.getV(); v++) {
			if (!FIND_ALL
					& HamiltonianEuler.euler(g2, v, new LinkedList<Integer>(),
							paths2, FIND_ALL))
				break;
		}

		for (List<Integer> p : paths2) {
			System.out.println(p);
			int prev = p.get(0);
			for (int i = 1; i < p.size(); i++) {
				int v = p.get(i);
				int w = (int) g2.getEdge(prev, v);
				System.out.print(labels.get(prev) + w + " ");
				prev = v;
			}
			System.out.print(labels.get(prev) + "x");
			System.out.println();
		}
	}
}
