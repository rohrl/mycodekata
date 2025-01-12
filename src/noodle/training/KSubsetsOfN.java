package noodle.training;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** generate K-element subsets of N-element set */
public class KSubsetsOfN {

	public static void subsets(int[] set, int K, int k, int s, int[] current,
			List<int[]> results) {

		if (k == 0) {
			results.add(current.clone());
			return;
		}

		for (int i = s; i <= set.length - k; i++) {
			current[K - k] = set[i];
			subsets(set, K, k - 1, i + 1, current, results);
		}

	}

	public static void main(String[] args) {

		LinkedList<int[]> results = new LinkedList<int[]>();

		subsets(new int[] { 1, 2, 3, 4, 5 }, 3, 3, 0, new int[3], results);

		for (int[] a : results)
			System.out.println(Arrays.toString(a));
	}
}
