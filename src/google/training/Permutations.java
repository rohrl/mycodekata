package google.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutations {

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/** all perms with no elements at their original positions */
	public static void derangements(int[] input, int k, List<int[]> results) {

		if (k == input.length - 1) {
			results.add(input.clone());
			return;
		}

		// only diff from normal permutation is starting from k+1
		for (int i = k; i < input.length; i++) {			
			swap(input, k, i);
			derangements(input, k + 1, results);
			swap(input, i, k);			
		}
	}

	/**
	 * multiset can contain duplicates, thus we should take care to not generate
	 * duplicate permutations
	 */
	public static void multisetPermutations(int[] input, int k,
			List<int[]> results) {

		if (k == input.length) {
			results.add(input.clone());
			return;
		}

		LinkedList<Integer> used = new LinkedList<Integer>();
		for (int i = k; i < input.length; i++) {
			int tmp = input[i];
			if (used.contains(tmp))
				continue;
			used.add(tmp);
			swap(input, k, i);
			multisetPermutations(input, k + 1, results);
			swap(input, i, k);
		}
	}

	public static void main(String[] args) {

		System.out.println("Derangements");
		int[] a = { 1, 2, 3, 4 }; // ,4, 5, 6, 7, 8, 9, 10 };
		ArrayList<int[]> results = new ArrayList<int[]>();
		derangements(a, 0, results);
		for (int[] arr : results)
			System.out.println(Arrays.toString(arr));

		System.out.println("Multiset perms");
		int[] multiset = { 1, 1, 2, 2 };
		results = new ArrayList<int[]>();
		multisetPermutations(multiset, 0, results);
		for (int[] arr : results)
			System.out.println(Arrays.toString(arr));
	}
}
