package noodle.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PossibleParenthesis {

	// n - positive & even
	static List<String> parents(int n, int open) {

		if (n == 0 || open > n) {
			return Collections.emptyList();
		}

		if (n == open) {
			return Collections.singletonList(Collections.nCopies(n, ")")
					.toString().replaceAll("[^()]", ""));
		}

		List<String> result = new ArrayList<String>();

		for (String s : parents(n - 1, open + 1)) {
			result.add("(" + s);
		}

		if (open > 0) {
			for (String s : parents(n - 1, open - 1))
				result.add(")" + s);
		}

		return result;
	}

	static List<String> parentsDyn(int n, int open) {

		n = n / 2 + 1;

		@SuppressWarnings("unchecked")
		List<String>[][] results = new List[n][n];

		results[0][0] = Collections.singletonList("");

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				ArrayList<String> r = new ArrayList<String>();
				if (i - 1 >= 0 && results[i - 1][j] != null) {
					for (String s : results[i - 1][j])
						r.add(s + "(");
				}
				if (j - 1 >= 0 && results[i][j - 1] != null) {
					for (String s : results[i][j - 1])
						r.add(s + ")");
				}
				results[i][j] = r;
			}
		}

		return results[n - 1][n - 1];
	}

	public static void main(String[] args) {
		int N = 6;
		System.out.println(parents(N, 0));

		for (int i = 2; i <= 26; i += 2)
			System.out.print(parents(i, 0).size() + " ");

		System.out.println("\nDynamic:");

		System.out.println(parentsDyn(N, 0));

		for (int i = 2; i <= 26; i += 2)
			System.out.print(parentsDyn(i, 0).size() + " ");
	}

}
