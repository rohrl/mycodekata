package crackingcoding;

import java.util.ArrayList;
import java.util.List;

/**
 * print all possible brace seqs. SEE ALSO CatalanNumbers
 */
public class Task8p5 {
	public static void possibleBraceSequences(int N, int l, int r,
			String result, List<String> results) {

		if (l + r == 2 * N) {
			results.add(result);
			return;
		}

		if (l < N) {
			possibleBraceSequences(N, l + 1, r, result + '(', results);
		}
		if (r < l) {
			possibleBraceSequences(N, l, r + 1, result + ')', results);
		}
	}

	public static void main(String[] args) {
		List<String> results = new ArrayList<String>();
		int N = 3;

		possibleBraceSequences(N, 0, 0, "", results);

		System.out.println(results);
	}
}
