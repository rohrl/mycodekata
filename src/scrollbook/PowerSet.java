package scrollbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * TODO: generate in lexicographic order
 *
 */
public class PowerSet {

	/** returns true if overflow */
	private static boolean increment(final boolean[] arr) {
		int i = arr.length - 1;
		while (i >= 0 && arr[i]) {
			arr[i] = false;
			i--;
		}
		if (i >= 0) {
			arr[i] = true;
			return false;
		} else
			return true;
	}

	/** clever - on binary number incrementation */
	public static <T> void powerSetOnBinary(Set<T> inputSet) {

		boolean[] bitSet = new boolean[inputSet.size()];

		do {

			int i = inputSet.size() - 1;
			for (T el : inputSet) {
				if (bitSet[i])
					System.out.print(el + " ");
				i--;
			}

			System.out.println();
		} while (!increment(bitSet));
	}

	/** there's another solution based on recurence */
	public static <T> List<List<T>> powerSetRecursive(List<T> inputSet) {

		if (inputSet.isEmpty())
			return new LinkedList<List<T>>(
					Collections.singletonList(Collections.<T> emptyList()));

		T head = inputSet.get(0);

		List<T> tail = inputSet.subList(1, inputSet.size());

		List<List<T>> tailSubsets = powerSetRecursive(tail);

		List<List<T>> newSubsets = new LinkedList<List<T>>();

		for (List<T> tailSubset : tailSubsets) {
			// add subsets created by merging head with tail subsets
			List<T> newOne = new LinkedList<T>(tailSubset);
			newOne.add(head);
			newSubsets.add(newOne);
		}

		tailSubsets.addAll(newSubsets);

		return tailSubsets;
	}

	/**
	 * ..and here's most efficient solution based on Skiena backtracking: no
	 * intermediate collections allocation, stack size can be reduced by
	 * converting all arguments to fields
	 */
	public static <T> void powerSetRecursive2(List<T> input,
			List<List<T>> results, int k, int j, T[] current) {

		if (k == input.size()) {
			results.add(Arrays.asList(Arrays.copyOf(current, j)));
			return;
		}

		powerSetRecursive2(input, results, k + 1, j, current);
		current[j] = input.get(k);
		powerSetRecursive2(input, results, k + 1, j + 1, current);
	}

	public static <T> void print(List<List<T>> subsets) {
		for (List<T> set : subsets) {
			for (T el : set)
				System.out.print(el + " ");
			System.out.println();
		}
	}

	public static void main(String[] args) {

		List<String> set = Arrays.asList(new String[] { "c", "b", "a" });

		powerSetOnBinary(new HashSet<String>(set));

		System.out.println(" ---------------------- ");

		print(powerSetRecursive(set));

		System.out.println(" ---------------------- ");

		List<List<String>> res = new ArrayList<List<String>>();
		powerSetRecursive2(set, res, 0, 0, new String[set.size()]);
		print(res);
	}
}
