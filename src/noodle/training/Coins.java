package noodle.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** Skiena 8-24 - minimum change for given amount */
public class Coins {

	static int[] denoms = { 50, 25, 10, 5, 1 };

	/**
	 * full exploration - note: sorting result to avoid reentering same branches
	 */
	public static List<Integer> minCoinsFor(int amount, int nextDenom) {

		if (amount == 0)
			return new LinkedList<Integer>();

		List<Integer> minChange = null;

		for (int d = nextDenom; d < denoms.length; d++) {
			if (amount - denoms[d] < 0)
				continue;
			List<Integer> res = minCoinsFor(amount - denoms[d], d);
			if (res != null
					&& (minChange == null || res.size() < minChange.size())) {
				minChange = res;
				minChange.add(denoms[d]);
			}
		}

		return minChange;
	}

	/** dynamic programming */
	public static List<Integer> minCoinsFor(int amount) {

		// for storing minimums
		int[] results = new int[amount + 1];
		// for storing backtracking info of which coins were used
		int[] coins = new int[amount + 1];

		// value indicating that change is not possible
		final int IMPOSSIBLE = amount + 1;

		Arrays.fill(results, IMPOSSIBLE);
		Arrays.fill(coins, 0);

		results[0] = 0;
		for (int a = 1; a <= amount; a++) {

			int min = IMPOSSIBLE;
			for (int d = 0; d < denoms.length; d++) {

				if (a >= denoms[d]) {
					int m = results[a - denoms[d]];
					if (m < min) {
						min = m;
						coins[a] = denoms[d];
					}
				}
			}

			results[a] = (min == IMPOSSIBLE) ? IMPOSSIBLE : min + 1;
		}

		// restore coin sequence used by backtracking
		LinkedList<Integer> coinSeq = new LinkedList<Integer>();
		int a = amount;
		while (a > 0) {
			coinSeq.add(coins[a]);
			a -= coins[a];
		}

		return coinSeq;
	}

	static int[] coins = { 2, 5, 10 };
	static Map<String, Integer> dpMap = null;

	static int change(int n) {
		dpMap = new HashMap<String, Integer>();
		return change(n, 0);
	}

	static int change(int n, int nextDenom) {

		Integer r = dpMap.get(n + "," + nextDenom);
		if (r != null)
			return r;

		int ways = 0;
		for (int d = nextDenom; d < coins.length; d++) {
			int denom = coins[d];
			if (n - denom == 0)
				ways++;
			else if (n - denom > 0) {
				int w = change(n - denom, d);
				if (w > 0) {
					ways += w;
				}
			} else
				break;
		}

		dpMap.put(n + "," + nextDenom, ways);
		return ways;
	}

	static List<List<Integer>> change2(int n, int nextDenom) {

		List<List<Integer>> ways = null;
		for (int d = nextDenom; d < coins.length; d++) {
			int denom = coins[d];
			if (n - denom == 0) {
				List<Integer> w = new LinkedList<Integer>();
				w.add(denom);
				if (ways == null)
					ways = new ArrayList<List<Integer>>();
				ways.add(w);
			} else if (n - denom > 0) {
				List<List<Integer>> w = change2(n - denom, d);
				if (w != null) {
					for (List<Integer> l : w)
						l.add(denom);
					if (ways == null)
						ways = new ArrayList<List<Integer>>();
					ways.addAll(w);
				}
			} else
				break;
		}

		return ways;
	}

	public static void main(String[] args) {

		int amount = 93;

		// O(?)
		List<Integer> result = minCoinsFor(amount, 0);
		System.out.println(result);

		// O(denoms * amount)
		result = minCoinsFor(amount);
		System.out.println(result);

		System.out.println("change");
		int n = 12;
		System.out.println(change(n));
		System.out.println("map size: " + dpMap.size()
				+ " vs size of array (N*denoms) = " + (n * coins.length));
		// ... theres no point in using hashmap for bigger n :)

		System.out.println("change2");
		for (List<Integer> l : change2(n, 0))
			System.out.println(l);
	}
}
