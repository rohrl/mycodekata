package google.phone;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Boggle {
	public static void main(String[] args) {

		char[][] a = { { 'A', 'D', 'S', 'E' }, { 'F', 'C', 'I', 'M' },
				{ 'P', 'U', 'R', 'T' }, { 'E', 'A', 'B', 'D' } };

		System.out.println(boggle(a, 4, 4));

	}

	static Collection<String> boggle(char[][] array, int N, int M) {

		Set<String> results = new LinkedHashSet<String>();
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				boggleRec(array, i, j, N, M, visited, "", results);
		return results;
	}

	static Set<String> dictionary;
	static {
		dictionary = new HashSet<String>();
		dictionary.add("semi");
		dictionary.add("dime");
		dictionary.add("dimes");
		dictionary.add("dim");
		dictionary.add("sir");
		dictionary.add("rise");
	}

	static boolean isWord(String s) {
		return dictionary.contains(s.toLowerCase());
	}

	static void boggleRec(char[][] a, int i, int j, int N, int M,
			boolean[][] visited, String solution, Collection<String> found) {

		visited[i][j] = true;
		String s = solution + a[i][j];
		if (isWord(s))
			found.add(s);

		for (int ii = i - 1; ii <= i + 1; ii++)
			for (int jj = j - 1; jj <= j + 1; jj++)
				if ((ii != i || jj != j) && ii >= 0 && ii < N && jj >= 0
						&& jj < M && !visited[ii][jj]) {
					boggleRec(a, ii, jj, N, M, visited, s, found);
				}

		visited[i][j] = false;
	}
}
