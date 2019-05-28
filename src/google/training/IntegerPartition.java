package google.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** integer partition  */
public class IntegerPartition {

	/** by winding all ones ... see Skiena, cause sth is wrong ;) */
	public static List<int[]> partition(int n) {

		LinkedList<int[]> results = new LinkedList<int[]>();

		int l = 0, k = 0;
		int[] part = new int[] { n };
		results.add(part);

		while (l >= 0) {

			while (part[l] == 1) {
				if (l == 0)
					return results;
				else
					l--;
			}

			int s = part[l] - 1;
			k = part.length - l - 1 + 1;
			int sc = k / s;
			k = k % s;

			int[] newPart = Arrays.copyOf(part, l + 1 + sc + k);
			Arrays.fill(newPart, l, l + sc + 1, s);
			Arrays.fill(newPart, l + sc + 1, l + sc + k + 1, 1);
			l = l + sc;

			part = newPart;
			results.add(part);
		}

		return results;
	}

	/** my algo */
	static int partitions(int n, int threshold, List<Integer> result,
			List<List<Integer>> results) {

		// check sanity of arguments n >= 1, threshold <= n
		if (n < 1 || threshold > n)
			throw new IllegalArgumentException(n + "," + threshold);

		// stop recursion
		if (n == 1) {
			result.add(n);
			results.add(result);
			return 1;
		}

		// one for account of n itself as possible partitioning of n
		int count = 1;

		// rest of possibilities
		for (int i = threshold; i <= n - i; i++) {

			LinkedList<Integer> r = new LinkedList<Integer>(result);
			r.add(i);
			count += partitions(n - i, i, r, results);

		}

		result.add(n);
		results.add(result);

		return count;
	}

	public static void main(String[] args) {

		int N = 7;

		for (int[] a : partition(N))
			System.out.println(Arrays.toString(a));

		// compare to another algorithm
		System.out.println("Differently calculated:");
		for (List<Integer> part : fb.IntegerPartition.getAllSummands(N))
			System.out.println(part);

		// my algo
		System.out.println("my algo");
		ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
		System.out
				.println(partitions(N, 1, new LinkedList<Integer>(), results));
		for (List<Integer> r : results)
			System.out.println(r);
	}

}
