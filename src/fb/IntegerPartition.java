package fb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** all sets of numbers (sequences optionally, see below) that sum to given int */
public class IntegerPartition {

	public static void main(String[] args) {

		for (int number = 5; number < 6; number++) {

			List<List<Integer>> result = getAllSummands(number);

			// number of all possible partitions is equal to:
			// - for sequences: 2^(n-1) - cause we can remove + sign from (n-1)
			// places if we write n as 1+1+..+1
			// - for sets: n-th element of sequence from http://oeis.org/A000041
			System.out.println(result.size());

			// print all partitions
			for (List<Integer> list : result) {
				for (int summand : list)
					System.out.print(summand + " ");

				System.out.println();
			}

		}

	}

	public static List<List<Integer>> getAllSummands(int n) {

		List<List<Integer>> summandList = new LinkedList<List<Integer>>();
		summandList.add(Collections.singletonList(n));

		for (int i = 1; i < n; i++) {

			List<List<Integer>> summandList1 = getAllSummands(i);

			for (List<Integer> l1 : summandList1) {

				// COMMENT THIS CONDITION IF YOU WANT SEQUENCES INSTEAD OF SETS
				if (l1.get(l1.size() - 1) > (n - i))
					continue;

				List<Integer> l = new ArrayList<Integer>(l1.size() + 1);
				l.addAll(l1);
				l.add(n - i);
				summandList.add(l);
			}
		}

		return summandList;
	}

}
